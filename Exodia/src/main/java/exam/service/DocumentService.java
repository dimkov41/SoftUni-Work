package exam.service;

import exam.domain.models.service.DocumentServiceModel;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Optional<DocumentServiceModel> save(DocumentServiceModel documentServiceModel);

    Optional<DocumentServiceModel> findById(String id);

    boolean deleteById(String id);

    List<DocumentServiceModel> getAll();
}
