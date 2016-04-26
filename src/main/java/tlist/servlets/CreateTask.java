package tlist.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class CreateTask extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!initialize(request, response, true)) {
            return;
        }

        TaskValidator validator = new TaskValidator(request);

        String name = validator.readName();
        Date schedule = validator.readSchedule();
        Priority priority = validator.readPriority();

        try {
            int projectId = ServletUtil.parseInt(getParameter("project"), -1);
            Project project = projectDao.find(projectId);

            if (validator.getErrorCount() == 0) {
                int taskId = taskDao.insert(projectId, name, priority, schedule);

                Task task = taskDao.find(taskId);
                setAttribute("saved", true);
                System.out.println("task created: " + taskId + " " + task.getName());
            }

            setAttribute("project", project);
            setAttribute("name", getParameter("name"));
            setAttribute("schedule", getParameter("schedule"));
            setAttribute("priority", priority.toInt());
            setAttribute("priorities", Priority.list);
        } catch (SQLException e) {
            error(e);
        }

        show("createTask.jsp");
    }

}
