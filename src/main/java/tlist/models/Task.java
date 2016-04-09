package tlist.models;

import java.time.LocalDate;

public class Task {

    private final int id;
    private int project;
    private String name;
    private int priority;
    private LocalDate schedule;
    private boolean completed;

    public Task(int id, int project, String name, int priority, LocalDate schedule, boolean completed) {
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

    public LocalDate getSchedule() {
        return schedule;
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

    public void setSchedule(LocalDate schedule) {
        this.schedule = schedule;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
