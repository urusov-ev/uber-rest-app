package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonEntity;

import java.util.List;

public interface JsonEntityService {

    List<JsonEntity> getAll();

    JsonEntity saveJsonObject(JsonNode jsonNode);

    JsonNode getJsonNodeById(long id);

    List<JsonEntity> findByKey(String key);

    List<JsonEntity> findByValue(String val);

    List<JsonEntity> findByKeyAndValue(String key, String value);

    List<JsonEntity> findKeyOrValue(String key);
}
