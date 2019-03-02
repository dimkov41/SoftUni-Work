package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.UserLoginBindingModel;
import sboj.domain.models.service.UserLoginServiceModel;
import sboj.service.UserService;
import sboj.utils.Constants;

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
public class UserLoginBean extends BaseBean {
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
            super.addMessage(FacesContext.getCurrentInstance(),Constants.USER_NOT_EXIST_MESSAGE);
            return;
        }

        if (!this.userLoginBindingModel.getPassword().equals(user.get().getPassword())){
            super.addMessage(FacesContext.getCurrentInstance(),Constants.INCORRECT_PASSWORD_MESSAGE);
            return;
        }

        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("userId",user.get().getId());
        sessionMap.put("username",user.get().getUsername());

        super.sendRedirect(FacesContext.getCurrentInstance(),Constants.HOME_PATH);
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }
}
