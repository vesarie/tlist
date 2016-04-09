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

}
