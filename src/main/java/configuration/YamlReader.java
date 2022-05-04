package configuration;

import model.EnvironmentStructure;
import model.Pojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YamlReader {

    private static Logger log = LoggerFactory.getLogger("YamlReader.class");

    public static Pojo getProperties(){
        final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try{
            return objectMapper.readValue(new File(System.getProperty("user.dir")+
                    "\\src\\main\\resources\\properties.yaml"), Pojo.class);
        }catch (IOException e){ log.error("Can not read properties due to IO Exception");}
        return null;
    }

    public static void setPropertiesFromYAMLEnvironment(){
        List<EnvironmentStructure> listOfEnvironments = getProperties().getEnvironments().listOfEnvironments();
        boolean foundActiveEnvironment = false;
        for(EnvironmentStructure environmentStructure : listOfEnvironments){
            if(environmentStructure.isActive()){
                foundActiveEnvironment = true;
                Map<String,Object> environmentProperties = environmentStructure.getStructure();
                for (Map.Entry entry : environmentProperties.entrySet()){
                    System.setProperty(entry.getKey().toString(),entry.getValue().toString());
                    log.info("Loaded environment property: {} = {}",entry.getKey().toString(),entry.getValue().toString());
                }
                log.info("Loaded environment properties total: {}", environmentProperties.size());
                break;
            }
        }
    }
}
