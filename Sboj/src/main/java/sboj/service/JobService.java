package sboj.service;

import sboj.domain.models.service.JobServiceModel;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<JobServiceModel> save(JobServiceModel jobServiceModel);

    List<JobServiceModel> getAll();

    Optional<JobServiceModel> findById(String id);

    boolean deleteById(String id);
}
