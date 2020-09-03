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

public class RecycleAllTask extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        ITaskDAO taskFactory = TasksFactory.getClassFromFactory();
        taskFactory.recycleAllTask(user);
        resp.sendRedirect(Constants.URL_MAIN);
    }
}
