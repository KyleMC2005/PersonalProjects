package src.Controllers;

import org.junit.jupiter.api.Test;
import src.*;
import src.Controllers.ControllerManager;
import src.DAO.CourseDAO;
import src.DAO.ModuleDAO;
import src.DAO.StudentDAO;
import src.Users.*;
import src.Users.Module;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.sql.Date;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ControllerManagerTest {
    private ViewLogin vLogin;
    private ViewManagerHome vMH;
    private ViewManageAccounts vMA;
    private ViewStudentDecisions vSD;
    private ViewManageCourseOrModule vMAC;
    private ViewBusinessRule vBR;
    private Lecturer mLecturer;
    private Manager mManager;
    private Student mStudent;
    private Module mModule;
    private Course mCourse;
    private ControllerManager cManager;

    private StudentDAO studentDAO;
    private ModuleDAO moduleDAO;
    private CourseDAO courseDAO;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vLogin = new ViewLogin();
        vMH = new ViewManagerHome();
        vMA =  new ViewManageAccounts();
        vSD = new ViewStudentDecisions();
        vMAC = new ViewManageCourseOrModule();
        vBR = new ViewBusinessRule();
        mStudent = new Student();
        mLecturer = new Lecturer();
        mManager = new Manager();
        mModule = new Module();
        mCourse = new Course();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
        moduleDAO = new ModuleDAO();
        cManager = new ControllerManager(vLogin, vMH, vMA, vSD, vMAC, vBR, mLecturer, mManager, mStudent, mCourse, mModule);
    }

    @Test
    void testAddCourse() {      // Adds a new course
        // Simulate the GUI text inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JTextField input4 = new JTextField();

        // Set text to the desired value
        input1.setText("201");
        // Set the correct input field on the GUI to that value
        vMAC.setCodeField2(input1);

        input2.setText("1");
        vMAC.setSemField2(input2);

        input3.setText("Software Engineering");
        vMAC.setNameField4(input3);

        input4.setText("Learn how to do software engineering!");
        vMAC.setDescField2(input4);

        // Add course using the inputs
        cManager.addCourse();
        Course newCourse = mCourse.getCourse(201);

        // Check that course has been successfully created
        assertEquals(201, newCourse.getCourseID());
        mCourse.deleteCourse(201);
    }

    @Test
    void testAddModule() {         // Adds a new module without course id or lecturer id inputs
        // Simulate the GUI text inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JTextField input4 = new JTextField();

        input1.setText("201");
        vMAC.setCodeField(input1);

        input2.setText("10");
        vMAC.setCreditField2(input2);

        input3.setText("Introduction to Programming");
        vMAC.setNameField2(input3);

        input4.setText("Learn the basic fundamentals of Java.");
        vMAC.setModDescField2(input4);

        cManager.addModule();
        Module newModule = mModule.getModule(201);
        assertEquals(201, newModule.getModuleID());
        mModule.deleteModule(201);
    }

    @Test
    void testAddModuleExtra() {      // Adds a new module WITH course id and lecturer id input
        // Simulate the GUI text inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JTextField input4 = new JTextField();
        JTextField input5 = new JTextField();
        JTextField input6 = new JTextField();

        input1.setText("201");
        vMAC.setCodeField(input1);

        input2.setText("10");
        vMAC.setCreditField2(input2);

        input3.setText("Introduction to Programming");
        vMAC.setNameField2(input3);

        input4.setText("Learn the basic fundamentals of Java.");
        vMAC.setModDescField2(input4);

        input5.setText("320");
        vMAC.setAssignedLec2(input5);

        input6.setText("308");
        vMAC.setOnCourse2(input6);

        cManager.addModule();
        Module newModule = mModule.getModule(201);
        assertEquals(201, newModule.getModuleID());
        mModule.unlinkLecturer(201, 320);
        mModule.unlinkCourse(308, 201);
        mModule.deleteModule(201);
    }

    @Test
    void testAddStudentCourse() {   // Links a student to a course
        // Simulate the GUI text inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();


        Date date = new Date(2000,12,12);
        studentDAO.setStudent(123456, "Peter", "Parker", "Male", "peterparker301@gmail.com", date, "Undergrad","password", "");

        input1.setText("123456");
        vMAC.setStudIdField(input1);

        vMAC.setStudIdField2(input1);

        courseDAO.setCourse(201, "Software Engineering", "Learn how to do software engineering!", 2, "Undergrad");

        input2.setText("201");
        vMAC.setCourseEnroll(input2);

        moduleDAO.setModule(201, "Software Engineering", 10, "Learn the basics of software engineering!", "Exam");

        moduleDAO.linkModuleToCourse(201,201);

        cManager.addStudentCourse();
        // If user is unlinked successfully, it means the linking worked
        assertEquals(true, mCourse.unlinkStudentCourse(123456,201));
        moduleDAO.unlinkModuleToCourse(201,201);
        moduleDAO.deleteModule(201);
        courseDAO.deleteCourse(201);
        studentDAO.deleteStudent(123456);
    }

    @Test
    void testAddStudentModule() {   // Links a student to a module
        moduleDAO.setModule(201, "Software Engineering", 10, "Learn the basics of software engineering!", "Exam");

        Date date = new Date(2000,12,12);
        studentDAO.setStudent(123456, "Peter", "Parker", "Male", "peterparker301@gmail.com", date, "Undergrad","password", "");

        // Simulate the GUI text inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();

        input1.setText("123456");
        input2.setText("201");
        vMAC.setStudIDField3(input1);

        vMAC.setStudIDField4(input1);

        vMAC.setModuleEnroll(input2);

        cManager.addStudentModule();
        // If user is unlinked successfully, it means the linking worked
        assertEquals(true, mModule.unlinkStudentModule(123456,201));
        moduleDAO.deleteModule(201);
        studentDAO.deleteStudent(123456);
    }

    @Test
    void testEditModule(){      // Edits the Name and Description of a module
        // Simulate the GUI text inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JTextField input4 = new JTextField();
        JTextField input5 = new JTextField();
        JTextField input6 = new JTextField();

        input1.setText("201");
        vMAC.setCodeField(input1);

        input2.setText("10");
        vMAC.setCreditField2(input2);

        input3.setText("Introduction to Programming");
        vMAC.setNameField2(input3);

        input4.setText("Learn the basic fundamentals of Java.");
        vMAC.setModDescField2(input4);

        cManager.addModule();
        Module newModule = mModule.getModule(201);
        assertEquals(201, newModule.getModuleID());

        vMAC.clearTxts();

        input1.setText("201");
        input5.setText("Advanced Programming");
        input6.setText("Learn advanced programming techniques in Java.");

        vMAC.setCodeDrop(input1);
        vMAC.setNameField1(input5);
        vMAC.setModDescField1(input6);

        cManager.editModule();
        Module editedModule = mModule.getModule(201);
        assertEquals("Advanced Programming", editedModule.getModuleName());
        assertEquals("Learn advanced programming techniques in Java.", editedModule.getDescription());
        mModule.deleteModule(201);
    }

    @Test
    void testEditCourse(){      // Edits the Name and Description of a Course
        // Simulate the GUI text inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JTextField input4 = new JTextField();
        JTextField input5 = new JTextField();
        JTextField input6 = new JTextField();

        // Set text to the desired value
        input1.setText("201");
        // Set the correct input field on the GUI to that value
        vMAC.setCodeField2(input1);

        input2.setText("1");
        vMAC.setSemField2(input2);

        input3.setText("Software Engineering");
        vMAC.setNameField4(input3);

        input4.setText("Learn how to do software engineering!");
        vMAC.setDescField2(input4);

        // Add course using the inputs
        cManager.addCourse();
        Course newCourse = mCourse.getCourse(201);

        // Check that course has been successfully created
        assertEquals(201, newCourse.getCourseID());


        vMAC.clearTxts();

        input1.setText("201");
        input5.setText("Computer Science");
        input6.setText("Learn all about the world of computer science.");

        vMAC.setCodeDrop2(input1);
        vMAC.setNameField3(input5);
        vMAC.setDescField1(input6);

        cManager.editCourse();
        Course editedCourse = mCourse.getCourse(201);
        assertEquals("Computer Science", editedCourse.getCourseName());
        assertEquals("Learn all about the world of computer science.", editedCourse.getDescription());
        mCourse.deleteCourse(201);
    }

