package eu.uberrestapp.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import eu.uberrestapp.model.converter.JsonToStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "json_object")
@NoArgsConstructor
@Getter
@Setter
@ToString
// Про "jsonb" и следующие @TypeDef см. здесь:
// https://stackoverflow.com/questions/49325802/is-it-possible-to-use-hibernate-with-postgresqls-jsonb-data-type
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class JsonObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "json_object_map", joinColumns = @JoinColumn(name = "json_object_id"))

//    (!): @Type с "jsonb" требует выключенного @Convert, также columnDefinition дожен быть "jsonb",
//    (но это не работает до конца: сохраняет правильно, но при получении из БД - ошибка)
//    @Type(type = "jsonb")

    // Конвертер конвертит только значение у мапы JsonNode<->String
    @Convert(converter = JsonToStringConverter.class, attributeName = "value")
//    @MapKeyColumn(columnDefinition = "jsonb")
    @Column(name = "json_map_value", columnDefinition = "text")
    private Map<String, JsonNode> jsonMap;

    //    @JsonAnyGetter
//    public Map<String, JsonNode> getJsonMap() {
//        return jsonMap;
//    }

    //    @JsonAnySetter
//    public void setJsonMap(Map<String, JsonNode> jsonMap) {
//        this.jsonMap = jsonMap;
//    }

//    @JsonAnyGetter
//    public Map<String, JsonNode> getJsonMap() {
//        return jsonMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getJsonNode()));
//    }

//    @JsonAnySetter
//    public void setJsonMap(Map<String, JsonNode> jsonMap) {
//        this.jsonMap = jsonMap.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
//    }

//    public JsonObject(JsonNode jsonMap) {
//        this.jsonMap = jsonMap;
//    }
}
