package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.view.JobHomeViewModel;
import sboj.service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobHomeBean {
    private ModelMapper modelMapper;
    private JobService jobService;

    public JobHomeBean() {
    }

    @Inject
    public JobHomeBean(ModelMapper modelMapper, JobService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    public List<JobHomeViewModel> getAll(){
        return Arrays.stream(
                this.modelMapper.map(this.jobService.getAll(), JobHomeViewModel[].class)).collect(Collectors.toList());
    }
}
