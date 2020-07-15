package eu.uberrestapp.dao;

import eu.uberrestapp.model.JsonEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class JsonEntityDaoImpl implements JsonEntityDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<JsonEntity> findAllWhereKeyAtTop(String key) {
        Query query = entityManager.createNativeQuery("SELECT * FROM \"UberDB\".public.json_entity WHERE \"json\" ? ?", JsonEntity.class);
        query.setParameter(1, '?');
        query.setParameter(2, "'" + key + "'");
        //noinspection unchecked
        List<JsonEntity> res = query.getResultList();
        return res;
    }

    @Override
    public JsonEntity findByPath(Long id, String jsonPath) {
        Query query = entityManager
                .createNativeQuery("SELECT json#>>:path\\:\\:text[] json_node FROM json_entity WHERE id=:id", "JsonNodeMapping")
                .setParameter("path", jsonPath)
                .setParameter("id", id);
        return (JsonEntity) query.getSingleResult();
    }
}
