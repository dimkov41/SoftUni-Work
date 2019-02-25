package exam.web.beans;

import exam.domain.models.binding.DocumentCreateBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;
import exam.utils.BeanUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Optional;

@Named
@RequestScoped
public class DocumentCreateBean {
    private ModelMapper modelMapper;
    private DocumentService documentService;

    private DocumentCreateBindingModel documentCreateBindingModel;

    public DocumentCreateBean() {
    }

    @Inject
    public DocumentCreateBean(ModelMapper modelMapper, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @PostConstruct
    public void init(){
        this.documentCreateBindingModel = new DocumentCreateBindingModel();
    }

    public void create() throws IOException {
        Optional<DocumentServiceModel> documentCreateServiceModel =
                this.documentService.save(this.modelMapper.map(documentCreateBindingModel,
                DocumentServiceModel.class));

        if(documentCreateServiceModel.isPresent()){
            //successfully created
            BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),
                    "/details/" + documentCreateServiceModel.get().getId());
            return;
        }

        BeanUtils.addMessage(FacesContext.getCurrentInstance(),"Error. Please try again");
    }

    public DocumentCreateBindingModel getDocumentCreateBindingModel() {
        return documentCreateBindingModel;
    }

    public void setDocumentCreateBindingModel(DocumentCreateBindingModel documentCreateBindingModel) {
        this.documentCreateBindingModel = documentCreateBindingModel;
    }
}
