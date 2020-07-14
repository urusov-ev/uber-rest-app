package eu.uberrestapp.repo;

import eu.uberrestapp.model.JsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JsonEntityRepo extends JpaRepository<JsonEntity, Long> {

    /**
     * Находит и возвращает все JSON`ы, которые содержат пару ключ-значение (key,val)
     */
    @Query(nativeQuery = true, value = "SELECT * FROM json_entity WHERE (json->:key)=:val\\:\\:jsonb")
    List<JsonEntity> findByKeyAndValue(@Param("key") String key, @Param("val") String val);

    /**
     * Находит и возвращает все JSON`ы, которые содержат на верхнем уровне JSON,
     * переданный в виде строки в параметре {@code kv}
     */
    @Query(nativeQuery = true, value = "SELECT * FROM json_entity WHERE json@>:kv\\:\\:jsonb")
    List<JsonEntity> findAtTheTopLevel(@Param("kv") String kv);

//    /**
//     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
//     * НЕ РАБОТАЕТ, ПОТОМУ ЧТО ЗНАК ВОПРОСА НЕЛЬЗЯ ЗАЭКРАНИРОВАТЬ НОРМАЛЬНО!
//     */
//    @Query(nativeQuery = true, value = "SELECT * FROM json_entity WHERE json?:key")
//    List<JsonEntity> findAllWhereKeyAtTop(@Param("key") String key);

}
