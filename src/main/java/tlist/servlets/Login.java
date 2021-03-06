package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class Login extends BaseServlet {

    public Login() {
        super(false);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (person != null) {
            redirect(".");
            return;
        }

        show("login.jsp");
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Person p = login(email, password);

        if (p != null) {
            redirect(".");
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
