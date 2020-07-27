package com.ziguar.repository.component;

import com.opencsv.CSVReader;
import com.ziguar.create.ComponentContext;
import com.ziguar.create.WorkflowContext;
import com.ziguar.entity.ComponentEntity;
import com.ziguar.entity.WorkflowEntity;
import com.ziguar.mapper.mapToCsv.CSVStore;
import com.ziguar.repository.Repository;

import java.io.IOException;
import java.util.List;

public class ComponentRepository implements Repository {

    ComponentContext componentContext;
    CSVStore store;
    String componentRepoPath = "C:\\Users\\Sid\\IdeaProjects\\WorkFlow\\resources\\component";
    String fileName = "component.csv";

    public ComponentRepository(ComponentContext componentContext) {
        this.componentContext = componentContext;
        this.store = new CSVStore(fileName, componentRepoPath);
    }

    @Override
    public ComponentEntity find(String id) throws IOException {
        ComponentEntity componentEntity = null;
        CSVReader reader = store.getReader();
        String[] nextRecord;
        while ((nextRecord = reader.readNext()) != null) {
            if(nextRecord[0].equals(id)) {
                componentEntity = new ComponentEntity();
                componentEntity.setId(Integer.parseInt(nextRecord[0]));
                componentEntity.setComponentName(nextRecord[1]);
                componentEntity.setComponentType(nextRecord[2]);
                componentEntity.setWorkflowId(nextRecord[3]);
                componentEntity.setWorkflowName(nextRecord[4]);
                componentEntity.setTimestamp(nextRecord[5]);
                break;
            }
        }
        return componentEntity;
    }

    @Override
    public List<ComponentEntity> getAllObjects() throws IOException {
        List<String[]> allRecords = store.getReaderHeaderSkipped().readAll();
        List<ComponentEntity> componentEntities = null;
        if(null != allRecords && !allRecords.isEmpty()) {
            for(String[] record : allRecords) {
                ComponentEntity componentEntity = new ComponentEntity();
                componentEntity.setId(Integer.parseInt(record[0]));
                componentEntity.setComponentName(record[1]);
                componentEntity.setComponentType(record[2]);
                componentEntity.setWorkflowId(record[3]);
                componentEntity.setWorkflowName(record[4]);
                componentEntity.setTimestamp(record[5]);
                componentEntities.add(componentEntity);
            }
        }
        return componentEntities;
    }

    @Override
    public List<Integer> getAllIds() throws IOException {
        return null;
    }

    @Override
    public Integer getLastId() throws IOException {
        return null;
    }
}
