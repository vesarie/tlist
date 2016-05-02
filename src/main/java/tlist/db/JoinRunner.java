package tlist.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinRunner {

    private final Database db;
    private final Joiner joiner;

    public JoinRunner(Database db, Joiner joiner) {
        this.db = db;
        this.joiner = joiner;
    }

    public void joinBasedOn(String query, Object... params) throws SQLException {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = prepare(connection, query, params);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                joiner.join(rs);
            }
        }
    }

    private PreparedStatement prepare(Connection connection, String query, Object[] params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        setParameters(stmt, params);
        return stmt;
    }

    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

}
