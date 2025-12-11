package src.Users;

import src.DAO.StudentDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class Student extends User{

    private String courseDecision;

    private String StudentType;
    private StudentDAO studentDAO;

    // empty constructor for only DAO
    public Student() {
        this.studentDAO = new StudentDAO();
    }

    public Student(int userID, String forename, String surname, String gender, String email, java.sql.Date DOB, char[] password, String courseDecision, String StudentType) {
        super(userID, forename, surname, gender, email, DOB, password);
        this.courseDecision = courseDecision;
    }

    // getter and setter for courseDecision
    public String getCourseDecision() {
        return courseDecision;
    }

    public void setCourseDecision(String courseDecision) {
        this.courseDecision = courseDecision;
    }

    public String getStudentType() {
        return StudentType;
    }

    public void setStudentType(String StudentType) {
        this.StudentType = StudentType;
    }

   /* public boolean signUpStudent(String firstName, String lastName, String email, char[] password, char[] passwordCheck, Date dob, String gender, String qual) {
        if (Arrays.equals(password,passwordCheck)){
            this.email = email;
            this.forename = firstName;
            this.surname = lastName;
            this.password = password;
            this.DOB = dob;
            this.gender = gender;
            return true;
        }
        return false;
    }*/

    public boolean loginStudent(String email, char[] password) {
        // use the studentDAO to check username and password against the database
        return studentDAO.loginStudent(email, password);
    }

    public boolean createStudent(int studentID, String FirstName, String Surname, String Gender, String Email, Date DOB, String PWord, String CourseType, String CourseDecision){
        // use studentDAO to insert student to database
        return studentDAO.setStudent(studentID, FirstName, Surname, Gender, Email, DOB, PWord, CourseType, CourseDecision);
    }

    public int getIDByEmailS(String email){
        return studentDAO.getStudentIDFromEmail(email);
    }

    public Student getStudentByID(int id){
        return studentDAO.getStudent(id);
    }

    // When the user chooses to change their own password
    public boolean updatePassword(String password, String email){
        return studentDAO.updateStudent(getIDByEmailS(email), "PWord", password);
    }

//    public getStudentCourse(int studentID){
//
//    }

    public boolean deleteStudent(int studentID){
       return studentDAO.deleteStudent(studentID);
    }

    public int checkStudentCourse(int studentID){
      return studentDAO.getStudentCourse(studentID);
    }

    public ArrayList<JButton> createModuleButtons(int studentID){
        ArrayList<Integer> modules;
        ArrayList<JButton> buttons = new ArrayList<>();

        modules = studentDAO.getModules(studentID);

        if(modules == null){
            return null;
        }

        for(int i = 0; i < modules.size(); i++){
            String moduleName = modules.get(i).toString();
            JButton button = new JButton(moduleName);
            button.setBackground(Color.decode("#CAE9FF"));
            button.setFont(new Font("Calibri", Font.PLAIN, 50));
            buttons.add(button);
        }

        return buttons;
    }

    public ArrayList<JButton> createNotesButtons(int moduleID){
        ArrayList<Integer> weeks;
        ArrayList<JButton> buttons = new ArrayList<>();

        weeks = studentDAO.getWeeks(moduleID);

        for(int i = 0; i < weeks.size(); i++){
            String moduleName = weeks.get(i).toString();
            JButton button = new JButton(moduleName);
            button.setBackground(Color.decode("#CAE9FF"));
            button.setFont(new Font("Calibri", Font.PLAIN, 50));
            buttons.add(button);
        }

        return buttons;
    }


}
