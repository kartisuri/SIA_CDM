package sg.edu.nus.iss.siacdm.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {

  public T map(ResultSet resultSet) throws SQLException;
}
