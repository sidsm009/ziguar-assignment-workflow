package com.ziguar.create.workflow;

import com.ziguar.Component;
import com.ziguar.WorkFlow;
import com.ziguar.create.WorkflowContext;
import com.ziguar.exception.WorkflowException;

import java.util.List;

public interface CreateWorkflow extends WorkFlow {
    WorkflowContext create() throws WorkflowException;
    List<Component> getComponents();
    String getWorkflowName();
}
