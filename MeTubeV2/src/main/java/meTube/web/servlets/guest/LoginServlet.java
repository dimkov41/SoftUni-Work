package meTube.web.servlets.guest;

import meTube.domain.models.binding.UserLoginBindingModel;
import meTube.domain.models.service.UserLoginServiceModel;
import meTube.domain.models.view.UserViewModel;
import meTube.service.UserService;
import meTube.util.Constants;
import meTube.util.ModelMapper;
import meTube.util.ValidationUtil;
import meTube.util.ServletUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(Constants.LOGIN_URL)
public class LoginServlet extends HttpServlet {
    private final String LOGIN_JSP_PATH = "/jsps/guest/login.jsp";

    private UserService userService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    @Inject
    public LoginServlet(UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.sendForward(getServletContext(),req,resp,LOGIN_JSP_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Constants.USERNAME_FIELD_NAME);
        String password = req.getParameter(Constants.PASSWORD_FIELD_NAME);
        UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel();
        userLoginBindingModel.setUsername(username);
        userLoginBindingModel.setPassword(password);

        if (this.validationUtil.isValid(userLoginBindingModel)) {
            this.userService.login(this.modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class))
                    .ifPresentOrElse(
                            (UserViewModel userViewModel) -> {
                                HttpSession session = req.getSession();
                                session.setAttribute(Constants.USERNAME_FIELD_NAME, userViewModel.getName());
                                session.setAttribute(Constants.EMAIL_FIELD_NAME, userViewModel.getEmail());

                                ServletUtils.sendRedirect(resp, Constants.HOME_LOGGED_IN_URL);
                            },
                            () -> {
                                ServletUtils.sendRedirect(resp, Constants.LOGIN_URL);
                            });
        }
    }
}
