package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class ViewProject extends BaseServlet {

    public ViewProject() {
        super(true);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        if (projects.isEmpty()) {
            // todo
        }

        Project project = getProject();

        setAttribute("project", project);
        setAttribute("projects", projects);
        setAttribute("tasks", taskDao.forProject(project.getId()));
        setAttribute("priorities", Priority.list);

        show("project.jsp");
    }

    private Project getProject() throws SQLException {
        int projectId = ServletUtil.parseInt(getParameter("id"), -1);

        if (projectId == -1) {
            return getFirstProject();
        }

        return projectDao.find(projectId);
    }

    private Project getFirstProject() throws SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        if (projects.isEmpty()) {
            return null;
        }

        return projects.get(0);
    }

}
