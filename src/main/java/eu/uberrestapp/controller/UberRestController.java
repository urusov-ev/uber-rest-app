package eu.uberrestapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonEntity;
import eu.uberrestapp.service.JsonEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UberRestController {

    private final JsonEntityService jsonEntityService;

    public UberRestController(JsonEntityService jsonEntityService) {
        this.jsonEntityService = jsonEntityService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<JsonEntity> saveJsonEntity(@RequestBody JsonNode inputJsonNode) {
        JsonEntity persisted = jsonEntityService.saveJsonEntity(inputJsonNode);
        return ResponseEntity.ok(persisted);
    }

    @GetMapping(value = "/")
    public ResponseEntity<JsonNode> getJsonNodeById(@PathParam("id") Long id) {
        JsonNode jsonNode = jsonEntityService.getJsonById(id);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping(value = "/find")
    ResponseEntity<List<JsonEntity>> findByKeyAndValue(@PathParam("key") String key, @PathParam("val") String val) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findByKeyAndValue(key, val);
        return ResponseEntity.ok(jsonEntityList);
    }

    @PostMapping(value = "/find2")
    ResponseEntity<List<JsonEntity>> findByKeyAndValue2(@RequestBody JsonNode inputJsonNode) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findAllAtTopLevel(inputJsonNode);
        return ResponseEntity.ok(jsonEntityList);
    }

    /**
     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
     */
    @GetMapping(value = "/findAllWhereKeyAtTop")
    ResponseEntity<List<JsonEntity>> findAllWhereKeyAtTop(@PathParam("key") String key) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findAllWhereKeyAtTop(key);
        return ResponseEntity.ok(jsonEntityList);
    }
}
