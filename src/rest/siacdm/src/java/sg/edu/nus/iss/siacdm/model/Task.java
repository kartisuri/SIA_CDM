package sg.edu.nus.iss.siacdm.model;

public class Task {
  private int id;
  private String name;
  private String description;
  private int duration;
  private String updatedBy;
  private int assignedCount;

    public int getAssignedCount() {
        return assignedCount;
    }
    
    public void setAssignedCount(int assignedCount) {
        this.assignedCount = assignedCount;
    }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }  
}
