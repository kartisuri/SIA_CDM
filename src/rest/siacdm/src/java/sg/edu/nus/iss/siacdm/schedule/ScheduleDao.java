package sg.edu.nus.iss.siacdm.schedule;

import java.util.List;
import sg.edu.nus.iss.siacdm.model.Schedule;

public interface ScheduleDao {

  public List<Schedule> findAll();

  public Schedule findOne(Schedule record);

  public Schedule create(Schedule newRecord);

  public Schedule update(Schedule record);

  public void delete(Schedule record);

  public List<Schedule> findRange(Schedule record);

  public List<Schedule> findOverlap(Schedule record);

  public List<Schedule> findAllSchedulesTask(Schedule record);

  public List<Schedule> findAllSchedulesUser(Schedule record);

  public void copy(Schedule record, int interval);
}
