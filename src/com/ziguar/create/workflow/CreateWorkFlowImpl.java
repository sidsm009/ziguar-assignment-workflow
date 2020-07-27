package com.ziguar.create.workflow;

import com.ziguar.Component;
import com.ziguar.create.WorkflowContext;
import com.ziguar.create.WorkflowContextImpl;
import com.ziguar.exception.WorkflowException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreateWorkFlowImpl extends AbstractCreateWorkFlow {

    public String workflowName;
    public List<List<String>> componentConnectionsList;
    public List<Component> components;

    public CreateWorkFlowImpl(CreateWorkFlowBuilder builder) {
        this.workflowName = builder.workflowName;
        this.components = builder.components;
        componentConnectionsList = new LinkedList<List<String>>();
    }

    public List<Component> getComponents() {
        return components;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public static class CreateWorkFlowBuilder {

        String workflowName;
        List<Component> components;

        public CreateWorkFlowBuilder() {

        }

        public CreateWorkFlowBuilder(String workflowName) {
            this.workflowName = workflowName;
        }

        public CreateWorkFlowBuilder withName(String workflowName) {
            this.workflowName = workflowName;
            return this;
        }

        public CreateWorkFlowBuilder withComponents(List<Component> components) {
            this.components = components;
            return this;
        }

        public CreateWorkFlowBuilder withComponent(Component component) {
            if(null == components ) {
                components = new ArrayList<>();
            }
            this.components.add(component);
            return this;
        }

        public CreateWorkFlowImpl build() {
            CreateWorkFlowImpl wfImpl = new CreateWorkFlowImpl(this);
            return wfImpl;
        }
    }

    @Override
    public WorkflowContext create() throws WorkflowException {
        WorkflowContext context = new WorkflowContextImpl();
        context.setWorkflowName(this.getWorkflowName());
        if(null != components && !components.isEmpty() && components.size() > 1) {
            int size = components.size();
            int i = 0;
            while(i < size-1) {
                List<String> connection = new LinkedList<String>();
                connection.add(components.get(i).getComponentName());
                connection.add(components.get(i+1).getComponentName());
                componentConnectionsList.add(connection);
                i++;
            }
            context.setConnections(componentConnectionsList);
        } else {
            throw new WorkflowException("Not enough components added");
        }

        return context;
    }
}
