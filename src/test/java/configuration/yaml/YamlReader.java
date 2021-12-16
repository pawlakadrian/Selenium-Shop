package configuration.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;
import java.io.IOException;

public class YamlReader {

   public static void main(String[] args) {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

      mapper.findAndRegisterModules();

      try {
         YamlConfig yamlConfig = mapper.readValue(new File("src/main/resources/config-local.yml"), YamlConfig.class);
         System.out.println(ReflectionToStringBuilder.toString(yamlConfig, ToStringStyle.MULTI_LINE_STYLE));
      } catch (IOException e) {
         e.printStackTrace();
      }

//      Object environment;
//
//      try {
//         environment = mapper.readValue(new File("src/main/resources/config-local.yml"), YamlConfig.class);
//         System.out.println(ReflectionToStringBuilder.toString(environment, ToStringStyle.MULTI_LINE_STYLE));
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
   }
}
