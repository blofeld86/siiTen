package model;

import java.util.ArrayList;
import java.util.List;

public class Environments {

    private EnvironmentStructure env_int;
    private EnvironmentStructure env_test;

    public EnvironmentStructure getEnv_int() { return env_int;}
    public void setEnv_int(EnvironmentStructure env_int) { this.env_int = env_int;}

    public EnvironmentStructure getEnv_test() { return env_test;}
    public void setEnv_test(EnvironmentStructure env_test) { this.env_test = env_test;}

    public List<EnvironmentStructure> listOfEnvironments(){
        List<EnvironmentStructure> list = new ArrayList<>();
        list.add(getEnv_int());
        list.add(getEnv_test());
        return list;
    }
}
