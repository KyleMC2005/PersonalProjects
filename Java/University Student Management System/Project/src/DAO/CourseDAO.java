package src.DAO;
import src.MySqlConnect;
import src.Users.Course;
import src.Users.Module;
import src.Users.Student;

import java.sql.*;
import java.util.ArrayList;

public class CourseDAO {
    private final Connection connection;

    public CourseDAO() {
        this.connection = MySqlConnect.getMysqlConnection();  // reference to singleton
    }

    // Find and return a Course
    public Course getCourse(int courseID){
        String sql = "SELECT * FROM `Course` WHERE `courseID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Course course = new Course();
                course.setCourseID(resultSet.getInt("CourseID"));
                course.setCourseName(resultSet.getString("CourseName"));
                course.setDescription(resultSet.getString("CourseDesc"));
                course.setSemesters(resultSet.getInt("Semesters"));
                course.setCompModules(resultSet.getInt("CompensatedModules"));

                course.setModules(getModules(resultSet.getInt("CourseID")));
                course.setStudents(getStudents(resultSet.getInt("CourseID")));
                return course;
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

    // Creates an ArrayList with all the Courses in database
    public ArrayList<Course> getAllCourses(){
        String sql = "SELECT * FROM `Course`";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                ArrayList<Course> courses = new ArrayList<>();
                do {
                    courses.add(getCourse(resultSet.getInt("CourseID")));
                } while(resultSet.next());
                return courses;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

     // Returns an ArrayList of all modules part of a course
    public ArrayList<Module> getModules(int courseID){
        String sql = "SELECT * FROM `CourseModule` WHERE `CourseID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                ArrayList<Module> modules = new ArrayList<>();
                do {
                    ModuleDAO moduleDAO = new ModuleDAO();
                    modules.add(moduleDAO.getModule(resultSet.getInt("ModuleID")));
                } while(resultSet.next());
                return modules;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // Returns an ArrayList of all students part of a course
    public ArrayList<Student> getStudents(int courseID){
        String sql = "SELECT * FROM `StudentCourse` WHERE `CourseID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                ArrayList<Student> students = new ArrayList<>();
                do {
                    StudentDAO studentDAO = new StudentDAO();
                    students.add(studentDAO.getStudent(resultSet.getInt("StudentID")));
                } while(resultSet.next());

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean setCourse(int courseID, String courseName, String courseDesc, int semesters, String courseType){  // inserts new course
        String sql =  "INSERT INTO `Course` (`CourseID`, `CourseName`, `CourseDesc`, `Semesters`, `CourseType`) VALUES (?, ?, ?, ?, ?)";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, courseID);
            statement.setString(2, courseName);
            statement.setString(3, courseDesc);
            statement.setInt(4, semesters);
            statement.setString(5, courseType);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCourse(int courseID, String columnName, String value){  // updates a specific column based on the courseID
        String sql = "UPDATE `Course` SET " +columnName+ " = ? WHERE `courseID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, value);
            statement.setInt(2, courseID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCourse(int courseID){  // deletes a specific course
        String sql = "DELETE FROM `Course` WHERE `courseID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,courseID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Links a student to a course
    public boolean linkStudentToCourse(int studentID, int courseID) {
        String sql = "INSERT INTO `StudentCourse` (`StudentID`, `CourseID`) VALUES (?, ?)";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setInt(2, courseID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Unlinks a student to a course
    public boolean unlinkStudentToCourse(int studentID, int courseID) {
        String sql = "DELETE FROM `StudentCourse` WHERE `StudentID`=? AND `CourseID`=?";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setInt(2, courseID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // If a student is enrolled onto a course, automatically enroll them onto that course's modules
    public boolean enrollStudentToCourseModules(int studentID, int courseID){
        boolean result = false;
        ArrayList<Module> modules = getModules(courseID);      // Get all modules in a course
        for(Module module : modules) {
            String sql = "INSERT INTO `StudentModule` (`StudentID`, `ModuleID`) VALUES (?, ?)";
            if (connection == null) {
                System.out.println("Connection is null");
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, studentID);
                statement.setInt(2, module.getModuleID());
                int rowsInserted = statement.executeUpdate();
                result = rowsInserted > 0;
                if (!result) {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return result;
    }

    // If a student is unenrolled from a course, automatically unenroll them from that course's modules
    public boolean unenrollStudentToCourseModules(int studentID, int courseID){
        boolean result = false;
        ArrayList<Module> modules = getModules(courseID);      // Get all modules in a course
        for(Module module : modules) {
            String sql = "DELETE FROM `StudentModule` WHERE `StudentID`=? AND `ModuleID`=?";
            if (connection == null) {
                System.out.println("Connection is null");
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, studentID);
                statement.setInt(2, module.getModuleID());
                int rowsInserted = statement.executeUpdate();
                result = rowsInserted > 0;
                if (!result) {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return result;
    }

//    // PURELY FOR TESTING THAT IT WORKS WITH THE DATABASE
//    public static void main(String[] args) {
//        CourseDAO dao = new CourseDAO();
//        System.out.println("Getting courses by courseID");
//        dao.getCourse(308);
//        dao.getCourse(999);  // not a course
//
//        System.out.println("\nGetting all courses");
//        dao.getAllCourses();
//
//        System.out.println("\nGetting moduleID by courseID");
//        dao.getModules(308);
//        dao.getModules(999);  // not a course
//
//        System.out.println("\nGetting studentID by courseID");
//        dao.getStudents(308);
//        dao.getStudents(999);  // not a course
//
//        /* dao.deleteCourse(312);
//        dao.setCourse(312, "Web Development", "Intro to creating a website", 1);
//        dao.updateCourse(312 ,"CourseName","NEW Web Development"); */
//    }

}