
import java.util.List;

public interface IEsevaManager {
    public User addUser(User user);

    public Complaint addComplaint(Complaint complaint);

    public List<Complaint> getComplaintsByUserId(int userId);

    public void updateComplaintStatus(int complaintId, String status);

    public void generateReport();

}
