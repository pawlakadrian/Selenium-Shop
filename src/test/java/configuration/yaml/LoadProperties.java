package configuration.yaml;

import configuration.yaml.model.BaseModel;

public class LoadProperties {
    YamlReader yamlReader = new YamlReader();
    public BaseModel environment = yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment();

    public void setProperties() {
        System.setProperty("url", environment.getUrl());
        System.setProperty("title", environment.getTitle());
        System.setProperty("browser", environment.getBrowser());
        System.setProperty("emailFailed", environment.getEmailFailed());
        System.setProperty("passwordFailed", environment.getPasswordFailed());
        System.setProperty("emailSuccess", environment.getEmailSuccess());
        System.setProperty("passwordSuccess", environment.getPasswordSuccess());
    }
}
