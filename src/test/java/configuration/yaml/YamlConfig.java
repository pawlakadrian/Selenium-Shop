package configuration.yaml;

import java.util.HashMap;
import java.util.Map;

public class YamlConfig {
    private Map<String, Environment> environment = new HashMap<>();

    public YamlConfig() {
    }

    public  Map<String, Environment> getEnvironment() {
        return environment;
    }

    public Environment getEnvironment(String environmentName) {
        return this.environment.get(environmentName);
    }

    @Override
    public String toString() {
        return "YamlConfig{" +
                "environment=" + environment +
                '}';
    }
}
