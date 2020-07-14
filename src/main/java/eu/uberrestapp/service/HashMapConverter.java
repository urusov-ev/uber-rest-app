//package eu.uberrestapp.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.util.Map;
//
//@Converter
//@Service
//public class HashMapConverter implements AttributeConverter<Map<String, JsonNode>, String> {
//    private final ObjectMapper objectMapper;
//
//    public HashMapConverter(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public String convertToDatabaseColumn(Map<String, JsonNode> attribute) {
//        String jsonString = null;
//        try {
//            jsonString = objectMapper.writeValueAsString(attribute);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return jsonString;
//    }
//
//    @Override
//    public Map<String, JsonNode> convertToEntityAttribute(String dbData) {
//        Map<String, JsonNode> map = null;
//        try {
//            //noinspection unchecked
//            map = objectMapper.readValue(dbData, Map.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//}
