package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.JobCreateBindingModel;
import sboj.domain.models.enums.Sector;
import sboj.domain.models.service.JobServiceModel;
import sboj.service.JobService;
import sboj.utils.BeanUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobCreateBean {
    private JobCreateBindingModel jobCreateBindingModel;

    private ModelMapper modelMapper;
    private JobService jobService;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(ModelMapper modelMapper, JobService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    @PostConstruct
    public void init(){
        this.jobCreateBindingModel = new JobCreateBindingModel();
    }

    public void createJob() throws IOException {
        String sector = this.jobCreateBindingModel.getSector();

        if(!sectorIsValid(sector)){
            BeanUtils.addMessage(FacesContext.getCurrentInstance(),"Sector is not valid");
            return;
        }

        if(this.jobService.save(this.modelMapper.map(this.jobCreateBindingModel, JobServiceModel.class)).isPresent()){
            BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/home");
            return;
        }

        //if save fail, reload add page
        BeanUtils.addMessage(FacesContext.getCurrentInstance(),"Try again");
        BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/jobs/add");
    }

    private boolean sectorIsValid(String sector){
        for (Sector currentSector : Sector.values()) {
            if(currentSector.name().equalsIgnoreCase(sector)){
                return true;
            }
        }
        return false;
    }

    public JobCreateBindingModel getJobCreateBindingModel() {
        return jobCreateBindingModel;
    }

    public void setJobCreateBindingModel(JobCreateBindingModel jobCreateBindingModel) {
        this.jobCreateBindingModel = jobCreateBindingModel;
    }
}
