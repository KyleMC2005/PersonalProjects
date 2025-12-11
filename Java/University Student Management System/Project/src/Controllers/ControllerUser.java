package src.Controllers;
import src.*;
import src.Users.Lecturer;
import src.Users.Manager;
import src.Users.Student;
import src.Users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ControllerUser {
    private ViewLogin vLogin;
    private ViewForgetPassword vFP;
    private ViewUserType vUserType;
    private ViewSignUp vSignUp;
    private ViewCourseOverview vCO;
    private ViewModule vModule;
    private ViewLecturerHome vLH;
    private ViewManagerHome vMH;
    private User mUser;
    private ControllerStudent sController;
    private ControllerLecturer lController;
    private ControllerManager mController;

    public ControllerUser(ViewLogin vLogin, ViewForgetPassword vFP, ViewUserType vUserType, ViewSignUp vSignUp, ViewCourseOverview vCO, ViewModule vModule, ViewLecturerHome vLH, ViewManagerHome vMH, User mUser,  ControllerStudent sController, ControllerLecturer lController, ControllerManager mController) {
        this.vLogin = vLogin;
        this.vFP = vFP;
        this.vUserType = vUserType;
        this.vSignUp = vSignUp;
        this.vCO = vCO;
        this.vModule = vModule;
        this.vLH = vLH;
        this.vMH = vMH;
        this.mUser = mUser;
        this.sController = sController;
        this.lController = lController;
        this.mController = mController;

        // Initialise views
        vLogin.getPanelMain().setVisible(true);       // Login view ready to be displayed
        vUserType.getPanelMain().setVisible(false);  // Hide start of sign up
        vSignUp.getPanelMain().setVisible(false);
        vFP.getPanelMain().setVisible(false);
        vCO.getPanelMain().setVisible(false);
        vModule.getPanelMain().setVisible(false);
        vLH.getPanelMain().setVisible(false);
        vMH.getPanelMain().setVisible(false);

        // Action listeners for ViewLogin

        // If user is trying to log in, check their credentials
        vLogin.getSignInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // If user is trying to create a new account, start the signup sequence
        vLogin.getCreateAccButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showChoiceGUI();
            }
        });

        // If user has forgotten password, go to "change password" page
        vLogin.getForgotButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forgotPasswordGUI();
            }
        });

        // Action listeners for ViewForgetPassword

        // When user clicks the back arrow, they go back to the page they were on before
        vFP.getBackButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackLogin();
            }
        });

        vFP.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });

        // Action listeners for ViewUserType

        // When user clicks the back arrow, they go back to the page they were on before
        vUserType.getBackButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackLogin();
            }
        });

        // Action listeners for ViewSignUp

        // When user clicks the back arrow, they go back to the page they were on before
        vSignUp.getBackButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackUserChoose();
            }
        });

        // User has filled in their details and wants to create an account
        vSignUp.getCreateAccButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                handleSignUp();
            }
        });

        // Action listeners for ViewCourseOverview

        // User decides to log out
        vCO.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });

