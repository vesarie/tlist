package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class EditProject extends BaseServlet {

    public EditProject() {
        super(true);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Project project = getProject();

        ProjectReader reader = new ProjectReader(request);
        reader.update(project);

        if (reader.getErrorCount() == 0) {
            save(project);
        }

        setAttributes(project);
        show("editProject.jsp");
    }

    private Project getProject() throws SQLException {
        int projectId = ServletUtil.parseInt(getParameter("id"), -1);
        return projectDao.find(projectId);
    }

    private void save(Project project) throws SQLException {
        projectDao.save(project);
        setAttribute("saved", true);
    }

    private void setAttributes(Project project) {
        setAttribute("id", project.getId());
        setAttribute("name", getParameter("name"));
    }

}
