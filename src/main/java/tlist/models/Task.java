package tlist.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private final int id;
    private int project;
    private String name;
    private Priority priority;
    private Date schedule;
    private boolean completed;

    private List<Label> labels;

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Task(int id, int project, String name, Priority priority, Date schedule, boolean completed) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.priority = priority;
        this.schedule = schedule;
        this.completed = completed;

        this.labels = new ArrayList<>();
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

    public List<Label> getLabels() {
        return labels;
    }

    public void setProject(Project project) {
        this.project = project.getId();
    }

    public void setProject(int project) {
        this.project = project;
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

    public void addLabel(Label label) {
        labels.add(label);
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

}
