package tlist.db;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import tlist.models.Priority;
import tlist.models.Task;

public class TaskDao implements Dao<Task> {

    private final TaskCollector collector;
    private final QueryRunner<Task> query;
    private final ProjectTaskMapper mapper;
    private final QueryProcessor map;

    public TaskDao(Database db) {
        this.collector = new TaskCollector();
        this.query = new QueryRunner<>(db, collector);
        this.mapper = new ProjectTaskMapper();
        this.map = new QueryProcessor(db, mapper);
    }

    private List<Task> queryList(String q, Object... params) throws SQLException {
        fetchProjects(q.replace("*", "id"), params);
        return query.queryList(q, params);
    }

    private void fetchProjects(String idQuery, Object... params) throws SQLException {
        mapper.reset();
        map.process(""
                + "SELECT * FROM ProjectTask "
                + "WHERE task IN (" + idQuery + ") "
                + "ORDER BY task, project", params);
        collector.setProjects(mapper.getProjects());
    }

    @Override
    public Task find(int id) throws SQLException {
        List<Task> list = queryList("SELECT * FROM Task WHERE id = ?", id);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<Task> findAll() throws SQLException {
        return queryList("SELECT * FROM Task ORDER BY id");
    }

    public List<Task> forProject(int projectId) throws SQLException {
        return queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN ProjectTask ON Task.id = ProjectTask.task "
                + "JOIN Project ON Project.id = ProjectTask.project "
                + "WHERE Project.id = ? "
                + "AND Task.completed = false "
                + "ORDER BY Task.id", projectId);
    }

    public List<Task> forProjectIncludingCompleted(int projectId) throws SQLException {
        return queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN ProjectTask ON Task.id = ProjectTask.task "
                + "JOIN Project ON Project.id = ProjectTask.project "
                + "WHERE Project.id = ? "
                + "ORDER BY Task.completed, Task.id", projectId);
    }

    public List<Task> forDate(int personId, Date schedule) throws SQLException {
        return queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN ProjectTask ON Task.id = ProjectTask.task "
                + "JOIN Project ON Project.id = ProjectTask.project "
                + "JOIN Person ON Person.id = Project.person "
                + "WHERE Task.schedule = ? "
                + "AND Person.id = ? "
                + "AND Task.completed = false "
                + "ORDER BY Task.id",
                schedule, personId);
    }

    public List<Task> forDateIncludingCompleted(int personId, Date schedule) throws SQLException {
        return queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN ProjectTask ON Task.id = ProjectTask.task "
                + "JOIN Project ON Project.id = ProjectTask.project "
                + "JOIN Person ON Person.id = Project.person "
                + "WHERE Task.schedule = ? "
                + "AND Person.id = ? "
                + "ORDER BY Task.completed, Task.id",
                schedule, personId);
    }

    public List<Task> forDateInterval(int personId, Date from, Date to) throws SQLException {
        return queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN ProjectTask ON Task.id = ProjectTask.task "
                + "JOIN Project ON Project.id = ProjectTask.project "
                + "JOIN Person ON Person.id = Project.person "
                + "WHERE Task.schedule >= ? "
                + "AND Task.schedule < ? "
                + "AND Person.id = ? "
                + "AND Task.completed = false "
                + "ORDER BY Task.schedule, Task.id",
                from, to, personId);
    }

    public List<Task> forDateIntervalIncludingCompleted(int personId, Date from, Date to) throws SQLException {
        return queryList(""
                + "SELECT Task.* FROM Task "
                + "JOIN ProjectTask ON Task.id = ProjectTask.task "
                + "JOIN Project ON Project.id = ProjectTask.project "
                + "JOIN Person ON Person.id = Project.person "
                + "WHERE Task.schedule >= ? "
                + "AND Task.schedule < ? "
                + "AND Person.id = ? "
                + "ORDER BY Task.schedule, Task.completed, Task.id",
                from, to, personId);
    }

    public int save(Task task) throws SQLException {
        return query.update(""
                + "UPDATE Task "
                + "SET name = ?, schedule = ?, priority = ? "
                + "WHERE id = ?",
                task.getName(), task.getSchedule(), task.getPriority().toInt(), task.getId());
    }

    public int setCompleted(int id, boolean completed) throws SQLException {
        return query.update("UPDATE Task SET completed = ? WHERE id = ?", completed, id);
    }

    public int create(int projectId, String name, Priority priority, Date schedule) throws SQLException {
        return query.insert(""
                + "INSERT INTO Task "
                + "(project, name, priority, schedule, completed) "
                + "VALUES (?, ?, ?, ?, ?)",
                projectId, name, priority.toInt(), schedule, false);
    }

    @Override
    public int delete(int id) throws SQLException {
        return query.update("DELETE FROM Task WHERE id = ?", id);
    }

    public int deleteAllInProject(int projectId) throws SQLException {
        return query.update(""
                + "DELETE FROM Task "
                + "WHERE id IN ("
                + "SELECT Task.id FROM Task "
                + "JOIN Project ON Project.id = Task.project "
                + "WHERE Project.id = ?)", projectId);
    }

//    private void fetchProjectsForTask(int taskId) throws SQLException {
//        mapper.reset();
//        map.process(""
//                + "SELECT * FROM ProjectTask "
//                + "WHERE task = ? "
//                + "ORDER BY task, project",
//                taskId);
//        collector.setProjects(mapper.getProjects());
//    }
//
//    private void fetchProjectsForPerson(int personId) throws SQLException {
//        mapper.reset();
//        map.process(""
//                + "SELECT ProjectTask.* FROM ProjectTask "
//                + "JOIN Project ON Project.id = ProjectTask.project "
//                + "JOIN Person ON Person.id = Project.person "
//                + "WHERE Person.id = ? "
//                + "ORDER BY ProjectTask.task, ProjectTask.project",
//                personId);
//        collector.setProjects(mapper.getProjects());
//    }
}
