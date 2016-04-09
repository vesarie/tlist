package tlist.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class Database {

    BasicDataSource connectionPool;

    public Database() throws URISyntaxException, SQLException {
        URI dbUri = new URI(getDatabaseUrl());
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        connectionPool = new BasicDataSource();

        if (dbUri.getUserInfo() != null) {
            connectionPool.setUsername(dbUri.getUserInfo().split(":")[0]);
            connectionPool.setPassword(dbUri.getUserInfo().split(":")[1]);
        }

        connectionPool.setDriverClassName("org.postgresql.Driver");
        connectionPool.setUrl(dbUrl);
        connectionPool.setInitialSize(1);
    }

    private String getDatabaseUrl() {
        String dbUrl = System.getenv("DATABASE_URL"); // Heroku

        if (dbUrl == null) {
            dbUrl = "postgres://localhost/tlist";
        }

        return dbUrl;
    }

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public void close() throws SQLException {
        connectionPool.close();
    }

}
