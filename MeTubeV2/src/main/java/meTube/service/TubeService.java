package meTube.service;

import meTube.domain.models.service.TubeDetailsServiceModel;
import meTube.domain.models.service.TubeHomeServiceModel;
import meTube.domain.models.service.TubeUploadServiceModel;

import java.util.List;
import java.util.Optional;

public interface TubeService {
    boolean upload(TubeUploadServiceModel tubeUploadServiceModel);

    Optional<TubeDetailsServiceModel> getTubeById(String id);

    List<TubeHomeServiceModel> getAll();
}
