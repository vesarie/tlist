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

    private final boolean isLoginRequired;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected Person person;

    public BaseServlet(boolean isLoginRequired) {
        this.isLoginRequired = isLoginRequired;
    }

    @Override
    public void init() throws ServletException {
        db = DatabaseConfig.getInstance(getServletContext());
        personDao = new PersonDao(db);
        projectDao = new ProjectDao(db);
        taskDao = new TaskDao(db);
    }

    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Sub-class should override this method to handle GET requests
    }

    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Sub-class should override this method to handle POST requests
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!initialize(request, response)) {
            return;
        }

        try {
            processGet(request, response);
        } catch (SQLException e) {
            error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!initialize(request, response)) {
            return;
        }

        try {
            processPost(request, response);
        } catch (SQLException e) {
            error(e);
        }
    }

    protected boolean initialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        set(request, response);
        setContentType();
        this.session = request.getSession();
        this.person = getLoggedInPerson();

        if (isLoginRequired) {
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

    protected String[] getParameterValues(String name) {
        return request.getParameterValues(name);
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
