package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProject extends BaseServlet {

    public CreateProject() {
        super(true);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ProjectReader reader = new ProjectReader(request);

        String name = reader.getName();

        if (reader.getErrorCount() == 0) {
            create(name);
        }

        setAttributes();
        show("createProject.jsp");
    }

    private void create(String name) throws SQLException {
        projectDao.create(person.getId(), name);
        setAttribute("saved", true);
    }

    private void setAttributes() {
        setAttribute("name", getParameter("name"));
    }

}
