package sboj.repository;

import sboj.domain.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends GenericRepository<Job, String> {
    Job findById(String id);

    boolean deleteById(String id);

    List<Job> getAll();
}
