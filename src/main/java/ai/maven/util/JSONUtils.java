package ai.maven.util;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * @author Pelumi Aboluwarin <pelumi@maven.ai>
 * Created on Apr 14, 2015 at 5:59:23 AM
 */
public class JSONUtils {

    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    public static String toJSON(Object pojo) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String classJson = "";
        try {
            classJson = mapper.writeValueAsString(pojo);
        }  catch (JsonMappingException e) {
            logger.error("JSON generation exception", e);
        } catch (IOException e) {
            logger.error("IOException in POJO to JSON conversion", e);
        }
        return classJson;
    }

    public static Object toPojo(Class classType, String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        Object pojo = null;
        try {
            pojo = mapper.readValue(jsonString, classType);
        } catch (IOException ex) {
            logger.error("IOException in JSON to POJO conversion", ex);

        }
        return pojo;
    }
    
}
