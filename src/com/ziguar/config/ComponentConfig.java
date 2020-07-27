package com.ziguar.config;

import java.util.Map;

public class ComponentConfig {
    Map<String, String> configs;

    public ComponentConfig(Map<String, String> configs) {
        this.configs = configs;
    }

    public Map<String, String> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, String> configs) {
        this.configs = configs;
    }
}
