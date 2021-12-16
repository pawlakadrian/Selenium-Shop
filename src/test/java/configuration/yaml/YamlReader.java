package configuration.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlReader {
private YamlConfig yamlConfig;

   public YamlConfig getYamlConfig() {
      return yamlConfig;
   }

   public YamlReader() {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

      mapper.findAndRegisterModules();

      try {
         this.yamlConfig = mapper.readValue(new File("src/main/resources/config-local.yml"), YamlConfig.class);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
