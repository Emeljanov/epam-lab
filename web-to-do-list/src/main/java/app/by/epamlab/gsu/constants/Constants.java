package app.by.epamlab.gsu.constants;

/**
 * DB connection constants
 */
public class Constants {
    public static final String DB_CLASSNAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/web1?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    public static final String DB_USER = "antonroot";
    public static final String DB_PASSWORD = "antonroot";

    public static final String RAM_DAO = "ram";
    public static final String DB_DAO = "db";
    public static final String EMPTY = "";
    public static final String DATE_DELIMETR = "-";

    public static final String KEY_ID_TASK = "idtask";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ID = "id";
    public static final String TASK = "task";
    public static final String DATE = "date";
    public static final String TODAY = "today";
    public static final String TOMORROW = "tomorrow";
    public static final String SOMEDAY = "someday";
    public static final String FIXED = "fixed";
    public static final String RECYCLE = "recycle";

    public static final String JUMP_INDEX = "/";
    public static final String JUMP_LOGIN = "/views/login.jsp";
    public static final String JUMP_MAIN = "/views/main.jsp";
    public static final String JUMP_ERROR = "/views/error.jsp";
    public static final String JUMP_REGISTRATE = "/views/registrate.jsp";
    public static final String JUMP_ADD_TASK = "/views/addtask.jsp";
    public static final String JUMP_NOREGISTRATE = "/views/noregistr.jsp";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_EMPTY = "";
    public static final String ERROR_EMPTY = "Login is empty.";
    public static final String ERROR_NULL = "Login or password are absent.";
    public static final String ERROR_SOURCE = "Input source proccessing problems.";
    public static final String ERROR_NOT_CHECKED = "Please, check task";

    public static final String ATR_USER = "user";
    public static final String ATR_TODAYDAY = "todayDay";
    public static final String ATR_TOMORROWDAY = "tomorrowDay";
    public static final String ATR_DISCRIPTION = "discription";
    public static final String ATR_TASK_ADD_DATE = "taskAddDate";
    public static final String ATR_ID_TASK = "idtask";
    public static final String ATR_TASK_BY_ID = "taskById";
    public static final String ATR_TASKATR = "taskATR";
    public static final String ATR_FILENAME = "fileName";

    public static final String KEY_TASKLIST = "taskList";
    public static final String KEY_ERROR = "error";
    public static final String KEY_SECTION = "section";

    public static final String URL_MAIN = "/app/operation/main";
    public static final String URL_ADD_TASK = "/views/addtask.jsp";
    public static final String URL_FIXTASK = "/views/fixtask.jsp";
    public static final String URL_FILEPAGE = "/views/file.jsp";


    public static final String ERROR_EMPTY_LOGIN = "Login is empty";
    public static final String ERROR_EMPTY_PASSWORD = "Password is empty";
    public static final String ERROR_LOGIN_PASSW_INC = "Login or passwrod incorrect";
    public static final String ERROR_LOGIN_EXISTS = "Login already exists";
    public static final String ERROR_EMPTY_DISCR = "Discription is empty";
    public static final String ERROR_EMPTY_DATE = "Date is empty";
    public static final String ERROR_RAM = "RAM edit error";
    public static final String ERROR_DB = "Cannot get connection to database...";

    public static final int LOGIN_INDEX = 1;
    public static final String SQL_SELECT_ID_USER ="select id from users where login=?;";
    public static final String SQL_TASK_TODAY = "select * from tasks where id_user = ? and date <= ? and recycle = 0 and fixed = 0 ;";
    public static final String SQL_TASK_TOMORROW = "select * from tasks where id_user = ? and date = ? and recycle = 0 and fixed = 0 ;";
    public static final String SQL_TASKS_FIXED = "select * from tasks where id_user = ? and fixed = 1 and recycle = 0;";
    public static final String SQL_TASKS_RECYCLED = "select * from tasks where id_user = ? and recycle = 1;";
    public static final String SQL_TASKS_SOMEDAY = "select * from tasks where id_user = ? and date > ?  and recycle = 0 and fixed = 0;";
    public static final String SQL_REMOVE_TASK = "update tasks set recycle = 1 where id = ? ;";
    public static final String SQL_FIX_TASK = "update tasks set fixed = 1 where id = ?;";
    public static final String SQL_CHECK_USER = "select * from users where login = ?";
    public static final String SQL_SAVE_USER = "insert into users (login, password) values (?,?);";
    public static final String SQL_GET_USER = "select * from users where password = ? and login = ?";
    public static final String SQL_ADD_TASK = "insert into tasks (id_user,task,date) values (?,?,?)";
    public static final String SQL_GET_TASK_BY_ID = "select * from tasks where id = ?";
    public static final String SQL_RECYCLE_TASK = "delete from tasks where id = ?;";
    public static final String SQL_RECYCLE_ALL_TASK = "delete from tasks where id_user = ? and recycle = 1;";
    public static final String SQL_RESUME_TASK = "update tasks set recycle = 0 where id = ? ;";
}
