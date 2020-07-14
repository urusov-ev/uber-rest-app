package eu.uberrestapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonEntity;
import eu.uberrestapp.service.JsonEntityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UberRestController {

    private final JsonEntityServiceImpl jsonEntityService;

    public UberRestController(JsonEntityServiceImpl jsonEntityService) {
        this.jsonEntityService = jsonEntityService;
    }

    // Получить все
    @GetMapping(value = "/")
    public ResponseEntity<List<JsonEntity>> getAll() {
        return ResponseEntity.ok(jsonEntityService.getAll());
    }

    // Сохранить новый JSON
    @PostMapping(value = "/")
    public ResponseEntity<JsonEntity> saveJsonObject(@RequestBody JsonNode inputJsonNode) {
        JsonEntity persisted = jsonEntityService.saveJsonObject(inputJsonNode);
        return ResponseEntity.ok(persisted);
    }

    // Получить по ID (JsonNode)
    @GetMapping(value = "/getById")
    public ResponseEntity<JsonNode> getJsonNodeById(@PathParam("id") Long id) {
        JsonNode jsonNode = jsonEntityService.getJsonNodeById(id);
        return ResponseEntity.ok(jsonNode);
    }

    // найти все имеющие данный ключ (только верхний уровень)
    @GetMapping(value = "/findByKey")
    ResponseEntity<List<JsonEntity>> findByKey(@PathParam("key") String key) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findByKey(key);
        return ResponseEntity.ok(jsonEntityList);
    }

    // найти все имеющие данное значение (только верхний уровень)
    @GetMapping(value = "/findByValue")
    ResponseEntity<List<JsonEntity>> findByValue(@PathParam("val") String val) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findByValue(val);
        return ResponseEntity.ok(jsonEntityList);
    }

    // Найти по ключу и по значению (только верхний уровень)
    @GetMapping(value = "/find")
    ResponseEntity<List<JsonEntity>> findByKeyAndValue(@PathParam("key") String key, @PathParam("val") String val) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findByKeyAndValue(key, val);
        return ResponseEntity.ok(jsonEntityList);
    }

    // найти по ключу или значению (или ключ или значение совпадают со строкой поиска; только верхний уровень)
    @GetMapping(value = "/findKeyOrValue")
    ResponseEntity<List<JsonEntity>> findKeyOrValue(@PathParam("key") String key) {
        List<JsonEntity> jsonEntityList = jsonEntityService.findKeyOrValue(key);
        return ResponseEntity.ok(jsonEntityList);
    }
}
