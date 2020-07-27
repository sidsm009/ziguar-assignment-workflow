package com.ziguar;

import com.ziguar.config.ComponentConfig;

public interface Component {
    String getComponentName();
    void setComponentName(String componentName);

    String getComponentType();
    void setComponentType(String componentType);

    ComponentConfig getComponentConfig();
}
