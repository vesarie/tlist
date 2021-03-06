package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class DeleteTask extends BaseServlet {

    public DeleteTask() {
        super(true);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Task task = getTask();

        if (task == null) {
            printError(response, "task not found");
            return;
        }

        taskDao.delete(task.getId());
    }

    private Task getTask() throws SQLException {
        int taskId = ServletUtil.parseInt(getParameter("id"), -1);
        return taskDao.find(taskId);
    }

    private void printError(HttpServletResponse response, String msg) throws IOException {
        response.getWriter().print("Task cannot be deleted: " + msg);
    }

}
