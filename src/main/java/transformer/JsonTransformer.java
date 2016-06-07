package transformer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import spark.ResponseTransformer;

import java.io.IOException;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(Object data) throws Exception
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD,Visibility.ANY);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(data);
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter? " + e.getMessage());
        }
    }

}
