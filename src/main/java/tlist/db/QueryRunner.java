package tlist.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryRunner<T> {

    private final Database db;
    private final Collector<T> collector;

    public QueryRunner(Database db, Collector<T> collector) {
        this.db = db;
        this.collector = collector;
    }

    public List<T> queryList(String query, Object... params) throws SQLException {
        List<T> rows = new ArrayList<>();
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = prepare(connection, query, params);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                rows.add(collector.collect(rs));
            }
        }

        return rows;
    }

    public int queryInt(String query, Object... params) throws SQLException {
        int result = 0;
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = prepare(connection, query, params);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }

        return result;
    }
    
    public int update(String query, Object... params) throws SQLException {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = prepare(connection, query, params)) {
            return stmt.executeUpdate();
        }
    }

    public int insert(String query, Object... params) throws SQLException {
        int generatedKey = -1;
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = prepareInsert(connection, query, params)) {
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }
            }
        }

        return generatedKey;
    }

    private PreparedStatement prepare(Connection connection, String query, Object[] params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        setParameters(stmt, params);
        return stmt;
    }

    private PreparedStatement prepareInsert(Connection connection, String query, Object[] params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        setParameters(stmt, params);
        return stmt;
    }

    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

}
