package eu.uberrestapp.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

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

    @Type(type = "jsonb")
    @Column(name = "json", columnDefinition = "jsonb")
    JsonNode json;

    public JsonEntity(JsonNode json) {
        this.json = json;
    }
}
