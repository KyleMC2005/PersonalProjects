package src.Users;

import src.DAO.LecturerDAO;
import src.DAO.ModuleDAO;

import java.sql.Date;
import java.util.ArrayList;

public class Lecturer extends User{

    private String qualification;
    private LecturerDAO lecturerDAO;
    private ModuleDAO moduleDAO;

    // empty constructor for only DAO
    public Lecturer(){
        this.lecturerDAO = new LecturerDAO();
        this.moduleDAO = new ModuleDAO();
    }

    public Lecturer(int userID, String forename, String surname, String gender, String email, java.sql.Date DOB, char[] password, String qualification) {
        super(userID, forename, surname, gender, email, DOB, password);
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public boolean loginLecturer(String email, char[] password) {
        // use the studentDAO to check username and password against the database
        return lecturerDAO.loginLecturer(email, password);
    }

    public boolean createLecturer(int lecturerID, String FirstName, String Surname, String Gender, String Email, Date DOB, String PWord, String Qualification){
        // use lecturerDAO to insert student to database
        return lecturerDAO.setLecturer(lecturerID, FirstName, Surname, Gender, Email, DOB, PWord, Qualification);
    }

    public int getIDByEmailL(String email){
        return lecturerDAO.getLecturerIDFromEmailL(email);
    }

    public Lecturer getLecturerByID(int id){
        return lecturerDAO.getLecturer(id);
    }

    // When the user chooses to change their own password
    public boolean updatePassword(String password, String email){
        return lecturerDAO.updateLecturer(getIDByEmailL(email), "PWord", password);
    }

    public boolean deleteLecturer(int id) {
        return lecturerDAO.deleteLecturer(id);
    }

    public boolean updateResults(int studentID, int moduleID, int result, String type){
        boolean success1 = lecturerDAO.updateResults(studentID, moduleID, result, type);
        boolean success2 = lecturerDAO.updateResults(studentID, moduleID, result, "Result");
        if(success1 && success2){
            return true;
        }
        return false;
    }

    public boolean updateBothResults(int studentID, int moduleID, int result, String type){
        boolean success1 = lecturerDAO.updateResults(studentID, moduleID, result, type);
        boolean success2 = lecturerDAO.addResults(studentID, moduleID);
        if(success1 && success2){
            return true;
        }
        return false;
    }

}