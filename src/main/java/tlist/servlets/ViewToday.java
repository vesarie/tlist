package tlist.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class ViewToday extends BaseServlet {

    public ViewToday() {
        super(true);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        if (projects.isEmpty()) {
            // todo
        }

        Date today = new Date(Calendar.getInstance().getTime().getTime());

        setAttribute("projects", projects);
        setAttribute("tasks", taskDao.forDate(person.getId(), today));
        setAttribute("priorities", Priority.list);

        show("today.jsp");
    }

}
