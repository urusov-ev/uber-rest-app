package eu.uberrestapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.uberrestapp.dao.JsonEntityDao;
import eu.uberrestapp.model.JsonEntity;
import eu.uberrestapp.repo.JsonEntityRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JsonEntityServiceImpl implements JsonEntityService {
    private final JsonEntityRepo jsonEntityRepo;
    private final JsonEntityDao jsonEntityDao;
    private ObjectMapper objectMapper;

    public JsonEntityServiceImpl(JsonEntityRepo jsonEntityRepo, JsonEntityDao jsonEntityDao, ObjectMapper objectMapper) {
        this.jsonEntityRepo = jsonEntityRepo;
        this.jsonEntityDao = jsonEntityDao;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public List<JsonEntity> getAll() {
        return jsonEntityRepo.findAll();
    }

    @Override
    @Transactional
    public JsonEntity saveJsonEntity(String jsonString) {
        JsonEntity jsonEntity = new JsonEntity(jsonString);
        return jsonEntityRepo.save(jsonEntity);
    }

    @Override
    @Transactional
    public JsonNode getJsonById(long id) {
        JsonEntity jsonEntity = jsonEntityRepo.getOne(id);
        String jsonString = jsonEntity.getJsonString();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    @Override
    @Transactional
    public List<JsonEntity> findByKeyAndValue(String key, String value) {
        return jsonEntityRepo.findByKeyAndValue(key, value);
    }

    @Override
    @Transactional
    public List<JsonEntity> findAllAtTopLevel(String jsonString) {
        return jsonEntityRepo.findAtTheTopLevel(jsonString);
    }

    @Override
    @Transactional
    public List<JsonEntity> findAllWhereKeyAtTop(String key) {
        return jsonEntityDao.findAllWhereKeyAtTop(key);
    }

    @Override
    public JsonEntity findByPath(Long id, String jsonPath) {
        System.out.println(jsonPath);
        String jsonPathAct = "{" + jsonPath + "}";
        System.out.println(jsonPathAct);
        return jsonEntityDao.findByPath(id, jsonPathAct);
    }
}
