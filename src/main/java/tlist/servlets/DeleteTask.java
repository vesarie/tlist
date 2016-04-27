package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class DeleteTask extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!initialize(request, response, true)) {
            return;
        }

        try {
            process(request, response);
        } catch (SQLException e) {
            error(e);
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Task task = getTask();

        if (task == null) {
            printError(response, "task not found");
            return;
        }

        taskDao.delete(task.getId());

        redirect(task.getProject());
    }

    private Task getTask() throws SQLException {
        int taskId = ServletUtil.parseInt(getParameter("id"), -1);
        return taskDao.find(taskId);
    }

    private void redirect(int projectId) throws IOException {
        redirect("project?id=" + projectId);
    }

    private void printError(HttpServletResponse response, String msg) throws IOException {
        response.getWriter().print("Task cannot be deleted: " + msg);
    }

}
