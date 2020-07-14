package eu.uberrestapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.uberrestapp.model.JsonObject;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonObjectFactoryImpl implements JsonObjectFactory {
    private final ObjectMapper objectMapper;

    public JsonObjectFactoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private Map<String, JsonNode> jsonToMap(JsonNode jsonNode) {
//        Iterator<String> iterator = jsonNode.fieldNames();
//        while (iterator.hasNext()) {
//            String key = iterator.next();
//            JsonNode val = jsonNode.findValue(key);
//            System.out.println(val.getNodeType() + " : " + key + " : " + val.toPrettyString());
//        }
        Map<String, JsonNode> resMap = null;
        try {
            resMap = objectMapper.readValue(jsonNode.toPrettyString(), new TypeReference<Map<String, JsonNode>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    @Override
    public JsonObject getJsonObject(JsonNode jsonNode) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.setJsonMap(jsonToMap(jsonNode));
        return jsonObject;
    }
}
