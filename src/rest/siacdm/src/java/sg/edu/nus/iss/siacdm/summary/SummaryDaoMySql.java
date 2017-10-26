package sg.edu.nus.iss.siacdm.summary;

import sg.edu.nus.iss.siacdm.model.Summary;
import sg.edu.nus.iss.siacdm.util.MySqlQuery;

public class SummaryDaoMySql implements SummaryDao {

  @Override
  public Summary findOne() {
    String sql
        = "select \n"
        + "(select count(1) from siacdm.user) as totalUsers,\n"
        + "(select count(1) from siacdm.schedule) as totalSlots,\n"
        + "(select count(1) from siacdm.task) as totalTasks";
    
    MySqlQuery query = new MySqlQuery(sql, 
        (r) -> {
          Summary s = new Summary();
          s.setTotalUsers(r.getInt("totalUsers"));
          s.setTotalSlots(r.getInt("totalSlots"));
          s.setTotalTasks(r.getInt("totalTasks"));          
          return s;
        });
    return query.findOne();    
  }
}
