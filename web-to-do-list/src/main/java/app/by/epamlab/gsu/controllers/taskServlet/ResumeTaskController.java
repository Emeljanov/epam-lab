package app.by.epamlab.gsu.controllers.taskServlet;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.model.beans.User;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.model.factories.TasksFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResumeTaskController extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(Constants.KEY_ID_TASK)== null) {
            forwardError(Constants.JUMP_ERROR,Constants.ERROR_NOT_CHECKED,req,resp);
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        ITaskDAO taskFactory = TasksFactory.getClassFromFactory();
        int id = Integer.parseInt(req.getParameter(Constants.KEY_ID_TASK));
        taskFactory.resumeTask(user,id);
        resp.sendRedirect(Constants.URL_MAIN);
    }
}
