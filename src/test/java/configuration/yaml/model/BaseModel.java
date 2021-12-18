package configuration.yaml.model;

public class BaseModel {
    private String url;
    private String title;
    private String active;
    private String browser;
    private String emailFailed;
    private String passwordFailed;
    private String emailSuccess;
    private String passwordSuccess;

    public BaseModel() {
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getActive() {
        return active;
    }

    public String getEmailFailed() {
        return emailFailed;
    }

    public String getPasswordFailed() {
        return passwordFailed;
    }

    public String getEmailSuccess() {
        return emailSuccess;
    }

    public String getPasswordSuccess() {
        return passwordSuccess;
    }

    public String getBrowser() {
        return browser;
    }
}

