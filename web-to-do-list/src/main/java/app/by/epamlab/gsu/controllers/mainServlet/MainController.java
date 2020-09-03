package app.by.epamlab.gsu.controllers.mainServlet;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.model.beans.Sections;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;
import app.by.epamlab.gsu.model.factories.TasksFactory;
import app.by.epamlab.gsu.utilities.TimeCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class MainController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        ITaskDAO taskFactory = TasksFactory.getClassFromFactory();
        String sectionFromReq = req.getParameter(Constants.KEY_SECTION);
        Sections sectionFromSession = (Sections) session.getAttribute(Constants.KEY_SECTION);
        Sections section;
        if (sectionFromReq == null && sectionFromSession == null) {
            section = Sections.TODAY;
        } else {
            section = sectionFromReq != null ? Sections.valueOf(sectionFromReq) : sectionFromSession;
        }
        List<Task> taskList = taskFactory.getTasks(user, section);
        session.setAttribute(Constants.KEY_TASKLIST, taskList);
        session.setAttribute(Constants.KEY_SECTION, section);

        Date today = TimeCalendar.getTodayDate();
        Date tomorrow = TimeCalendar.getTomorrowDate();

        session.setAttribute(Constants.ATR_TODAYDAY, today);
        session.setAttribute(Constants.ATR_TOMORROWDAY, tomorrow);
        resp.sendRedirect(Constants.JUMP_MAIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }
}
