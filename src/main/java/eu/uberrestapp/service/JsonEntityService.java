package eu.uberrestapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import eu.uberrestapp.model.JsonEntity;

import java.util.List;

public interface JsonEntityService {

    // Получить все
    List<JsonEntity> getAll();

    // Сохранить новый JSON
    JsonEntity saveJsonEntity(String jsonString);

    // Получить по ID (JsonNode)
    JsonNode getJsonById(long id);

    // Найти по ключу и по значению
    List<JsonEntity> findByKeyAndValue(String key, String value);

    // Найти по ключу и по значению (json string)
    List<JsonEntity> findAllAtTopLevel(String jsonString);

    // Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
    List<JsonEntity> findAllWhereKeyAtTop(String key);

    JsonEntity findByPath(Long id, String jsonPath);
}
