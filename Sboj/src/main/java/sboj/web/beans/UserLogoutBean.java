package sboj.web.beans;

import sboj.utils.Constants;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserLogoutBean extends BaseBean{
    public void logout() throws IOException {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        super.sendRedirect(FacesContext.getCurrentInstance(),Constants.INDEX_PATH);
    }
}
