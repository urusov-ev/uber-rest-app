package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.uberrestapp.model.JsonEntity;
import eu.uberrestapp.repo.JsonEntityRepo;
import eu.uberrestapp.service.factory.JsonEntityFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class JsonEntityServiceImpl implements JsonEntityService {
    private final JsonEntityRepo jsonEntityRepo;
    private final ObjectMapper objectMapper;
    private final JsonEntityFactory jsonEntityFactory;

    public JsonEntityServiceImpl(JsonEntityRepo jsonEntityRepo, ObjectMapper objectMapper, JsonEntityFactory jsonEntityFactory) {
        this.jsonEntityRepo = jsonEntityRepo;
        this.objectMapper = objectMapper;
        this.jsonEntityFactory = jsonEntityFactory;
    }

    private JsonNode mapToJsonNode(Map<String, JsonNode> map) {
        return objectMapper.convertValue(map, JsonNode.class);
    }

    // Получить все
    @Override
    @Transactional
    public List<JsonEntity> getAll() {
        return jsonEntityRepo.findAll();
    }

    // Сохранить новый JSON
    @Override
    @Transactional
    public JsonEntity saveJsonObject(JsonNode jsonNode) {
        return jsonEntityRepo.save(jsonEntityFactory.getJsonObject(jsonNode));
    }

    // Получить по ID (JsonNode)
    @Override
    @Transactional
    public JsonNode getJsonNodeById(long id) {
        return mapToJsonNode(jsonEntityRepo.getOne(id).getJsonMap());
    }

    // найти все имеющие данный ключ (только верхний уровень)
    @Override
    @Transactional
    public List<JsonEntity> findByKey(String key) {
        return jsonEntityRepo.findAllByKey(key);
    }

    // найти все имеющие данное значение (только верхний уровень)
    @Override
    @Transactional
    public List<JsonEntity> findByValue(String val) {
        return jsonEntityRepo.findAllByValue(val);
    }

    // Найти по ключу и по значению (только верхний уровень)
    @Override
    @Transactional
    public List<JsonEntity> findByKeyAndValue(String key, String value) {
        return jsonEntityRepo.findAllByKeyAndValue(key, value);
    }

    // найти по ключу или значению (или ключ или значение совпадают со строкой поиска; только верхний уровень)
    @Override
    @Transactional
    public List<JsonEntity> findKeyOrValue(String key) {
        return jsonEntityRepo.findAllByKeyOrValue(key);
    }
}
