import java.sql.*;
import java.util.*;

class StudentManagement {

    static Scanner sc = new Scanner(System.in);

    static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        return DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE",
            "Sujoy",
            "Sujoy"
        );
    }

    // ADD STUDENT
    static void addStudent() {
        try {
            Connection con = getConnection();

            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            String sql = "INSERT INTO student VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, marks);

            int x = ps.executeUpdate();

            if (x > 0)
                System.out.println("Student added successfully");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // VIEW STUDENTS
    static void viewStudents() {
        try {
            Connection con = getConnection();

            String sql = "SELECT * FROM student ORDER BY id";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nID\tName\tMarks");
            System.out.println("----------------------");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getInt("marks")
                );
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // SEARCH STUDENT
    static void searchStudent() {
        try {
            Connection con = getConnection();

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql = "SELECT * FROM student WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nStudent Found");
                System.out.println("ID    : " + rs.getInt("id"));
                System.out.println("Name  : " + rs.getString("name"));
                System.out.println("Marks : " + rs.getInt("marks"));
            } else {
                System.out.println("Student not found");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // UPDATE STUDENT
    static void updateStudent() {
        try {
            Connection con = getConnection();

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Marks: ");
            int marks = sc.nextInt();

            String sql =
                "UPDATE student SET name = ?, marks = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, marks);
            ps.setInt(3, id);

            int x = ps.executeUpdate();

            if (x > 0)
                System.out.println("Student updated successfully");
            else
                System.out.println("Student not found");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // DELETE STUDENT
    static void deleteStudent() {
        try {
            Connection con = getConnection();

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM student WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int x = ps.executeUpdate();

            if (x > 0)
                System.out.println("Student deleted successfully");
            else
                System.out.println("Student not found");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // MAIN MENU
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}