package com.ziguar.create;

import com.ziguar.config.ComponentConfig;

public class ComponentContextImpl implements ComponentContext {
    String componentName;
    String componentType;
    ComponentConfig componentConfig;

    public ComponentContextImpl(String componentName, String componentType, ComponentConfig componentConfig) {
        this.componentName = componentName;
        this.componentType = componentType;
        this.componentConfig = componentConfig;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public ComponentConfig getComponentConfig() {
        return componentConfig;
    }

    public void setComponentConfig(ComponentConfig componentConfig) {
        this.componentConfig = componentConfig;
    }
}
