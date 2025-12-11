package src.DAO;
import src.MySqlConnect;
import src.Users.Student;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StudentDAO{
    private final Connection connection;

    public StudentDAO() {
        this.connection = MySqlConnect.getMysqlConnection();  // reference to singleton
    }

    // this will only be used once and it's when there is a confirmed id for the email
    public int getStudentIDFromEmail(String email){
        String sql = "SELECT `StudentID` FROM `Student` WHERE `Email` = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("StudentID");
                return id;
            } else {
                return -1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public Student getStudent(int studentID){
        String sql = "SELECT * FROM `Student` WHERE `StudentID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Student student = new Student();
                student.setUserID(resultSet.getInt("StudentID"));
                student.setForename(resultSet.getString("FirstName"));
                student.setSurname(resultSet.getString("Surname"));
                student.setGender(resultSet.getString("Gender"));
                student.setEmail(resultSet.getString("Email"));
                student.setPassword(resultSet.getString("PWord").toCharArray());
                student.setStudentType(resultSet.getString("StudentType"));
                student.setDOB(resultSet.getDate("DOB"));
                student.setCourseDecision(resultSet.getString("CourseDecision"));
                return student;
            }
            else{
                System.out.println("No student with this ID");
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getnoOfModules(int studentID){

        String sql = "SELECT COUNT(ModuleID) AS noOfModules FROM `StudentModule` WHERE `StudentID` = ?";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return -1;
            } else {
                return resultSet.getInt("noOfModules");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Integer> getModules(int studentID){
        String sql = "SELECT ModuleID FROM `StudentModule` WHERE `StudentID` = ?";

        if (connection == null) {
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                // Create module arraylist
                ArrayList<Integer> modules = new ArrayList<>();
                do {
                    // add modules one at a time
                    modules.add(resultSet.getInt("ModuleID"));
                } while(resultSet.next());
                return modules;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
//    public int[] getModules(int studentID){
//        String sql = "SELECT ModuleID FROM `StudentModule` WHERE `StudentID` = ?";
//
//        if (connection == null) {
//            System.out.println("Connection is null");
//        }
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, studentID);
//            ResultSet resultSet = statement.executeQuery();
//            if (!resultSet.next()) {
//                return null;
//            } else {
//                 resultSet.getArray(1);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public JButton[] createModuleButtons(int studentID){
//        int noOfModules = 0;
//
//        String sql = "SELECT COUNT(ModuleID) AS noOfModules FROM `StudentModule` WHERE `StudentID` = ?";
//
//        if(connection==null){
//            System.out.println("Connection is null");
//        }
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, studentID);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                noOfModules = resultSet.getInt("noOfModules");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        JButton[] buttons = new JButton[noOfModules];
//
//        sql = "SELECT ModuleID FROM StudentModule WHERE StudentID = ?";
//
//        if(connection==null){
//            System.out.println("Connection is null");
//        }
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, studentID);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                for(int i = 0; i < noOfModules; i++){
//                    buttons[i] = new JButton(resultSet.getString("ModuleID"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return buttons;
//    }


    public boolean setStudent(int StudentID, String FirstName, String Surname, String Gender, String Email, Date DOB, String PWord, String StudentType, String CourseDecision){
        String sql =  "INSERT INTO `Student` (`StudentID`, `FirstName`, `Surname`, `Gender`, `Email`, `DOB`, `PWord`, `StudentType`, `CourseDecision`, `Manager`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, StudentID);
            statement.setString(2, FirstName);
            statement.setString(3, Surname);
            statement.setString(4, Gender);
            statement.setString(5, Email);
            statement.setDate(6, DOB);
            statement.setString(7, PWord);
            statement.setString(8, StudentType);
            statement.setString(9, CourseDecision);
            statement.setString(10, "4");
            int rowsInserted = statement.executeUpdate();
            System.out.println("Student has been added to the database");
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudent(int StudentID, String columnName, String value){
        String sql = "UPDATE `Student` SET " +columnName+ " = ? WHERE `StudentID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, value);
            statement.setInt(2, StudentID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteStudent(int StudentID){
        String sql = "DELETE FROM `Student` WHERE `StudentID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,StudentID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginStudent(String email, char[] password){
        String sql = "SELECT * FROM `Student` WHERE `Email` = ? AND `PWord` = ? AND `Active` = 1 AND `Approved` = 1";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            statement.setString(2, new String(password));
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();  // returns true if student is found
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public int getStudentCourse(int studentID) {    // Get the ID of course student is on
        String sql = "SELECT * FROM `StudentCourse` WHERE `StudentID` = ?";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return -1;
            } else {
                return resultSet.getInt("CourseID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public InputStream getLabNoteBlob(int weekID, int moduleID){
        String sql = "SELECT LabNote FROM `Week` WHERE weekID = ? AND ModuleID = ?";

        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, weekID);
            statement.setInt(2, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Blob blob = resultSet.getBlob(1);
                InputStream inputStream = blob.getBinaryStream();
                return inputStream;
            }
            else{
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getWeeks(int moduleID){
        String sql = "SELECT weekID FROM `Week` WHERE ModuleID = ?";

        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                // Create weeks arraylist
                ArrayList<Integer> weeks = new ArrayList<>();
                do {
                    // add modules one at a time
                    weeks.add(resultSet.getInt(1));
                } while(resultSet.next());
                return weeks;
            }
            else{
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public InputStream getLectureNoteBlob(int weekID, int moduleID){
        String sql = "SELECT LectureNote FROM `Week` WHERE weekID = ? AND ModuleID = ?";

        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, weekID);
            statement.setInt(2, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Blob blob = resultSet.getBlob(1);

                InputStream inputStream = blob.getBinaryStream();
                return inputStream;
            }
            else{
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    // Testing
//    public static void main(String[] args) {
//            StudentDAO dao = new StudentDAO();
//            dao.getStudents(1234);
//
//            dao.setStudent(6969, "Tohru", "Adachi","Male", "beachesandshores@outlook.com", Date.valueOf(("1984-02-01")), "password", "0");
//            dao.updateStudent(6969, "CourseDecision", "1");
//        }
}