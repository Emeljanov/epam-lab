package app.by.epamlab.gsu.managers;
import app.by.epamlab.gsu.constants.Constants;
import java.sql.*;

public class ManagerDB {

    public static Connection getConnection() {
        try {
            Class.forName(Constants.DB_CLASSNAME);
            return DriverManager.getConnection(Constants.DB_URL,Constants.DB_USER, Constants.DB_PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(Constants.ERROR_DB);
    }

}
