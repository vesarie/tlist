package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Joiner<U, V> {

    void join(ResultSet rs) throws SQLException;

}
