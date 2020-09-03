package app.by.epamlab.gsu.model.dao.impl;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.exceptions.TaskDAOException;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.managers.ManagerDB;
import app.by.epamlab.gsu.model.beans.Sections;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;
import app.by.epamlab.gsu.utilities.TimeCalendar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseTasksFactory implements ITaskDAO {
    private static DataBaseTasksFactory dataBaseTasksFactory = new DataBaseTasksFactory();

    private DataBaseTasksFactory() {
    }

    public static DataBaseTasksFactory getTasksFactory() {
        return dataBaseTasksFactory;
    }

    @Override
    public List<Task> getTasks(User user, Sections section) {
        List<Task> taskList = new ArrayList<>();
        int id_user = DataBaseUserRepository.getRepository().getUserId(user.getLogin());

        switch (section) {
            case TODAY: {
                getTodayTask(id_user,taskList);
                break;
            }
            case TOMORROW: {
                getTomorrowTask(id_user,taskList);
                break;
            }
            case SOMEDAY: {
                getSomedayTask(id_user,taskList);
                break;
            }
            case FIXED: {
                getFixedTask(id_user,taskList);
                break;
            }
            case RECYCLE:{
                getRecycledTask(id_user,taskList);
                break;
            }
        }
        return taskList;
    }

    @Override
    public void removeTask(User user, int id) {
        try(Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_REMOVE_TASK)) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fixTask(User user, int id)  {
        try(Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_FIX_TASK)) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(User user, String description, Date date) throws TaskDAOException {
        if (Constants.EMPTY.equals(description) || description == null) {
            throw new TaskDAOException(Constants.ERROR_EMPTY_DISCR);
        } else if (date == null) {
            throw new TaskDAOException(Constants.ERROR_EMPTY_DATE);
        } else {
            try (Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_ADD_TASK)){
                int id_user = DataBaseUserRepository.getRepository().getUserId(user.getLogin());
                ps.setInt(1, id_user);
                ps.setString(2, description);
                ps.setDate(3, date);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void recycleTask(User user, int id) {
        try(Connection conn = ManagerDB.getConnection();PreparedStatement ps = conn.prepareStatement(Constants.SQL_RECYCLE_TASK)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recycleAllTask(User user) {
        try (Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_RECYCLE_ALL_TASK)){
            int id_user = DataBaseUserRepository.getRepository().getUserId(user.getLogin());
            ps.setInt(1,id_user);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeTask(User user, int id) {
        try (Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_RESUME_TASK)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task getTaskByID(User user, int id) {
        Task task;
        try (Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_GET_TASK_BY_ID)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String discr = rs.getString(Constants.TASK);
                    Date date = rs.getDate(Constants.DATE);
                    task = new Task(discr, date, id);
                    return task;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    private static void getTodayTask(int id_user, List<Task> taskList) {
        try(Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_TASK_TODAY)) {
            ps.setInt(1,id_user);
            ps.setDate(2, TimeCalendar.getTodayDate());
            try(ResultSet rs = ps.executeQuery()) {
                taskAddToList(rs,taskList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getTomorrowTask(int id_user, List<Task> taskList)  {
        try(Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_TASK_TOMORROW)) {
            ps.setInt(1,id_user);
            ps.setDate(2, TimeCalendar.getTomorrowDate());
            try(ResultSet rs = ps.executeQuery()) {
                taskAddToList(rs,taskList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getSomedayTask(int id_user, List<Task> taskList) {
        try(Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_TASKS_SOMEDAY)) {
            ps.setInt(1,id_user);
            ps.setDate(2, TimeCalendar.getTomorrowDate());
            try(ResultSet rs = ps.executeQuery()) {
                taskAddToList(rs,taskList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getFixedTask(int id_user, List<Task> taskList) {
        try(Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_TASKS_FIXED)) {
            ps.setInt(1,id_user);
            try(ResultSet rs = ps.executeQuery()) {
                taskAddToList(rs,taskList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getRecycledTask(int id_user, List<Task> taskList) {
        try(Connection conn = ManagerDB.getConnection(); PreparedStatement ps = conn.prepareStatement(Constants.SQL_TASKS_RECYCLED)) {
            ps.setInt(1,id_user);
            try(ResultSet rs = ps.executeQuery()) {
                taskAddToList(rs,taskList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void taskAddToList(ResultSet resultSet, List<Task> taskList) throws SQLException {
        while (resultSet.next()) {
            String taskName = resultSet.getString(Constants.TASK);
            Date dateTask = resultSet.getDate(Constants.DATE);
            int id = resultSet.getInt(Constants.ID);
            taskList.add(new Task(taskName, dateTask, id));
        }
    }
}
