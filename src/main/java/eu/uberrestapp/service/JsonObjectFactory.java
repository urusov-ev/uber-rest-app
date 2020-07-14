package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonObject;

public interface JsonObjectFactory {
    JsonObject getJsonObject(JsonNode jsonNode);
}
