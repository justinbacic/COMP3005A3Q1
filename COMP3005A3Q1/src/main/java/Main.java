import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static String url = "jdbc:postgresql://localhost:5432/A3Q1";
    static String user = "postgres";
    static String password = "password";
    public static void main(String[] args){
        menu();
    }
    //Menu function for user interaction
    public static void menu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("What operation would you like to preform?\n1. Get all students\n2. Add student\n3. Update student email\n4. Delete student\n5. Exit");
        String choice = scan.nextLine();
        while(!choice.equals("5")){
            if(choice.equals("1")){
                getAllStudents();
            }else if(choice.equals("2")){
                System.out.println("New students first name");
                String fname = scan.nextLine();
                System.out.println("New students last name");
                String lname = scan.nextLine();
                System.out.println("New students email");
                String email = scan.nextLine();
                System.out.println("New students enrollment date (yyyy-mm-dd)");
                String date = scan.nextLine();
                addStudent(fname,lname,email,date);
            }else if(choice.equals("3")){
                System.out.println("Enter the ID of the student who's email you want to update");
                String id = scan.nextLine();
                System.out.println("Enter the new email for the student");
                String email = scan.nextLine();
                updateStudentEmail(id,email);
            }else if(choice.equals("4")){
                System.out.println("Enter the ID of the student you want to delete");
                String id = scan.nextLine();
                deleteStudent(id);
            }else{
                System.out.println("Input not valid");
            }
            System.out.println("What operation would you like to preform?\n1. Get all students\n2. Add student\n3. Update student email\n4. Delete student\n5. Exit");
            choice = scan.nextLine();
        }
    }
    //getAllStudents(): Retrieves and displays all records from the students table.
    public static void getAllStudents(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students;");
            ResultSet resultSet = statement.getResultSet();
            System.out.println("student_id \t first_name \t last_name \t email \t enrollment_date");
            while(resultSet.next()){
                System.out.println(resultSet.getString("student_id")+"\t"+resultSet.getString("first_name")+"\t"+resultSet.getString("last_name") +"\t"+resultSet.getString("email")+"\t"+resultSet.getString("enrollment_date"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    //addStudent(first_name, last_name, email, enrollment_date): Inserts a new student record into the students table.
    public static void addStudent(String first_name,String last_name,String email,String enrollment_date){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ( '" + first_name + "', '"+last_name+"', '"+email+"', '" + enrollment_date+"');");
            System.out.println("Insertion successful");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    //updateStudentEmail(student_id, new_email): Updates the email address for a student with the specified student_id.
    public static void updateStudentEmail(String student_id, String new_email){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET email = '" +new_email+"' WHERE student_id ='" +student_id+"'");
            System.out.println("Update successful");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    //deleteStudent(student_id): Deletes the record of the student with the specified student_id.
    public static void deleteStudent(String student_id){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM students WHERE student_id ='" +student_id+"'");
            System.out.println("Deletion successful");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
