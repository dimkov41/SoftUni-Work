package sboj.web.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;

abstract class BaseBean {
    void sendRedirect(FacesContext facesContext, String path) throws IOException {
        facesContext.getExternalContext().redirect(path);
    }

    void addMessage(FacesContext facesContext, String message){
        facesContext.addMessage("message",new FacesMessage(message));
    }
}
