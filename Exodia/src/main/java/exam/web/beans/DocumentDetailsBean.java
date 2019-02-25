package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentDetailsViewModel;
import exam.service.DocumentService;
import exam.utils.BeanUtils;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.Bean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Optional;

@Named
@RequestScoped
public class DocumentDetailsBean {
    private ModelMapper modelMaper;
    private DocumentService documentService;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(ModelMapper modelMaper, DocumentService documentService) {
        this.modelMaper = modelMaper;
        this.documentService = documentService;
    }

    public DocumentDetailsViewModel getDetails() throws IOException {
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");

        Optional<DocumentServiceModel> documentServiceModel = this.documentService.findById(id);

        if(documentServiceModel.isPresent()){
            //if exist
            return this.modelMaper.map(documentServiceModel.get(),
                    DocumentDetailsViewModel.class);
        }

        //if document doesn't exist
        BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/home");
        return new DocumentDetailsViewModel();
    }
}
