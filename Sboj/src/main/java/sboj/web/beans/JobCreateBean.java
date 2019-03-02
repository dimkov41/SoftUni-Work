package sboj.web.beans;

import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.JobCreateBindingModel;
import sboj.domain.models.enums.Sector;
import sboj.domain.models.service.JobServiceModel;
import sboj.service.JobService;
import sboj.utils.Constants;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobCreateBean extends BaseBean {
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
            super.addMessage(FacesContext.getCurrentInstance(),Constants.INVALID_SECTOR_MESSAGE);
            return;
        }

        if(this.jobService.save(this.modelMapper.map(this.jobCreateBindingModel, JobServiceModel.class)).isPresent()){
            super.sendRedirect(FacesContext.getCurrentInstance(),Constants.HOME_PATH);
            return;
        }

        //if save fail, reload add page
        super.addMessage(FacesContext.getCurrentInstance(),Constants.TRY_AGAIN_MESSAGE);
        super.sendRedirect(FacesContext.getCurrentInstance(),Constants.ADD_JOB_PATH);
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
