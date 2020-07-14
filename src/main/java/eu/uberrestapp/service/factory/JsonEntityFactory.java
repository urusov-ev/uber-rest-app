package eu.uberrestapp.service.factory;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonEntity;

public interface JsonEntityFactory {
    JsonEntity getJsonObject(JsonNode jsonNode);
}
