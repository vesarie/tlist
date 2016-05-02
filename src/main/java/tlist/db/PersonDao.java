package tlist.db;

import java.sql.SQLException;
import java.util.List;
import tlist.models.Person;

public class PersonDao implements Dao<Person> {

    private final QueryRunner<Person> query;

    public PersonDao(Database db) {
        this.query = new QueryRunner<>(db, new PersonCollector());
    }

    @Override
    public Person find(int id) throws SQLException {
        return query.queryObject("SELECT * FROM Person WHERE id = ?", id);
    }

    public Person find(String email, String password) throws SQLException {
        if (email == null || password == null) {
            return null;
        }

        email = email.trim();
        password = password.trim();

        if (email.isEmpty() || password.isEmpty()) {
            return null;
        }

        return query.queryObject(""
                + "SELECT * FROM Person WHERE email = ? AND password = ?",
                email, password);
    }

    @Override
    public List<Person> findAll() throws SQLException {
        return query.queryList("SELECT * FROM Person ORDER BY id");
    }

    @Override
    public int delete(int id) throws SQLException {
        return query.update("DELETE FROM Person WHERE id = ?", id);
    }

}
