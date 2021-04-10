package tlist.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class Database {

    BasicDataSource connectionPool;

    public Database() throws URISyntaxException, SQLException {
        String dbUrl = getJdbcDatabaseUrl();
        connectionPool = new BasicDataSource();

        connectionPool.setDriverClassName("org.postgresql.Driver");
        connectionPool.setUrl(dbUrl);
        connectionPool.setInitialSize(1);
    }

    private String getJdbcDatabaseUrl() throws URISyntaxException {
        String dbUrl = System.getenv("DATABASE_URL"); // Heroku

        if (dbUrl != null) {
            URI dbUri = new URI(dbUrl);
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            return "jdbc:postgresql://"
                + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
                + "?user=" + username + "&password=" + password
                + "&sslmode=require";
        }

        // For the local dev environment (default)
        return "jdbc:postgresql://db/postgres?user=postgres&password=secret";
    }

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public void close() throws SQLException {
        connectionPool.close();
    }

}
