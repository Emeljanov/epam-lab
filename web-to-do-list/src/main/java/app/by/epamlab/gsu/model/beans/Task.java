package app.by.epamlab.gsu.model.beans;

import java.sql.Date;

/**
 * Bean for task
 */
public class Task {
    private String name;
    private Date date;
    private int id;
    private boolean fixed;
    private boolean recycle;
//    String filePath;

    public Task(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Task(String name, Date date, int id) {
        this.name = name;
        this.date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean getFixed() {
        return fixed;
    }

    public void setRecycle(boolean recycle) {
        this.recycle = recycle;
    }

    public boolean getRecycle() {
        return recycle;
    }

}
