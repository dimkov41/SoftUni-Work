package meTube.repository;

import meTube.domain.entities.Tube;

import java.util.List;
import java.util.Optional;

public interface TubeRepository extends GenericRepository<Tube,String> {
    Optional<Tube> findById(String id);
    List<Tube> findAll();
}
