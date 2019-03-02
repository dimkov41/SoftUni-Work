package exam.service;

import exam.domain.entity.User;
import exam.domain.models.service.UserLoginServiceModel;
import exam.domain.models.service.UserRegisterServiceModel;
import exam.repository.UserRepository;
import org.modelmapper.ModelMapper;

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
        return Optional.ofNullable(
                this.modelMapper
                        .map(this.userRepository
                                .save(this.modelMapper.map(userRegisterServiceModel, User.class)), UserRegisterServiceModel.class));
    }

    @Override
    public Optional<UserLoginServiceModel> findByUsername(String username) {
        return Optional.ofNullable(this.modelMapper.map(this.userRepository.findByUsername(username), UserLoginServiceModel.class));
    }
}
