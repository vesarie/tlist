package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListData extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initialize(request, response, false);

        try {
            setAttribute("persons", personDao.find());
            setAttribute("projects", projectDao.find());
            setAttribute("tasks", taskDao.find());
        } catch (SQLException e) {
            error(e);
        }

        show("listData.jsp");
    }

}
