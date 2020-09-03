package app.by.epamlab.gsu.controllers.fileServlet;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.managers.FileManager;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.model.factories.TasksFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FileController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        int idTask = Integer.parseInt(req.getParameter(Constants.ATR_ID_TASK));
        ITaskDAO taskDAO = TasksFactory.getClassFromFactory();
        Task task = taskDAO.getTaskByID(user,idTask);
        session.setAttribute(Constants.ATR_TASKATR,task);
        String name = FileManager.getFileName(user,task);
        session.setAttribute(Constants.ATR_FILENAME,name);
        forward(Constants.URL_FILEPAGE,req,resp);

    }
}
