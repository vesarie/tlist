package tlist.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

public class Task {

    private final int id;
    private List<Integer> projects;
    private String name;
    private Priority priority;
    private Date schedule;
    private boolean completed;

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Task(int id, List<Integer> projects, String name, Priority priority, Date schedule, boolean completed) {
        this.id = id;
        this.projects = projects;
        this.name = name;
        this.priority = priority;
        this.schedule = schedule;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getProjects() {
        return projects;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
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

    public void setProjects(List<Integer> projects) {
        this.projects = projects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
