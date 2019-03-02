package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import exam.service.DocumentService;
import exam.utils.BeanUtils;
import exam.utils.Constants;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Named
@RequestScoped
public class DocumentPrintBean {
    private DocumentService documentService;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void delete() throws IOException {
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getParameter(Constants.ID_PARAMETHER_KEY);

        if(this.documentService.deleteById(id)){
            BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),Constants.HOME_PATH);
            return;
        }

        BeanUtils.addMessage(FacesContext.getCurrentInstance(),Constants.TRY_AGAIN_MESSAGE);
    }
}
