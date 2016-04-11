package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import tlist.models.Task;

public class TaskCollector implements Collector<Task> {

    @Override
    public Task collect(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer project = rs.getInt("project");
        String name = rs.getString("name");
        int priority = rs.getInt("priority");
        //LocalDate schedule = new LocalDate(rs.getDate("schedule"));
        LocalDate schedule = null;
        boolean completed = rs.getBoolean("completed");

        return new Task(id, project, name, priority, schedule, completed);
    }

}