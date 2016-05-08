package tlist.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryProcessor {

    private final Database db;
    private final Processor processor;

    public QueryProcessor(Database db, Processor processor) {
        this.db = db;
        this.processor = processor;
    }

    public void process(String query, Object... params) throws SQLException {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = prepare(connection, query, params);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                processor.process(rs);
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
