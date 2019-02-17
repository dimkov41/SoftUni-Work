package meTube.web.servlets.guest;

import meTube.domain.models.binding.UserRegisterBindingModel;
import meTube.domain.models.service.UserRegisterServiceModel;
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
import java.io.IOException;

@WebServlet(Constants.REGISTER_URL)
public class RegisterServlet extends HttpServlet {
    private final String REGISTER_JSP_PATH = "/jsps/guest/register.jsp";

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public RegisterServlet(UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.sendForward(getServletContext(),req,resp,REGISTER_JSP_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Constants.USERNAME_FIELD_NAME);
        String password = req.getParameter(Constants.PASSWORD_FIELD_NAME);
        String confirmPassword = req.getParameter(Constants.CONFIRM_PASSWORD_FIELD_NAME);
        String email = req.getParameter(Constants.EMAIL_FIELD_NAME);
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel(username,password,confirmPassword,email);

        if(this.validationUtil.isValid(userRegisterBindingModel)){
            if (this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class))) {
                //if user not exists
                ServletUtils.sendRedirect(resp,Constants.LOGIN_URL);
                return;
            }
        }

        //invalid
        ServletUtils.sendRedirect(resp,Constants.REGISTER_URL);
    }
}
