package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectTaskMapper implements Processor {

    private Map<Integer, List<Integer>> projectsForEachTask;

    public ProjectTaskMapper() {
        this.projectsForEachTask = new HashMap<>();
    }

    public void reset() {
        this.projectsForEachTask = new HashMap<>();
    }

    @Override
    public void process(ResultSet rs) throws SQLException {
        int task = rs.getInt("task");
        int project = rs.getInt("project");

        List<Integer> projects = projectsForEachTask.get(task);

        if (projects == null) {
            projects = new ArrayList<>();
            projectsForEachTask.put(task, projects);
        }

        projects.add(project);
    }

    public Map<Integer, List<Integer>> getProjects() {
        return projectsForEachTask;
    }

}
