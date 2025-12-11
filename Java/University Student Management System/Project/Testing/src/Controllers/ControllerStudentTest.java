package src.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;
import src.DAO.ModuleDAO;
import src.DAO.StudentDAO;
import src.Users.Lecturer;
import src.Users.Module;
import src.Users.Student;

import javax.swing.*;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ControllerStudentTest {
    private ViewUserType vUserType;
    private ViewSignUp vSignUp;
    private ViewLogin vLogin;  // here for after account creation
    private ViewCourseOverview vCO;
    private ViewModule vModule;
    private Student sModel;
    private Lecturer lModel;
    private Module mModel;
    private ControllerStudent sController;
    private StudentDAO studentDAO;
    private ModuleDAO moduleDAO;

    @BeforeEach
    void setUp() {
        vUserType = new ViewUserType();
        vSignUp = new ViewSignUp();
        vLogin = new ViewLogin();
        vCO = new ViewCourseOverview();
        vModule = new ViewModule();
        sModel = new Student();
        mModel = new Module();
        lModel = new Lecturer();
        studentDAO = new StudentDAO();
        moduleDAO = new ModuleDAO();
        sController = new ControllerStudent(vUserType,vSignUp, vLogin, vCO, vModule, sModel, mModel, studentDAO);
    }

    // Tests that the course decision result is correctly obtained and displayed
    @Test
    void testShowResults() {
        sModel.setCourseDecision("Award");
        sController.showResults();
        assertEquals("Award",vCO.getNoResultsLabel().getText());
    }

    // tests that correct module result(s) gets displayed to user
    @Test
    void testShowModuleResult() {
        moduleDAO.setModule(201, "Freelance Police Training", 10, "Learn the basics of becoming freelance police!", "Exam");
        Module module = moduleDAO.getModule(201);

        Date date = new Date(2000,12,12);
        studentDAO.setStudent(123456, "Sam", "Max", "Male", "freelancepolice@outlook.com", date, "Undergrad","password", "Pending");
        Student student = studentDAO.getStudent(123456);

        moduleDAO.linkStudentToModule(123456,201);

        lModel.updateResults(123456,201,73,"ExamResult");

        sController.showModuleResult(module, student.getUserID());
        sModel.setCourseDecision("Award");


        moduleDAO.unlinkStudentToModule(123456,201);
        moduleDAO.deleteModule(201);
        studentDAO.deleteStudent(123456);
        assertEquals("<html>Current Results<br><br>Result: 73<br>Exam Result: 73</html>", vModule.getResultLabel().getText());
    }

    // Tests that students can sign up, and that their account is successfully created
    @Test
    void testStudentSignUp(){
        // Set up the user inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JPasswordField input4 = new JPasswordField();

        // get the inputs
        input1.setText("Bruce");
        input2.setText("Wayne");
        input3.setText("thedarkknight@gmail.com");
        input4.setText("password");

        // Set the view inputs to have the desired inputs from above
        vSignUp.setFnField(input1);
        vSignUp.setSnField(input2);
        vSignUp.setEmailField(input3);

        vSignUp.setPasswordField1(input4);
        vSignUp.setPasswordField2(input4);

        // DOB
        JSpinner input5 = new JSpinner();
        JSpinner input6 = new JSpinner();
        JSpinner input7 = new JSpinner();

        // Set DOB values
        input5.setValue(1989);
        input5.setValue(12);
        input5.setValue(12);

        vSignUp.setYearSpin(input5);
        vSignUp.setMonthSpin(input6);
        vSignUp.setDaySpin(input7);

        // Combo boxes set up
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Male");
        // Make sure value added is selected
        comboBox.setSelectedIndex(0);

        JComboBox comboBox2 = new JComboBox();
        comboBox2.addItem("Undergrad");
        comboBox2.setSelectedIndex(0);

        vSignUp.setGender(comboBox);
        vSignUp.setStudentType(comboBox2);

        // Sign up user as all inputs are given
        sController.studentSignUp();

        // Find users id
        int id = sModel.getIDByEmailS("thedarkknight@gmail.com");
        Student student = studentDAO.getStudent(id);
        // Check that user exists and details are there
        assertEquals("Bruce",student.getForename());
        assertEquals("Wayne",student.getSurname());
        assertEquals("Male",student.getGender());
        assertEquals("Undergrad",student.getStudentType());

        // Delete student to avoid duplicates
        assertTrue(studentDAO.deleteStudent(id));
    }

    // Tests that student doesnt get signed up if a field is missing
    @Test
    void testFalseStudentSignUp(){
        // Set up the user inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JPasswordField input4 = new JPasswordField();

        // get the inputs
        input1.setText("Bruce");
        input2.setText("Wayne");
        input4.setText("password");

        // Set the view inputs to have the desired inputs from above
        vSignUp.setFnField(input1);
        vSignUp.setSnField(input2);
        vSignUp.setEmailField(input3);

        vSignUp.setPasswordField1(input4);
        vSignUp.setPasswordField2(input4);

        // DOB
        JSpinner input5 = new JSpinner();
        JSpinner input6 = new JSpinner();
        JSpinner input7 = new JSpinner();

        // Set DOB values
        input5.setValue(1989);
        input5.setValue(12);
        input5.setValue(12);

        vSignUp.setYearSpin(input5);
        vSignUp.setMonthSpin(input6);
        vSignUp.setDaySpin(input7);

        // Combo boxes set up
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Male");
        // Make sure value added is selected
        comboBox.setSelectedIndex(0);

        JComboBox comboBox2 = new JComboBox();
        comboBox2.addItem("Undergrad");
        comboBox2.setSelectedIndex(0);

        vSignUp.setGender(comboBox);
        vSignUp.setStudentType(comboBox2);

        // Sign up user as all inputs are given
        sController.studentSignUp();

        // Find users id
        assertEquals(-1, sModel.getIDByEmailS("thedarkknight@gmail.com"));

    }

}