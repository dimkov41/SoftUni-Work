package meTube.web.servlets.guest;

import meTube.util.Constants;
import meTube.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.HOME_URL)
public class HomeServlet extends HttpServlet {
    private final String HOME_JSP_PATH = "/jsps/guest/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.sendForward(getServletContext(),req,resp,HOME_JSP_PATH);
    }
}
