package tlist.db;

import java.sql.SQLException;
import java.util.List;
import tlist.models.Project;

public class ProjectDao implements Dao<Project> {

    private final QueryRunner<Project> query;

    public ProjectDao(Database db) {
        this.query = new QueryRunner<>(db, new ProjectCollector());
    }

    @Override
    public Project find(int id) throws SQLException {
        List<Project> list = query.queryList("SELECT * FROM Project WHERE id = ? ORDER BY id", id);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<Project> find() throws SQLException {
        return query.queryList("SELECT * FROM Project");
    }

    public List<Project> forPerson(int personId) throws SQLException {
        return query.queryList(""
                + "SELECT Project.* FROM Project "
                + "JOIN Person ON Project.person = Person.id "
                + "WHERE Person.id =  ? "
                + "ORDER BY id", personId);
    }

}
