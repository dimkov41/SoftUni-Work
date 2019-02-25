package sboj.web.beans;

import sboj.utils.BeanUtils;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserLogoutBean {
    public void logout() throws IOException {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        BeanUtils.sendRedirect(FacesContext.getCurrentInstance(),"/");
    }
}
