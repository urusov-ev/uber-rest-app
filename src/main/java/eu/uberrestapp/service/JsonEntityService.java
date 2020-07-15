package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonEntity;

import java.util.List;

public interface JsonEntityService {

    List<JsonEntity> getAll();

    JsonEntity saveJsonEntity(JsonNode jsonNode);

    JsonNode getJsonById(long id);

    List<JsonEntity> findByKeyAndValue(String key, String value);

    List<JsonEntity> findAllAtTopLevel(JsonNode jsonNode);

    /**
     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
     */
    List<JsonEntity> findAllWhereKeyAtTop(String key);
}
