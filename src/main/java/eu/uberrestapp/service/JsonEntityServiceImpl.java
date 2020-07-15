package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
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

    public JsonEntityServiceImpl(JsonEntityRepo jsonEntityRepo, JsonEntityDao jsonEntityDao) {
        this.jsonEntityRepo = jsonEntityRepo;
        this.jsonEntityDao = jsonEntityDao;
    }

    @Override
    @Transactional
    public List<JsonEntity> getAll() {
        return jsonEntityRepo.findAll();
    }

    @Override
    @Transactional
    public JsonEntity saveJsonEntity(JsonNode jsonNode) {
        JsonEntity jsonEntity = new JsonEntity(jsonNode);
        return jsonEntityRepo.save(jsonEntity);
    }

    @Override
    @Transactional
    public JsonNode getJsonById(long id) {
        JsonEntity jsonEntity = jsonEntityRepo.getOne(id);
        return jsonEntity.getJson();
    }

    @Override
    @Transactional
    public List<JsonEntity> findByKeyAndValue(String key, String value) {
        return jsonEntityRepo.findByKeyAndValue(key, value);
    }

    @Override
    @Transactional
    public List<JsonEntity> findAllAtTopLevel(JsonNode jsonNode) {
        System.out.println(jsonNode.toString());
        return jsonEntityRepo.findAtTheTopLevel(jsonNode.toString());
    }

    @Override
    @Transactional
    public List<JsonEntity> findAllWhereKeyAtTop(String key) {
        return jsonEntityDao.findAllWhereKeyAtTop(key);
    }
}
