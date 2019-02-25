package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.UserRegisterBindingModel;
import sboj.domain.models.service.UserRegisterServiceModel;
import sboj.service.UserService;
import sboj.utils.BeanUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel userRegisterBindingModel;

    private ModelMapper modelMapper;
    private UserService userService;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        //if passwords does not match
        if(!this.userRegisterBindingModel.getPassword()
                .equals(this.userRegisterBindingModel.getConfirmPassword())) {
            BeanUtils.addMessage(FacesContext.getCurrentInstance(),"Passwords does not match");
            return;
        }

        if (this.userService.save(
                this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class)
        ).isPresent()) {
            BeanUtils.sendRedirect(FacesContext.getCurrentInstance(), "/login");
            return;
        }

        BeanUtils.addMessage(FacesContext.getCurrentInstance(),"Username already exists.");
    }

}
