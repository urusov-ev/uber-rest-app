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

//    public JsonObjectService(JsonObjectDao jsonObjectDao) {
//        this.jsonObjectDao = jsonObjectDao;
//    }
//
//    private final JsonObjectDao jsonObjectDao;

    @Transactional
    public JsonObject saveJsonObject(JsonNode jsonNode) {
        JsonObject jsonObject = new JsonObject(jsonNode);
//        Long id = jsonObjectDao.persist(jsonObject);
//        return jsonObjectDao.getById(id);
        return jsonObjectRepo.save(jsonObject);
    }

    @Transactional
    public JsonNode getJsonById(long id) {
//        JsonObject jsonObject = jsonObjectDao.getById(id);
        JsonObject jsonObject = jsonObjectRepo.getOne(id);
        return jsonObject.getJson();
    }
}
