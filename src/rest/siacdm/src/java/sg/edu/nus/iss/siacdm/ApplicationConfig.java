package sg.edu.nus.iss.siacdm;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(sg.edu.nus.iss.siacdm.account.AccountResource.class);
    resources.add(sg.edu.nus.iss.siacdm.schedule.ScheduleResource.class);
    resources.add(sg.edu.nus.iss.siacdm.summary.SummaryResource.class);
    resources.add(sg.edu.nus.iss.siacdm.task.TaskResource.class);
    resources.add(sg.edu.nus.iss.siacdm.user.RoleMemberResource.class);
    resources.add(sg.edu.nus.iss.siacdm.user.UserResource.class);
  }
}
