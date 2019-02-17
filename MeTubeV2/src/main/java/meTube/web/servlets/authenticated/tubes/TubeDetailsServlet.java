package meTube.web.servlets.authenticated.tubes;

import meTube.domain.models.service.TubeDetailsServiceModel;
import meTube.domain.models.view.TubeDetailsViewModel;
import meTube.repository.TubeRepository;
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
import java.io.IOException;
import java.util.Optional;

@WebServlet(Constants.TUBE_DETAILS_URL + "/*")
public class TubeDetailsServlet extends HttpServlet {
    private final String TUBE_DETAILS_JSP_PATH = "/jsps/authenticated/details.jsp";


    private final ModelMapper modelMapper;
    private final TubeService tubeService;

    @Inject
    public TubeDetailsServlet(ModelMapper modelMapper, TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer buffer = req.getRequestURL();
        String tubeId = buffer.substring(buffer.lastIndexOf("/") + 1);

        Optional<TubeDetailsServiceModel> tubeDetailsServiceModel = this.tubeService.getTubeById(tubeId);

        tubeDetailsServiceModel
                .ifPresentOrElse((t) -> {
                            TubeDetailsViewModel tubeDetailsViewModel = this.modelMapper.map(t, TubeDetailsViewModel.class);

                            req.getSession().setAttribute(Constants.TUBES_DETAILS_FIELD_NAME, tubeDetailsViewModel);
                            ServletUtils.sendForward(getServletContext(), req, resp, TUBE_DETAILS_JSP_PATH);
                        },
                        () -> ServletUtils.sendRedirect(resp, Constants.PROFILE_URL)
                );
    }
}
