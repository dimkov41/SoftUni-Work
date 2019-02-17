package meTube.web.servlets.authenticated.user;

import meTube.util.Constants;
import meTube.util.ServletUtils;
import meTube.util.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private final ValidationUtil validationUtil;

    @Inject
    public LogoutServlet(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(this.validationUtil.isAuthenticated(session)){
            session.removeAttribute(Constants.USERNAME_FIELD_NAME);
        }

        ServletUtils.sendRedirect(resp, Constants.HOME_URL);
    }
}
