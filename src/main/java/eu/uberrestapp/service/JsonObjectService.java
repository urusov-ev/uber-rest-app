package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonObject;
import eu.uberrestapp.repo.JsonObjectRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JsonObjectService {
    private final JsonObjectRepo jsonObjectRepo;

    public JsonObjectService(JsonObjectRepo jsonObjectRepo) {
        this.jsonObjectRepo = jsonObjectRepo;
    }

    @Transactional
    public JsonObject saveJsonObject(JsonNode jsonNode) {
        JsonObject jsonObject = new JsonObject(jsonNode);
        return jsonObjectRepo.save(jsonObject);
    }

    @Transactional
    public JsonNode getJsonById(long id) {
        JsonObject jsonObject = jsonObjectRepo.getOne(id);
        return jsonObject.getJson();
    }
}
