package tlist.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.db.*;
import tlist.models.*;

public class CreateTaskServlet extends HttpServlet {

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

        TaskValidator validator = new TaskValidator(request);

        String name = validator.readName();
        Date schedule = validator.readSchedule();
        Priority priority = validator.readPriority();

        try {
            int projectId = Integer.parseInt(request.getParameter("project"));
            Project project = projectDao.find(projectId);

            if (validator.getErrorCount() == 0) {
                int taskId = taskDao.insert(projectId, name, priority, schedule);

                Task task = taskDao.find(taskId);
                request.setAttribute("saved", true);
                System.out.println("task created: " + taskId + " " + task.getName());
            }

            request.setAttribute("project", project);
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("schedule", request.getParameter("schedule"));
            request.setAttribute("priority", priority.toInt());
            request.setAttribute("priorities", Priority.list);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("createTask.jsp");
        dispatcher.forward(request, response);
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
