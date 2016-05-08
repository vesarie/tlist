package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tlist.models.Priority;
import tlist.models.Task;

public class TaskCollector implements Collector<Task> {

    private Map<Integer, List<Integer>> projectsForEachTask;

    public TaskCollector() {
        this.projectsForEachTask = new HashMap<>();
    }

    public void setProjects(Map<Integer, List<Integer>> projects) {
        this.projectsForEachTask = projects;
    }

    @Override
    public Task collect(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Priority priority = Priority.convert(rs.getInt("priority"));
        Date schedule = rs.getDate("schedule");
        boolean completed = rs.getBoolean("completed");

        List<Integer> projects = projectsForEachTask.get(id);

        if (projects == null) {
            projects = new ArrayList<>();
        }

        return new Task(id, projects, name, priority, schedule, completed);
    }

}
