package src;
import src.Controllers.ControllerLecturer;
import src.Controllers.ControllerManager;
import src.Controllers.ControllerStudent;
import src.Controllers.ControllerUser;
import src.DAO.StudentDAO;
import src.Users.*;
import src.Users.Module;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

// The Main application that we run

public class App_26 {
    public static void main(String[] args) {
        // Creates the main application frame which will contain the views
        JFrame mainFrame = new JFrame("Group 26 USMS");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Execution stops if application window is closed

        // Instances of all views
        ViewLogin vLogin = new ViewLogin();
        ViewUserType vUserType = new ViewUserType();
        ViewSignUp vSignUp = new ViewSignUp();
        ViewForgetPassword vFP = new ViewForgetPassword();
        ViewCourseOverview vCO = new ViewCourseOverview();
        ViewModule vModule = new ViewModule();
        ViewManagerHome vMH = new ViewManagerHome();
        ViewManageAccounts vMA = new ViewManageAccounts();
        ViewStudentDecisions vSD = new ViewStudentDecisions();
        ViewManageCourseOrModule vMAC = new ViewManageCourseOrModule();
        ViewBusinessRule vBR = new ViewBusinessRule();
        ViewExamResultsLecturer vERL = new ViewExamResultsLecturer();
        ViewLecturerHome vLH = new ViewLecturerHome();
        ViewListModuleStudents vLMS = new ViewListModuleStudents();
        ViewUploadMaterials vUP = new ViewUploadMaterials();
        ViewLecturerModuleEdit vLME = new ViewLecturerModuleEdit();

        StudentDAO sDAO = new StudentDAO();

        // Instances of Models
        User mUser = new User();
        Student sModel = new Student();
        Lecturer lModel = new Lecturer();
        Manager mManager = new Manager();
        Course mCourse = new Course();
        Module mModule = new Module();

        // Controller created, views and models passed into the controller

        ControllerStudent sController = new ControllerStudent(vUserType,vSignUp, vLogin, vCO, vModule, sModel, mModule, sDAO);
        ControllerLecturer lController = new ControllerLecturer(vLogin, vUserType, vSignUp, vLH, vERL, vLMS, vUP, vLME, lModel, mModule);
        ControllerManager mController = new ControllerManager(vLogin,vMH, vMA, vSD, vMAC, vBR, lModel, mManager, sModel, mCourse, mModule);
        ControllerUser uController = new ControllerUser(vLogin, vFP, vUserType, vSignUp, vCO, vModule, vLH, vMH, mUser, sController, lController, mController);

//        set resizable to false for now
//        mainFrame.setResizable(false);

        // Get user's resolution so that application automatically displays at that size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        vLogin.getPanelMain().setBounds(0,0,width,height);
        vUserType.getPanelMain().setBounds(0,0,width,height);
        vSignUp.getPanelMain().setBounds(0,0,width,height);
        vFP.getPanelMain().setBounds(0,0,width,height);
        vCO.getPanelMain().setBounds(0,0,width,height);
        vModule.getPanelMain().setBounds(0,0,width,height);
        vLH.getPanelMain().setBounds(0,0,width,height);
        vMH.getPanelMain().setBounds(0,0,width,height);
        vMA.getPanelMain().setBounds(0,0,width,height);
        vSD.getPanelMain().setBounds(0,0,width,height);
        vMAC.getPanelMain().setBounds(0,0,width,height);
        vBR.getPanelMain().setBounds(0,0,width,height);
        vERL.getPanelMain().setBounds(0, 0, width, height);
        vLMS.getPanelMain().setBounds(0, 0, width, height);
        vUP.getPanelMain().setBounds(0, 0, width, height);
        vLME.getPanelMain().setBounds(0, 0, width, height);

        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Force application window to display fullscreen
        // Decide which views are visible initially
        vLogin.getPanelMain().setVisible(true);         // Login page is shown as default page when app is opened
        vUserType.getPanelMain().setVisible(false);    // Sign up pages hidden
        vSignUp.getPanelMain().setVisible(false);
        vFP.getPanelMain().setVisible(false);
        vCO.getPanelMain().setVisible(false);
        vModule.getPanelMain().setVisible(false);
        vLH.getPanelMain().setVisible(false);
        vMH.getPanelMain().setVisible(false);
        vMA.getPanelMain().setVisible(false);
        vSD.getPanelMain().setVisible(false);
        vMAC.getPanelMain().setVisible(false);
        vBR.getPanelMain().setVisible(false);
        vERL.getPanelMain().setVisible(false);
        vLMS.getPanelMain().setVisible(false);
        vUP.getPanelMain().setVisible(false);
        vLME.getPanelMain().setVisible(false);

        // All views to be added to the mainFrame, but only visible view shown
        mainFrame.add(vLogin.getPanelMain());
        mainFrame.add(vUserType.getPanelMain());
        mainFrame.add(vSignUp.getPanelMain());
        mainFrame.add(vFP.getPanelMain());
        mainFrame.add(vCO.getPanelMain());
        mainFrame.add(vModule.getPanelMain());
        mainFrame.add(vLH.getPanelMain());
        mainFrame.add(vMH.getPanelMain());
        mainFrame.add(vMA.getPanelMain());
        mainFrame.add(vSD.getPanelMain());
        mainFrame.add(vMAC.getPanelMain());
        mainFrame.add(vBR.getPanelMain());
        mainFrame.add(vERL.getPanelMain());
        mainFrame.add(vLMS.getPanelMain());
        mainFrame.add(vUP.getPanelMain());
        mainFrame.add(vLME.getPanelMain());

        mainFrame.setVisible(true);                       // Display the main frame

    }
}
