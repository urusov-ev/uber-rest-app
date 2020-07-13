package eu.uberrestapp.repo;

import eu.uberrestapp.model.JsonObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonObjectRepo extends JpaRepository<JsonObject, Long> {
}
