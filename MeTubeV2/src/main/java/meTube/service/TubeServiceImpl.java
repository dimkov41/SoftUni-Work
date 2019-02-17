package meTube.service;

import meTube.domain.entities.Tube;
import meTube.domain.entities.User;
import meTube.domain.models.service.TubeDetailsServiceModel;
import meTube.domain.models.service.TubeProfileServiceModel;
import meTube.domain.models.service.TubeUploadServiceModel;
import meTube.repository.TubeRepository;
import meTube.repository.UserRepository;
import meTube.util.ModelMapper;

import javax.inject.Inject;
import java.util.Optional;

public class TubeServiceImpl implements TubeService {
    private final ModelMapper modelMapper;
    private final TubeRepository tubeRepository;
    private final UserRepository userRepository;

    @Inject
    public TubeServiceImpl(ModelMapper modelMapper, TubeRepository tubeRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.tubeRepository = tubeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean upload(TubeUploadServiceModel tubeUploadServiceModel) {
        Optional<User> user = this.userRepository.findByName(tubeUploadServiceModel.getUploader());
        if (user.isPresent()) {
            Tube tube = this.modelMapper.map(tubeUploadServiceModel, Tube.class);
            tube.setUploader(user.get());
            return this.tubeRepository.save(tube);
        }

        return false;
    }

    @Override
    public Optional<TubeDetailsServiceModel> getTubeById(String id) {
            Optional<Tube> tube = this.tubeRepository.findById(id);
        return tube.map(t -> this.modelMapper.map(t, TubeDetailsServiceModel.class));
    }


}
