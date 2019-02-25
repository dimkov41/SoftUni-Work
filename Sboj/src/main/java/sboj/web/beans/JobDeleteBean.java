package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.service.JobServiceModel;
import sboj.domain.models.view.JobDeleteViewModel;
import sboj.service.JobService;
import sboj.utils.BeanUtils;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Named
@RequestScoped
public class JobDeleteBean {
    private JobService jobService;
    private ModelMapper modelMapper;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    public JobDeleteViewModel getViewModel(String id) throws IOException {
        Optional<JobServiceModel> jobServiceModel = this.jobService.findById(id);

        if(jobServiceModel.isPresent()){
            return this.modelMapper.map(jobServiceModel.get(), JobDeleteViewModel.class);
        }

        BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/home");
        return new JobDeleteViewModel();
    }

    public void delete() throws IOException {
        String id = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getParameter("id");

        if(this.jobService.deleteById(id)){
            BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/home");
            return;
        }

        BeanUtils.addMessage(FacesContext.getCurrentInstance(),"Error! Please try again.");
    }
}
