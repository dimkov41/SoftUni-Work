package meTube.service;

import meTube.domain.entities.Tube;
import meTube.domain.entities.User;
import meTube.domain.models.service.TubeDetailsServiceModel;
import meTube.domain.models.service.TubeHomeServiceModel;
import meTube.domain.models.service.TubeProfileServiceModel;
import meTube.domain.models.service.TubeUploadServiceModel;
import meTube.repository.TubeRepository;
import meTube.repository.UserRepository;
import meTube.util.ModelMapper;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    //{0} need to be replaced with video code
    private final String THUMBNAIL_YOUTUBE_LINK = "https://img.youtube.com/vi/{0}/mqdefault.jpg";

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

    @Override
    public List<TubeHomeServiceModel> getAll() {
        List<Tube> tubes = this.tubeRepository.findAll();

        List<TubeHomeServiceModel> tubeHomeServiceModels= new ArrayList<>();
        for (Tube tube : tubes) {
            TubeHomeServiceModel t = this.modelMapper.map(tube,TubeHomeServiceModel.class);
            String code = tube.getYoutubeLink().substring(tube.getYoutubeLink().lastIndexOf("=")  + 1);
            t.setYoutubeThumbnail(THUMBNAIL_YOUTUBE_LINK.replace("{0}",code));
            tubeHomeServiceModels.add(t);
        }

        return tubeHomeServiceModels;
    }


}
