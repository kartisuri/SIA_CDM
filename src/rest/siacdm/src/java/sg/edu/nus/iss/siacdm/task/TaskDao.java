package sg.edu.nus.iss.siacdm.task;

import java.util.List;
import sg.edu.nus.iss.siacdm.model.Task;

public interface TaskDao {

  public List<Task> findAll();

  public Task findOne(Task example);

  public Task create(Task newRecord);

  public Task update(Task record);

  public void delete(Task record);
}
