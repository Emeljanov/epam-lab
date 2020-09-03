package app.by.epamlab.gsu.controllers.taskServlet;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.exceptions.TaskDAOException;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;
import app.by.epamlab.gsu.model.factories.TasksFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FixTaskController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(Constants.ATR_ID_TASK) == null) {
            forwardError(Constants.JUMP_ERROR, Constants.ERROR_NOT_CHECKED,req,resp);
        }
            int idtask = Integer.parseInt(req.getParameter(Constants.ATR_ID_TASK));
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(Constants.ATR_USER);
            ITaskDAO taskFactory = TasksFactory.getClassFromFactory();
            Task task =  taskFactory.getTaskByID(user,idtask);
            session.setAttribute(Constants.ATR_TASK_BY_ID,task);
            forward(Constants.URL_FIXTASK,req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        int idtask = Integer.parseInt(req.getParameter(Constants.ATR_ID_TASK));
        try {
            ITaskDAO taskFactory = TasksFactory.getClassFromFactory();
            taskFactory.fixTask(user,idtask);
            resp.sendRedirect(Constants.URL_MAIN);

        }catch (TaskDAOException e) {
            forwardError(Constants.JUMP_ERROR,e.getMessage(),req,resp);
        }
    }

}
