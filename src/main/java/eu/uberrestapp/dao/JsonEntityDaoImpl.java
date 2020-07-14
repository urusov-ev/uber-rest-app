package eu.uberrestapp.dao;

import eu.uberrestapp.model.JsonEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class JsonEntityDaoImpl implements JsonEntityDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<JsonEntity> findAllWhereKeyAtTop(String key) {
        //noinspection unchecked
        List<JsonEntity> res = (List<JsonEntity>) entityManager.createNativeQuery("SELECT * FROM \"UberDB\".public.json_entity WHERE json\\?" + key)
                .getResultList();
        return res;
    }
}
