package tlist.db;

import java.sql.SQLException;
import java.util.List;
import tlist.models.Task;

public class TaskDao implements Dao<Task> {

    private final QueryRunner<Task> query;

    public TaskDao(Database db) {
        this.query = new QueryRunner<>(db, new TaskCollector());
    }

    @Override
    public Task find(int id) throws SQLException {
        List<Task> list = query.queryList("SELECT * FROM Task WHERE id = ?", id);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<Task> find() throws SQLException {
        return query.queryList("SELECT * FROM Task");
    }

    public List<Task> forProject(int projectId) throws SQLException {
        return query.queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN Project ON Task.project = Project.id "
                + "WHERE Project.id = ?", projectId);
    }

    public void save(Task task) throws SQLException {
        query.queryInt("UPDATE SET name = ?, schedule = ?, priority = ? WHERE id = ?",
                task.getName(), task.getSchedule(), task.getPriority(), task.getId());
    }

}
