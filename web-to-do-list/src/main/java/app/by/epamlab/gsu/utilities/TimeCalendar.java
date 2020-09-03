package app.by.epamlab.gsu.utilities;

import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.exceptions.TaskDAOException;

import java.sql.Date;
import java.util.Calendar;

public class TimeCalendar {

    public static Date getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        refreshDate(calendar);
        java.util.Date todayFromCalendar = calendar.getTime();
        Date today = new Date(todayFromCalendar.getTime());

        return today;
    }

    public static Date getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        refreshDate(calendar);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        java.util.Date tomorrowFromCalendar = calendar.getTime();
        Date tomorrow = new Date(tomorrowFromCalendar.getTime());

        return tomorrow;
    }

    public static Date getDate(String date) throws TaskDAOException {
        if (Constants.EMPTY.equals(date) || date == null) {
            throw new TaskDAOException(Constants.ERROR_EMPTY_DATE);
        }
        else {
            String[] _date = date.split(Constants.DATE_DELIMETR);
            int year = Integer.parseInt(_date[0]);
            int month = Integer.parseInt(_date[1]) - 1;
            int day = Integer.parseInt(_date[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            refreshDate(calendar);
            java.util.Date dateUTIL = calendar.getTime();
            Date dateSQL = new Date(dateUTIL.getTime());

            return dateSQL;
        }
    }

    private static void refreshDate(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
