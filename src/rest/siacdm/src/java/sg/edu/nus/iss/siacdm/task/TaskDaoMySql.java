package sg.edu.nus.iss.siacdm.task;

import java.util.List;
import sg.edu.nus.iss.siacdm.model.Task;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;
import sg.edu.nus.iss.siacdm.util.ParameterSetter;
import sg.edu.nus.iss.siacdm.util.ResultSetMapper;

public class TaskDaoMySql implements TaskDao {

  @Override
  public Task create(Task newRecord) {
    String sql
      = "insert into siacdm.task\n"
      + "(\n"
      + "  name,\n"
      + "  description,\n"
      + "  typical_duration,\n"
      + "  updated_by\n"
      + ")\n"
      + "values\n"
      + "(\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?\n"
      + ")";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setString(1, newRecord.getName());
        s.setString(2, newRecord.getDescription());
        s.setInt(3, newRecord.getDuration());
        s.setString(4, newRecord.getUpdatedBy());
      });
    query.update();
    Task example = new Task();
    example.setName(newRecord.getName());
    Task created = findOne(example);
    return created;
  }

  @Override
  public Task update(Task newRecord) {
    String sql
      = "update \n"
      + "  siacdm.task\n"
      + "set\n"
      + "  name = ?,\n"
      + "  description = ?,\n"
      + "  typical_duration = ?,\n"
      + "  updated_by = ?\n"
      + "where task_id = ?";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setString(1, newRecord.getName());
        s.setString(2, newRecord.getDescription());
        s.setInt(3, newRecord.getDuration());
        s.setString(4, newRecord.getUpdatedBy());
        s.setInt(5, newRecord.getId());
      });
    query.update();
    Task example = new Task();
    example.setId(newRecord.getId());
    Task updated = findOne(example);
    return updated;
  }

  @Override
  public void delete(Task record) {
    String sql = "delete from siacdm.task where task_id = ?";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getId());
      });
    query.update();
  }

  @Override
  public List<Task> findAll() {
    String findSql = prepareFindSql(null);
    ResultSetMapper mapper = prepareFindResultMapper();
    MySqlQuery query = new MySqlQuery(findSql, mapper);
    return query.findAll();
  }

  @Override
  public Task findOne(Task example) {
    String findSql = prepareFindSql(example);
    ParameterSetter parameters = prepareFindParameters(example);
    ResultSetMapper mapper = prepareFindResultMapper();
    MySqlQuery query = new MySqlQuery(findSql, parameters, mapper);
    return query.findOne();
  }

  private ParameterSetter prepareFindParameters(Task p) {
    // IMPORTANT: 
    // the if-else logic and sequence of s.setXyz method call 
    // must be the same as 
    // the if-else logic and sequence of sb.append method call 
    // in prepareFindSql() 
    // because we are setting parameter values based on the sql statement
    // generated in prepareFindSql()
    return (s) -> {
      int i = 1;
      if (p.getId() > 0) {
        s.setInt(i++, p.getId());
      }
      if (p.getName() != null) {
        s.setString(i++, p.getName());
      }
    };
  }

  private String prepareFindSql(Task p) {
    StringBuilder sb = new StringBuilder(
      "select\n"
      + "  t.task_id,\n"
      + "  t.name,\n"
      + "  t.description,\n"
      + "  t.typical_duration,\n"
      + "  t.updated_by,\n"
      + "(select count(1) from siacdm.schedule s\n"
      + "where s.task_id = t.task_id) as total_assigned\n"
      + "from\n"
      + "  siacdm.task t "
      + "where 1 = 1 ");

    if (p != null) {
      if (p.getId() > 0) {
        sb.append(" and t.task_id = ? ");
      }
      if (p.getName() != null) {
        sb.append(" and t.name = ? ");
      }
    }
    sb.append(" order by name");
    return sb.toString();
  }

  private ResultSetMapper prepareFindResultMapper() {
    return (r) -> {
      Task p = new Task();
      p.setId(r.getInt("task_id"));
      p.setName(r.getString("name"));
      p.setDescription(r.getString("description"));
      p.setDuration(r.getInt("typical_duration"));
      p.setUpdatedBy(r.getString("updated_by"));
      p.setAssignedCount(r.getInt("total_assigned"));
      return p;
    };
  }
}
