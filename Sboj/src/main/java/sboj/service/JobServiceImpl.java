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
        return Optional.ofNullable(this.modelMapper.map(
                this.jobRepository
                        .save(this.modelMapper.map(jobServiceModel, Job.class)), JobServiceModel.class));
    }

    @Override
    public List<JobServiceModel> getAll() {
        return Arrays.stream(
                this.modelMapper
                        .map(this.jobRepository.getAll(), JobServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public Optional<JobServiceModel> findById(String id) {
        return Optional.ofNullable(
                this.modelMapper.map(this.jobRepository.findById(id), JobServiceModel.class));

    }

    @Override
    public boolean deleteById(String id) {
        return this.jobRepository.deleteById(id);
    }
}
