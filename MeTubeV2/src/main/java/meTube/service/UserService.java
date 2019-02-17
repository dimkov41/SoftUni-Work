package meTube.service;

import meTube.domain.models.service.TubeProfileServiceModel;
import meTube.domain.models.service.UserLoginServiceModel;
import meTube.domain.models.service.UserRegisterServiceModel;
import meTube.domain.models.view.UserViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean register(UserRegisterServiceModel userRegisterServiceModel);
    Optional<UserViewModel> login(UserLoginServiceModel userLoginServiceModel);
    List<TubeProfileServiceModel> getAllTubes(String name);

}
