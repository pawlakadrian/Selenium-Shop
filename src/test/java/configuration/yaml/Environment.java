package configuration.yaml;

import java.util.List;

public class Environment {
    String url;
    String title;

    public Environment() {
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "url=" + url +
                ", title=" + title +
                '}';
    }
}
