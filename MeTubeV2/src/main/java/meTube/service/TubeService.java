package meTube.service;

import meTube.domain.models.service.TubeDetailsServiceModel;
import meTube.domain.models.service.TubeUploadServiceModel;

import java.util.Optional;

public interface TubeService {
    boolean upload(TubeUploadServiceModel tubeUploadServiceModel);
    Optional<TubeDetailsServiceModel> getTubeById(String id);
}
