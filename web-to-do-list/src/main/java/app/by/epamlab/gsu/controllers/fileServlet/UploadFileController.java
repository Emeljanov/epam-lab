package app.by.epamlab.gsu.controllers.fileServlet;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.managers.FileManager;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UploadFileController extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        Task task = (Task) session.getAttribute(Constants.ATR_TASKATR);
        FileManager.uploadTaskFile(req,user,task);
        resp.sendRedirect(Constants.URL_MAIN);
    }
}
