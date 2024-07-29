
import java.util.Scanner;

public class Main {
    static Scanner scan;
    static IEsevaManager manager;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        DBHelper.createTables();

        manager = new EsevaManager();

        int option = 0;
        do {
            System.out.println(
                    "\n\n\n1.Add User\n2.Add Complaint\n3.update Complaint status\n4.Generate report\n5.Exit\n");
            System.err.println("enter the option :");
            option = scan.nextInt();
            scan.nextLine();
            if (option == 1)
                addUser();
            else if (option == 2)
                addComplaint();
            else if (option == 3)
                updateComplaintStatus();
            else if (option == 4)
                generateReport();
        } while (option != 5);

        // Updating complaint status

        // Generating report

    }

    private static void generateReport() {
        manager.generateReport();
    }

    private static void updateComplaintStatus() {
        System.out.println("enter the complaint ID :");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("complaint Status :");
        String status = scan.nextLine();
        manager.updateComplaintStatus(id, status);

    }

    private static void addComplaint() {
        System.out.println("enter the userId :");
        int userId = scan.nextInt();
        scan.nextLine();
        System.out.println("Theft reported :");
        String description = scan.nextLine();

        Complaint complaint1 = new Complaint(0, userId, description, "Pending");
        System.out.println(manager.addComplaint(complaint1).toString());
    }

    private static void addUser() {
        System.out.println("enter the use Name :");
        String name = scan.nextLine();
        System.out.println("enter the phone Number :");
        String phoneNumber = scan.nextLine();
        User user1 = new User(0, name, phoneNumber);

        System.out.println(manager.addUser(user1).toString());

    }
}