//        vCO.getModule1().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) { loadCourseModule();}
//        });

        // Action listeners for ViewModule

        vModule.getBackButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goHome();
            }
        });

    }


    // Just go to base module page for now, will make it more specific later
    private void loadCourseModule() {
        vCO.getPanelMain().setVisible(false);
        vModule.getPanelMain().setVisible(true);
    }

    private void handleLogin() {
        // Reads inputs from vLogin GUI
        String email = vLogin.getIdField().getText();
        char[] password = vLogin.getPasswordField().getPassword();
        String role = "";  // used to tell what role the user is for logging in
        boolean loginSuccess = false;

        if(vLogin.getStudentRadioButton().isSelected()) {
            role = "Student";
        }
        if(vLogin.getLecturerRadioButton().isSelected()) {
            role = "Lecturer";
        }
        if(vLogin.getManagerRadioButton().isSelected()) {
            role = "Manager";
        }

        // set the user to be a specific type of user
        switch (role) {
            case "Student":
                Student mStudent = new Student();
                loginSuccess = mStudent.loginStudent(email, password);   // Use model method and return the outcome
                if (loginSuccess) {
                    int id = mStudent.getIDByEmailS(email);
                    Student currentStudent = mStudent.getStudentByID(id);
                    sController.getCurrentStudent(currentStudent);
                    JOptionPane.showMessageDialog(vLogin, "Login Successful", "Info", JOptionPane.INFORMATION_MESSAGE);
                    vLogin.clearTxts();
                    vLogin.getPanelMain().setVisible(false);  // hide login screen
                    sController.showResults();// show course overview
                    sController.displayModuleButtons(id);
                }
                else {
                    JOptionPane.showMessageDialog(vLogin, "Login unsuccessful, please check your details or contact manager if problem persists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Lecturer":
                Lecturer mLecturer = new Lecturer();
                loginSuccess = mLecturer.loginLecturer(email, password);  // use model method and return outcome
                if(loginSuccess) {
                    int id = mLecturer.getIDByEmailL(email);
                    Lecturer currentLecturer = mLecturer.getLecturerByID(id);
                    lController.getCurrentLecturer(currentLecturer);
                    JOptionPane.showMessageDialog(vLogin, "Login Successful", "Info", JOptionPane.INFORMATION_MESSAGE);
                    vLogin.clearTxts();
                    vLogin.getPanelMain().setVisible(false);  // hide login screen
                    vLH.getPanelMain().setVisible(true);  // show lecturer home
                }
                else {
                    JOptionPane.showMessageDialog(vLogin, "Login unsuccessful, please check your details or contact manager if problem persists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Manager":
                Manager mManager = new Manager();
                loginSuccess = mManager.loginManager(email, password);
                if(loginSuccess) {
                    int id = mManager.getIDByEmailM(email);
                    Manager currentManager = mManager.getManagerByID(id);
                    mController.getCurrentManager(currentManager);
                    JOptionPane.showMessageDialog(vLogin, "Login Successful", "Info", JOptionPane.INFORMATION_MESSAGE);
                    vLogin.clearTxts();
                    vLogin.getPanelMain().setVisible(false);  // hide login screen
                    vMH.getPanelMain().setVisible(true);  // show manager home page
                    vMH.getSignUpWorkFlow();
                }
                else {
                    JOptionPane.showMessageDialog(vLogin, "Login unsuccessful, please check your details or contact manager if problem persists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private void showChoiceGUI() {      // Start sign up by changing to the choice of user page
        vLogin.getPanelMain().setVisible(false);
        vUserType.getPanelMain().setVisible(true);
    }

    private void forgotPasswordGUI() {
        // Display "change password" GUI and start the change password process
        vLogin.getPanelMain().setVisible(false);
        vFP.getPanelMain().setVisible(true);
    }

    private void goBackLogin() {      // Go Back to the login page
        vUserType.getPanelMain().setVisible(false);
        vFP.getPanelMain().setVisible(false);
        vLogin.getPanelMain().setVisible(true);
    }

    private void goBackUserChoose() {
        vSignUp.getPanelMain().setVisible(false);
        vUserType.getPanelMain().setVisible(true);
    }
    private void logOut() { // Logs the user out
        JOptionPane.showMessageDialog(vSignUp, "You have been successfully logged out.", "Info", JOptionPane.INFORMATION_MESSAGE);
        vCO.getPanelMain().setVisible(false);
        vMH.getPanelMain().setVisible(false);
        vLogin.getPanelMain().setVisible(true); // (sends user back to login page, may become more complex later)
    }

    private void goHome() {
        vModule.getPanelMain().setVisible(false);
        vCO.getPanelMain().setVisible(true);
    }

    // Changes the user's password on forgot password page
    private void changePassword() {
            // Request Email:
            String LocalEmail = vFP.getEmailField().getText();
            char[] Password = vFP.getNewPassword().getPassword();
            char[] ConfirmPass = vFP.getConfirmPassword().getPassword();
            Manager unapproveAcc = new Manager();
            boolean success = false;
            // More Code

            if (!Arrays.equals(Password, ConfirmPass)) {
                JOptionPane.showMessageDialog(vFP, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                // If Email is valid: (Database Comparison)
                // Find what user type the user is
                String user = mUser.getUserTypeEmail(LocalEmail);
                if(user == null){
                    JOptionPane.showMessageDialog(vFP, "Email does not match any existing users.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                switch (user){
                    case "Student":
                        Student mStudent = new Student();
                        success = mStudent.updatePassword(String.valueOf(Password), LocalEmail);
                        // Set the account to be unapproved in case someone other than the user tried to change the account
                        unapproveAcc.approveStudent(mStudent.getIDByEmailS(LocalEmail), "0");
                        break;
                    case "Lecturer":
                        Lecturer mLecturer = new Lecturer();
                        success = mLecturer.updatePassword(String.valueOf(Password), LocalEmail);
                        // Set the account to be unapproved in case someone other than the user tried to change the account
                        unapproveAcc.approveLecturer(mLecturer.getIDByEmailL(LocalEmail), "0");
                        break;
                        case "Manager":
                            JOptionPane.showMessageDialog(vFP, "Cannot change password as this user is a Manager.", "Error", JOptionPane.ERROR_MESSAGE);
                            default:
                                JOptionPane.showMessageDialog(vFP, "An error has occured.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                }

                // If password updated successfully
                if (success){
                    JOptionPane.showMessageDialog(vFP, "Password successfully updated!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    vFP.clearTxts();
                    vFP.getPanelMain().setVisible(false);
                    vLogin.getPanelMain().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vFP, "An error occurred when trying to update your password.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }
}


