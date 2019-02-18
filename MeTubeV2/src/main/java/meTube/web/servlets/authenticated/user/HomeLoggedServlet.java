package meTube.web.servlets.authenticated.user;

import meTube.domain.models.view.TubeHomeViewModel;
import meTube.service.TubeService;
import meTube.util.Constants;
import meTube.util.ModelMapper;
import meTube.util.ServletUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeLoggedServlet extends HttpServlet {
    private final String HOME_JSP_PATH = "/jsps/authenticated/home.jsp";

    private final ModelMapper modelMapper;
    private final TubeService tubeService;

    @Inject
    public HomeLoggedServlet(ModelMapper modelMapper, TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeHomeViewModel> tubeHomeViewModelList =
                Arrays.stream(
                        this.modelMapper.map(this.tubeService.getAll(), TubeHomeViewModel[].class))
                        .collect(Collectors.toList());

        req.getSession().setAttribute(Constants.TUBES_HOME_FIELD_NAME,tubeHomeViewModelList);
        ServletUtils.sendForward(getServletContext(),req,resp,HOME_JSP_PATH);
    }
}
