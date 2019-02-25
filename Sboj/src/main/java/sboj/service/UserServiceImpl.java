package sboj.service;

import org.modelmapper.ModelMapper;
import sboj.domain.entity.User;
import sboj.domain.models.service.UserLoginServiceModel;
import sboj.domain.models.service.UserRegisterServiceModel;
import sboj.repository.UserRepository;

import javax.inject.Inject;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<UserRegisterServiceModel> save(UserRegisterServiceModel userRegisterServiceModel) {
        if(this.userRepository.save(this.modelMapper.map(userRegisterServiceModel, User.class)).isPresent()){
            return Optional.of(userRegisterServiceModel);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserLoginServiceModel> findByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);

        if(user.isPresent()){
            return Optional.of(this.modelMapper.map(user.get(),UserLoginServiceModel.class));
        }

        return Optional.empty();
    }
}
