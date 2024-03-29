package metube.repository;

import metube.domain.entities.User;

public interface UserRepository extends GenericRepository<User,String> {
    User findByNameAndPassword(String username,String password);
}
