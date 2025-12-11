package src.Controllers;
import src.*;
import src.DAO.StudentDAO;
import src.Users.Student;
import src.Users.Module;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.sql.Date;

public class ControllerStudent {
    private ViewUserType vUserType;
    private ViewSignUp vSignUp;
    private ViewLogin vLogin;  // here for after account creation
    private ViewCourseOverview vCO;
    private ViewModule vModule;
    private StudentDAO sDAO;
    private Student sModel;
    private Module mModel;



    public ControllerStudent(ViewUserType vUserType, ViewSignUp vSignUp, ViewLogin vLogin, ViewCourseOverview vCO, ViewModule vM, Student sModel, Module mModel, StudentDAO sDAO) {
        this.vUserType = vUserType;
        this.vSignUp = vSignUp;
        this.vLogin = vLogin;
        this.vCO = vCO;
        this.vModule = vM;
        this.sModel = sModel;
        this.mModel = mModel;
        this.sDAO = sDAO;

        vUserType.getPanelMain().setVisible(false);  // Hide start of sign up
        vSignUp.getPanelMain().setVisible(false);

        vCO.getPanelMain().setVisible(false); //hide course overview until logged in

        // User says they are a student so show the student signup page
        vUserType.getsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentSignUp();
            }
        });

        vSignUp.getCreateAccButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vSignUp.getRole().equals("Student")){  // this should always be true but prevents student signup from running
                    studentSignUp();
                }
            }
        });

    }


    public void getCurrentStudent(Student currentStudent) {
        sModel = currentStudent;
//        System.out.println(sModel.getUserID() + " " + sModel.getForename() + " " + sModel.getSurname() + " " + sModel.getEmail() + " " + Arrays.toString(sModel.getPassword()) + " " + sModel.getDOB() + " " + sModel.getGender() + " " + sModel.getCourseDecision());
    }

    public void showResults(){
        vCO.getPanelMain().setVisible(true);
        String decision = sModel.getCourseDecision();
        vCO.getNoResultsLabel().setText(decision);
    }

    public void displayModuleButtons(int studentID){
        ArrayList<JButton> buttons = new ArrayList<>();
        vCO.getPanelModuleList().removeAll();
        vCO.getPanelModuleList().setLayout(new GridLayout(3,3, 30, 30));
        vCO.getPanelModuleList().setBorder(new EmptyBorder(30, 30, 30, 30));

        buttons = sModel.createModuleButtons(studentID);

        if(buttons == null){
            JLabel noModules = new JLabel();
            noModules.setText("No modules found. Speak to a manager if there are supposed to be");
            vCO.getPanelModuleList().add(noModules);
        }

        for (int i = 0; i < buttons.size(); i++) {
            int moduleID = Integer.parseInt(buttons.get(i).getText());
            buttons.get(i).addActionListener(new ActionListener() {  // make an action listener for every button in the array
                @Override
                public void actionPerformed(ActionEvent e) {
                    showModuleOverview(moduleID);
                }
            });
            vCO.getPanelModuleList().add(buttons.get(i));
        }
    }

    public void displayNoteButtons(int moduleID, String identifer){
        ArrayList<JButton> buttons = new ArrayList<>();

        buttons = sModel.createNotesButtons(moduleID);

            switch(identifer) {
                case "lecture":
                    JPanel lecturePanel = new JPanel();
                    lecturePanel.setLayout(new GridLayout(10, 1, 30, 30));
                    lecturePanel.setBorder(new EmptyBorder(30, 30, 30, 30));

                    vModule.getMaterials1().setViewportView(lecturePanel);

                    for (int i = 0; i < buttons.size(); i++) {
                        int weekID = Integer.parseInt(buttons.get(i).getText());
                    buttons.get(i).addActionListener(new ActionListener() {  // make an action listener for every button in the array
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            InputStream inputStream = sDAO.getLectureNoteBlob(weekID, moduleID);
                            try {
                                if (inputStream == null) {
                                    JOptionPane.showMessageDialog(null, "No file found for this week.");
                                    return;
                                }
                                Path downloads = Path.of(System.getProperty("user.home"), "Downloads");
                                Path target = downloads.resolve("lecturenotes.pdf");

                                Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
                                JOptionPane.showMessageDialog(null, "Lecture notes downloaded, please check your downloads folder");
                            } catch (IOException ex) {
                                System.out.println("File path not found lab");
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                        lecturePanel.add(buttons.get(i));
                    }
                    break;

                case "lab":
                    JPanel labPanel = new JPanel();
                    labPanel.setLayout(new GridLayout(10, 1, 30, 30));
                    labPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

                    vModule.getMaterials2().setViewportView(labPanel);

                    for (int i = 0; i < buttons.size(); i++) {
                        int weekID = Integer.parseInt(buttons.get(i).getText());
                    buttons.get(i).addActionListener(new ActionListener() {  // make an action listener for every button in the array
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            InputStream inputStream = sDAO.getLabNoteBlob(weekID, moduleID);
                            try {
                                if (inputStream == null) {
                                    JOptionPane.showMessageDialog(null, "No file found for this week.");
                                    return;
                                }
                                Path downloads = Path.of(System.getProperty("user.home"), "Downloads");
                                Path target = downloads.resolve("labnotes.pdf");

                                Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);

                                JOptionPane.showMessageDialog(null, "Lab notes downloaded, please check your downloads folder");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                        labPanel.add(buttons.get(i));
                    }
                    break;
            }

    }


    private void showModuleOverview(int moduleID){
        Module module = mModel.getModule(moduleID);  // get the information for the module and load it into the text fields
        int studentID = sModel.getUserID();
        vModule.getModuleTitle().setText(module.getModuleName());
        vModule.getModuleDescription().setText(module.getDescription());
        showModuleResult(module, studentID);  //
        vCO.getPanelMain().setVisible(false);
        vModule.getPanelMain().setVisible(true);
        displayNoteButtons(moduleID,"lab");
        displayNoteButtons(moduleID,"lecture");
    }

    public void showModuleResult(Module module, int studentID){
        int moduleID = module.getModuleID();
        ArrayList<Integer> results = mModel.studentResult(moduleID, studentID);  // saved in the order result, examResult, labResult
        String moduleMarking = module.getModuleMarking();
        switch (moduleMarking){  // depending on the way of module marking the correct results will be displayed
            case "Exam":
                vModule.getResultLabel().setText("<html>Current Results<br><br>Result: " + results.get(0) + "<br>Exam Result: " + results.get(1)+"</html>");
                break;
            case "Lab":
                vModule.getResultLabel().setText("<html>Current Results<br><br>Result: " + results.get(0) + "<br>Lab Result: " + results.get(2)+"</html>");
                break;
            case "Both":
                vModule.getResultLabel().setText("<html>Current Results<br><br>Result: " + results.get(0) + "<br>Exam Result: " + results.get(1) + "<br>Lab Result: " + results.get(2)+"</html>");
        }
    }

    private void showStudentSignUp() {
        vUserType.getPanelMain().setVisible(false);     // Display student signup GUI
        vSignUp.getPanelMain().setVisible(true);
        vSignUp.setStudentSignUp();
    }

    public void studentSignUp(){
        // Creates an account
        Random rand = new Random();
        int id = rand.nextInt(9999);
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
        String studentType = vSignUp.getStudentType().getSelectedItem().toString();
        String errors = validation(firstname, lastname, email, password, checkPassword, dob, gender, studentType);

        if(!(errors.isEmpty())){  // if there is an error
            JOptionPane.showMessageDialog(vSignUp, "Ensure that all errors are fixed:\n"+errors, "Password Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            if(!(sModel.getIDByEmailS(email) == -1)){  // returns -1 if there is no account so checking if an id exists
                JOptionPane.showMessageDialog(vSignUp, "Account Already exists.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                sModel.createStudent(id, firstname, lastname, gender, email, dob, String.valueOf(password), studentType, "Pending");  // dont need to add in active as it is 0 until approved
                vSignUp.clearTxts();
                JOptionPane.showMessageDialog(vSignUp, "Account Successfully created.\nNOTE: the account will not be active until approved by a manager.", "Info", JOptionPane.INFORMATION_MESSAGE);
                vSignUp.getPanelMain().setVisible(false);
                vLogin.getPanelMain().setVisible(true);  // go back to login
            }
        }
    }

    private String validation(String firstname, String lastname, String email, char[] password, char[] checkPassword, Date dob, String gender, String studentType) {
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
        // degree type check
        if(studentType.equals("<choose>")) {
            errors += "Please select a degree type.\n";
        }

        return errors;
    }

}
