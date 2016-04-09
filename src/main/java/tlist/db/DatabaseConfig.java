package tlist.db;

import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseConfig implements ServletContextListener {

    private static final String attributeName = "database";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Database db = new Database();
            ServletContext servletContext = sce.getServletContext();
            servletContext.setAttribute(attributeName, db);
        } catch (URISyntaxException | SQLException e) {
            System.out.println("ERROR: " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Database db = getInstance(sce.getServletContext());
            db.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance(ServletContext servletContext) {
        return (Database) servletContext.getAttribute(attributeName);
    }

}
