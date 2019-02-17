package meTube.repository;

import meTube.domain.entities.Tube;
import meTube.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User,String>{
    Optional<User> findByName(String name);
    List<Tube> getAllTubes(String name);
}
