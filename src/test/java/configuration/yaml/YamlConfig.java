package configuration.yaml;

import configuration.yaml.model.EnvironmentModel;

import java.util.HashMap;
import java.util.Map;

public class YamlConfig {
//    private Map<String, Environment> environment = new HashMap<>();
    private EnvironmentModel environmentModel;

    public EnvironmentModel getEnvironmentModel() {
        return environmentModel;
    }

    public YamlConfig() {
    }
//
//    public  Map<String, Environment> getEnvironment() {
//        return environment;
//    }
//
//    public Environment getEnvironment(String environmentName) {
//        return this.environment.get(environmentName);
//    }

//    @Override
//    public String toString() {
//        return "YamlConfig{" +
//                "environment=" + environment +
//                '}';
//    }
}
