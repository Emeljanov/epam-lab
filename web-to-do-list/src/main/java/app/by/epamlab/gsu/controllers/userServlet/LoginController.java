package app.by.epamlab.gsu.controllers.userServlet;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.exceptions.UserDAOException;
import app.by.epamlab.gsu.managers.FileManager;
import app.by.epamlab.gsu.model.beans.User;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import app.by.epamlab.gsu.model.dao.interfaces.UserDAO;
import app.by.epamlab.gsu.model.factories.TasksFactory;
import app.by.epamlab.gsu.model.factories.UserFactory;


public class LoginController extends BaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        UserFactory.setGlobals(Constants.DB_DAO);
        TasksFactory.setGlobals(Constants.DB_DAO);
        FileManager.setGlobals(Constants.DB_DAO);
//        UserFactory.setGlobals(Constants.RAM_DAO);
//        TasksFactory.setGlobals(Constants.RAM_DAO);
//        FileManager.setGlobals(Constants.RAM_DAO);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.JUMP_LOGIN,req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getMain(req,resp);
    }
    private void getMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);

        try {
            UserDAO UserRepository = UserFactory.getClassFromFactory();
            User user = UserRepository.getUser(login,password);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.ATR_USER, user);
            forward(Constants.URL_MAIN,request,response);
        }
        catch (UserDAOException e) {
            forwardError(Constants.JUMP_ERROR,e.getMessage(),request,response);
        }
    }
}
