package eu.uberrestapp.service.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.uberrestapp.model.JsonEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonEntityFactoryImpl implements JsonEntityFactory {
    private final ObjectMapper objectMapper;

    public JsonEntityFactoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private Map<String, JsonNode> jsonToMap(JsonNode jsonNode) {
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
    public JsonEntity getJsonObject(JsonNode jsonNode) {
        JsonEntity jsonEntity = new JsonEntity();
        jsonEntity.setJsonMap(jsonToMap(jsonNode));
        return jsonEntity;
    }
}
