package tlist.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
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
        Map<Integer, Project> projectMap = projectDao.map(projects);
        if (projects.isEmpty()) {
            // todo
        }

        Date today = new Date(Calendar.getInstance().getTime().getTime());

        List<Task> tasks = getTasks(today);

        setAttribute("projects", projects);
        setAttribute("projectMap", projectMap);
        setAttribute("tasks", tasks);
        setAttribute("priorities", Priority.list);
        setAttribute("showCompletedTasks", showCompletedTasks());

        show("today.jsp");
    }

    private List<Task> getTasks(Date today) throws SQLException {
        if (showCompletedTasks()) {
            return taskDao.forDateIncludingCompleted(person.getId(), today);
        }

        return taskDao.forDate(person.getId(), today);
    }

    private boolean showCompletedTasks() {
        return getParameter("showCompletedTasks") != null;
    }

}