//    @Test
//    void issueDecision(){
//        Date date = new Date(2000,12,12);
//        studentDAO.setStudent(123456, "Peter", "Parker", "Male", "peterparker301@gmail.com", date,"Undergraduate","password", "Pending");
//        courseDAO.setCourse(201, "Software Engineering", "Learn how to do software engineering!", 2, "Undergrad");
//        moduleDAO.setModule(201, "Software Engineering", 10, "Learn the basics of software engineering!", "Exam");
//        mLecturer.updateResults(123456, 201, 51, "Result");
//
//        JTextField input1 = new JTextField();
//
//        moduleDAO.linkStudentToModule(123456,201);
//        moduleDAO.linkModuleToCourse(201,201);
//        input1.setText("123456");
//        vSD.setTextField1(input1);
//        vSD.getAwardCheckBox().setSelected(true);
//        cManager.issueDecision();
//
//        Student current = studentDAO.getStudent(123456);
//
//        moduleDAO.unlinkModuleToCourse(201,201);
//        moduleDAO.unlinkStudentToModule(123456,201);
//        studentDAO.deleteStudent(123456);
//        moduleDAO.deleteModule(201);
//        courseDAO.deleteCourse(201);
//
//        assertEquals("Award",current.getCourseDecision());
//    }

//    @Test
//    void testResetPassword(){   // Resets the users password to 1234
//        Date date = new Date(2000,12,12);
//        studentDAO.setStudent(123456, "Peter", "Parker", "Male", "peterparker301@gmail.com", date,"password", "");
//
//        JTextField input1 = new JTextField();
//        input1.setText("123456");
//
//        vMA.setEnterID(input1);
//        vMA.setReenterID(input1);
//
//        cManager.resetPassword();
//        Student student = mStudent.getStudentByID(123456);
//
//        String pass = String.valueOf(student.getPassword());
//
//        assertEquals("1234", pass);
//        mStudent.deleteStudent(123456);
//    }

    @Test
    void testModuleAttempts(){
        moduleDAO.setModule(201, "Software Engineering", 10, "Learn the basics of software engineering!", "Exam");

        JTextField input1 = new JTextField();
        JSpinner input2 = new JSpinner();
        input1.setText("201");
        input2.setValue(5);

        vBR.setModuleCode(input1);
        vBR.setSpinner1(input2);
        cManager.maxModuleAttempts();

        Module module = mModule.getModule(201);
        assertEquals(5, module.getMaxModuleAttempts());
        moduleDAO.deleteModule(201);
    }

    @Test
    void testModuleComp(){      // Sets the amount of modules allowed to be compensated for a Course
        courseDAO.setCourse(201, "Software Engineering", "Learn how to do software engineering!", 2, "Undergrad");

        JTextField input1 = new JTextField();
        JSpinner input2 = new JSpinner();
        input1.setText("201");
        input2.setValue(5);

        vBR.setCourseCode(input1);
        vBR.setSpinner2(input2);
        cManager.moduleCompensations();

        Course course = mCourse.getCourse(201);
        assertEquals(5, course.getCompModules());
        courseDAO.deleteCourse(201);
    }

}