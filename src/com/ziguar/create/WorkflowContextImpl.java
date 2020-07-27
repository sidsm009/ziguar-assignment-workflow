package com.ziguar.create;

import java.util.List;

public class WorkflowContextImpl implements WorkflowContext {
    String workflowName;
    List<List<String>> connections;

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public List<List<String>> getConnections() {
        return connections;
    }

    public void setConnections(List<List<String>> connections) {
        this.connections = connections;
    }
}
