package tlist.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tlist.models.*;

public class ViewNext7Days extends BaseServlet {

    public class TasksForDate {

        private final String header;
        private final String subHeader;
        private final Date date;
        private final List<Task> tasks;

        public TasksForDate(Date date, String header, String subHeader) {
            this.header = header;
            this.subHeader = subHeader;
            this.date = date;
            this.tasks = new ArrayList<>();
        }

        public String getHeader() {
            return header;
        }

        public String getSubHeader() {
            return subHeader;
        }

        public Date getDate() {
            return date;
        }

        public List<Task> getTasks() {
            return tasks;
        }
    }

    private final SimpleDateFormat dayOfWeek;
    private final SimpleDateFormat fullDate;

    public ViewNext7Days() {
        super(true);
        dayOfWeek = new SimpleDateFormat("EEEE", Locale.US);
        fullDate = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Project> projects = projectDao.forPerson(person.getId());
        if (projects.isEmpty()) {
            // todo
        }

        Date today = getToday();
        Date plus7 = get7DaysFromNow();

        List<Task> tasks = getTasks(today, plus7);
        List<TasksForDate> tasksByDate = listByDate(tasks);

        setAttribute("projects", projects);
        setAttribute("tasksByDate", tasksByDate);
        setAttribute("priorities", Priority.list);
        setAttribute("showCompletedTasks", showCompletedTasks());

        show("next7.jsp");
    }

    private List<Task> getTasks(Date from, Date to) throws SQLException {
        if (showCompletedTasks()) {
            return taskDao.forDateIntervalIncludingCompleted(person.getId(), from, to);
        }

        return taskDao.forDateInterval(person.getId(), from, to);
    }

    private boolean showCompletedTasks() {
        return getParameter("showCompletedTasks") != null;
    }

    private List<TasksForDate> listByDate(List<Task> tasks) {
        List<TasksForDate> list = new ArrayList<>();
        TasksForDate item = null;
        Date today = getToday();
        Date tomorrow = getTomorrow();
        for (Task task : tasks) {
            Date schedule = task.getSchedule();

            if (item == null || item.date.compareTo(schedule) != 0) {
                item = createItem(schedule, today, tomorrow);
                list.add(item);
            }

            item.tasks.add(task);
        }

        return list;
    }

    private TasksForDate createItem(Date schedule, Date today, Date tomorrow) {
        String header = getHeader(schedule, today, tomorrow);
        String subHeader = getSubHeader(schedule, today, tomorrow);

        return new TasksForDate(schedule, header, subHeader);
    }

    private String getHeader(Date date, Date today, Date tomorrow) {
        if (date.toLocalDate().isEqual(today.toLocalDate())) {
            return "Today";
        }

        if (date.toLocalDate().isEqual(tomorrow.toLocalDate())) {
            return "Tomorrow";
        }

        return dayOfWeek.format(date);
    }

    private String getSubHeader(Date date, Date today, Date tomorrow) {
        return fullDate.format(date);
    }

    private Date getToday() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }

    private Date getTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return new Date(cal.getTime().getTime());
    }

    private Date get7DaysFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        return new Date(cal.getTime().getTime());
    }

}
