package sboj.service;

import sboj.domain.models.service.UserLoginServiceModel;
import sboj.domain.models.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserService {
    Optional<UserRegisterServiceModel> save(UserRegisterServiceModel userRegisterServiceModel);

    Optional<UserLoginServiceModel> findByUsername(String username);
}
