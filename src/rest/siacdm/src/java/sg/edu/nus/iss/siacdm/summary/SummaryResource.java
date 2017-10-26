package sg.edu.nus.iss.siacdm.summary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.siacdm.DaoFactory;
import sg.edu.nus.iss.siacdm.model.Summary;

@Path("/")
public class SummaryResource {
  private SummaryDao dao;

  public SummaryResource() {
    dao = DaoFactory.getSummaryDao();
  }

  @Path("summary")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Summary findOne() {
    Summary summary = dao.findOne();
    return summary;
  }
}
