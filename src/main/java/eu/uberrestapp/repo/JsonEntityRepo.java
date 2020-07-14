package eu.uberrestapp.repo;

import eu.uberrestapp.model.JsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JsonEntityRepo extends JpaRepository<JsonEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM json_entity o JOIN json_entity_map m ON o.id=m.json_entity_id WHERE m.json_map_key=:key")
    List<JsonEntity> findAllByKey(@Param("key") String key);

    @Query(nativeQuery = true, value = "SELECT * FROM json_entity o JOIN json_entity_map m ON o.id=m.json_entity_id WHERE m.json_map_value=:val")
    List<JsonEntity> findAllByValue(@Param("val") String val);

    @Query(nativeQuery = true, value = "SELECT * FROM json_entity o JOIN json_entity_map m ON o.id=m.json_entity_id WHERE m.json_map_key=:key AND m.json_map_value=:val")
    List<JsonEntity> findAllByKeyAndValue(@Param("key") String key, @Param("val") String val);

    @Query(nativeQuery = true, value = "SELECT * FROM json_entity o JOIN json_entity_map m ON o.id=m.json_entity_id WHERE m.json_map_key=:key OR m.json_map_value=:key")
    List<JsonEntity> findAllByKeyOrValue(@Param("key") String key);
}
