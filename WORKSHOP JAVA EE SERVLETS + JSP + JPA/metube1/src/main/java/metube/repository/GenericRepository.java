package metube.repository;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<Entity, Id> {
    Entity save(Entity entity);

    List<Entity> findAll();

    Optional<Entity> findById(Id id);
}
