package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class EditTaskServlet extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!initialize(request, response, true)) {
            return;
        }

        try {
            int taskId = ServletUtil.parseInt(getParameter("id"), -1);
            Task task = taskDao.find(taskId);

            TaskValidator validator = new TaskValidator(request);
            validator.readInto(task);

            if (validator.getErrorCount() == 0) {
                taskDao.save(task);
                setAttribute("saved", true);
                System.out.println("task saved: " + taskId + " " + task.getName());
            }

            setAttribute("id", task.getId());
            setAttribute("name", getParameter("name"));
            setAttribute("schedule", getParameter("schedule"));
            setAttribute("priority", task.getPriority().toInt());
            setAttribute("priorities", Priority.list);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            throw new RuntimeException(e);
        }

        show("editTask.jsp");
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
