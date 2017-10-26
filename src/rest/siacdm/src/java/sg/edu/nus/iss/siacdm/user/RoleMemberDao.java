package sg.edu.nus.iss.siacdm.user;

import java.util.List;
import sg.edu.nus.iss.siacdm.model.RoleMember;

public interface RoleMemberDao {

  public List<RoleMember> findAll(String roleId);
}
