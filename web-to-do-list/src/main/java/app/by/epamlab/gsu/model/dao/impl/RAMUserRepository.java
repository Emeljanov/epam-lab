package app.by.epamlab.gsu.model.dao.impl;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.exceptions.UserDAOException;
import app.by.epamlab.gsu.model.dao.interfaces.UserDAO;
import app.by.epamlab.gsu.managers.ManagerRAM;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RAMUserRepository implements UserDAO {

    public static RAMUserRepository getRepository() {
        return rs;
    }

    private static RAMUserRepository rs = new RAMUserRepository();

    private RAMUserRepository() {
    }

    @Override
    public User getUser(String name, String password) throws UserDAOException {
        Map<User, List<Task>> RAMStorage = ManagerRAM.getStorageRAM();

        if (Constants.EMPTY.equals(name) || name == null) {
            throw new UserDAOException(Constants.ERROR_EMPTY_LOGIN);
        }
        else if (Constants.EMPTY.equals(password) || password == null) {
            throw new UserDAOException(Constants.ERROR_EMPTY_PASSWORD);
        }
        else {
            for (User userFromStorage : RAMStorage.keySet()) {
                if (userFromStorage.getLogin().equals(name) && userFromStorage.getPassword().equals(password)) {
                    return userFromStorage;
                }
            }
            throw new UserDAOException(Constants.ERROR_LOGIN_PASSW_INC);
        }
    }

    @Override
    public synchronized void saveUser(String login, String password) throws UserDAOException {
            if (Constants.EMPTY.equals(login) || login == null) {
                throw new UserDAOException(Constants.ERROR_EMPTY_LOGIN);
            }
            else if (Constants.EMPTY.equals(password) || password == null) {
                throw new UserDAOException(Constants.ERROR_EMPTY_PASSWORD);
            }
            else {
                Map<User, List<Task>> RAMStorage = ManagerRAM.getStorageRAM();
                for (User userFromStorage : RAMStorage.keySet()) {
                    if (userFromStorage.getLogin().equals(login)) {
                        throw new UserDAOException(Constants.ERROR_LOGIN_EXISTS);
                    }
                }
                User user = new User(login, password);
                List<Task> taskList = new ArrayList<>();
                RAMStorage.put(user, taskList);
            }
    }
}
