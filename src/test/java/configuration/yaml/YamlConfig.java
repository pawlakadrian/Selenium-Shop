package configuration.yaml;

import configuration.yaml.model.EnvironmentModel;

import java.util.HashMap;
import java.util.Map;

public class YamlConfig {
    private EnvironmentModel environmentModel;

    public EnvironmentModel getEnvironmentModel() {
        return environmentModel;
    }

    public YamlConfig() {
    }
}
