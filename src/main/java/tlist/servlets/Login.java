package tlist.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class Login extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!initialize(request, response, false)) {
            return;
        }

        if (person != null) {
            redirect("project");
            return;
        }

        show("login.jsp");
    }

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

}
