//package eu.uberrestapp.model;
//
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.databind.JsonNode;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Convert;
//import javax.persistence.Embeddable;
//
//@Embeddable
//public class JsonNodeEmbeddable {
//    @Column(columnDefinition = "jsonb")
//    @Convert(converter = JsonToStringConverter.class)
//    private JsonNode jsonNode;
//
////    @JsonAnyGetter
//    public JsonNode getJsonNode() {
//        return jsonNode;
//    }
//
//    @JsonAnySetter
//    public void setJsonNode(JsonNode jsonNode) {
//        this.jsonNode = jsonNode;
//    }
//}
