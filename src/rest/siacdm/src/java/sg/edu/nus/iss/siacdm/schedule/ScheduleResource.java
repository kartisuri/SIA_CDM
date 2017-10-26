package sg.edu.nus.iss.siacdm.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.siacdm.DaoFactory;
import sg.edu.nus.iss.siacdm.model.Schedule;

@Path("/")
public class ScheduleResource {
  private ScheduleDao dao;

  public ScheduleResource() {
    dao = DaoFactory.getScheduleDao();
  }

  @Path("schedules")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Schedule> findAll() {
    List<Schedule> schedules;
    schedules = dao.findAll();
    return schedules;
  }

  @Path("schedules/week/{weekNumber}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Schedule> findWeek(@PathParam("weekNumber") int weekNumber) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    c.set(Calendar.WEEK_OF_YEAR, weekNumber);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    Date startDate = c.getTime();
    c.add(Calendar.DATE, 6); // adding 6 days to Monday gives us Sunday
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);
    Date endDate = c.getTime();
    List<Schedule> schedules;
    Schedule example = new Schedule();
    example.setStart(startDate);
    example.setEnd(endDate);
    schedules = dao.findRange(example);
    return schedules;
  }

  @Path("schedules/task/{taskId}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Schedule> findAllSchedulesTask(@PathParam("taskId") int taskId) {

    List<Schedule> schedules;
    Schedule example = new Schedule();
    example.setTaskId(taskId);
    schedules = dao.findAllSchedulesTask(example);
    return schedules;
  }

  @Path("schedules/user/{userId}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Schedule> findAllSchedulesUser(@PathParam("userId") String userId) {
    List<Schedule> schedules;
    Schedule example = new Schedule();
    example.setTechnicianId(userId);
    schedules = dao.findAllSchedulesUser(example);
    return schedules;
  }
  
  @Path("schedule/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Schedule findOne(@PathParam("id") int id) {
    Schedule example = new Schedule();
    example.setId(id);
    Schedule result;
    result = dao.findOne(example);
    return result;
  }
  
  @Path("schedule")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Schedule create(Schedule schedule) {
    Schedule result = dao.create(schedule);
    return result;
  }

  @Path("schedule")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Schedule update(Schedule schedule) {
    Schedule result = dao.update(schedule);
    return result;
  }

  @Path("schedule/overlap")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Schedule> overlap(Schedule schedule) {
    List<Schedule> schedules = dao.findOverlap(schedule);
    return schedules;
  }

  @Path("schedule/{id}")
  @DELETE
  public void delete(@PathParam("id") int id) {
    Schedule example = new Schedule();
    example.setId(id);
    dao.delete(example);
  }

  @Path("schedules/copy/week/{sourceWeekNumber}/{targetWeekNumber}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public void copyWeek(
    @PathParam("sourceWeekNumber") int sourceWeekNumber,
    @PathParam("targetWeekNumber") int targetWeekNumber) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    c.set(Calendar.WEEK_OF_YEAR, sourceWeekNumber);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    Date startDate = c.getTime();
    c.add(Calendar.DATE, 6); // adding 6 days to Monday gives us Sunday
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);
    Date endDate = c.getTime();
    Schedule example = new Schedule();
    example.setStart(startDate);
    example.setEnd(endDate);
    int interval = (targetWeekNumber - sourceWeekNumber) * 7;
    dao.copy(example, interval);
  }
}
