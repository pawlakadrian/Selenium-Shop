package configuration.yaml.model;

import java.util.Objects;

public class EnvironmentModel {

    private BaseModel test;
    private BaseModel stag;

    public BaseModel getTest() {
        return test;
    }

    public BaseModel getStag() {
        return stag;
    }

    public BaseModel choseActiveEnvironment() {
        if (Objects.equals(getTest().getActive(), "Y")){
            return getTest();
        } else if (Objects.equals(getStag().getActive(), "Y")){
            return getStag();
        } else {
            return getTest();
        }
    }
}
