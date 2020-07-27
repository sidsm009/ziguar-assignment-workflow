package com.ziguar.repository.workflow;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.ziguar.create.WorkflowContext;
import com.ziguar.entity.WorkflowEntity;
import com.ziguar.exception.WorkflowException;
import com.ziguar.mapper.mapToCsv.CSVStore;
import com.ziguar.mapper.mapToJson.JsonWriter;
import com.ziguar.repository.Repository;
import com.ziguar.util.Constants;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WorkflowRepository implements Repository {
    WorkflowContext workflowContext;
    CSVStore store;
    String workflowRepoPath = "C:\\Users\\Sid\\IdeaProjects\\WorkFlow\\resources\\workflow";
    String fileName = "workflow.csv";

    public WorkflowRepository(WorkflowContext workflowContext) {
        this.workflowContext = workflowContext;
        store = new CSVStore(fileName, workflowRepoPath);
    }

    @Override
    public WorkflowEntity find(String id) throws IOException {
        WorkflowEntity workflowEntity = null;
        CSVReader reader = store.getReader();
        String[] nextRecord;
        while ((nextRecord = reader.readNext()) != null) {
            if(nextRecord[0].equals(id)) {
                workflowEntity = new WorkflowEntity();
                workflowEntity.setId(Integer.parseInt(nextRecord[0]));
                workflowEntity.setWorkflowName(nextRecord[1]);
                break;
            }
        }
        return workflowEntity;
    }

    @Override
    public List<WorkflowEntity> getAllObjects() throws IOException {
        List<String[]> allRecords = store.getReaderHeaderSkipped().readAll();
        List<WorkflowEntity> workflows = null;
        if(null != allRecords && !allRecords.isEmpty()) {
            for(String[] record : allRecords) {
                WorkflowEntity workflowEntity = new WorkflowEntity();
                workflowEntity.setId(Integer.parseInt(record[0]));
                workflowEntity.setWorkflowName(record[1]);
                workflows.add(workflowEntity);
            }
        }

        return workflows;
    }

    @Override
    public List<Integer> getAllIds() throws IOException {
        List<String[]> allRecords = store.getReaderHeaderSkipped().readAll();
        List<Integer> ids = new LinkedList<>();
        if(null != allRecords && !allRecords.isEmpty()) {
            for(String[] record : allRecords) {
                ids.add(Integer.parseInt(record[0]));
            }
        }

        return ids;
    }

    @Override
    public Integer getLastId() throws IOException {
        List<Integer> ids = getAllIds();
        return ((null != ids) && (!ids.isEmpty())) ? ids.get(ids.size()-1) : 0;
    }

    public void persistRecord() {
        if(null != workflowContext) {
            try {
                validateFile();
                Integer lastId = getLastId();
                String workflowName = workflowContext.getWorkflowName();
                Integer id = lastId++;
                String new_id = String.valueOf(id);
                writeLineToCsv(new String[]{new_id, workflowName});
                try {
                    createWorkflowJson(workflowContext, new_id);
                } catch (WorkflowException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createWorkflowJson(WorkflowContext context, String id) throws WorkflowException {
        String fileName = id+"_Workflow";
        JsonWriter jsonWriter = new JsonWriter("workflow", fileName);
        try {
            jsonWriter.createJson(context);
        } catch (IOException e) {
            System.out.println("Exception => "+e.getMessage());
            throw new WorkflowException(e.getCause());
        }
    }

    public void validateFile() throws IOException {
        boolean isHeaderPresent = false;
        boolean fileExists = store.fileExists();
        if(fileExists) {
            CSVReader csvReader = store.getReader();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if(nextRecord[0] == Constants.ID && nextRecord[1] == Constants.WORKFLOW_NAME_COLUMN) {
                    isHeaderPresent = true;
                    break;
                }
                break;
            }
        }

        if(!isHeaderPresent) {
            CSVWriter csvWriter = store.getWriter();
            String line[] = {Constants.ID, Constants.WORKFLOW_NAME_COLUMN};
            csvWriter.writeNext(line);
            csvWriter.flush();
        }
    }

    public void writeLineToCsv(String[] line) {
        try {
            CSVWriter csvWriter = store.getWriter();
            csvWriter.writeNext(line);
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
