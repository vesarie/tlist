package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListData extends BaseServlet {

    public ListData() {
        super(false);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        setAttribute("persons", personDao.findAll());
        setAttribute("projects", projectDao.findAll());
        setAttribute("tasks", taskDao.findAll());

        show("listData.jsp");
    }

}
