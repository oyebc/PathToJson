package ai.maven.labs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Pelumi<pelumi@maven.ai>
 *         Created on 18/03/16 at 22:28.
 */

public class CustomSerializer extends JsonSerializer<DirTreeNode> {

    //feel free to serialise in whatever way you deem fit here
    public void serialize(DirTreeNode value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (value.getChildren().size() == 1)
            //if it's a single child, just add as a jsonobject not a json array
            jgen.writeObjectField(value.getName(), value.getChildren().toArray()[0]);
         else if (value.getChildren().size() == 0)
            //if it has no child add as an empty string instead of empty json array
            jgen.writeObjectField(value.getName(), "");
         else
            jgen.writeObjectField(value.getName(), value.getChildren());

        jgen.writeEndObject();
    }

}