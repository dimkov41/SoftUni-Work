package sboj.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/home",
        "/jobs/add",
        "/jobs/details",
        "/jobs/delete/*"
})
public class GuestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userId = (String) ((HttpServletRequest)servletRequest).getSession().getAttribute("userId");

        if(userId == null){
            ((HttpServletResponse) servletResponse).sendRedirect("/");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
