package app.by.epamlab.gsu.model.factories;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.model.dao.impl.DataBaseTasksFactory;
import app.by.epamlab.gsu.model.dao.impl.RAMTasksFactory;

/**
 * Factory for tasks
 */
public class TasksFactory {
    private static ITaskDAO iTaskDAO;

    public static void setGlobals(String strDAO) {
        if (strDAO.equals(Constants.DB_DAO)) {
            iTaskDAO = DataBaseTasksFactory.getTasksFactory();
        }
        else if (strDAO.equals(Constants.RAM_DAO)) {
            iTaskDAO = RAMTasksFactory.getTaskFactory();
        }
    }
    public static ITaskDAO getClassFromFactory() {
        return iTaskDAO;
    }
}
