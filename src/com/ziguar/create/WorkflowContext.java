package com.ziguar.create;

import com.ziguar.Context;
import java.util.List;

public interface WorkflowContext extends Context {
    String getWorkflowName();
    void setWorkflowName(String workflowName);
    List<List<String>> getConnections();
    void setConnections(List<List<String>> connections);
}
