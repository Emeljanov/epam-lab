package app.by.epamlab.gsu.managers;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.exceptions.TaskDAOException;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;
import app.by.epamlab.gsu.utilities.TimeCalendar;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerRAM {
    private static Map<User, List<Task>> storageRAM = new HashMap<>();
    private static int idtask = 1;
    static {
        try {
            User anton = new User("Anton", "4321");
            List<Task> taskForAnton = new ArrayList<>();
            storageRAM.put(anton, taskForAnton);
            addTaskToUser(anton, "Go to pool", TimeCalendar.getTodayDate());
            addTaskToUser(anton, "to to bike", TimeCalendar.getTomorrowDate());
            addTaskToUser(anton, "Go for a work", TimeCalendar.getDate("2019-10-11"));
            addTaskToUser(anton, "Read a book", TimeCalendar.getDate("2019-11-25"));
            addTaskToUser(anton, "Sleep", TimeCalendar.getDate("2019-12-12"));
            addTaskToUser(anton,"task 1", TimeCalendar.getDate("2019-08-11"));
            addTaskToUser(anton,"task 2", TimeCalendar.getDate("2019-10-19"));
            addTaskToUser(anton,"task 3", TimeCalendar.getDate("2019-05-27"));
            addTaskToUser(anton,"task 4", TimeCalendar.getDate("2019-11-25"));
            addTaskToUser(anton,"task 5", TimeCalendar.getDate("2019-09-29"));
            addTaskToUser(anton,"task 6", TimeCalendar.getDate("2019-11-19"));
            addTaskToUser(anton,"task 7", TimeCalendar.getDate("2019-05-28"));
            addTaskToUser(anton,"task 8", TimeCalendar.getDate("2019-11-2"));
            addTaskToUser(anton,"task 9", TimeCalendar.getDate("2019-09-28"));
            addTaskToUser(anton,"task 10", TimeCalendar.getDate("2019-09-15"));
            addTaskToUser(anton,"task 11", TimeCalendar.getDate("2019-09-29"));
            addTaskToUser(anton,"task 12", TimeCalendar.getDate("2019-12-30"));
            addTaskToUser(anton,"task 13", TimeCalendar.getDate("2019-05-28"));
            addTaskToUser(anton,"task 14", TimeCalendar.getDate("2019-11-2"));
            addTaskToUser(anton,"task 15", TimeCalendar.getDate("2019-09-28"));

            User marina = new User("Marina", "11");
            List<Task> taskForMarina = new ArrayList<>();
            storageRAM.put(marina, taskForMarina);
            addTaskToUser(marina, "Go to read some book", TimeCalendar.getTodayDate());
            addTaskToUser(marina, "Sleep", TimeCalendar.getTomorrowDate());
            addTaskToUser(marina, "Work", TimeCalendar.getDate("2019-11-12"));
            addTaskToUser(marina, "Go for a walk", TimeCalendar.getDate("2020-01-01"));
        }
        catch (TaskDAOException e)
        {
            System.err.println(Constants.ERROR_RAM);
        }
    }
    private ManagerRAM(){
    }

    public static Map<User, List<Task>> getStorageRAM() {
        return storageRAM;
    }

    public static void addTaskToUser(User user, String discription, Date date) {
        List<Task> taskList = storageRAM.get(user);
        int id = idtask;
        idtask++;
        taskList.add(new Task(discription,date,id));
    }
}

