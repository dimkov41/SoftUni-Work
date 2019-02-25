package sboj.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;

public final class BeanUtils {
    public static void sendRedirect(FacesContext facesContext, String path) throws IOException {
        facesContext.getExternalContext().redirect(path);
    }

    public static void addMessage(FacesContext facesContext,String message){
        facesContext.addMessage("message",new FacesMessage(message));
    }
}