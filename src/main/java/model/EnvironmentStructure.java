package model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnvironmentStructure {

    boolean active;
    public boolean isActive() { return active;}

    @JsonAnyGetter
    @JsonAnySetter
    private Map<String,Object> structure = new LinkedHashMap<>();

    public Map<String, Object> getStructure() { return structure;}
    public void setStructure(Map<String, Object> structure) { this.structure = structure;}
}
