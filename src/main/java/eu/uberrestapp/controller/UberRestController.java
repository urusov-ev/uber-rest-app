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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> saveJsonObject(@RequestBody JsonNode inputJsonNode) {
//        Iterator<String> iterator = inputJsonNode.fieldNames();
//        while (iterator.hasNext()) {
//            String key = iterator.next();
//            JsonNode val = inputJsonNode.findValue(key);
//            System.out.println(val.getNodeType() + " : " + key + " : " + val.toPrettyString());
//        }
        JsonObject persisted = jsonObjectService.saveJsonObject(inputJsonNode);
        return ResponseEntity.ok(persisted);
    }

//    @PostMapping
//    public ResponseEntity<String> saveJsonObject(@RequestBody String request) {
//        System.out.println(request);
//
//        return ResponseEntity.ok(request);
//    }

    @GetMapping
    public ResponseEntity<JsonNode> getJsonNodeById(@PathParam("id") Long id) {
        JsonNode jsonNode = jsonObjectService.getJsonById(id);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping(value = "/find")
    ResponseEntity<List<JsonObject>> findByKeyAndValue(@PathParam("key") String key, @PathParam("val") String val) {
        List<JsonObject> jsonObjectList = jsonObjectService.findByKeyAndValue(key, val);
        return ResponseEntity.ok(jsonObjectList);
    }

//    @GetMapping(value = "get-with-value")
//    ResponseEntity<List<JsonObject>> getContainingvalue(@PathParam("val") String val){
//
//    }

    @PostMapping(value = "/find2")
    ResponseEntity<List<JsonObject>> findByKeyAndValue2(@RequestBody JsonNode inputJsonNode) {
        List<JsonObject> jsonObjectList = jsonObjectService.findAllAtTopLevel(inputJsonNode);
        return ResponseEntity.ok(jsonObjectList);
    }

    /**
     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
     */
    @GetMapping(value = "/findAllWhereKeyAtTop")
    ResponseEntity<List<JsonObject>> findAllWhereKeyAtTop(@PathParam("key") String key) {
        List<JsonObject> jsonObjectList = jsonObjectService.findAllWhereKeyAtTop(key);
        return ResponseEntity.ok(jsonObjectList);
    }
}
