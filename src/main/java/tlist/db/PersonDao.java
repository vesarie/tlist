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
        List<Person> list = query.queryList("SELECT * FROM Person WHERE id = ?", id);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<Person> find() throws SQLException {
        return query.queryList("SELECT * FROM Person");
    }

}