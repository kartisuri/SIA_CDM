package sg.edu.nus.iss.siacdm.schedule;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import sg.edu.nus.iss.siacdm.model.Schedule;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class ScheduleDaoMySql implements ScheduleDao {

  @Override
  public List<Schedule> findAll() {
    String sql = "select s.slot_id, s.task_id, t.name as task_name,\n"
      + "s.technician_id, te.name as technician_name, s.remarks,\n"
      + "s.start, s.end, s.updated_by\n"
      + "from siacdm.schedule s left join siacdm.task t\n"
      + "on t.task_id = s.task_id left join siacdm.user te\n"
      + "on te.id = s.technician_id;";

    MySqlQuery query = new MySqlQuery(sql,
      (r) -> {
        Schedule p = new Schedule();
        p.setId(r.getInt("slot_id"));
        p.setTechnicianId(r.getString("technician_id"));
        p.setRemarks(r.getString("remarks"));
        p.setTaskId(r.getInt("task_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setTaskName(r.getString("task_name"));
        p.setTechnicianName(r.getString("technician_name"));
        return p;
      });

    return query.findAll();
  }

  @Override
  public Schedule findOne(Schedule record) {
    String sql = "select s.slot_id, s.task_id, t.name as task_name,\n"
      + "s.technician_id, te.name as technician_name, s.remarks,\n"
      + "s.start, s.end, s.updated_by\n"
      + "from siacdm.schedule s left join siacdm.task t\n"
      + "on t.task_id = s.task_id left join siacdm.user te\n"
      + "on te.id = s.technician_id where s.slot_id = ?;";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getId());
      },
      (r) -> {
        Schedule p = new Schedule();
        p.setId(r.getInt("slot_id"));
        p.setTechnicianId(r.getString("technician_id"));
        p.setRemarks(r.getString("remarks"));
        p.setTaskId(r.getInt("task_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setTaskName(r.getString("task_name"));
        p.setTechnicianName(r.getString("technician_name"));
        return p;
      });

    return query.findOne();
  }

  @Override
  public List<Schedule> findOverlap(Schedule record) {
    String sql = "select s.slot_id, s.task_id, t.name as task_name,\n"
      + "s.technician_id, te.name as technician_name, s.remarks,\n"
      + "s.start, s.end, s.updated_by\n"
      + "from siacdm.schedule s left join siacdm.task t\n"
      + "on t.task_id = s.task_id left join siacdm.user te\n"
      + "on te.id = s.technician_id where\n"
      + "((s.start between ? and ?)\n"
      + "or (s.end between ? and ?)\n"
      + "or (s.start = ? and s.end = ?)\n"
      + "or (s.start <= ? and s.end >= ?)\n"
      + "or (s.start >= ? and s.end <= ?))\n"
      + "and s.technician_id = ?;";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setTimestamp(1,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(2,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(3,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(4,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(5,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(6,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(7,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(8,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(9,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(10,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setString(11, record.getTechnicianId());
      },
      (r) -> {
        Schedule p = new Schedule();
        p.setId(r.getInt("slot_id"));
        p.setTechnicianId(r.getString("technician_id"));
        p.setRemarks(r.getString("remarks"));
        p.setTaskId(r.getInt("task_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setTaskName(r.getString("task_name"));
        p.setTechnicianName(r.getString("technician_name"));
        return p;
      });
    return query.findAll();
  }

  @Override
  public List<Schedule> findRange(Schedule record) {
    String sql = "select s.slot_id, s.task_id, t.name as task_name,\n"
      + "s.technician_id, te.name as technician_name, s.remarks,\n"
      + "s.start, s.end, s.updated_by,\n"
      + "date_format(s.start,'%a') as day_name\n"
      + "from siacdm.schedule s left join siacdm.task t\n"
      + "on t.task_id = s.task_id left join siacdm.user te\n"
      + "on te.id = s.technician_id where (s.start between ? and ?)\n"
      + "or (s.end between ? and ?);";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setTimestamp(1,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(2,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(3,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(4,
          new java.sql.Timestamp(record.getEnd().getTime()));
      },
      (r) -> {
        Schedule p = new Schedule();
        p.setId(r.getInt("slot_id"));
        p.setTechnicianId(r.getString("technician_id"));
        p.setRemarks(r.getString("remarks"));
        p.setTaskId(r.getInt("task_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setTaskName(r.getString("task_name"));
        p.setTechnicianName(r.getString("technician_name"));
        p.setDayName(r.getString("day_name"));
        return p;
      });
    return query.findAll();
  }

  @Override
  public Schedule update(Schedule newRecord) {
    String sql = "update siacdm.schedule set\n"
      + "task_id = ?,\n"
      + "technician_id = ?,\n"
      + "remarks =?,\n"
      + "updated_by = ?,\n"
      + "start = ?,\n"
      + "end = ?\n"
      + "where (slot_id = ?);";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, newRecord.getTaskId());
        s.setString(2, newRecord.getTechnicianId());
        s.setString(3, newRecord.getRemarks());
        s.setString(4, newRecord.getUpdatedBy());
        s.setTimestamp(5, new java.sql.Timestamp(
          newRecord.getStart().getTime()));
        s.setTimestamp(6, new java.sql.Timestamp(
          newRecord.getEnd().getTime()));
        s.setInt(7, newRecord.getId());
      });
    query.update(newRecord);
    Schedule example = new Schedule();
    example.setId(newRecord.getId());
    Schedule updated = findOne(example);
    return updated;
  }

  @Override
  public Schedule create(Schedule newRecord) {
    String sql = "insert into siacdm.schedule\n"
      + "(task_id, technician_id,\n"
      + "remarks, updated_by, start, end)\n"
      + " values (?,?,?,?,?,?);";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, newRecord.getTaskId());
        s.setString(2, newRecord.getTechnicianId());
        s.setString(3, newRecord.getRemarks());
        s.setString(4, newRecord.getUpdatedBy());
        s.setTimestamp(5,
          new java.sql.Timestamp(newRecord.getStart().getTime()));
        s.setTimestamp(6,
          new java.sql.Timestamp(newRecord.getEnd().getTime()));
      });
    return query.update(newRecord);
  }

  @Override
  public void delete(Schedule record) {
    String sql = "delete from siacdm.schedule\n"
      + "where (slot_id = ?);";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getId());
      });
    query.update();
  }

  @Override
  public List<Schedule> findAllSchedulesTask(Schedule record) {
    String sql = "select s.slot_id, s.task_id, t.name as task_name,\n"
      + "s.technician_id, te.name as technician_name, s.remarks,\n"
      + "s.start, s.end, s.updated_by\n"
      + "from siacdm.schedule s left join siacdm.task t\n"
      + "on t.task_id = s.task_id left join siacdm.user te\n"
      + "on te.id = s.technician_id where s.task_id = ?;";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getTaskId());
      },
      (r) -> {
        Schedule p = new Schedule();
        p.setId(r.getInt("slot_id"));
        p.setTechnicianId(r.getString("technician_id"));
        p.setRemarks(r.getString("remarks"));
        p.setTaskId(r.getInt("task_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setTaskName(r.getString("task_name"));
        p.setTechnicianName(r.getString("technician_name"));
        return p;
      });
    return query.findAll();
  }

  @Override
  public List<Schedule> findAllSchedulesUser(Schedule record) {
    String sql = "select s.slot_id, s.task_id, t.name as task_name,\n"
      + "s.technician_id, te.name as technician_name, s.remarks,\n"
      + "s.start, s.end, s.updated_by\n"
      + "from siacdm.schedule s left join siacdm.task t\n"
      + "on t.task_id = s.task_id left join siacdm.user te\n"
      + "on te.id = s.technician_id where s.technician_id = ?;";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setString(1, record.getTechnicianId());
      },
      (r) -> {
        Schedule p = new Schedule();
        p.setId(r.getInt("slot_id"));
        p.setTechnicianId(r.getString("technician_id"));
        p.setRemarks(r.getString("remarks"));
        p.setTaskId(r.getInt("task_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setTaskName(r.getString("task_name"));
        p.setTechnicianName(r.getString("technician_name"));
        return p;
      });
    return query.findAll();
  }
  
  @Override
  public void copy(Schedule record, int interval) {
    String sql = "insert into siacdm.schedule (task_id, technician_id,\n"
      + "remarks, updated_by, start, end) select s.task_id,\n"
      + "s.technician_id, s.remarks, s.updated_by,\n"
      + "date_add(s.start, interval ? day), date_add(s.end, interval ? day)\n"
      + "from siacdm.schedule s where s.start >= ? and s.end <= ?;";
    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, interval);
        s.setInt(2, interval);
        s.setTimestamp(3,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(4,
          new java.sql.Timestamp(record.getEnd().getTime()));
      });
    query.update();
  }
}
