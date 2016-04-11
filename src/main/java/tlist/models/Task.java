package tlist.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Task {

    private final int id;
    private int project;
    private String name;
    private int priority;
    private Date schedule;
    private boolean completed;

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Task(int id, int project, String name, int priority, Date schedule, boolean completed) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.priority = priority;
        this.schedule = schedule;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public int getProject() {
        return project;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public Date getSchedule() {
        return schedule;
    }

    public String getScheduleAsString() {
        if (schedule == null) {
            return "";
        }

        //return dateFormat.format(schedule);
        return schedule.toString();
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public void setProject(Project project) {
        this.project = project.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
