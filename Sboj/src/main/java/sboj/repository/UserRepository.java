package sboj.repository;

import sboj.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,String> {
    User findByUsername(String username);
}
