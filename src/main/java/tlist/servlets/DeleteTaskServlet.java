package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.db.*;
import tlist.models.*;

public class DeleteTaskServlet extends HttpServlet {

    private Database db;
    private PersonDao personDao;
    private ProjectDao projectDao;
    private TaskDao taskDao;

    @Override
    public void init() throws ServletException {
        db = DatabaseConfig.getInstance(getServletContext());
        personDao = new PersonDao(db);
        projectDao = new ProjectDao(db);
        taskDao = new TaskDao(db);
    }

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
        response.setContentType("text/html;charset=UTF-8");

        try {
            int taskId = ServletUtil.parseInt(request.getParameter("id"), -1);
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

            response.sendRedirect("project?id=" + task.getProject());
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e);
            throw new RuntimeException(e);
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
