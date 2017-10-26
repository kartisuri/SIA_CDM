package sg.edu.nus.iss.siacdm.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterSetter {

  public void SetParameters(PreparedStatement statement) throws SQLException;
}
