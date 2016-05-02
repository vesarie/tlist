package tlist.db;

import java.sql.SQLException;
import java.util.List;
import tlist.models.Label;

public class LabelDao implements Dao<Label> {

    private final QueryRunner<Label> query;

    public LabelDao(Database db) {
        this.query = new QueryRunner<>(db, new LabelCollector());
    }

    @Override
    public Label find(int id) throws SQLException {
        return query.queryObject(
                "SELECT * FROM Label WHERE id = ?", id);
    }

    @Override
    public List<Label> findAll() throws SQLException {
        return query.queryList("SELECT * FROM Label ORDER BY id");
    }

    public List<Label> forPerson(int personId) throws SQLException {
        return query.queryList(""
                + "SELECT Label.* FROM Label "
                + "JOIN Person ON Person.id = Label.person "
                + "WHERE Person.id = ? "
                + "ORDER BY Label.id", personId);
    }

    @Override
    public int delete(int id) throws SQLException {
        query.update("DELETE FROM TaskLabel WHERE label = ?", id);
        return query.update("DELETE FROM Label WHERE id = ?", id);
    }

}
