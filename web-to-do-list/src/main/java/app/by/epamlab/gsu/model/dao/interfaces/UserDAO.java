package app.by.epamlab.gsu.model.dao.interfaces;

import app.by.epamlab.gsu.exceptions.UserDAOException;
import app.by.epamlab.gsu.model.beans.User;

public interface UserDAO {
    public User getUser(String name,String password) throws UserDAOException;
    public void saveUser(String login, String password) throws UserDAOException;
}
