package tlist.db;

import java.sql.Date;
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
        return query.queryList("SELECT * FROM Task ORDER BY id");
    }

    public List<Task> forProject(int projectId) throws SQLException {
        return query.queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN Project ON Task.project = Project.id "
                + "WHERE Project.id = ? "
                + "ORDER BY id", projectId);
    }

    public int save(Task task) throws SQLException {
        return query.update(""
                + "UPDATE Task "
                + "SET name = ?, schedule = ?, priority = ? "
                + "WHERE id = ?",
                task.getName(), task.getSchedule(), task.getPriority(), task.getId());
    }

    public int insert(int projectId, String name, int priority, Date schedule) throws SQLException {
        return query.insert(""
                + "INSERT INTO Task "
                + "(project, name, priority, schedule, completed) "
                + "VALUES (?, ?, ?, ?)", projectId, name, priority, schedule, false);
    }
    
    public int delete(int id) throws SQLException {
        return query.update("DELETE FROM Task WHERE id = ?", id);
    }

}
