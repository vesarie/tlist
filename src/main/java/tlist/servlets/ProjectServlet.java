package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class ProjectServlet extends BaseServlet {

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
            System.out.println("person=" + person);
            System.out.println("projectDao" + projectDao);

            List<Project> projects = projectDao.forPerson(person.getId());
            if (projects.isEmpty()) {
                // todo
            }

            Project project = getProject();

            setAttribute("project", project);
            setAttribute("projects", projects);
            setAttribute("tasks", taskDao.forProject(project.getId()));
            setAttribute("priorities", Priority.list);
        } catch (SQLException e) {
            error(e);
        }

        show("project.jsp");
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

    private Project getProject() throws SQLException {
        int projectId = ServletUtil.parseInt(getParameter("id"), -1);

        if (projectId == -1) {
            return getFirstProject();
        }

        return projectDao.find(projectId);
    }

    private Project getFirstProject() throws SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        if (projects.isEmpty()) {
            return null;
        }

        return projects.get(0);
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
