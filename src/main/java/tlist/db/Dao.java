package tlist.db;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    T find(int id) throws SQLException;

    List<T> findAll() throws SQLException;
    
    public int delete(int id) throws SQLException;

}
