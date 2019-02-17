package meTube.web.servlets.authenticated.tubes;

import meTube.domain.models.binding.TubeUploadBindingModel;
import meTube.domain.models.service.TubeProfileServiceModel;
import meTube.domain.models.service.TubeUploadServiceModel;
import meTube.service.TubeService;
import meTube.service.UserService;
import meTube.util.Constants;
import meTube.util.ModelMapper;
import meTube.util.ServletUtils;
import meTube.util.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class TubeUploadServet extends HttpServlet {
    private final String TUBE_UPLOAD_JSP_PATH = "/jsps/authenticated/uploadTube.jsp";

    private final ModelMapper modelMapper;
    private final TubeService tubeService;
    private final UserService userService;
    private final ValidationUtil validationUtil;


    @Inject
    public TubeUploadServet(ModelMapper modelMapper, TubeService tubeService, UserService userService, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
        this.userService = userService;
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.sendForward(req.getServletContext(), req, resp, TUBE_UPLOAD_JSP_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(Constants.TUBE_TITLE_FIELD_NAME);
        String author = req.getParameter(Constants.TUBE_AUTHOR_FIELD_NAME);
        String youtubeLink = req.getParameter(Constants.TUBE_YOUTUBE_LINK_FIELD_NAME);
        String description = req.getParameter(Constants.TUBE_DESCRIPTION_FIELD_NAME);
        String uploader = (String) req.getSession().getAttribute(Constants.USERNAME_FIELD_NAME);

        TubeUploadBindingModel tubeUploadBindingModel = new TubeUploadBindingModel(title, author, youtubeLink, description,uploader);

        if (this.validationUtil.isValid(tubeUploadBindingModel)) {
            if (this.tubeService.upload(this.modelMapper.map(tubeUploadBindingModel, TubeUploadServiceModel.class))) {
                ServletUtils.sendRedirect(resp, Constants.HOME_LOGGED_IN_URL);
                return;
            }
        }

        ServletUtils.sendRedirect(resp, Constants.TUBE_UPLOAD_URL);

    }
}
