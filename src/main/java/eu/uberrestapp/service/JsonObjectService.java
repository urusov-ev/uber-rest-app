package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.dao.JsonObjectDao;
import eu.uberrestapp.model.JsonObject;
import eu.uberrestapp.repo.JsonObjectRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JsonObjectService {
    private final JsonObjectRepo jsonObjectRepo;
    private final JsonObjectDao jsonObjectDao;

    public JsonObjectService(JsonObjectRepo jsonObjectRepo, JsonObjectDao jsonObjectDao) {
        this.jsonObjectRepo = jsonObjectRepo;
        this.jsonObjectDao = jsonObjectDao;
    }

//    public JsonObjectService(JsonObjectDao jsonObjectDao) {
//        this.jsonObjectDao = jsonObjectDao;
//    }

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

    @Transactional
    public List<JsonObject> findByKeyAndValue(String key, String value) {
        return jsonObjectRepo.findByKeyAndValue(key, value);
    }

    @Transactional
    public List<JsonObject> findAllAtTopLevel(JsonNode jsonNode) {
        System.out.println(jsonNode.toString());
        return jsonObjectRepo.findAtTheTopLevel(jsonNode.toString());
    }

    /**
     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
     */
    @Transactional
    public List<JsonObject> findAllWhereKeyAtTop(String key) {
        return jsonObjectDao.findAllWhereKeyAtTop(key);
    }
}
