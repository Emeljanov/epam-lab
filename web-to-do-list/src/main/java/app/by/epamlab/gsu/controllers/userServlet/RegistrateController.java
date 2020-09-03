package app.by.epamlab.gsu.controllers.userServlet;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.controllers.BaseController;
import app.by.epamlab.gsu.exceptions.UserDAOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import app.by.epamlab.gsu.model.dao.interfaces.UserDAO;
import app.by.epamlab.gsu.model.factories.UserFactory;


public class RegistrateController extends BaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        UserFactory.setGlobals(Constants.DB_DAO);
//        UserFactory.setGlobals(Constants.RAM_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.JUMP_REGISTRATE,req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Constants.LOGIN);
        String password = req.getParameter(Constants.PASSWORD);
        try {
            UserDAO UserRepository = UserFactory.getClassFromFactory();
            UserRepository.saveUser(login, password);
            resp.sendRedirect(Constants.JUMP_INDEX);        }
        catch (UserDAOException e) {
            forwardError(Constants.JUMP_ERROR, e.getMessage(), req, resp);
        }
    }
}
