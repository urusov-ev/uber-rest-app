//package eu.uberrestapp.dao;
//
//import eu.uberrestapp.model.JsonObject;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//@Transactional
//public class JsonObjectDaoImpl implements JsonObjectDao {
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public List<JsonObject> findAllWhereKeyAtTop(String key) {
//        //noinspection unchecked
//        List<JsonObject> res = (List<JsonObject>) entityManager.createNativeQuery("SELECT * FROM \"UberDB\".public.json_object WHERE json\\?" + key)
//                .getResultList();
//        return res;
//    }
//}
