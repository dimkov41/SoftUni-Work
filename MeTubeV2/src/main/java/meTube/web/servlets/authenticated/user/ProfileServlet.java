package meTube.web.servlets.authenticated.user;

import meTube.domain.models.view.TubeProfileViewModel;
import meTube.service.UserService;
import meTube.util.Constants;
import meTube.util.ModelMapper;
import meTube.util.ServletUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final String TUBE_PROFILE_JSP_PATH = "/jsps/authenticated/profile.jsp";

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploader = (String) req.getSession().getAttribute(Constants.USERNAME_FIELD_NAME);

        List<TubeProfileViewModel> tubeProfileViewModels =
                Arrays.stream(
                        this.modelMapper
                                .map(this.userService.getAllTubes(uploader), TubeProfileViewModel[].class))
                        .collect(Collectors.toList());

        req.getSession().setAttribute(Constants.TUBES_PROFILE_FIELD_NAME, tubeProfileViewModels);
        ServletUtils.sendForward(req.getServletContext(),req,resp,TUBE_PROFILE_JSP_PATH);

    }
}
