//package eu.uberrestapp.model;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import eu.uberrestapp.config.UberRestAppConfig;
//import lombok.SneakyThrows;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//
//@Converter
//public class JsonToStringConverter implements AttributeConverter<JsonNode, String> {
//
//    private final ObjectMapper objectMapper;
//
//    public JsonToStringConverter() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(UberRestAppConfig.class);
//        objectMapper = context.getBean(MappingJackson2HttpMessageConverter.class).getObjectMapper();
//    }
//
//    @Override
//    public String convertToDatabaseColumn(JsonNode attribute) {
//        return attribute.toPrettyString();
//    }
//
//    @SneakyThrows
//    @Override
//    public JsonNode convertToEntityAttribute(String jsonString) {
//        return objectMapper.readTree(jsonString);
//    }
//}
