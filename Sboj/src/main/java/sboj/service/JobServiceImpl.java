package sboj.service;

import org.modelmapper.ModelMapper;
import sboj.domain.entity.Job;
import sboj.domain.models.service.JobServiceModel;
import sboj.repository.JobRepository;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {
    private ModelMapper modelMapper;
    private JobRepository jobRepository;

    @Inject
    public JobServiceImpl(ModelMapper modelMapper, JobRepository jobRepository) {
        this.modelMapper = modelMapper;
        this.jobRepository = jobRepository;
    }

    @Override
    public Optional<JobServiceModel> save(JobServiceModel jobServiceModel) {
        if(this.jobRepository.save(this.modelMapper.map(jobServiceModel, Job.class)).isPresent()){
            return Optional.of(jobServiceModel);
        }

        return Optional.empty();
    }

    @Override
    public List<JobServiceModel> getAll() {
        return Arrays.stream(
                this.modelMapper
                        .map(this.jobRepository.getAll(), JobServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public Optional<JobServiceModel> findById(String id) {
        Optional<Job> jobs = this.jobRepository.findById(id);

        if(jobs.isPresent()){
            return Optional.of(this.modelMapper.map(jobs.get(),JobServiceModel.class));
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(String id) {
        return this.jobRepository.deleteById(id);
    }
}
