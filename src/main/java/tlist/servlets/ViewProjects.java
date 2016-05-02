package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class ViewProjects extends BaseServlet {

    public ViewProjects() {
        super(true);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        if (projects.isEmpty()) {
            // todo
        }

        setAttribute("projects", projects);

        show("projects.jsp");
    }

}
