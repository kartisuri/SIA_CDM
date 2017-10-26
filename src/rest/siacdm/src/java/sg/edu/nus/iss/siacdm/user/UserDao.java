package sg.edu.nus.iss.siacdm.user;

import java.util.List;
import sg.edu.nus.iss.siacdm.model.User;

public interface UserDao {

    public List<User> findAll();

    public User findOne(User record);    

    public User createUser(User newRecord);

    public User updateUser(User record);

    public void deleteUser(User record);

    public User findAssignedUser(User record);
}
