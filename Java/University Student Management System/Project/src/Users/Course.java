package src.Users;

import src.DAO.CourseDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

public class Course {

    // Initialise User Variables
    private int CourseID;
    private String CourseName;
    private String Description;
    private int Semesters;
    private int compModules;

    private String courseType;
    private ArrayList<Module> Modules;
    private ArrayList<Student> Students;
    private CourseDAO courseDAO;

    public Course(){
        this.courseDAO = new CourseDAO();
    }

    public Course(int CourseID, String CourseName, String Description, int Semesters, int compModules, String courseType, ArrayList<src.Users.Module> Modules, ArrayList<src.Users.Student> Students) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.Description = Description;
        this.Semesters = Semesters;
        this.compModules = compModules;
        this.courseType = courseType;
        this.Modules = Modules;
        this.Students = Students;
    }

    // getters and setters
    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getSemesters() {
        return Semesters;
    }

    public void setSemesters(int semesters) {
        Semesters = semesters;
    }

    public ArrayList<Module> getModules() {
        return Modules;
    }

    public void setModules(ArrayList<Module> modules) {
        Modules = modules;
    }

    public ArrayList<Student> getStudents() {
        return Students;
    }

    public void setStudents(ArrayList<Student> students) {
        Students = students;
    }

    public int getCompModules() {
        return compModules;
    }

    public void setCompModules(int compModules) {
        this.compModules = compModules;
    }

    public Course getCourse(int courseID) {
        return courseDAO.getCourse(courseID);
    }

    public ArrayList<Course> getCourses() {     // Get all courses in database
        return courseDAO.getAllCourses();
    }

    public ArrayList<Module> getCourseModules(int courseID) {   // Get all
        return courseDAO.getModules(courseID);
    }

    public ArrayList<Student> getCourseStudents(int courseID) {
        return courseDAO.getStudents(courseID);
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String CourseType) {
        courseType = CourseType;
    }

    public boolean updateCourse(int courseID, String columnName, String value) {
        return courseDAO.updateCourse(courseID, columnName, value);
    }

    public boolean deleteCourse(int courseID) {
        return courseDAO.deleteCourse(courseID);
    }

    public boolean unlinkStudentCourse(int studentID, int courseID) {
        return courseDAO.unlinkStudentToCourse(studentID, courseID);
    }

}
