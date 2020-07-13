package eu.uberrestapp.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.AttributeConverter;

public class JsonToStringConverter implements AttributeConverter<JsonNode, String> {
    private final ObjectMapper objectMapper;
//    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    public JsonToStringConverter() {
        this.objectMapper = new ObjectMapper();
//        this.objectMapper = context.getBean(ObjectMapper.class);
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
