package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import tlist.models.Project;

public class ProjectCollector implements Collector<Project> {

    @Override
    public Project collect(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer person = rs.getInt("person");
        String name = rs.getString("name");
        int color = rs.getInt("color");

        return new Project(id, person, name, color);
    }

}
