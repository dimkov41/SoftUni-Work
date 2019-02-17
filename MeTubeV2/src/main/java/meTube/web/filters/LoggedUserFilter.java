package meTube.web.filters;

import meTube.util.Constants;
import meTube.util.ServletUtils;
import meTube.util.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        Constants.REGISTER_URL,
        Constants.LOGIN_URL,
        Constants.HOME_URL
})
public class LoggedUserFilter implements Filter {
    private final ValidationUtil validationUtil;

    @Inject
    public LoggedUserFilter(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if(validationUtil.isAuthenticated(req.getSession())){
            ServletUtils.sendRedirect(resp,Constants.HOME_LOGGED_IN_URL);
            return;
        }

        filterChain.doFilter(req,resp);
    }

}
