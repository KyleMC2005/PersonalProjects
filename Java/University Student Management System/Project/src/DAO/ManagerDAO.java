package src.DAO;
import src.MySqlConnect;
import src.Users.Lecturer;
import src.Users.Manager;
import src.Users.User;

import java.sql.*;
import java.util.ArrayList;

public class ManagerDAO {
    private final Connection connection;

    public ManagerDAO() {
        this.connection = MySqlConnect.getMysqlConnection();  // reference to singleton
    }

    public Manager getManager(int managerID){
        String sql = "SELECT * FROM `Manager` WHERE `ManagerID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, managerID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Manager manager = new Manager();
                manager.setUserID(resultSet.getInt("ManagerID"));
//                manager.setForename(resultSet.getString("Forename"));
//                manager.setSurname(resultSet.getString("Surname"));
//                manager.setGender(resultSet.getString("Gender"));
//                manager.setEmail(resultSet.getString("Email"));
//                manager.setDOB(resultSet.getDate("DOB"));

                return manager;
            }
            else{
                System.out.println("No manager with this ID");
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // this will only be used once and it's when there is a confirmed id for the email
    public int getManagerIDFromEmail(String email){
        String sql = "SELECT `ManagerID` FROM `Manager` WHERE `Email` = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("ManagerID");
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

    public boolean setManager(int ManagerID, String email, String PWord){
        String sql =  "INSERT INTO `Manager` (`ManagerID`, `Email`, `PWord`, `Manager`) VALUES (?, ?, ?, ?)";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, ManagerID);
            statement.setString(2, email);
            statement.setString(3, PWord);
            statement.setString(4, "4");
            int rowsInserted = statement.executeUpdate();
            System.out.println("Manager has been added to the database");
            return rowsInserted > 0;

        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateManager(int ManagerID, String columnName, String value){
        String sql = "UPDATE `Manager` SET " +columnName+ " = ? WHERE `ManagerID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, value);
            statement.setInt(2, ManagerID);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Manager has been updated to the database");
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteManager(int ManagerID){
        String sql = "DELETE FROM `Manager` WHERE `ManagerID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,ManagerID);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Manager has been deleted from the database");
            return rowsDeleted > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginManager(String email, char[] password) {
        String sql = "SELECT * FROM `Manager` WHERE `Email` = ? AND `PWord` = ? AND `Active` = 1;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, new String(password));
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();  // returns true if student is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> findUnapprovedUsers(){
        String sql = "SELECT `FirstName`, `Surname`, `Approved`, `StudentID` AS ID FROM Student WHERE Approved = 0 UNION SELECT `FirstName`, `Surname`, `Approved`, `LecturerID` FROM Lecturer WHERE Approved = 0 ORDER BY FirstName";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if(!resultSet.next()){
                return null;
            }
            else{
                // Create arraylist of users
                ArrayList<User> unapproved = new ArrayList<>();
                do {
                    User currentUser = new User(resultSet.getInt("ID"), resultSet.getString("FirstName"), resultSet.getString("Surname"), "", "", null, null);
                    unapproved.add(currentUser);
                } while(resultSet.next());
                return unapproved;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // PURELY FOR TESTING THAT IT WORKS WITH THE DATABASE
    /*public static void main(String[] args) {
        ManagerDAO dao = new ManagerDAO();
        dao.getManager(308);
        dao.getManager(999);

        //dao.deleteManager(312);
        // dao.setManager(312, "asdasdbasdbasd");
        dao.updateManager(312 ,"ManagerID","5");
    }*/
}