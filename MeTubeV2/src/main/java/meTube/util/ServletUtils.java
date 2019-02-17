package meTube.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ServletUtils {
    public static void sendRedirect(HttpServletResponse resp, String path){
        try {
            resp.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void sendForward(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, String path){
        try {
            servletContext.getRequestDispatcher(path).forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
