package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.uberrestapp.model.JsonObject;
import eu.uberrestapp.repo.JsonObjectRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class JsonObjectService {
    private final JsonObjectRepo jsonObjectRepo;
    private final ObjectMapper objectMapper;
    private final JsonObjectFactory jsonObjectFactory;

    public JsonObjectService(JsonObjectRepo jsonObjectRepo, ObjectMapper objectMapper, JsonObjectFactory jsonObjectFactory) {
        this.jsonObjectRepo = jsonObjectRepo;
        this.objectMapper = objectMapper;
        this.jsonObjectFactory = jsonObjectFactory;
    }

    private JsonNode mapToJsonNode(Map<String, JsonNode> map) {
        return objectMapper.convertValue(map, JsonNode.class);
    }

    // Получить все
    @Transactional
    public List<JsonObject> getAll() {
        return jsonObjectRepo.findAll();
    }

    // Сохранить новый JSON
    @Transactional
    public JsonObject saveJsonObject(JsonNode jsonNode) {
        return jsonObjectRepo.save(jsonObjectFactory.getJsonObject(jsonNode));
    }

    // Получить по ID (JsonNode)
    @Transactional
    public JsonNode getJsonNodeById(long id) {
        return mapToJsonNode(jsonObjectRepo.getOne(id).getJsonMap());
    }

    // найти все имеющие данный ключ (только верхний уровень)
    @Transactional
    public List<JsonObject> findByKey(String key) {
        return jsonObjectRepo.findAllByKey(key);
    }

    // найти все имеющие данное значение (только верхний уровень)
    @Transactional
    public List<JsonObject> findByValue(String val) {
        return jsonObjectRepo.findAllByValue(val);
    }

    // Найти по ключу и по значению (только верхний уровень)
    @Transactional
    public List<JsonObject> findByKeyAndValue(String key, String value) {
        return jsonObjectRepo.findAllByKeyAndValue(key, value);
    }

    // найти по ключу или значению (или ключ или значение совпадают со строкой поиска; только верхний уровень)
    @Transactional
    public List<JsonObject> findKeyOrValue(String key) {
        return jsonObjectRepo.findAllByKeyOrValue(key);
    }

//    @Transactional
//    public List<JsonObject> findAllAtTopLevel(JsonNode jsonNode) {
//        System.out.println(jsonNode.toString());
//        return jsonObjectRepo.findAtTheTopLevel(jsonNode.toString());
//    }

//    /**
//     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
//     */
//    @Transactional
//    public List<JsonObject> findAllWhereKeyAtTop(String key) {
//        return jsonObjectDao.findAllWhereKeyAtTop(key);
//    }
}
