package tlist.db;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import tlist.models.Label;
import tlist.models.Priority;
import tlist.models.Task;

public class TaskDao implements Dao<Task> {

    private final TaskCollector collector;
    private final TaskLabelJoiner joiner;
    private final QueryRunner<Task> query;
    private final JoinRunner join;

    public TaskDao(Database db) {
        this.collector = new TaskCollector();
        this.joiner = new TaskLabelJoiner();
        this.query = new QueryRunner<>(db, collector);
        this.join = new JoinRunner(db, joiner);
    }

    public void updateDependencies(List<Label> labels) {
        joiner.setLabels(labels);
    }

    @Override
    public Task find(int id) throws SQLException {
        return fetchLabels(query.queryObject("SELECT * FROM Task WHERE id = ?", id));
    }

    @Override
    public List<Task> findAll() throws SQLException {
        return fetchLabels(query.queryList("SELECT * FROM Task ORDER BY id"));
    }

    public List<Task> forProject(int projectId) throws SQLException {
        return fetchLabels(query.queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN Project ON Project.id = Task.project "
                + "WHERE Project.id = ? "
                + "ORDER BY Task.id", projectId));
    }

    public List<Task> forLabel(int labelId) throws SQLException {
        return fetchLabels(query.queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN TaskLabel ON Task.id = TaskLabel.task "
                + "JOIN Label ON Label.id = TaskLabel.label "
                + "WHERE Label.id = ? "
                + "ORDER BY Task.id", labelId));
    }

    public int save(Task task) throws SQLException {
        return query.update(""
                + "UPDATE Task "
                + "SET name = ?, schedule = ?, priority = ? "
                + "WHERE id = ?",
                task.getName(), task.getSchedule(), task.getPriority().toInt(), task.getId());
    }

    public int create(int projectId, String name, Priority priority, Date schedule) throws SQLException {
        return query.insert(""
                + "INSERT INTO Task "
                + "(project, name, priority, schedule, completed) "
                + "VALUES (?, ?, ?, ?, ?)",
                projectId, name, priority.toInt(), schedule, false);
    }

    @Override
    public int delete(int id) throws SQLException {
        return query.update("DELETE FROM Task WHERE id = ?", id);
    }

    public int deleteAllInProject(int projectId) throws SQLException {
        return query.update(""
                + "DELETE FROM Task "
                + "WHERE id IN ("
                + "SELECT Task.id FROM Task "
                + "JOIN Project ON Project.id = Task.project "
                + "WHERE Project.id = ?)", projectId);
    }

    private Task fetchLabels(Task task) throws SQLException {
        joiner.setTasks(task);
        join.joinBasedOn("SELECT * FROM TaskLabel WHERE task = ?", task.getId());
        return task;
    }

    private List<Task> fetchLabels(List<Task> tasks) throws SQLException {
        joiner.setTasks(tasks);
        join.joinBasedOn("SELECT * FROM TaskLabel"); // todo: should be more specific
        return tasks;
    }

}
