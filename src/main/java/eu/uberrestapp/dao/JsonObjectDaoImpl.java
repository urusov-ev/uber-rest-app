//package eu.uberrestapp.dao;
//
//import eu.uberrestapp.model.JsonObject;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//@Repository
//@Transactional
//public class JsonObjectDaoImpl implements JsonObjectDao {
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public JsonObject getById(Long id) {
//        return entityManager.find(JsonObject.class, id);
//    }
//
//    @Override
//    public Long persist(JsonObject jsonObject) {
//        entityManager.persist(jsonObject);
//        return jsonObject.getId();
//    }
//}
