package app.by.epamlab.gsu.model.dao.impl;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.exceptions.UserDAOException;
import app.by.epamlab.gsu.model.dao.interfaces.UserDAO;
import app.by.epamlab.gsu.managers.ManagerDB;
import app.by.epamlab.gsu.model.beans.User;

import java.sql.*;

public class DataBaseUserRepository implements UserDAO {

    private static DataBaseUserRepository dataBaseUserRepository = new DataBaseUserRepository();

    private DataBaseUserRepository() {
    }

    public static DataBaseUserRepository getRepository() {
        return dataBaseUserRepository;
    }

    @Override
    public User getUser(String login, String password) throws UserDAOException {
        if (Constants.EMPTY.equals(login) || login == null) {
            throw new UserDAOException(Constants.ERROR_EMPTY_LOGIN);
        }
        else if (Constants.EMPTY.equals(password) || password == null) {
            throw new UserDAOException(Constants.ERROR_EMPTY_PASSWORD);
        }
        else {
            try (Connection connection = ManagerDB.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_GET_USER)) {
                preparedStatement.setString(1,password);
                preparedStatement.setString(2,login);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if(resultSet.next()) {
                        return new User(resultSet.getString(Constants.LOGIN),
                                resultSet.getString(Constants.PASSWORD));
                    }
                    else {
                        throw  new UserDAOException(Constants.ERROR_LOGIN_PASSW_INC);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void saveUser(String login, String password) throws UserDAOException {
        synchronized (DataBaseUserRepository.class) {

            if (Constants.EMPTY.equals(login) || login == null) {
                throw new UserDAOException(Constants.ERROR_EMPTY_LOGIN);
            }
            else if (Constants.EMPTY.equals(password) || password == null) {
                throw new UserDAOException(Constants.ERROR_EMPTY_PASSWORD);
            }
            else {
                try(Connection connection = ManagerDB.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_CHECK_USER)) {
                    preparedStatement.setString(1,login);
                    try(ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(resultSet.next()) {
                            throw  new UserDAOException(Constants.ERROR_LOGIN_EXISTS);
                        }
                        else {
                            try(PreparedStatement pstSaveUser = connection.prepareStatement(Constants.SQL_SAVE_USER)) {
                                pstSaveUser.setString(1,login);
                                pstSaveUser.setString(2,password);
                                pstSaveUser.executeUpdate();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    int getUserId(String login) {
        try(Connection connection = ManagerDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ID_USER)) {
            preparedStatement.setString(Constants.LOGIN_INDEX,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt(Constants.ID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

}
