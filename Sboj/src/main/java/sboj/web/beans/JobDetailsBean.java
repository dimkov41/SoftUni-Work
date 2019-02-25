package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.service.JobServiceModel;
import sboj.domain.models.view.JobDetailsViewModel;
import sboj.service.JobService;
import sboj.utils.BeanUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.DocFlavor;
import java.io.IOException;
import java.util.Optional;

@Named
@RequestScoped
public class JobDetailsBean {
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

        BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/home");
        return new JobDetailsViewModel();
    }
}
