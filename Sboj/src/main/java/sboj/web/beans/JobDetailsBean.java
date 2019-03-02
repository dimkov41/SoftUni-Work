package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.service.JobServiceModel;
import sboj.domain.models.view.JobDetailsViewModel;
import sboj.service.JobService;
import sboj.utils.Constants;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Optional;

@Named
@RequestScoped
public class JobDetailsBean extends BaseBean{
    private ModelMapper modelMapper;
    private JobService jobService;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(ModelMapper modelMapper, JobService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    public JobDetailsViewModel getDetails(String id) throws IOException {
        Optional<JobServiceModel> jobServiceModel = this.jobService.findById(id);

        if(jobServiceModel.isPresent()){
            return this.modelMapper.map(jobServiceModel.get(),JobDetailsViewModel.class);
        }

        super.sendRedirect(FacesContext.getCurrentInstance(),Constants.HOME_PATH);
        return new JobDetailsViewModel();
    }
}
