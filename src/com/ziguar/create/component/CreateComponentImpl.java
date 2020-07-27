package com.ziguar.create.component;

import com.ziguar.config.ComponentConfig;
import com.ziguar.create.ComponentContext;
import com.ziguar.exception.WorkflowException;

public class CreateComponentImpl implements CreateComponent {
    public String componentName;
    public String componentType;
    public ComponentConfig componentConfig;

    private CreateComponentImpl() {

    }

    public CreateComponentImpl(CreateComponentBuilder builder) {
        this.componentName = builder.componentName;
        this.componentType = builder.componentType;
        this.componentConfig = builder.componentConfig;
    }

    @Override
    public String getComponentName() {
        return componentName;
    }

    @Override
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    @Override
    public String getComponentType() {
        return componentType;
    }

    @Override
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    @Override
    public ComponentConfig getComponentConfig() {
        return componentConfig;
    }

    @Override
    public ComponentContext create() throws WorkflowException {
        return null;
    }

    public static class CreateComponentBuilder {

        public String componentName;
        public String componentType;
        public ComponentConfig componentConfig;

        public CreateComponentBuilder(String componentName) {
            this.componentName = componentName;
        }

        public CreateComponentBuilder withComponentName(String componentName) {
            this.componentName = componentName;
            return this;
        }

        public CreateComponentBuilder withComponentType(String componentType) {
            this.componentType = componentType;
            return this;
        }

        public CreateComponentBuilder withComponentConfig(ComponentConfig componentConfig) {
            this.componentConfig = componentConfig;
            return this;
        }

        public CreateComponentImpl build() {
            CreateComponentImpl createComponent = new CreateComponentImpl(this);
            return createComponent;
        }
    }
}
