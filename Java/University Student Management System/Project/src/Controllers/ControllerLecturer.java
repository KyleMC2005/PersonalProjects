package src.Controllers;

import src.Users.Lecturer;
import src.Users.Module;
import src.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.Arrays;
import java.util.Random;

public class ControllerLecturer {
    private ViewLogin vLogin;
    private ViewUserType vUserType;
    private ViewSignUp vSignUp;
    private ViewLecturerHome vLH;
    private ViewExamResultsLecturer vERL;
    private ViewListModuleStudents vLMS;
    private ViewUploadMaterials vUP;
    private ViewLecturerModuleEdit vLME;
    private Lecturer lModel;
    private Module mModule;

    public ControllerLecturer(ViewLogin vLogin, ViewUserType vUserType, ViewSignUp vSignUp, ViewLecturerHome vLH, ViewExamResultsLecturer vERL, ViewListModuleStudents vLMS, ViewUploadMaterials vUP, ViewLecturerModuleEdit vLME, Lecturer lModel, Module mModule) {
        this.vLogin = vLogin;
        this.vUserType = vUserType;
        this.vSignUp = vSignUp;
        this.vLH = vLH;
        this.vERL = vERL;
        this.vLMS = vLMS;
        this.vUP = vUP;
        this.vLME = vLME;
        this.lModel = lModel;
        this.mModule = mModule;

        vUserType.getPanelMain().setVisible(false);  // Hide start of sign up
        vSignUp.getPanelMain().setVisible(false);    // Panel for Sign up Page for Lecturers
        vLH.getPanelMain().setVisible(false);        // Panel for ViewLecturerHome
        vERL.getPanelMain().setVisible(false);       // Panel for View ExamResultsLecturer
        vLMS.getPanelMain().setVisible(false);       // Panel for ViewLecturer Model Students
        vUP.getPanelMain().setVisible(false);        // Panel for ViewUploadMaterials
        vLME.getPanelMain().setVisible(false);


        // User says they are a lecturer so show the lecturer signup page
        vUserType.getlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLecturerSignUp();
            }
        });

        vLH.getIssueExamResultsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExamResultsLecturer();
            }
        });

        vLH.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });

        vLMS.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goLecturerHome();
            }
        });

        vUP.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goLecturerHome();
            }
        });

        vERL.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               goLecturerHome();
            }
        });

        vLME.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { goLecturerHome(); }
        });

        vSignUp.getCreateAccButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vSignUp.getRole().equals("Lecturer")){  // this should always be true but prevents student signup from running
                    lecturerSignUp();
                }
            }
        });

        vLH.getViewStudentsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowListModuleStudents();
            }
        });

        vERL.getSubmitGradeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignResults();
            }
        });

        vLH.getUpdateModuleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowupdateModuleButton();
            }
        });

        vLH.getUpdateModuleDetailsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateModuleDetails();
            }
        });

        vLME.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDetails();
            }
        });

        // Insert Materials
        vUP.getCreateMaterialsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mod = Integer.parseInt(vUP.getModuleField().getText());          // Get ModuleID
                int week = Integer.parseInt(vUP.getWeekField().getText());           // Get WeekID
                String content = vUP.getContentField().getText();
                File  LecNoteFile = null;
                File  LabNoteFile = null;

                JFileChooser fileLec =  new JFileChooser();
                int returnval = fileLec.showOpenDialog(null);

                if (returnval == JFileChooser.APPROVE_OPTION) {
                    LecNoteFile = fileLec.getSelectedFile();
                    System.out.println("File Selected: " + LecNoteFile.getAbsolutePath());
                } else System.out.println("You need to select a file!");

                JFileChooser fileLab =  new JFileChooser();
                int returnval2 = fileLab.showOpenDialog(null);

                if (returnval2 == JFileChooser.APPROVE_OPTION) {
                    LabNoteFile = fileLab.getSelectedFile();
                    System.out.println("File Selected: " + LabNoteFile.getAbsolutePath());
                } else System.out.println("You need to select a file!");
                ShowCreateMaterials(mod, week, content, LecNoteFile, LabNoteFile);
            }
        });

        // Update Materials
        vUP.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mod = Integer.parseInt(vUP.getModuleField().getText());          // Get ModuleID
                int week = Integer.parseInt(vUP.getWeekField().getText());           // Get WeekID
                String content = vUP.getContentField().getText();                    // Get content
                File  LecNoteFile = null;
                File  LabNoteFile = null;

                JFileChooser fileLec =  new JFileChooser();
                int returnval = fileLec.showOpenDialog(null);

                if (returnval == JFileChooser.APPROVE_OPTION) {
                    LecNoteFile = fileLec.getSelectedFile();
                    System.out.println("File Selected: " + LecNoteFile.getAbsolutePath());
                } else System.out.println("You need to select a file!");

                JFileChooser fileLab =  new JFileChooser();
                int returnval2 = fileLab.showOpenDialog(null);

                if (returnval2 == JFileChooser.APPROVE_OPTION) {
                    LabNoteFile = fileLab.getSelectedFile();
                    System.out.println("File Selected: " + LabNoteFile.getAbsolutePath());
                } else System.out.println("You need to select a file!");


                ShowUpdateMaterials(mod, week, content, LecNoteFile, LabNoteFile);
            }
        });

        /*vUP.uploadLabPdfButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get Code from input
                GetFilesLec();
            }
        });

        vUP.uploadLecturePdfButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get Code from input
                GetFilesLab();

            }
        });*/
    }

    /*private File GetFilesLec() {

    }

    private File GetFilesLab() {

    }*/

    private void showUpdateModuleDetails(){
        vLH.getPanelMain().setVisible(false);
        vLME.getPanelMain().setVisible(true);
    }

    private void updateDetails(){
        int moduleID;
        try {
            moduleID = Integer.parseInt(vLME.getIdTextField().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid module ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "";

        if (mModule.getModule(moduleID) == null) {
            JOptionPane.showMessageDialog(vLME, "Course does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!vLME.getTitleTextField().getText().equals("")) {
                boolean success1 = mModule.updateDetails(moduleID, "ModuleName", vLME.getTitleTextField().getText());
                if (!success1) {
                    message += "Unable to update module title. \n";
                }
            }
            if (!vLME.getDescTextField().getText().equals("")) {
                boolean success2 = mModule.updateModule(moduleID, "ModuleInfo", vLME.getDescTextField().getText());
                if (!success2) {
                    message += "Unable to update course description. ";
                }
            }

            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(vLME, "Course successfully updated!", "Info", JOptionPane.INFORMATION_MESSAGE);
                vLME.clearTxts();
            } else {
                JOptionPane.showMessageDialog(vLME, message, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void ShowCreateMaterials(int weekID, int moduleID, String content, File labNote, File lectureNote) {
        vUP.getPanelMain().setVisible(true);
        if (vUP.getCreateMaterials(weekID, moduleID, content, labNote, lectureNote)) {
            JOptionPane.showMessageDialog(vUP, "Materials have been created.\n", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vUP, "Error creating Materials.\n", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ShowUpdateMaterials(int weekID, int moduleID, String content, File labNote, File lectureNote) {
        vUP.getPanelMain().setVisible(true);
        if (vUP.getUpdateMaterials(weekID, moduleID, content, labNote, lectureNote)) {
            JOptionPane.showMessageDialog(vUP, "Materials have been updated.\n", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(vUP, "Error updating Materials.\n", "Error", JOptionPane.ERROR_MESSAGE);
    }

     private void ShowListModuleStudents() {
        vLH.getPanelMain().setVisible(false);
        vLMS.getPanelMain().setVisible(true);
        vLMS.getRequestsSection().setVisible(true);
        vLMS.getAllStudents(lModel.getUserID());
    }

    private void ShowupdateModuleButton() {
        vLH.getPanelMain().setVisible(false);
        vUP.getPanelMain().setVisible(true);
        vUP.getModuleField().setVisible(true);
        vUP.getWeekField().setVisible(true);
        vUP.getContentField().setVisible(true);
        vUP.getUpdateButton().setVisible(true);
        vUP.getPanelHeader().setVisible(true);
    }

    public void showLecturerSignUp() {
        vUserType.getPanelMain().setVisible(false);     // Display lecturer signup GUI
        vSignUp.getPanelMain().setVisible(true);
        vSignUp.setLecturerSignUp();
    }

    public void lecturerSignUp(){
        // Creates an account
        Random rand = new Random();
        int id = rand.nextInt(999);
        String firstname = vSignUp.getFnField().getText();
        String lastname = vSignUp.getSnField().getText();
        String email = vSignUp.getEmailField().getText();

        char[] password = vSignUp.getPasswordField1().getPassword();
        char[] checkPassword = vSignUp.getPasswordField2().getPassword();
        // create date of birth, there's separate variables just so it's easier to look at
        int year = (Integer) vSignUp.getYearSpin().getValue();
        int month = (Integer) vSignUp.getMonthSpin().getValue();
        int day = (Integer) vSignUp.getDaySpin().getValue();
        Date dob = new Date(year-1900, month-1, day);  // subtraction matches up with sql type
        String gender = vSignUp.getGender().getSelectedItem().toString();
        String qualification = vSignUp.getQual().getSelectedItem().toString();
        String errors = validation(firstname, lastname, email, password, checkPassword, dob, gender, qualification);

        if(!(errors.isEmpty())){  // if there is an error
            JOptionPane.showMessageDialog(vSignUp, "Ensure that all errors are fixed:\n"+errors, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            lModel.createLecturer(id, firstname, lastname, gender, email, dob, String.valueOf(password), qualification);  // dont need to add in active as it is 0 until approved
            vSignUp.clearTxts();
            JOptionPane.showMessageDialog(vSignUp, "Account Successfully created.\nNOTE: the account will not be active until approved by a manager.", "Info", JOptionPane.INFORMATION_MESSAGE);
            vSignUp.getPanelMain().setVisible(false);
            vLogin.getPanelMain().setVisible(true);  // go back to login
        }
    }

    private String validation(String firstname, String lastname, String email, char[] password, char[] checkPassword, Date dob, String gender, String qualification) {
        String errors = "";

        // for password since it's not a string cant use isBlank, go through and try find a non-whitespace character
        boolean passBlank = true;
        boolean passCBlank = true;
        for(int i = 0; i < password.length; i++) {
            if((!Character.isWhitespace(password[i]))) {
                passBlank = false;
                break;
            }
        }
        for(int i = 0; i < checkPassword.length; i++) {
            if((!Character.isWhitespace(checkPassword[i]))) {
                passCBlank = false;
                break;
            }
        }

        // presence checks (also for only whitespace), dob and gender cannot be left blank
        if(firstname.isBlank() || lastname.isBlank() || email.isBlank() || passBlank || passCBlank) {
            errors += "Please ensure all fields have a value.\n";
            return errors;
        }
        // names checks
        if(firstname.length() > 30){
            errors += "First name cannot be greater than 30 characters.\n";
        }
        if(lastname.length() > 30){
            errors += "Last name cannot be greater than 30 characters.\n";
        }
        // make sure there's no numbers in the name (this can be changed cause some people have 3rd in their name but its here for now)
        boolean containNum = false;
        for(int i = 0; i < firstname.length(); i++) {
            if(Character.isDigit(firstname.charAt(i))) {
                containNum = true;
                break;
            }
        }
        if(!containNum) {  // only need to check the last name if there's not a number in the first name
            for(int i = 0; i < lastname.length(); i++) {
                if(Character.isDigit(lastname.charAt(i))) {
                    containNum = true;
                    break;
                }
            }
        }
        if(containNum) {
            errors += "Name cannot contain a number.\n";
        }
        // email checks
        if(email.length() > 50){
            errors += "Email cannot be greater than 50 characters.\n";
        }
        if(!(email.contains("@")) || !(email.contains("."))) {
            errors += "Please enter a valid email address.\n";
        }
        // password checks
        if(password.length > 30 || checkPassword.length > 30) {
            errors += "Password cannot be greater than 30 characters.\n";
        }
        if(!(Arrays.equals(password, checkPassword))) {
            errors += "Passwords don't match.\n";
        }
        // gender check
        if(gender.equals("<choose>")) {
            errors += "Please select a gender.\n";
        }
        // qualification check
        if(qualification.equals("<choose>")) {
            errors += "Please select a qualification.\n";
        }

        return errors;
    }

    void showExamResultsLecturer() {
        vLH.getPanelMain().setVisible(false);
        vERL.getPanelMain().setVisible(true);
    }

    public void logOut() { // Logs the user out
        JOptionPane.showMessageDialog(vSignUp, "You have been successfully logged out.", "Info", JOptionPane.INFORMATION_MESSAGE);
        vLH.getPanelMain().setVisible(false);
        vLogin.getPanelMain().setVisible(true); // (sends user back to login page, may become more complex later)
    }

    // Go to the manager home page
    void goLecturerHome() {
        vERL.getPanelMain().setVisible(false);
        vUP.getPanelMain().setVisible(false);
        vLMS.getPanelMain().setVisible(false);
        vLME.getPanelMain().setVisible(false);
        vLH.getPanelMain().setVisible(true);
    }


    public void assignResults(){
        int studentID;
        int moduleID;
        int grade;

        // Validate numeric fields
        try {
            studentID = Integer.parseInt(vERL.getEnterStudentID().getText());
            moduleID = Integer.parseInt(vERL.getSelectModuleCombo().getText());
            grade = Integer.parseInt(vERL.getEnterGradeText().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vERL, "Inputs must all be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check that student is part of module
        boolean success1 = mModule.getModuleLink(studentID, moduleID);
        // Check that the lecturer teaches the module
        boolean success2 = mModule.checkTeachesModule(lModel.getUserID(), moduleID);
        boolean success3 = false;

        if(success1 && success2) {
            if (vERL.getExamOrLab().getSelectedItem().equals("Exam")) {
                Module currentModule = mModule.getModule(moduleID);
                if(currentModule.getMarkingScheme().equals("Lab")) {
                    JOptionPane.showMessageDialog(vERL, "This module doesn't have any lab marks.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(currentModule.getMarkingScheme().equals("Both")){
                    if(grade >50 || grade < 0) {
                        JOptionPane.showMessageDialog(vERL, "Mark must be between 1-50.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        success3 = lModel.updateResults(studentID, moduleID, grade, "ExamResult");
                    }
                } else {
                    if(grade > 100 || grade < 0) {
                        JOptionPane.showMessageDialog(vERL, "Mark must be between 1-100.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        success3 = lModel.updateResults(studentID, moduleID, grade, "ExamResult");

                    }
                }

            } else {
                Module currentModule = mModule.getModule(moduleID);
                if(currentModule.getMarkingScheme().equals("Exam")) {
                    JOptionPane.showMessageDialog(vERL, "This module doesn't have any exam marks.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(currentModule.getMarkingScheme().equals("Both")){
                    if(grade > 50 || grade < 0) {
                        JOptionPane.showMessageDialog(vERL, "Mark must be between 1-50.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        success3 = lModel.updateBothResults(studentID, moduleID, grade, "LabResult");
                    }
                } else {
                    if(grade >100 || grade < 0) {
                        JOptionPane.showMessageDialog(vERL, "Mark must be between 1-100.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        success3 = lModel.updateResults(studentID, moduleID, grade, "LabResult");
                    }
                }
            }

            if (success3) {
                JOptionPane.showMessageDialog(vERL, "Student successfully marked.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(vERL, "Unable to mark student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(vERL, "Error, student is not part of module or lecturer doesn't teach module.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void getCurrentLecturer(Lecturer currentLecturer) {
        lModel = currentLecturer;
                System.out.println(lModel.getUserID() + " " + lModel.getForename() + " " + lModel.getSurname() + " " + lModel.getEmail() + " " + Arrays.toString(lModel.getPassword()) + " " + lModel.getDOB() + " " + lModel.getGender() + " " + lModel.getQualification());

    }
}
