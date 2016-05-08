package tlist.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import tlist.models.Project;

public class ProjectDao implements Dao<Project> {

    private final QueryRunner<Project> query;

    public ProjectDao(Database db) {
        this.query = new QueryRunner<>(db, new ProjectCollector());
    }

    @Override
    public Project find(int id) throws SQLException {
        return query.queryObject("SELECT * FROM Project WHERE id = ?", id);
    }

    @Override
    public List<Project> findAll() throws SQLException {
        return query.queryList("SELECT * FROM Project ORDER BY id");
    }

    public List<Project> forPerson(int personId) throws SQLException {
        return query.queryList(""
                + "SELECT Project.* FROM Project "
                + "JOIN Person ON Person.id = Project.person "
                + "WHERE Person.id = ? "
                + "ORDER BY Project.id", personId);
    }

    public Map<Integer, Project> map(List<Project> projects) throws SQLException {
        Map<Integer, Project> map = new TreeMap<>();
        for (Project project : projects) {
            map.put(project.getId(), project);
        }

        return map;
    }

    public int save(Project project) throws SQLException {
        return query.update(""
                + "UPDATE Project "
                + "SET name = ? "
                + "WHERE id = ?",
                project.getName(), project.getId());
    }

    public int create(int personId, String name) throws SQLException {
        return query.insert(""
                + "INSERT INTO Project "
                + "(person, name) "
                + "VALUES (?, ?)",
                personId, name);
    }

    @Override
    public int delete(int id) throws SQLException {
        return query.update("DELETE FROM Project WHERE id = ?", id);
    }

}
