package src.Controllers;

import org.junit.jupiter.api.Test;
import src.*;
import src.Controllers.ControllerLecturer;
import src.DAO.CourseDAO;
import src.DAO.LecturerDAO;
import src.DAO.ModuleDAO;
import src.DAO.StudentDAO;
import src.Users.*;
import src.Users.Module;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerLecturerTest {
    private ViewLogin vLogin;
    private ViewLecturerHome vLH;
    private Lecturer mLecturer;
    private Student mStudent;
    private Module mModule;
    private Course mCourse;
    private ControllerLecturer lController;
    private LecturerDAO lecturerDAO;
    private StudentDAO studentDAO;
    private ModuleDAO moduleDAO;
    private CourseDAO courseDAO;
    private ViewUserType vUserType;
    private ViewSignUp vSignUp;
    private ViewExamResultsLecturer vERL;
    private ViewListModuleStudents vLMS;
    private ViewUploadMaterials vUP;
    private ViewLecturerModuleEdit vLME;
    private Lecturer lModel;
    private User mUser;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // initialisation
        vLogin = new ViewLogin();
        vLH = new ViewLecturerHome();
        vUserType = new ViewUserType();
        vSignUp = new ViewSignUp();
        vERL = new ViewExamResultsLecturer();
        vLMS = new ViewListModuleStudents();
        vUP = new ViewUploadMaterials();
        vLME = new ViewLecturerModuleEdit();
        mLecturer = new Lecturer();
        mStudent = new Student();
        mModule = new Module();
        mCourse = new Course();
        lecturerDAO = new LecturerDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
        moduleDAO = new ModuleDAO();
        lModel = new Lecturer();
        lController = new ControllerLecturer(vLogin, vUserType, vSignUp, vLH, vERL, vLMS, vUP, vLME, lModel, mModule);
    }

    @Test
    void testLecturerSignUp() {
        // Setting up text user inputs
        JTextField input1 = new JTextField();
        JTextField input2 = new JTextField();
        JTextField input3 = new JTextField();
        JPasswordField input4 = new JPasswordField();

        // Get the inputs
        input1.setText("Bully");
        input2.setText("Maguire");
        input3.setText("symbiotespiderman@gmail.com");
        input4.setText("GreenGobbling");

        // Setting the view user inputs
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
        input5.setValue(2007);
        input6.setValue(05);
        input7.setValue(04);

        vSignUp.setYearSpin(input5);
        vSignUp.setMonthSpin(input6);
        vSignUp.setDaySpin(input7);

        // Combo boxes set up
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Male");
        // Make sure value added is selected
        comboBox.setSelectedIndex(0);


        JComboBox comboBox3 = new JComboBox();
        comboBox3.addItem("BSc");
        comboBox3.setSelectedIndex(0);

        vSignUp.setGender(comboBox);
        vSignUp.setQual(comboBox3);

        lController.lecturerSignUp();

        // Find users id
        int id = lModel.getIDByEmailL("symbiotespiderman@gmail.com");
        Lecturer lecturer = lecturerDAO.getLecturer(id);
        // Check that user exists and details are there
        assertEquals("Bully",lecturer.getForename());
        assertEquals("Maguire",lecturer.getSurname());
        assertEquals("Male",lecturer.getGender());
        assertEquals("BSc", lecturer.getQualification());


        // Delete Lecturer to avoid duplicates
        assertTrue(lecturerDAO.deleteLecturer(id));
    }

    // These tests basically make sure all of the expected panels are displayed for pages
    @Test
    void testShowListModuleStudents() {
        // Checking that everyhting that is supposed to be visible is visible, and everything thats not is not
        assertFalse(vLH.getPanelMain().isVisible());
        assertFalse(vLMS.getPanelMain().isVisible());
        assertTrue(vLMS.getRequestsSection().isVisible());
    }

    @Test
    void testExpectedStartupDisplay() {
        // Checks if upon startup only the login panel is obviously visible with everything else off
        assertTrue(vLogin.getPanelMain().isVisible());
        assertFalse(vSignUp.getPanelMain().isVisible());
        assertFalse(vLH.getPanelMain().isVisible());
        assertFalse(vERL.getPanelMain().isVisible());
        assertFalse(vLMS.getPanelMain().isVisible());
        assertFalse(vUP.getPanelMain().isVisible());
    }

    @Test
    void testShowLecturerHomePanels() {
        // Checking that everything that is supposed to be visible is visible, and everything thats not is not

        // run method and check that the other main panels don't display
        lController.goLecturerHome();

        assertFalse(vERL.getPanelMain().isVisible());
        assertFalse(vUP.getPanelMain().isVisible());
        assertFalse(vLMS.getPanelMain().isVisible());
        assertTrue(vLH.getPanelMain().isVisible());
    }

    @Test
    void testShowExamResults() {
        // Checking that everything that is supposed to be visible is visible, and everything thats not is not

        // run method and check that the other main panels don't display
        lController.showExamResultsLecturer();

        assertFalse(vLH.getPanelMain().isVisible());
        assertTrue(vERL.getPanelMain().isVisible());
    }

    @Test
    void testShowLogOut() {
        // Checking that everything that is supposed to be visible is visible, and everything thats not is not

        // run method and check that the other main panels don't display
        lController.logOut();

        assertFalse(vLH.getPanelMain().isVisible());
        assertTrue(vLogin.getPanelMain().isVisible());
    }
}