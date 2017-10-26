package sg.edu.nus.iss.siacdm.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String password;
    private String fullName;
    private String phone;
    private List<Role> roles;
    private boolean authenticated;
    private int slots;
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public List<Role> getRoles(){
        if(roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }
    
    public void setRoles(List<Role> roles){
        this.roles = roles;
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }
    
    public int getSlots(){
        return slots;
    }
    
    public void setSlots(int programslots){
        this.slots = programslots;
    }
}
