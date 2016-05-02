package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tlist.models.Label;
import tlist.models.Task;

public class TaskLabelJoiner implements Joiner<Task, Label> {

    private Map<Integer, Task> tasks;
    private Map<Integer, Label> labels;

    public TaskLabelJoiner() {
        this.tasks = new HashMap<>();
        this.labels = new HashMap<>();
    }

    @Override
    public void join(ResultSet rs) throws SQLException {
        Task task = tasks.get(rs.getInt("task"));
        Label label = labels.get(rs.getInt("label"));

        if (task == null || label == null) {
            return;
        }

        task.addLabel(label);
    }

    public void setLabels(List<Label> labels) {
        this.labels = mapLabels(labels);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = mapTasks(tasks);
    }

    public void setTasks(Task task) {
        tasks = new HashMap<>();
        tasks.put(task.getId(), task);
    }

    private Map<Integer, Task> mapTasks(List<Task> tasks) {
        HashMap<Integer, Task> map = new HashMap<>();
        for (Task task : tasks) {
            map.put(task.getId(), task);
        }

        return map;
    }

    private Map<Integer, Label> mapLabels(List<Label> labels) {
        HashMap<Integer, Label> map = new HashMap<>();
        for (Label label : labels) {
            map.put(label.getId(), label);
        }

        return map;
    }

}
