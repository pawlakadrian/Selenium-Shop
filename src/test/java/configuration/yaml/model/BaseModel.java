package configuration.yaml.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseModel {
    private String active;

    Map<String, Object> properties = new LinkedHashMap<>();

    @JsonAnySetter
    void setProperties(String key, Object value) {
        properties.put(key, value);
    }
    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    public BaseModel() {
    }

    public String getActive() {
        return active;
    }

}

