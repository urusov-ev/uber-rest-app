package eu.uberrestapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonObject;
import eu.uberrestapp.service.JsonObjectService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UberRestController {

    private final JsonObjectService jsonObjectService;

    public UberRestController(JsonObjectService jsonObjectService) {
        this.jsonObjectService = jsonObjectService;
    }

    // Получить все
    @GetMapping(value = "/")
    public ResponseEntity<List<JsonObject>> getAll() {
        return ResponseEntity.ok(jsonObjectService.getAll());
    }

    // Сохранить новый JSON
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> saveJsonObject(@RequestBody JsonNode inputJsonNode) {
        JsonObject persisted = jsonObjectService.saveJsonObject(inputJsonNode);
        return ResponseEntity.ok(persisted);
    }

    // Получить по ID (JsonNode)
    @GetMapping(value = "/getById")
    public ResponseEntity<JsonNode> getJsonNodeById(@PathParam("id") Long id) {
        JsonNode jsonNode = jsonObjectService.getJsonNodeById(id);
        return ResponseEntity.ok(jsonNode);
    }

    // найти все имеющие данный ключ (только верхний уровень)
    @GetMapping(value = "/findByKey")
    ResponseEntity<List<JsonObject>> findByKey(@PathParam("key") String key) {
        List<JsonObject> jsonObjectList = jsonObjectService.findByKey(key);
        return ResponseEntity.ok(jsonObjectList);
    }

    // найти все имеющие данное значение (только верхний уровень)
    @GetMapping(value = "/findByValue")
    ResponseEntity<List<JsonObject>> findByValue(@PathParam("val") String val) {
        List<JsonObject> jsonObjectList = jsonObjectService.findByValue(val);
        return ResponseEntity.ok(jsonObjectList);
    }

    // Найти по ключу и по значению (только верхний уровень)
    @GetMapping(value = "/find")
    ResponseEntity<List<JsonObject>> findByKeyAndValue(@PathParam("key") String key, @PathParam("val") String val) {
        List<JsonObject> jsonObjectList = jsonObjectService.findByKeyAndValue(key, val);
        return ResponseEntity.ok(jsonObjectList);
    }

    // найти по ключу или значению (или ключ или значение совпадают со строкой поиска; только верхний уровень)
    @GetMapping(value = "/findKeyOrValue")
    ResponseEntity<List<JsonObject>> findKeyOrValue(@PathParam("key") String key) {
        List<JsonObject> jsonObjectList = jsonObjectService.findKeyOrValue(key);
        return ResponseEntity.ok(jsonObjectList);
    }

//    @GetMapping(value = "get-with-value")
//    ResponseEntity<List<JsonObject>> getContainingvalue(@PathParam("val") String val){
//
//    }

//    @PostMapping(value = "/find2")
//    ResponseEntity<List<JsonObject>> findByKeyAndValue2(@RequestBody JsonNode inputJsonNode) {
//        List<JsonObject> jsonObjectList = jsonObjectService.findAllAtTopLevel(inputJsonNode);
//        return ResponseEntity.ok(jsonObjectList);
//    }

//    /**
//     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
//     */
//    @GetMapping(value = "/findAllWhereKeyAtTop")
//    ResponseEntity<List<JsonObject>> findAllWhereKeyAtTop(@PathParam("key") String key) {
//        List<JsonObject> jsonObjectList = jsonObjectService.findAllWhereKeyAtTop(key);
//        return ResponseEntity.ok(jsonObjectList);
//    }
}
