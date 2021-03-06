package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import tlist.models.Project;

public class ProjectCollector implements Collector<Project> {

    @Override
    public Project collect(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int person = rs.getInt("person");
        String name = rs.getString("name");

        return new Project(id, person, name);
    }

}
