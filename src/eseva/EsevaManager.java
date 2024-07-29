
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EsevaManager implements IEsevaManager {
    @Override
    public User addUser(User user) {
        String sql = "INSERT INTO users(name, contact) VALUES(?, ?)";

        try (Connection conn = DBHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getContact());
            pstmt.executeUpdate();
            try (ResultSet generatedKey = pstmt.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    int id = generatedKey.getInt(1);
                    user.setId(id);

                } else {
                    throw new SQLException("Creating user Failed");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User created Successfully");
        return user;
    }

    @Override
    public Complaint addComplaint(Complaint complaint) {
        String sql = "INSERT INTO complaints(userId, description, status) VALUES(?, ?, ?)";

        try (Connection conn = DBHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, complaint.getUserId());
            pstmt.setString(2, complaint.getDescription());
            pstmt.setString(3, complaint.getStatus());
            pstmt.executeUpdate();
            try (ResultSet generatedKey = pstmt.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    complaint.setId(generatedKey.getInt(1));
                } else {
                    throw new SQLException("Complaint not Created");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return complaint;
    }

    @Override
    public List<Complaint> getComplaintsByUserId(int userId) {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints WHERE userId= ?";

        try (Connection conn = DBHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Complaint complaint = new Complaint(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("description"),
                        rs.getString("status"));
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return complaints;
    }

    @Override
    public void updateComplaintStatus(int complaintId, String status) {
        String sql = "UPDATE complaints SET status = ? WHERE id = ?";

        try (Connection conn = DBHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, complaintId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void generateReport() {
        String sql = "SELECT users.id as userId,complaints.id as complaintId,users.name, complaints.description, complaints.status "
                +
                "FROM complaints " +
                "INNER JOIN users ON complaints.userId = users.id";

        try (Connection conn = DBHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ComplaintId\t UserId\t\t User\t\t Description\t\tStatus");
            while (rs.next()) {
                System.out.println(
                        rs.getString("complaintId") + "\t\t " + rs.getString("userId") + "\t\t" + rs.getString("name")
                                + "\t\t  " +
                                rs.getString("description") + "\t\t  " +
                                rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
