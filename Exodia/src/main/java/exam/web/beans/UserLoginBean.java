package exam.web.beans;

import exam.utils.Constants;
import org.modelmapper.ModelMapper;
import exam.domain.models.binding.UserLoginBindingModel;
import exam.domain.models.service.UserLoginServiceModel;
import exam.service.UserService;
import exam.utils.BeanUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Named
@RequestScoped
public class
UserLoginBean {
    private UserLoginBindingModel userLoginBindingModel;

    private ModelMapper modelMapper;
    private UserService userService;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public void login() throws IOException {
        Optional<UserLoginServiceModel> user =
                this.userService.findByUsername(this.userLoginBindingModel.getUsername());

        if(!user.isPresent()){
            BeanUtils.addMessage(FacesContext.getCurrentInstance(),Constants.USER_NOT_EXIST_MESSAGE);
            return;
        }

        if (!this.userLoginBindingModel.getPassword().equals(user.get().getPassword())){
            BeanUtils.addMessage(FacesContext.getCurrentInstance(),Constants.INCORRECT_PASSWORD_MESSAGE);
            return;
        }

        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("userId",user.get().getId());
        sessionMap.put("username",user.get().getUsername());

        BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),Constants.HOME_PATH);
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }
}
