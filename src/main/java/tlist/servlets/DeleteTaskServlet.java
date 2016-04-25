package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class DeleteTaskServlet extends BaseServlet {

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
