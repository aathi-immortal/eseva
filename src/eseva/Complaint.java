
public class Complaint {
    private int id;
    private int userId;
    private String description;
    private String status;

    public Complaint(int id, int userId, String description, String status) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint [id= " + id + ", userId= " + userId + ", description= " + description + ", status=" + status
                + "]";
    }

}
