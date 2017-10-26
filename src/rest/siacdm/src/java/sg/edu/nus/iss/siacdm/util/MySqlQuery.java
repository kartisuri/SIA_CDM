package sg.edu.nus.iss.siacdm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlQuery {

  private ParameterSetter parameters;
  private final ResultSetMapper mapper;
  private final String sql;

  public MySqlQuery(String sql, ParameterSetter parameters, ResultSetMapper mapper) {
    this.sql = sql;
    this.parameters = parameters;
    this.mapper = mapper;
  }

  public MySqlQuery(String sql, ResultSetMapper mapper) {
    this.sql = sql;
    this.parameters = null;
    this.mapper = mapper;
  }

  public MySqlQuery(String sql, ParameterSetter parameters) {
    this.sql = sql;
    this.parameters = parameters;
    this.mapper = null;
  }

  public MySqlQuery(String sql) {
    this.sql = sql;
    this.parameters = null;
    this.mapper = null;
  }

  private Connection openConnection() {
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.print(e.getMessage());
    }
    try {
      conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/siacdm", "phoenix", "password");
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }
    return conn;
  }

  public <T> T findOne() {
    PreparedStatement stmt = null;
    Connection conn = null;
    T result = null;
    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);
      if (parameters != null) {
        parameters.SetParameters(stmt);
      }
      ResultSet resultSet = stmt.executeQuery();

      if (resultSet.next()) {
        result = (T) mapper.map(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return result;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }

  public <T> List<T> findAll() {
    PreparedStatement stmt = null;
    Connection conn = null;
    List<T> results = new ArrayList<T>();
    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);
      if (parameters != null) {
        parameters.SetParameters(stmt);
      }
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        T item = (T) mapper.map(resultSet);
        results.add(item);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return results;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    return results;
  }

  public int update() {
    PreparedStatement stmt = null;
    Connection conn = null;
    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);
      if (parameters != null) {
        parameters.SetParameters(stmt);
      }
      int rowcount = stmt.executeUpdate();
      return rowcount;
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public <T> T update(T newRecord) {
    PreparedStatement stmt = null;
    Connection conn = null;
    T result = null;
    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);
      if (parameters != null) {
        parameters.SetParameters(stmt);
      }
      int rowcount = stmt.executeUpdate();
      result = newRecord;
    } catch (SQLException e) {
      e.printStackTrace();
      return result;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }
}
