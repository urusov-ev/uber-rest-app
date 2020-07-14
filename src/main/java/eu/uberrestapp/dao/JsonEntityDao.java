package eu.uberrestapp.dao;

import eu.uberrestapp.model.JsonEntity;

import java.util.List;

public interface JsonEntityDao {

    List<JsonEntity> findAllWhereKeyAtTop(String key);

}
