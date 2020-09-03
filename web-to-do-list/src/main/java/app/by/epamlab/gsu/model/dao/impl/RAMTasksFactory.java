package app.by.epamlab.gsu.model.dao.impl;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.exceptions.TaskDAOException;
import app.by.epamlab.gsu.managers.FileManager;
import app.by.epamlab.gsu.managers.ManagerRAM;
import app.by.epamlab.gsu.model.beans.*;
import app.by.epamlab.gsu.model.dao.interfaces.ITaskDAO;
import app.by.epamlab.gsu.utilities.TimeCalendar;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RAMTasksFactory implements ITaskDAO {

    private static RAMTasksFactory ramTasksFactory = new RAMTasksFactory();

    private RAMTasksFactory() {
    }

    public static RAMTasksFactory getTaskFactory() {
        return ramTasksFactory;
    }

    @Override
    public List<Task> getTasks(User user, Sections sections) {
        Map<User, List<Task>> RAMStorage = ManagerRAM.getStorageRAM();
        List<Task> taskList = RAMStorage.get(user);

        switch (sections) {
            case TODAY: {
                return taskList.stream()
                        .filter(this::filterToday)
                        .collect(Collectors.toList());

            }
            case TOMORROW: {
                return taskList.stream()
                        .filter(this::filterTomorrow)
                        .collect(Collectors.toList());

            }
            case SOMEDAY: {
                return taskList.stream()
                        .filter(this::filterSomeday)
                        .collect(Collectors.toList());

            }
            case FIXED: {
                return taskList.stream()
                        .filter(this::filterFixed)
                        .collect(Collectors.toList());
            }
            case RECYCLE: {
                return taskList.stream()
                        .filter(this::filterRecycle)
                        .collect(Collectors.toList());
            }
        }

        return taskList;
    }

    @Override
    public void removeTask(User user, int id) {
        Map<User, List<Task>> RAMStorage = ManagerRAM.getStorageRAM();
        List<Task> taskList = RAMStorage.get(user);
        taskList
                .stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .get()
                .setRecycle(true);

    }

    @Override
    public void fixTask(User user, int id) throws TaskDAOException {
            Map<User, List<Task>> RAMStorage = ManagerRAM.getStorageRAM();
            List<Task> taskList = RAMStorage.get(user);
            taskList
                    .stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .get()
                    .setFixed(true);
    }

    @Override
    public void addTask(User user, String description, Date date) throws TaskDAOException {
        if (description == null || Constants.EMPTY.equals(description)) {
            throw new TaskDAOException(Constants.ERROR_EMPTY_DISCR);
        }
        else if (date == null) {
            throw new TaskDAOException(Constants.ERROR_EMPTY_DATE);
        }
        else {
            ManagerRAM.addTaskToUser(user, description, date);
        }
    }

    @Override
    public void recycleTask(User user, int id) {
        Map<User,List<Task>> storageRAM = ManagerRAM.getStorageRAM();

        Task taskForFileRemove = storageRAM
                .get(user)
                .stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .get();
        FileManager.removeTaskFile(user,taskForFileRemove);

        storageRAM
                .get(user)
                .removeIf(task -> task.getId() == id);
    }

    @Override
    public void recycleAllTask(User user) {
        ManagerRAM.getStorageRAM().get(user)
                .stream()
                .filter(Task::getRecycle)
                .forEach(task -> FileManager.removeTaskFile(user,task));

        ManagerRAM.getStorageRAM().get(user)
                .removeIf(Task::getRecycle);
    }

    @Override
    public void resumeTask(User user, int id) {
        ManagerRAM.getStorageRAM().get(user)
                .stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .get().setRecycle(false);
    }

    @Override
    public Task getTaskByID(User user, int id) {
        Map<User, List<Task>> RAMStorage = ManagerRAM.getStorageRAM();
        List<Task> taskList = RAMStorage.get(user);
        Task taskResult = taskList
                .stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .get();
        return taskResult;
    }

    private boolean filterToday(Task task) {
        return  compareDateToday(task.getDate()) && !task.getRecycle() && !task.getFixed();
    }
    private boolean compareDateToday(Date date) {
        Date today = TimeCalendar.getTodayDate();
        if(today.equals(date)) {
            return true;
        }
        else return today.compareTo(date) > 0;
    }
    private boolean filterTomorrow(Task task) {
        Date tomorrow = TimeCalendar.getTomorrowDate();
        return  tomorrow.equals(task.getDate()) && !task.getRecycle() && !task.getFixed();
    }
    private boolean filterSomeday(Task task){
        return compareDateSomeday(task.getDate()) && !task.getRecycle() && !task.getFixed();
    }
    private boolean compareDateSomeday(Date date) {
        Date tomorrow = TimeCalendar.getTomorrowDate();
        if(tomorrow.equals(date)){
            return false;
        }
        else return tomorrow.compareTo(date) <= 0;
    }
    private boolean filterFixed(Task task) {
        return task.getFixed() && !task.getRecycle();
    }
    private boolean filterRecycle(Task task) {
        return task.getRecycle();
    }
}
