package app.by.epamlab.gsu.model.factories;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.model.dao.interfaces.UserDAO;
import app.by.epamlab.gsu.model.dao.impl.DataBaseUserRepository;
import app.by.epamlab.gsu.model.dao.impl.RAMUserRepository;

public class UserFactory {
    private static UserDAO userDAO;

    public static void setGlobals(String strDAO) {
        if (strDAO.equals(Constants.DB_DAO)) {
            userDAO = DataBaseUserRepository.getRepository();
        }
        else if (strDAO.equals(Constants.RAM_DAO)) {
            userDAO = RAMUserRepository.getRepository();
        }
    }

    public static UserDAO getClassFromFactory() {
        return userDAO;
    }

}
