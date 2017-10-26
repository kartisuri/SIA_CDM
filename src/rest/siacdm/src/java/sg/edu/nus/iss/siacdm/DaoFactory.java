package sg.edu.nus.iss.siacdm;

import sg.edu.nus.iss.siacdm.user.RoleMemberDao;
import sg.edu.nus.iss.siacdm.user.RoleMemberDaoMySql;
import sg.edu.nus.iss.siacdm.user.UserDao;
import sg.edu.nus.iss.siacdm.user.UserDaoMySql;
import sg.edu.nus.iss.siacdm.account.AccountDao;
import sg.edu.nus.iss.siacdm.account.AccountDaoMySql;
import sg.edu.nus.iss.siacdm.task.TaskDaoMySql;
import sg.edu.nus.iss.siacdm.schedule.ScheduleDao;
import sg.edu.nus.iss.siacdm.schedule.ScheduleDaoMySql;
import sg.edu.nus.iss.siacdm.summary.SummaryDao;
import sg.edu.nus.iss.siacdm.summary.SummaryDaoMySql;
import sg.edu.nus.iss.siacdm.task.TaskDao;

public class DaoFactory {

  public static AccountDao getAcountDao() {
    return new AccountDaoMySql();
  }

  public static TaskDao getTaskDao() {
    return new TaskDaoMySql();
  }
  
  public static ScheduleDao getScheduleDao() {
    return new ScheduleDaoMySql();
  }
  
  public static SummaryDao getSummaryDao() {
    return new SummaryDaoMySql();
  }

  public static UserDao getUserDao() {
    return new UserDaoMySql();
  }

  public static RoleMemberDao getRoleMemberDao() {
    return new RoleMemberDaoMySql();
  }
}
