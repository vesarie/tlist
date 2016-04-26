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
            int taskId = ServletUtil.parseInt(getParameter("id"), -1);
            if (taskId == -1) {
                printError(response, "invalid or missing id");
                return;
            }

            Task task = taskDao.find(taskId);
            if (task == null) {
                printError(response, "task not found");
                return;
            }

            taskDao.delete(taskId);
            System.out.println("task deleted: " + taskId + " " + task.getName());

            redirect("project?id=" + task.getProject());
        } catch (SQLException e) {
            error(e);
        }
    }

    private void printError(HttpServletResponse response, String msg) throws IOException {
        response.getWriter().print("Task cannot be deleted: " + msg);
    }

}
