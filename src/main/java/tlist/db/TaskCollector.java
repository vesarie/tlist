package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import tlist.models.Priority;
import tlist.models.Task;

public class TaskCollector implements Collector<Task> {

    @Override
    public Task collect(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer project = rs.getInt("project");
        String name = rs.getString("name");
        Priority priority = Priority.convert(rs.getInt("priority"));
        Date schedule = rs.getDate("schedule");
        boolean completed = rs.getBoolean("completed");

        return new Task(id, project, name, priority, schedule, completed);
    }

}
