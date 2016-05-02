package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import tlist.models.Label;

public class LabelCollector implements Collector<Label> {

    @Override
    public Label collect(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int person = rs.getInt("person");
        String name = rs.getString("name");

        return new Label(id, person, name);
    }

}
