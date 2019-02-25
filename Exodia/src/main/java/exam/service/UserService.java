package exam.service;

import exam.domain.models.service.UserLoginServiceModel;
import exam.domain.models.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserService {
    Optional<UserRegisterServiceModel> save(UserRegisterServiceModel userRegisterServiceModel);

    Optional<UserLoginServiceModel> findByUsername(String username);
}
