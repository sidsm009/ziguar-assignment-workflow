package com.ziguar.create.component;

import com.ziguar.Component;
import com.ziguar.create.ComponentContext;
import com.ziguar.exception.WorkflowException;

public interface CreateComponent extends Component {
    ComponentContext create() throws WorkflowException;
}
