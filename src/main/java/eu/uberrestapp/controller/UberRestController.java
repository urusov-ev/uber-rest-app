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

    // Получить все
    @GetMapping(value = "/")
    public ResponseEntity<List<JsonEntity>> getAll() {
        return ResponseEntity.ok(jsonEntityService.getAll());
    }

    // Сохранить новый JSON
    @PostMapping(value = "/")
    public ResponseEntity<JsonEntity> saveJsonEntity(@RequestBody String jsonString) {
        JsonEntity persisted = jsonEntityService.saveJsonEntity(jsonString);
        return ResponseEntity.ok(persisted);
    }

    // Получить по ID (JsonNode)
    @GetMapping(value = "/getById")
    public ResponseEntity<JsonNode> getJsonNodeById(@PathParam("id") Long id) {
        JsonNode jsonNode = jsonEntityService.getJsonById(id);
        return ResponseEntity.ok(jsonNode);
    }

    // Найти по ключу и по значению
    @GetMapping(value = "/find")
    ResponseEntity<List<JsonEntity>> findByKeyAndValue(@PathParam("key") String key, @PathParam("val") String val) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findByKeyAndValue(key, val);
        return ResponseEntity.ok(jsonEntityList);
    }

    // Найти по ключу и по значению (json string)
    @GetMapping(value = "/find2")
    ResponseEntity<List<JsonEntity>> findByKeyAndValue2(@PathParam("find") String jsonString) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findAllAtTopLevel(jsonString);
        return ResponseEntity.ok(jsonEntityList);
    }

    // Найти подобьект JSON по заданному пути в объекте с заданным ID
    @GetMapping(value = "/getByPath")
    ResponseEntity<String> findByPath(@PathParam("id") Long id, @PathParam("jp") String jp) {
        JsonEntity jsonEntity = jsonEntityService.findByPath(id, jp);
        return ResponseEntity.ok(jsonEntity.getJsonString());
    }

    // Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
    @GetMapping(value = "/findAllWhereKeyAtTop")
    ResponseEntity<List<JsonEntity>> findAllWhereKeyAtTop(@PathParam("key") String key) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findAllWhereKeyAtTop(key);
        return ResponseEntity.ok(jsonEntityList);
    }
}
