package eu.uberrestapp.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@SqlResultSetMapping(name = "JsonNodeMapping",
        classes = {
                @ConstructorResult(targetClass = JsonEntity.class,
                        columns = {
                                @ColumnResult(name = "json_node", type = String.class),
                        })
        }
)
@Entity
@Table(name = "json_entity")
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class JsonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @Type(type = "jsonb")
//    @Column(name = "json", columnDefinition = "jsonb")
//    JsonNode json;

    @Type(type = "jsonb")
    @Column(name = "json", columnDefinition = "jsonb")
    String jsonString;

//    public JsonEntity(JsonNode json) {
//        this.json = json;
//    }

    public JsonEntity(String jsonString) {
        this.jsonString = jsonString;
    }
}
