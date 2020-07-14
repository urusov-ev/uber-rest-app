package eu.uberrestapp.repo;

import eu.uberrestapp.model.JsonObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JsonObjectRepo extends JpaRepository<JsonObject, Long> {

    /**
     * Находит и возвращает все JSON`ы, которые содержат пару ключ-значение (key,val)
     */
    @Query(nativeQuery = true, value = "SELECT * FROM json_object WHERE (json->:key)=:val\\:\\:jsonb")
    List<JsonObject> findByKeyAndValue(@Param("key") String key, @Param("val") String val);

    /**
     * Находит и возвращает все JSON`ы, которые содержат на верхнем уровне JSON,
     * переданный в виде строки в параметре {@code kv}
     */
    @Query(nativeQuery = true, value = "SELECT * FROM json_object WHERE json@>:kv\\:\\:jsonb")
    List<JsonObject> findAtTheTopLevel(@Param("kv") String kv);

//    /**
//     * Находит и возвращает все JSON`ы, которые содержат строку {@code str} в качестве ключа верхнего уровня
//     */
//    @Query(nativeQuery = true, value = "SELECT * FROM json_object WHERE json?:key")
//    List<JsonObject> findAllWhereKeyAtTop(@Param("key") String key);

}
