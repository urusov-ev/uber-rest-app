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
import java.util.Iterator;

@RestController
public class UberRestController {

    private final JsonObjectService jsonObjectService;

    public UberRestController(JsonObjectService jsonObjectService) {
        this.jsonObjectService = jsonObjectService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> saveJsonObject(@RequestBody JsonNode inputJsonNode) {
//        System.out.println(request);
        Iterator<String> iterator = inputJsonNode.fieldNames();
//        System.out.println(request.toPrettyString());
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + ": " + inputJsonNode.findValue(key).toString());
        }
        JsonObject persisted = jsonObjectService.saveJsonObject(inputJsonNode);
//        System.out.println(persisted);
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
}
