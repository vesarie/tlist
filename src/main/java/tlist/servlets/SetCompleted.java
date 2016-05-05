package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCompleted extends BaseServlet {

    public SetCompleted() {
        super(true);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = getTaskId();
        boolean value = getValue();

        taskDao.setCompleted(id, value);
    }

    private int getTaskId() {
        return ServletUtil.parseInt(getParameter("id"), -1);
    }

    private boolean getValue() {
        return Boolean.parseBoolean(getParameter("value"));
    }

}
