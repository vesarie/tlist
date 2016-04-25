package tlist.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class LoginServlet extends BaseServlet {

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
        initialize(request, response, false);

        if (person != null) {
            redirect("project");
            return;
        }

        show("login.jsp");
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
        if (!initialize(request, response, false)) {
            return;
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Person p = login(email, password);

        if (p != null) {
            redirect("project");
            return;
        }

        setEmail(email);
        indicateInvalidLogin();

        show("login.jsp");
    }

    private void indicateInvalidLogin() {
        request.setAttribute("invalidLogin", true);
        request.setAttribute("errorMsg", "Invalid email or password");
    }

    private void setEmail(String email) {
        request.setAttribute("email", email);
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
