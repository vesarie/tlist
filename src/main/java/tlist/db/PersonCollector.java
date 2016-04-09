package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import tlist.models.Person;

public class PersonCollector implements Collector<Person> {

    @Override
    public Person collect(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String forename = rs.getString("forename");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String password = rs.getString("password");

        return new Person(id, forename, surname, email, password);
    }

}
