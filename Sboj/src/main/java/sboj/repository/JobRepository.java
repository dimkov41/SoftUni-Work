package sboj.repository;

import sboj.domain.entity.Job;

import java.util.Optional;

public interface JobRepository extends GenericRepository<Job,String> {
    Optional<Job> findById(String id);

    boolean deleteById(String id);
}
