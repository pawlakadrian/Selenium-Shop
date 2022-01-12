package configuration.yaml;

import configuration.yaml.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LoadProperties {
    Logger logger = LoggerFactory.getLogger(LoadProperties.class);
    YamlReader yamlReader = new YamlReader();
    public BaseModel environment = yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment();

    public void setProperties() {
        Map<String, Object> dataBaseProperties = environment.getProperties();
        for (Map.Entry entry : dataBaseProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Loaded property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
        logger.info("Loaded properties total: {}", dataBaseProperties.size());
    }
}
