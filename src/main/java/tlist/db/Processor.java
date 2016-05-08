package tlist.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Processor {

    void process(ResultSet rs) throws SQLException;

}
