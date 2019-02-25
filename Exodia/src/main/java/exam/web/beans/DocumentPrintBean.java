package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import exam.service.DocumentService;
import exam.utils.BeanUtils;

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
                .getParameter("id");

        if(this.documentService.deleteById(id)){
            BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/home");
            return;
        }

        BeanUtils.addMessage(FacesContext.getCurrentInstance(),"Error! Please try again.");
    }
}
