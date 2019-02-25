package exam.service;

import exam.domain.entity.Document;
import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;
    private ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<DocumentServiceModel> save(DocumentServiceModel documentServiceModel) {
        Optional<Document> document = this.documentRepository.save(this.modelMapper.map(documentServiceModel,
                Document.class));

        if(document.isPresent()){
            documentServiceModel.setId(document.get().getId());
            return Optional.of(documentServiceModel);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DocumentServiceModel> findById(String id) {
        Optional<Document> document = this.documentRepository.findById(id);

        if(document.isPresent()){
            return Optional.of(this.modelMapper.map(document.get(),DocumentServiceModel.class));
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(String id) {
        return this.documentRepository.deleteById(id);
    }

    @Override
    public List<DocumentServiceModel> getAll() {
        return Arrays
                .stream(this.modelMapper.map(this.documentRepository.getAll(), DocumentServiceModel[].class))
                .collect(Collectors.toList());
    }
}
