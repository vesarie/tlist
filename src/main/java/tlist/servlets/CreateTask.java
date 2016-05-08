package tlist.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class CreateTask extends BaseServlet {

    public CreateTask() {
        super(true);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        TaskReader reader = new TaskReader(request);

        String name = reader.getName();
        Date schedule = reader.getSchedule();
        Priority priority = reader.getPriority();
        List<Integer> projects = reader.getProjects();

        if (reader.getErrorCount() == 0) {
            create(projects, name, priority, schedule);
        }

        setAttributes(projects, priority);
        show("createTask.jsp");
    }

    private void create(List<Integer> projects, String name, Priority priority, Date schedule) throws SQLException {
        taskDao.create(projects, name, priority, schedule);
        setAttribute("saved", true);
    }

    private void setAttributes(List<Integer> selectedList, Priority priority) throws SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        Map<Integer, Boolean> isSelected = EditTask.mapSelectedProjects(projects, selectedList);
        setAttribute("name", getParameter("name"));
        setAttribute("schedule", getParameter("schedule"));
        setAttribute("priority", priority.toInt());
        setAttribute("priorities", Priority.list);
        setAttribute("projects", projects);
        setAttribute("isSelected", isSelected);
    }

}
