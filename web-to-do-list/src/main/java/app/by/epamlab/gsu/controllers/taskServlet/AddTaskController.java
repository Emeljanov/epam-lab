package app.by.epamlab.gsu.controllers.taskServlet;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.exceptions.TaskDAOException;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.model.beans.User;
import app.by.epamlab.gsu.model.factories.TasksFactory;
import app.by.epamlab.gsu.utilities.TimeCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class AddTaskController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.URL_ADD_TASK,req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        String taskDiscrp = req.getParameter(Constants.ATR_DISCRIPTION);
        String dateFromJSP = req.getParameter(Constants.ATR_TASK_ADD_DATE);
        try {
            Date date = TimeCalendar.getDate(dateFromJSP);
            ITaskDAO taskFactory = TasksFactory.getClassFromFactory();
            taskFactory.addTask(user,taskDiscrp,date);
            resp.sendRedirect(Constants.URL_MAIN);
        }
        catch (TaskDAOException e) {
            forwardError(Constants.JUMP_ERROR,e.getMessage(),req,resp);
        }
    }
}
