package src.DAO;
import src.MySqlConnect;
import src.Users.Lecturer;

import java.sql.*;
import java.time.*;

public class LecturerDAO {
    private final Connection connection;

    public LecturerDAO() {
        this.connection = MySqlConnect.getMysqlConnection();  // reference to singleton
    }

    public Lecturer getLecturer(int lecturerID){
        String sql = "SELECT * FROM `Lecturer` WHERE `LecturerID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, lecturerID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Lecturer lecturer = new Lecturer();
                lecturer.setUserID(resultSet.getInt("LecturerID"));
                lecturer.setForename(resultSet.getString("FirstName"));
                lecturer.setSurname(resultSet.getString("Surname"));
                lecturer.setGender(resultSet.getString("Gender"));
                lecturer.setEmail(resultSet.getString("Email"));
                lecturer.setDOB(resultSet.getDate("DOB"));
                lecturer.setPassword(resultSet.getString("PWord").toCharArray());
                lecturer.setQualification(resultSet.getString("Qualifications"));
                return lecturer;
            }
            else{
                System.out.println("No lecturer with this ID");
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // this will only be used once and it's when there is a confirmed id for the email
    public int getLecturerIDFromEmailL(String email){
        String sql = "SELECT `LecturerID` FROM `Lecturer` WHERE `Email` = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("LecturerID");
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

    public boolean setLecturer(int LecturerID, String FirstName, String Surname,String Gender, String Email,Date DOB, String PWord,String Qualifications){
        String sql =  "INSERT INTO `Lecturer` (`LecturerID`, `FirstName`, `Surname`, `Gender`, `Email`, `DOB`, `PWord`, `Qualifications`, `Manager`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, LecturerID);
            statement.setString(2, FirstName);
            statement.setString(3, Surname);
            statement.setString(4, Gender);
            statement.setString(5, Email);
            statement.setDate(6, DOB);
            statement.setString(7, PWord);
            statement.setString(8, Qualifications);
            statement.setString(9, "4");
            int rowsInserted = statement.executeUpdate();
            System.out.println("Lecturer has been added to the database");
            return rowsInserted > 0;

        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLecturer(int LecturerID, String columnName, String value){
        String sql = "UPDATE `Lecturer` SET " +columnName+ " = ? WHERE `LecturerID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, value);
            statement.setInt(2, LecturerID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLecturer(int LecturerID){
        String sql = "DELETE FROM `Lecturer` WHERE `LecturerID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,LecturerID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginLecturer(String email, char[] password){
        String sql = "SELECT * FROM `Lecturer` WHERE `Email` = ? AND `PWord` = ? AND `Active` = 1 AND `Approved` = 1";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            statement.setString(2, new String(password));
            ResultSet resultSet = statement.executeQuery();
            System.out.println(sql);
            return resultSet.next();  // returns true if student is found
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateResults(int studentID, int moduleID, int result, String type){
        String sql = "UPDATE `StudentModule` SET " +type+ " = ? WHERE `StudentID` = ? AND `ModuleID` = ?";


        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, result);
            statement.setInt(2,studentID);
            statement.setInt(3, moduleID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // If module is marked by both then add the exam and lab results together to get final result
    public boolean addResults(int studentID, int moduleID){
        boolean success2;
        int result1 = 0;
        int result2 = 0;

        String sql = "UPDATE `StudentModule` SET `Result`= ? + ? WHERE `StudentID` = ? AND `ModuleID` = ?";

        if(connection==null){
            System.out.println("Connection is null");
        }

        String sql2 = "SELECT ExamResult, LabResult FROM `StudentModule` WHERE StudentID=? AND ModuleID=?";

        try(PreparedStatement statement2 = connection.prepareStatement(sql2)){
            statement2.setInt(1, studentID);
            statement2.setInt(2, moduleID);
            ResultSet resultSet = statement2.executeQuery();
            if (resultSet.next()) {
                result1 = resultSet.getInt("ExamResult");
                result2 = resultSet.getInt("LabResult");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try(PreparedStatement statement1 = connection.prepareStatement(sql)){
            statement1.setInt(1, result1);
            statement1.setInt(2, result2);
            statement1.setInt(3, studentID);
            statement1.setInt(4, moduleID);
            int rowsUpdated2 = statement1.executeUpdate();
            success2 = rowsUpdated2 > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if (success2){
            return true;
        }
        return false;
    }

    }

    // PURELY FOR TESTING THAT IT WORKS WITH THE DATABASE
    /*public static void main(String[] args) {
        LecturerDAO dao = new LecturerDAO();
        dao.getLecturer(308);
        dao.getLecturer(999);

        //dao.deleteLecturer(312);
        dao.setLecturer(312, "Kyle", "McCann", "Male", "kylerandom@gmail.com", Date.valueOf(("2010-01-01")), "randompassword", "Doesn't Need One");
        dao.updateLecturer(312 ,"FirstName","Martin");
    }*/