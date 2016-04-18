package tlist.servlets;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import tlist.models.Priority;
import tlist.models.Task;

public class TaskValidator extends FormValidator<Task> {

    public TaskValidator(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void readInto(Task task) {
        task.setName(readName());
        task.setSchedule(readSchedule());
        task.setPriority(readPriority());
    }

    public String readName() {
        String name = ServletUtil.getStringParameter(request, "name");
        if (name.isEmpty()) {
            setErrorMsg("name", "Name is required.");
        }

        return name;
    }

    public Date readSchedule() {
        String scheduleStr = ServletUtil.getStringParameter(request, "schedule");
        if (scheduleStr.isEmpty()) {
            return null;
        }

        Date schedule = ServletUtil.parseDate(scheduleStr, null);
        if (schedule == null) {
            setErrorMsg("schedule", "Invalid date");
        }

        return schedule;
    }

    public Priority readPriority() {
        int priority = ServletUtil.parseInt(request.getParameter("priority"), -1);
        if (priority < 1 || priority > 4) {
            setErrorMsg("priority", "Priority must be in the interval 1-4");
            return Priority.defaultPriority;
        }

        return Priority.convert(priority);
    }

}
