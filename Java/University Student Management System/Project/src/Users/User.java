package src.Users;

import src.DAO.LecturerDAO;
import src.DAO.ManagerDAO;
import src.DAO.StudentDAO;

import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;

public class User {

    protected int userID;
    protected String forename;
    protected String surname;
    protected String email;
    protected String gender;
    protected Date DOB;
    protected char[] password;
    protected StudentDAO studentDAO;
    protected LecturerDAO lecturerDAO;
    protected ManagerDAO managerDAO;

    public User(){
        this.studentDAO = new StudentDAO();
        this.lecturerDAO = new LecturerDAO();
        this.managerDAO = new ManagerDAO();
    }

    public User(int userID, String forename, String surname, String gender, String email, java.sql.Date DOB, char[] password){
        this.userID = userID;
        this.forename = forename;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.DOB = DOB;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    // Get type of user using email
    public String getUserTypeEmail(String email){

        if(studentDAO.getStudentIDFromEmail(email)!=-1){
            return "Student";
        } else if (lecturerDAO.getLecturerIDFromEmailL(email)!=-1){
            return "Lecturer";
        } else if (managerDAO.getManagerIDFromEmail(email)!=-1){
            return "Manager";
        } else{
            return null;
        }
    }

}
