package meTube.web.servlets.authenticated.user;

import meTube.util.Constants;
import meTube.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")
public class HomeLoggedServlet extends HttpServlet {
    private final String HOME_JSP_PATH = "/jsps/authenticated/home.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.sendForward(getServletContext(),req,resp,HOME_JSP_PATH);
    }
}
