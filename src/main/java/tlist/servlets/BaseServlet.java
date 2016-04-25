package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tlist.db.*;
import tlist.models.*;

public class BaseServlet extends HttpServlet {

    protected Database db;
    protected PersonDao personDao;
    protected ProjectDao projectDao;
    protected TaskDao taskDao;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected Person person;

    @Override
    public void init() throws ServletException {
        db = DatabaseConfig.getInstance(getServletContext());
        personDao = new PersonDao(db);
        projectDao = new ProjectDao(db);
        taskDao = new TaskDao(db);
    }

    protected boolean initialize(HttpServletRequest request, HttpServletResponse response, boolean requiresLogin) throws IOException {
        set(request, response);
        setContentType();
        this.session = request.getSession();
        this.person = getLoggedInPerson();

        if (requiresLogin) {
            return requireLogin();
        }

        return true;
    }

    private void set(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    protected void setContentType() {
        response.setContentType("text/html;charset=UTF-8");
    }

    protected boolean requireLogin() throws IOException {
        if (person == null) {
            redirect("login");
        }

        return person != null;
    }

    protected void show(String path) throws ServletException, IOException {
        if (person != null) {
            setAttribute("person", person);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    protected void redirect(String path) throws IOException {
        response.sendRedirect(path);
    }

    protected String getParameter(String name) {
        return request.getParameter(name);
    }

    protected void setAttribute(String name, Object o) {
        request.setAttribute(name, o);
    }

    private Person getLoggedInPerson() {
        return (Person) session.getAttribute("login");
    }

    private void setLoggedInPerson(Person p) {
        this.person = p;
        session.setAttribute("login", p);
    }

    protected Person login(String email, String password) {
        try {
            Person p = personDao.find(email, password);

            if (p != null) {
                setLoggedInPerson(p);
            }

            return p;
        } catch (SQLException e) {
            error(e);
            return null;
        }
    }

    protected void logout() {
        this.person = null;
        session.removeAttribute("login");
    }

    protected void error(Exception e) {
        System.out.println("ERROR: " + e);
        throw new RuntimeException(e);
    }

}
