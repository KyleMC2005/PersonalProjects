package src.DAO;

import src.MySqlConnect;
import src.Users.Course;
import src.Users.Lecturer;
import src.Users.Module;
import src.Users.Student;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Blob;

public class ModuleDAO {
    private final Connection connection;


    public ModuleDAO() {
        this.connection = MySqlConnect.getMysqlConnection();  // reference to singleton
    }


    // Find and return a Module
    public Module getModule(int moduleID){
        String sql = "SELECT * FROM `Module` WHERE `ModuleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                // Put the module information into a Module object
                Module module = new Module();
                CourseDAO courseDAO = new CourseDAO();
                module.setModuleID(resultSet.getInt("ModuleID"));
                module.setModuleName(resultSet.getString("ModuleName"));
                module.setCredits(resultSet.getInt("Credits"));
                module.setDescription(resultSet.getString("ModuleInfo"));
                module.setMaxModuleAttempts(resultSet.getInt("MaxAttempts"));
                module.setModuleMarking(resultSet.getString("ModuleMarking"));
                module.setLecturers(getLecturers(resultSet.getInt("ModuleID")));
                module.setMarkingScheme(resultSet.getString("ModuleMarking"));

                // Get the courseID to find students who are enrolled on that course
                int tempCourse = getModuleCourseID(resultSet.getInt("ModuleID"));
                module.setStudents(courseDAO.getStudents(tempCourse));
                return module;
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

    // Get all modules in database and put them into an ArrayList
    public ArrayList<Module> getAllModules() {
        String sql = "SELECT * FROM `Module`";

        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                // Create module arraylist
                ArrayList<Module> modules = new ArrayList<>();
                do {
                    // add modules one at a time
                    modules.add(getModule(resultSet.getInt("ModuleID")));
                } while(resultSet.next());
                return modules;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
        }

// Get all lecturers who teach a specific module
    public ArrayList<Lecturer> getLecturers(int moduleID){  // Returns an ArrayList of all lecturers on a module
        String sql = "SELECT * FROM `ModuleLecturer` WHERE `ModuleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                // Create arraylist of lecturers
                ArrayList<Lecturer> lecturers = new ArrayList<>();
                do {
                    LecturerDAO lecturerDAO = new LecturerDAO();
                    // Add lecturers one at a time
                    lecturers.add(lecturerDAO.getLecturer(resultSet.getInt("LecturerID")));
                } while(resultSet.next());
                return lecturers;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getAllWeeksForModule(int moduleID){
        String sql = "SELECT weekID FROM `Week` WHERE `ModuleID` = ?";

        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                // Create arraylist of lecturers
                ArrayList<Integer> weeks = new ArrayList<>();
                do {
                    weeks.add(resultSet.getInt("weekID"));
                } while(resultSet.next());

                return weeks;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // Get the course that a specific module is part of
    public Course getModuleCourse(int moduleID){
        String sql = "SELECT * FROM `CourseModule` WHERE `ModuleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                CourseDAO courseDAO = new CourseDAO();
                return courseDAO.getCourse(resultSet.getInt("CourseID"));
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

    // Get the course id from the course that a specific module is part of
    public int getModuleCourseID(int moduleID){
        String sql = "SELECT * FROM `CourseModule` WHERE `ModuleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                CourseDAO courseDAO = new CourseDAO();
                // Return courseID
                return resultSet.getInt("CourseID");
            }
            else{
                return -1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public boolean setModule(int moduleID, String moduleName, int credits, String moduleInfo, String marking) {
        String sql = "INSERT INTO `Module` (`ModuleID`, `ModuleName`, `Credits`, `ModuleInfo`, `ModuleMarking`) VALUES (?, ?, ?, ?, ?)";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, moduleID);
            statement.setString(2, moduleName);
            statement.setInt(3, credits);
            statement.setString(4, moduleInfo);
            statement.setString(5, marking);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateModule(int moduleID, String columnName, String value){
        String sql = "UPDATE `Module` SET " +columnName+ " = ? WHERE `moduleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, value);
            statement.setInt(2, moduleID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteModule(int moduleID){
        String sql = "DELETE FROM `Module` WHERE `moduleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,moduleID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Links a module to a course
    public boolean linkModuleToCourse(int courseID, int moduleID){
        String sql = "INSERT INTO `CourseModule` (`CourseID`, `ModuleID`) VALUES (?, ?)";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseID);
            statement.setInt(2, moduleID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Links a lecturer to a module
    public boolean linkLecturerToModule(int moduleID, int lecturerID) {
        String sql = "INSERT INTO `ModuleLecturer` (`ModuleID`, `LecturerID`) VALUES (?, ?)";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, moduleID);
            statement.setInt(2, lecturerID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Unlinks a module to a course
    public boolean unlinkModuleToCourse(int courseID, int moduleID){
        String sql = "DELETE FROM `CourseModule` WHERE `CourseID`=? AND `ModuleID`=?";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseID);
            statement.setInt(2, moduleID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Unlinks a lecturer to a module
    public boolean unlinkLecturerToModule(int moduleID, int lecturerID) {
        String sql = "DELETE FROM `ModuleLecturer` WHERE `ModuleID`=? AND `LecturerID`=?";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, moduleID);
            statement.setInt(2, lecturerID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Links a student to a module
    public boolean linkStudentToModule(int studentID, int moduleID) {
        String sql = "INSERT INTO `StudentModule` (`StudentID`, `ModuleID`) VALUES (?, ?)";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setInt(2, moduleID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Unlinks a student to a module
    public boolean unlinkStudentToModule(int studentID, int moduleID) {
        String sql = "DELETE FROM `StudentModule` WHERE `StudentID`=? AND `ModuleID`=?";
        if (connection == null) {
            System.out.println("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setInt(2, moduleID);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a students marks for a module (pass or fail)
    public int getModuleMark(int studentID, int moduleID) {
        String sql = "SELECT * FROM `StudentModule` WHERE `StudentID` = ? AND `ModuleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, studentID);
            statement.setInt(2, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt("Result") >= 50){
                    return 1;   // 1 == Pass
                } else if(resultSet.getInt("Result") == -1){
                    return -1;      // -1 == Undefined
                } else {
                    return 0;       // 0 == Fail
                }
            }
            else{
                return -1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getStudentAttempt(int studentID, int moduleID) {
        String sql = "SELECT * FROM `StudentModule` WHERE `StudentID` = ? AND `ModuleID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, studentID);
            statement.setInt(2, moduleID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("Attempts");
            }
            else{
                return -1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    // Returns an ArrayList of all modules a student is part of
    public ArrayList<Module> getStudentModules(int studentID){
        String sql = "SELECT * FROM `StudentModule` WHERE `StudentID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            else{
                ArrayList<Module> modules = new ArrayList<>();
                do {
                    modules.add(getModule(resultSet.getInt("ModuleID")));
                } while(resultSet.next());
                return modules;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

   // Get all lecturers who teach a specific module
    public ArrayList<Student> getAllStudentsForALecturer(int lecturerID) {
        String sql = "SELECT ModuleLecturer.LecturerID, StudentModule.StudentID, Student.FirstName, Student.Surname "
                + "FROM `ModuleLecturer`, `StudentModule`, `Student` "
                + "WHERE ModuleLecturer.ModuleID = StudentModule.ModuleID "
                + "AND StudentModule.StudentID = Student.StudentID "
                + "AND ModuleLecturer.LecturerID = ?;";

        if (connection == null) {
            System.out.println("Connection is null");
            return null;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, lecturerID);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            } else {
                System.out.println("The chosen lecturer teaches the following students:");
                ArrayList<Student> students = new ArrayList<>();
                do {
                    // Retrieve student details
                    int studentID = resultSet.getInt("StudentID");
                    String firstName = resultSet.getString("FirstName");
                    String surname = resultSet.getString("Surname");

                    StudentDAO studentDAO = new StudentDAO();
                    Student student = studentDAO.getStudent(studentID);


                    student.setForename(firstName);
                    student.setSurname(surname);
                    students.add(student);
                    System.out.println("StudentID: " + studentID);
                    System.out.println("Student Name: " + firstName + " " + surname);
                } while (resultSet.next());
                return students;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // In case of error
    }

//    // Read Lecture Notes and Lab Materials for Module
//    public ArrayList<Module> ReadLectureNotesandLabMaterialsforModule(int weekID,int moduleID) {
//        String sql = "SELECT LabNote, LectureNote FROM `Week` WHERE weekID = ? AND moduleID = ?; ";    // insert statement here
//
//        if (connection == null) {
//            System.out.println("Connection is null");
//            return null;
//        }
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, weekID);
//            statement.setInt(2, moduleID);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (!resultSet.next()) {    // If no results
//                return null;
//            } else {
//                System.out.println("The chosen module has the following notes and lab materials: ");
//                ArrayList<Module> modulenotesmaterials = new ArrayList<>();
//                do {
//                    // Retrieve notes and materials bytestreams
//                    Blob LabNote = resultSet.getBlob("LabNote");
//                    Blob LectureNote = resultSet.getBlob("LectureNote");
//
//                    ModuleDAO moduleDAO = new ModuleDAO();
//                    Module module = moduleDAO.getModule(moduleID);
//
//
//                    module.setLabNote(LabNote);
//                    module.setLectureNote(LectureNote);
//                    modulenotesmaterials.add(module);
//                    System.out.println("Lab Notes: " + LabNote);
//                    System.out.println("Lecture Notes: " + LectureNote);
//                } while (resultSet.next());
//                return modulenotesmaterials;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null; // In case of error
//    }

    public boolean CreateLectureNotesandLabMaterialsforModule(int weekID, int moduleID, String content, File labNote, File lectureNote) {
        String sql = "INSERT INTO `Week`(`weekID`, `ModuleID`, `content`, `LabNote`, `LectureNote`) VALUES (?,?,?,?,?); ";    // insert statement here

        if (connection == null) {
            System.out.println("Connection is null");
            return false;
        }

        // File insertion to convert to Blob
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, weekID);
            statement.setInt(2, moduleID);
            statement.setString(3, content);

            if (labNote != null && labNote.exists()) {
                try {
                    byte[] labNoteBytes = Files.readAllBytes(labNote.toPath());
                    Blob pdf = new SerialBlob(labNoteBytes);
                    statement.setBlob(4, pdf);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                statement.setBlob(4, (InputStream.nullInputStream()));
            }

            if (lectureNote != null && lectureNote.exists()) {
                try {
                    byte[] lectureNoteBytes = Files.readAllBytes(lectureNote.toPath());
                    Blob pdf = new SerialBlob(lectureNoteBytes);
                    statement.setBlob(5, pdf);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                statement.setBlob(5, (InputStream.nullInputStream()));
            }

            int rows = statement.executeUpdate();


            if (rows == 0) {    // Nothing in
                return false;
            } else {
                System.out.println("The chosen module has created notes and lab materials: " + moduleID);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // In case of error
    } // Create Lecture Notes and Lab Materials

    // Update Lecture Notes and Lab
    public boolean UpdateMaterialsForModule(int weekID, int moduleID, String content, File labNote, File lectureNote) {
        String sql = "UPDATE `Week` SET `content` = ?, `LectureNote` = ?, `LabNote`= ?  WHERE weekID = ? AND ModuleID = ?;";    // insert statement here



        if (connection == null) {
            System.out.println("Connection is null");
            return false;
        }

        System.out.println("Test 1: "+labNote);
        System.out.println("Test 1: "+lectureNote);

        // File insertion to convert to Blob
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(4, weekID);
            statement.setInt(5, moduleID);
            statement.setString(1, content);

            if (labNote != null && labNote.exists()) {
                try {
                    byte[] labNoteBytes = Files.readAllBytes(labNote.toPath());
                    System.out.println("Test 1: " + Arrays.toString(labNoteBytes));
                    Blob pdf = new SerialBlob(labNoteBytes);
                    statement.setBlob(3, pdf);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("returned Null for this");
                statement.setBlob(3, (InputStream.nullInputStream()));
            }

            if (lectureNote != null && lectureNote.exists()) {
                try {
                    byte[] lectureNoteBytes = Files.readAllBytes(lectureNote.toPath());
                    System.out.println("Test 2: " + Arrays.toString(lectureNoteBytes));
                    Blob pdf = new SerialBlob(lectureNoteBytes);
                    statement.setBlob(2, pdf);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("returned Null for this");
                statement.setBlob(2, (InputStream.nullInputStream()));
            }

            System.out.println(weekID);
            System.out.println(moduleID);
            System.out.println(content);
            System.out.println(labNote);
            System.out.println(lectureNote);
            System.out.println(statement.toString());
            int rows = statement.executeUpdate();
            System.out.println("Rows: "+rows);

            if (rows > 0) {    // Nothing in
                System.out.println("Update has ran and rows have changed");
                return true;
            } else {
                System.out.println("The Selected module has been updated with new notes and lab materials:" + moduleID);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Something went wrong while updating module: " + moduleID);
        return true; // In case of error
    }


    public ArrayList<Integer> getResultStudentModule(int moduleID, int studentID) {  // all fields needed are of type int
        ArrayList<Integer> result = new ArrayList<>();
        String sql = "SELECT * FROM `StudentModule` WHERE `ModuleID` = ?  AND `StudentID` = ?";
        if(connection==null){
            System.out.println("Connection is null");
        }
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, moduleID);
            statement.setInt(2, studentID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                // put the information into the array
                result.add(resultSet.getInt("Result"));
                result.add(resultSet.getInt("ExamResult"));
                result.add(resultSet.getInt("LabResult"));
                return result;
            }
            else{
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    // Testing
    public static void main(String[] args) {
            ModuleDAO dao = new ModuleDAO();
//            dao.getModule(453);
//            dao.getModule(715);
                dao.getResultStudentModule(453, 4321);
////
//            dao.setModule(699, "Boring Class", 10,"Why are you taking this class?");
//            dao.setModule(700, "Boring Class", 10,"Why are you taking this class?");
//            dao.updateModule(699, "ModuleName", "Alvin and the Chipmunks Lore");
//            dao.updateModule(699, "ModuleInfo", "An extensive deep dive into all of the existing lore of Alvin and the Chipmunks.");
//            dao.deleteModule(700);
//      System.out.println(dao.getModuleMark(6969,453));

//      Creating Files for Testing
        File lecturerNote = new File("C:\\University\\Year 3\\CS308\\Coursework\\cs308-group-26-main\\Project\\files\\example.pdf");
        File labNote = new File("C:\\University\\Year 3\\CS308\\Coursework\\cs308-group-26-main\\Project\\files\\labMaterialExample.pdf");
        File UpdatedLecturerNote = new File("C:\\University\\Year 3\\CS308\\Coursework\\cs308-group-26-main\\Project\\files\\UpdatedLectureNotes.pdf");
        File UpdatedLabMaterials = new File("C:\\University\\Year 3\\CS308\\Coursework\\cs308-group-26-main\\Project\\files\\UpdatedLabMaterials.pdf");

//      System.out.println(dao.ReadLectureNotesandLabMaterialsforModule(1, 505));
//      System.out.println(dao.CreateLectureNotesandLabMaterialsforModule(2, 505, "This is a new one", lect urerNote, labNote));
//      System.out.println(dao.UpdateMaterialsForModule(2, 505, "ContentUpdated", UpdatedLabMaterials, UpdatedLecturerNote));
 //       System.out.println(        dao.getModuleMark(6969,453));

//            ArrayList<Integer> array = dao.getAllModuleMarks(6969);
//            for(Integer mark : array){
//                System.out.println(mark);
//            }
     }

}

