package eu.uberrestapp.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class JsonToStringConverter implements AttributeConverter<JsonNode, String> {
    private final ObjectMapper objectMapper;

    public JsonToStringConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        return attribute.toPrettyString();
    }

    @SneakyThrows
    @Override
    public JsonNode convertToEntityAttribute(String jsonString) {
        return objectMapper.readTree(jsonString);
    }
}
