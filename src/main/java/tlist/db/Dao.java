package tlist.db;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    T find(int id) throws SQLException;

    List<T> find() throws SQLException;

}
