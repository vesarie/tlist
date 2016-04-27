package tlist.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
        Project project = getProject();

        TaskReader reader = new TaskReader(request);

        String name = reader.getName();
        Date schedule = reader.getSchedule();
        Priority priority = reader.getPriority();

        if (reader.getErrorCount() == 0) {
            create(project, name, priority, schedule);
        }

        setAttributes(project, priority);
        show("createTask.jsp");
    }

    private Project getProject() throws SQLException {
        int projectId = ServletUtil.parseInt(getParameter("project"), -1);
        return projectDao.find(projectId);
    }

    private void create(Project project, String name, Priority priority, Date schedule) throws SQLException {
        taskDao.insert(project.getId(), name, priority, schedule);
        setAttribute("saved", true);
    }

    private void setAttributes(Project project, Priority priority) {
        setAttribute("project", project);
        setAttribute("name", getParameter("name"));
        setAttribute("schedule", getParameter("schedule"));
        setAttribute("priority", priority.toInt());
        setAttribute("priorities", Priority.list);
    }

}
