package tlist.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class EditTask extends BaseServlet {

    public EditTask() {
        super(true);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Task task = getTask();

        TaskReader reader = new TaskReader(request);
        reader.update(task);

        if (reader.getErrorCount() == 0) {
            save(task);
        }

        setAttributes(task);
        show("editTask.jsp");
    }

    private Task getTask() throws SQLException {
        int taskId = ServletUtil.parseInt(getParameter("id"), -1);
        return taskDao.find(taskId);
    }

    private void save(Task task) throws SQLException {
        taskDao.save(task);
        setAttribute("saved", true);
    }

    private void setAttributes(Task task) throws SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        Map<Integer, Boolean> isSelected = mapSelectedProjects(projects, task.getProjects());
        setAttribute("id", task.getId());
        setAttribute("name", getParameter("name"));
        setAttribute("schedule", getParameter("schedule"));
        setAttribute("priority", task.getPriority().toInt());
        setAttribute("priorities", Priority.list);
        setAttribute("projects", projects);
        setAttribute("isSelected", isSelected);
    }

    public static Map<Integer, Boolean> mapSelectedProjects(List<Project> projects, List<Integer> selected) {
        Map<Integer, Boolean> selectedMap = new TreeMap<>();
        for (Project project : projects) {
            selectedMap.put(project.getId(), false);
        }

        for (int project : selected) {
            selectedMap.put(project, true);
        }

        return selectedMap;
    }

}
