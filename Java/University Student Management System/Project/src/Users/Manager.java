package src.Users;

import src.DAO.*;

import java.sql.Date;
import java.util.ArrayList;

public class Manager extends User{

    private ManagerDAO managerDAO;
    private StudentDAO studentDAO;
    private LecturerDAO lecturerDAO;
    private ModuleDAO moduleDAO;
    private CourseDAO courseDAO;

    // empty constructor for only DAO
    public Manager() {
        this.managerDAO = new ManagerDAO();
        this.moduleDAO = new ModuleDAO();
        this.courseDAO = new CourseDAO();
        this.studentDAO = new StudentDAO();
        this.lecturerDAO = new LecturerDAO();
    }

    public Manager(int userID, String forename, String surname, String gender, String email, Date DOB, char[] password) {
        super(userID, forename, surname, gender, email, DOB, password);
    }

    public boolean loginManager(String email, char[] password) {
        // use the studentDAO to check username and password against the database
        return managerDAO.loginManager(email, password);
    }

    public int getIDByEmailM(String email){
        return managerDAO.getManagerIDFromEmail(email);
    }

    public Manager getManagerByID(int id){
        return managerDAO.getManager(id);
    }

    // Add a new module to the database
    public boolean addModule(int moduleID, String moduleName, int credits, String description, String marking){
        return moduleDAO.setModule(moduleID, moduleName , credits, description, marking);
    }

    // Add a new course to the database
    public boolean addCourse(int courseID, String courseName, String description, int semesters, String courseType){
        return courseDAO.setCourse(courseID, courseName , description, semesters, courseType);
    }

    // Add a module to a course
    public boolean addCourseModule(int courseID, int moduleID) {
        return moduleDAO.linkModuleToCourse(courseID, moduleID);
    }

    // Assign a lecturer to a module
    public boolean addLecturerModule(int moduleID, int lecturerID){
        return moduleDAO.linkLecturerToModule(moduleID, lecturerID);
    }

    // Enroll a student onto a course
    public boolean enrollStudent(int studentID, int courseID) {
        boolean onCourse = courseDAO.linkStudentToCourse(studentID, courseID);
        boolean onModules = false;
        if(onCourse){
            onModules = courseDAO.enrollStudentToCourseModules(studentID, courseID); 
        }
        return onCourse && onModules;
    }

    // Unlinks a student from a course, and unlinks them from that course's modules
    public boolean unenrollStudent(int studentID, int courseID) {
        boolean course = courseDAO.unlinkStudentToCourse(studentID, courseID);
        boolean modules = false;
        if(course){
            modules = courseDAO.unenrollStudentToCourseModules(studentID, courseID);
        }
        return course && modules;
    }

    // Adds a business rule
    public boolean addBusinessRule(int id, int value, String type){
        // If the user is attempting to change maximum number of attempts
        if (type.equals("attempts")){
            return moduleDAO.updateModule(id, "MaxAttempts", String.valueOf(value));
        } else {    // User is attempting to change number of compensated modules
            return courseDAO.updateCourse(id, "CompensatedModules", String.valueOf(value));
        }

    }

    public boolean enrollStudentModule(int studentID, int moduleID) {
        return moduleDAO.linkStudentToModule(studentID, moduleID);
    }

    // Check if student has failed a module, or if it is unmarked
    public boolean checkMarks(int studentID, int value){
        ArrayList<Module> modules = moduleDAO.getStudentModules(studentID);

        for (Module module : modules){
            if(moduleDAO.getModuleMark(studentID, module.getModuleID()) == value){
                return false;
            }
        }
        return true;
    }

    // Check how many attempts a student has left in module if they have failed it
    public boolean resitAllowed(int studentID){
        ArrayList<Module> modules = moduleDAO.getStudentModules(studentID);
        for (Module module : modules){
            // If student has failed a module
            if (moduleDAO.getModuleMark(studentID, module.getModuleID()) == 0){
                // If student is on their last attempt
                if (moduleDAO.getStudentAttempt(studentID, module.getModuleID()) >= module.getMaxModuleAttempts()){
                    // Not allowed to resit
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    // Issue student decision
    public boolean issueDecision(int studentID, String decision){
        StudentDAO studentDAO = new StudentDAO();
        return studentDAO.updateStudent(studentID, "CourseDecision", decision);
    }

    // When the user chooses to change their own password
    public boolean updatePassword(String password, String email){
        return managerDAO.updateManager(getIDByEmailM(email), "PWord", password);
    }

    public boolean activeStudent(int studentID, String value){
        return studentDAO.updateStudent(studentID,"Active",value);
    }

    public boolean activeLecturer(int lecturerID, String value){
        return lecturerDAO.updateLecturer(lecturerID,"Active", value);
    }

    public boolean activeManager(int managerID, String value) {
        return managerDAO.updateManager(managerID,"Active",value);
    }

    // When the manager chooses to reset a student's password
    public boolean resetSPassword(int id, String password) {
        return studentDAO.updateStudent(id, "PWord", password);
    }

    // When the manager chooses to reset a lecturer's password
    public boolean resetLPassword(int id, String password) {
        return lecturerDAO.updateLecturer(id, "PWord", password);
    }

    public boolean resetMPassword(int id, String password) { return managerDAO.updateManager(id, "PWord", password); }

    public ArrayList<User> findUnapproved(){
        return managerDAO.findUnapprovedUsers();
    }

    public boolean deleteManager(int managerID){
        return managerDAO.deleteManager(managerID);
    }

    public boolean approveStudent(int id, String value) {
        return studentDAO.updateStudent(id, "Approved", value);
    }

    public boolean approveLecturer(int id, String value) {
        return lecturerDAO.updateLecturer(id, "Approved", value);
    }

    // Approve or Deny all students on the sign up workflow
    public boolean approveAllStudents(String value) {
        ArrayList<User> unapproved = managerDAO.findUnapprovedUsers();
        if (unapproved == null || unapproved.isEmpty()){
            return false;
        } else {

            for (User user : unapproved) {
                studentDAO.updateStudent(user.getUserID(), "Approved", value);
            }
            return true;
        }
    }

    // Approve or Deny all lecturers based on the sign up workflow
    public boolean approveAllLecturers(String value) {
        ArrayList<User> unapproved = managerDAO.findUnapprovedUsers();
        if (unapproved == null || unapproved.isEmpty()) {
            return false;
        } else {
            for (User user : unapproved) {
                lecturerDAO.updateLecturer(user.getUserID(), "Approved", value);
            }
            return true;
        }
    }

}
