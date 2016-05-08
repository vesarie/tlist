package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class DeleteProject extends BaseServlet {

    public DeleteProject() {
        super(true);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Project project = getProject();

        if (project == null) {
            printError(response, "project not found");
            return;
        }

        taskDao.deleteAllOnlyInGivenProject(project.getId());
        projectDao.delete(project.getId());
    }

    private Project getProject() throws SQLException {
        int projectId = ServletUtil.parseInt(getParameter("id"), -1);
        return projectDao.find(projectId);
    }

    private void printError(HttpServletResponse response, String msg) throws IOException {
        response.getWriter().print("Project cannot be deleted: " + msg);
    }

}
