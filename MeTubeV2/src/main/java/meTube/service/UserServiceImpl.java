package meTube.service;

import meTube.domain.entities.User;
import meTube.domain.models.service.TubeProfileServiceModel;
import meTube.domain.models.service.UserLoginServiceModel;
import meTube.domain.models.service.UserRegisterServiceModel;
import meTube.domain.models.view.UserViewModel;
import meTube.repository.UserRepository;
import meTube.util.ModelMapper;
import meTube.util.PasswordHasher;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordHasher passwordHasher;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public boolean register(UserRegisterServiceModel userRegisterServiceModel) {
        String hashedPasswod = passwordHasher.hash(userRegisterServiceModel.getPassword().getBytes());
        User user = this.modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(hashedPasswod);
        return this.userRepository.save(user);
    }

    @Override
    public Optional<UserViewModel> login(UserLoginServiceModel userLoginServiceModel) {
        Optional<User> user = this.userRepository
                .findByName(userLoginServiceModel.getUsername());

        if (user.isPresent()) {
            if (passwordHasher.matches(user.get().getPassword(), userLoginServiceModel.getPassword().getBytes())) {
                return Optional.of(new UserViewModel(user.get().getUsername(),user.get().getEmail()));
            }
        }

        return Optional.empty();
    }

    @Override
    public List<TubeProfileServiceModel> getAllTubes(String username) {
        return Arrays.stream(this.modelMapper.map(this.userRepository.getAllTubes(username), TubeProfileServiceModel[].class)).collect(Collectors.toList());
    }
}
