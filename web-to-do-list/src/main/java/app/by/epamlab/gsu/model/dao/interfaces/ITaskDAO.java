package app.by.epamlab.gsu.model.dao.interfaces;

import app.by.epamlab.gsu.exceptions.TaskDAOException;
import app.by.epamlab.gsu.model.beans.Sections;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;

import java.sql.Date;
import java.util.List;

public interface ITaskDAO {
    /**
     * Get user tasks
     *
     * @param user     current user
     * @param sections task day
     * @return list with tasks for  section
     */
    List<Task> getTasks(User user, Sections sections);

    /**
     * @param user
     * @param id
     */
    void removeTask(User user, int id);
    /**
     * @param user
     * @param id
     * @throws TaskDAOException
     */
    void fixTask(User user, int id) throws TaskDAOException;

    /**
     * @param user
     * @param description
     * @param date
     */
    void addTask(User user, String description, Date date) throws TaskDAOException;

    void recycleTask(User user,int id);

    void recycleAllTask(User user);

    void resumeTask(User user, int id);

    Task getTaskByID(User user, int id);
}

