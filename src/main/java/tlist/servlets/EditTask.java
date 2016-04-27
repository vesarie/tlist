package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class EditTask extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!initialize(request, response, true)) {
            return;
        }

        try {
            process(request);
        } catch (SQLException e) {
            error(e);
        }

        show("editTask.jsp");
    }

    private void process(HttpServletRequest request) throws SQLException {
        Task task = getTask();

        TaskReader reader = new TaskReader(request);
        reader.update(task);

        if (reader.getErrorCount() == 0) {
            save(task);
        }

        setAttributes(task);
    }

    private Task getTask() throws SQLException {
        int taskId = ServletUtil.parseInt(getParameter("id"), -1);
        return taskDao.find(taskId);
    }

    private void save(Task task) throws SQLException {
        taskDao.update(task);
        setAttribute("saved", true);
    }

    private void setAttributes(Task task) {
        setAttribute("id", task.getId());
        setAttribute("name", getParameter("name"));
        setAttribute("schedule", getParameter("schedule"));
        setAttribute("priority", task.getPriority().toInt());
        setAttribute("priorities", Priority.list);
    }

}
