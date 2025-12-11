package src.Controllers;
import src.*;
import src.Users.*;
import src.Users.Module;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerManager {
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

    public ControllerManager(ViewLogin vLogin, ViewManagerHome vMH, ViewManageAccounts vMA, ViewStudentDecisions vSD, ViewManageCourseOrModule vMAC, ViewBusinessRule vBR, Lecturer mLecturer, Manager mManager, Student mStudent, Course mCourse, Module mModule) {
        this.vLogin = vLogin;
        this.vMH = vMH;
        this.vMA = vMA;
        this.vSD = vSD;
        this.vMAC = vMAC;
        this.vBR = vBR;
        this.mLecturer = mLecturer;
        this.mManager = mManager;
        this.mStudent = mStudent;
        this.mCourse = mCourse;
        this.mModule = mModule;
        this.mCourse = mCourse;

        vLogin.getPanelMain().setVisible(false);
        vMH.getPanelMain().setVisible(false);
        vMA.getPanelMain().setVisible(false);
        vSD.getPanelMain().setVisible(false);
        vMAC.getPanelMain().setVisible(false);
        vBR.getPanelMain().setVisible(false);

        // Action Listeners for ViewManagerHome

        // Home button
        vMH.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goManagerHome();
            }
        });

        vMH.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });

        // Manage Accounts Button
        vMH.getManageAccountsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showManageAccGUI();
            }
        });

        // Issue Decision Button
        vMH.getIssueDecisionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIssueDecGUI();
            }
        });

        // Business Rules Button
        vMH.getAddBusinessRuleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBusinessGUI();
            }
        });

        // Course/Module Options Button
        vMH.getCourseModuleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCourseModuleGUI();
            }
        });

        vMH.getApproveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveUsers();
            }
        });

        vMH.getDenyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unapproveUsers();
            }
        });

        // Add module button
        vMAC.getAddButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addModule();
            }
        });

        // Add course button
        vMAC.getAddCourse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        // Enroll student onto a course
        vMAC.getEnrollButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentCourse();
            }
        });

        vMAC.getUnenrollButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unaddStudentCourse();
            }
        });

        // Enroll student onto a specific module
        vMAC.getEnrollButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentModule();
            }
        });

        // Edit module button
        vMAC.getUpdateButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editModule();
            }
        });

        // Edit course button
        vMAC.getUpdateButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCourse();
            }
        });

        // Manager GUI Home buttons
        vMAC.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goManagerHome();
            }
        });

        vMAC.getViewModuleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayModule();
            }
        });

        vMAC.getViewCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCourse();
            }
        });

        // Home button
        vMA.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goManagerHome();
            }
        });

        vMA.getActivateAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activateAccount();
            }
        });

        vMA.getDeactivateAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deactivateAccount();
            }
        });

        vMA.getResetPasswordButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
            }
        });

        vMA.getDeleteAccount().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });

        vMA.getApproveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveUser();
            }
        });

        vMA.getUnapproveAccount().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unapproveUser();
            }
        });

        vBR.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goManagerHome();
            }
        });

        // Chnage maximum module attempts
        vBR.getUpdateButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxModuleAttempts();
            }
        });

        // Change number of compensated modules
        vBR.getUpdateButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduleCompensations();
            }
        });

        vSD.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goManagerHome();
            }
        });

        vSD.getIssueDecisionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issueDecision();
            }
        });

    }

    private void showIssueDecGUI() {
        vMH.getPanelMain().setVisible(false);
        vSD.getPanelMain().setVisible(true);
    }

    private void showManageAccGUI() {
        vMH.getPanelMain().setVisible(false);
        vMA.getPanelMain().setVisible(true);
    }

    private void showBusinessGUI() {
        vMH.getPanelMain().setVisible(false);
        vBR.getPanelMain().setVisible(true);
    }

    private void showCourseModuleGUI() {
        vMH.getPanelMain().setVisible(false);
        vMAC.getPanelMain().setVisible(true);
    }

    // Go to the manager home page
    private void goManagerHome() {
        vMA.getPanelMain().setVisible(false);
        vMAC.getPanelMain().setVisible(false);
        vBR.getPanelMain().setVisible(false);
        vSD.getPanelMain().setVisible(false);
        vMH.getSignUpWorkFlow();
        vMH.getPanelMain().setVisible(true);
    }

    private void logOut() { // Logs the user out
        JOptionPane.showMessageDialog(vMH, "You have been successfully logged out.", "Info", JOptionPane.INFORMATION_MESSAGE);
        vMH.getPanelMain().setVisible(false);
        vLogin.getPanelMain().setVisible(true); // (sends user back to login page, may become more complex later)
    }

    // Adds a new module to the Module database
    void addModule() {
        // Get values from the user
        int moduleID;
        int credits;
        String marking;

        try {
            moduleID = Integer.parseInt(vMAC.getCodeField().getText());
            credits = Integer.parseInt(vMAC.getCreditField2().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "Module code and credits must have a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String moduleName = vMAC.getNameField2().getText();
        String description = vMAC.getModDescField2().getText();

        if (vMAC.getMarkingScheme().getSelectedItem().equals("Exam 100%")) {
            marking = "Exam";
        } else if (vMAC.getMarkingScheme().getSelectedItem().equals("Lab(s) 100%")){
            marking = "Lab";
        } else {
            marking = "Both";
        }

        int courseID;
        int lecturerID;

        boolean success1 = mManager.addModule(moduleID, moduleName, credits, description, marking);

        // If Module database updated successfully
        if (success1) {

            // Allow user to not input value for lecturer ID
            if (vMAC.getAssignedLec2().getText() == null || vMAC.getAssignedLec2().getText().equals("")) {
                lecturerID = -1;
            } else {
                lecturerID = Integer.parseInt(vMAC.getAssignedLec2().getText());
                // Link Lecturer to Module
                boolean success2 = mManager.addLecturerModule(moduleID, lecturerID);
                if (!success2) {
                    JOptionPane.showMessageDialog(vMAC, "Lecturer unable to be assigned to module.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Allow user to not input value for course ID
            if (vMAC.getOnCourse2().getText() == null || vMAC.getOnCourse2().getText().equals("")) {
                courseID = -1;
            } else {
                courseID = Integer.parseInt(vMAC.getOnCourse2().getText());
                // Link Module to Course
                boolean success3 = mManager.addCourseModule(courseID, moduleID);
                if (!success3) {
                    JOptionPane.showMessageDialog(vMAC, "Module unable to be assigned to course.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(vMAC, "Module \"" + moduleName + " successfully added!", "Info", JOptionPane.INFORMATION_MESSAGE);
            vMAC.clearTxts();
        } else {
            JOptionPane.showMessageDialog(vMAC, "Module unable to be added.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Edit a module in the Module database
    void editModule() {
        int moduleID;

        // Check that module ID input is valid
        try {
            moduleID = Integer.parseInt(vMAC.getCodeDrop().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "Module code must have a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "";    // Create an error message if needed

        // Check that the module exists using Module ID
        if (mModule.getModule(moduleID) == null) {
            JOptionPane.showMessageDialog(vMAC, "Module does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            // User doesn't have to fill in every field, so if input field is empty skip over it

            if (!vMAC.getNameField1().getText().equals("")) {
                boolean success1 = mModule.updateModule(moduleID, "ModuleName", vMAC.getNameField1().getText());
                if (!success1) {
                    message += "Unable to update module name. ";
                }
            }

            if (!vMAC.getCreditField1().getText().equals("")) {
                boolean success2 = mModule.updateModule(moduleID, "Credits", vMAC.getCreditField1().getText());
                if (!success2) {
                    message += "Unable to update module credits. ";
                }
            }

            if (!vMAC.getModDescField1().getText().equals("")) {
                boolean success3 = mModule.updateModule(moduleID, "ModuleInfo", vMAC.getModDescField1().getText());
                if (!success3) {
                    message += "Unable to update module information. ";
                }
            }

            if (!vMAC.getAssignedLec().getText().equals("")) {
                boolean success4 = mManager.addLecturerModule(moduleID, Integer.parseInt(vMAC.getAssignedLec().getText()));
                if (!success4) {
                    message += "Unable to link lecturer to module. ";
                }
            }

            if (!vMAC.getOnCourse().getText().equals("")) {
                boolean success5 = mManager.addCourseModule(Integer.parseInt(vMAC.getOnCourse().getText()), moduleID);
                if (!success5) {
                    message += "Unable to link module to course. ";
                }
            }

            // If message is empty, no errors occured
            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(vMAC, "Module successfully updated!", "Info", JOptionPane.INFORMATION_MESSAGE);
                vMAC.clearTxts();
            } else {
                JOptionPane.showMessageDialog(vMAC, message, "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    // Edit a Course in the Course database
    void editCourse() {
        int courseID;
        // Check if course ID is a valid input

        try {
            courseID = Integer.parseInt(vMAC.getCodeDrop2().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "Course code must have a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "";

        // Check if course exists
        if (mCourse.getCourse(courseID) == null) {
            JOptionPane.showMessageDialog(vMAC, "Course does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            // User doesn't have to fill in every field, so if input field is empty skip over it

            if (!vMAC.getNameField3().getText().equals("")) {
                boolean success1 = mCourse.updateCourse(courseID, "CourseName", vMAC.getNameField3().getText());
                if (!success1) {
                    message += "Unable to update course name. ";
                }
            }

            if (!vMAC.getDescField1().getText().equals("")) {
                boolean success2 = mCourse.updateCourse(courseID, "CourseDesc", vMAC.getDescField1().getText());
                if (!success2) {
                    message += "Unable to update course description. ";
                }
            }

            if (!vMAC.getSemField().getText().equals("")) {
                boolean success3 = mCourse.updateCourse(courseID, "Semesters", vMAC.getSemField().getText());
                if (!success3) {
                    message += "Unable to update course semesters. ";
                }
            }

            if (!vMAC.getCourseTypeSelection2().getSelectedItem().equals("<choose>")) {
                boolean success4 = mCourse.updateCourse(courseID, "CourseType", vMAC.getCourseTypeSelection2().getSelectedItem().toString());
                if (!success4) {
                    message += "Unable to update course type. ";
                }
            }

            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(vMAC, "Course successfully updated!", "Info", JOptionPane.INFORMATION_MESSAGE);
                vMAC.clearTxts();
            } else {
                JOptionPane.showMessageDialog(vMAC, message, "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    // Add a new Course to Course database
    void addCourse() {
        // Get user inputs
        int courseID;
        int semesters;

        try {
            System.out.println(vMAC.getCodeField2().getText());
            courseID = Integer.parseInt(vMAC.getCodeField2().getText());
            semesters = Integer.parseInt(vMAC.getSemField2().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "Course code and semesters must have a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String courseName = vMAC.getNameField4().getText();
        String description = vMAC.getDescField2().getText();
        String courseType = vMAC.getCourseTypeSelection().getSelectedItem().toString();

        boolean success = mManager.addCourse(courseID, courseName, description, semesters, courseType);

        // If course was added successfully to database
        if (success) {
            JOptionPane.showMessageDialog(vMAC, "Course \"" + courseName + " successfully added!", "Info", JOptionPane.INFORMATION_MESSAGE);
            vMAC.clearTxts();
        } else {
            JOptionPane.showMessageDialog(vMAC, "Course unable to be added.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    boolean validateCourseEnroll(){
    // Get user inputs
        int studentID;
        int confirm;
        int courseID;

        try {
            studentID = Integer.parseInt(vMAC.getStudIdField().getText());
            confirm = Integer.parseInt(vMAC.getStudIdField2().getText());
            courseID = Integer.parseInt(vMAC.getCourseEnroll().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "All inputs must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // If the repeated studentID doesn't match the original input
        if (studentID != confirm) {
            JOptionPane.showMessageDialog(vMAC, "Student ID doesn't match.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    // Add a student onto a course
    void addStudentCourse() {
        if (validateCourseEnroll()) {
            int studentID = Integer.parseInt(vMAC.getStudIdField().getText());
            int confirm = Integer.parseInt(vMAC.getStudIdField2().getText());
            int courseID = Integer.parseInt(vMAC.getCourseEnroll().getText());

            // Enroll student
            int tempID = mStudent.checkStudentCourse(studentID);
            if (tempID != -1) {
                JOptionPane.showMessageDialog(vMAC, "Student is already enrolled onto a course.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success2 = mManager.enrollStudent(studentID, courseID);
            if (success2) {
                JOptionPane.showMessageDialog(vMAC, "Student successfully enrolled onto course!", "Info", JOptionPane.INFORMATION_MESSAGE);
                vMAC.clearTxts();
            } else {
                JOptionPane.showMessageDialog(vMAC, "Student or course id doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        }

    private void unaddStudentCourse() {
        if(validateCourseEnroll()) {
            int studentID = Integer.parseInt(vMAC.getStudIdField().getText());
            int confirm = Integer.parseInt(vMAC.getStudIdField2().getText());
            int courseID = Integer.parseInt(vMAC.getCourseEnroll().getText());

            // Unenroll student
            int tempID = mStudent.checkStudentCourse(studentID);
            if (tempID == -1) {
                JOptionPane.showMessageDialog(vMAC, "Student isn't enrolled onto a course.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success2 = mManager.unenrollStudent(studentID, courseID);
            if (success2) {
                JOptionPane.showMessageDialog(vMAC, "Student successfully removed from course!", "Info", JOptionPane.INFORMATION_MESSAGE);
                vMAC.clearTxts();
            } else {
                JOptionPane.showMessageDialog(vMAC, "Student or course id doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Add a student onto a specific module
    void addStudentModule() {
        // Get user inputs
        int studentID;
        int confirm;
        int moduleID;

        try {
            studentID = Integer.parseInt(vMAC.getStudIDField3().getText());
            confirm = Integer.parseInt(vMAC.getStudIDField4().getText());
            moduleID = Integer.parseInt(vMAC.getModuleEnroll().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "All inputs must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If the repeated studentID doesn't match the original input
        if (studentID != confirm) {
            JOptionPane.showMessageDialog(vMAC, "Student ID doesn't match.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Enroll student
            boolean success = mManager.enrollStudentModule(studentID, moduleID);
            if (success) {
                JOptionPane.showMessageDialog(vMAC, "Student successfully enrolled onto module!", "Info", JOptionPane.INFORMATION_MESSAGE);
                vMAC.clearTxts();
            } else {
                JOptionPane.showMessageDialog(vMAC, "Student or module id doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Merge these two business rules later?

    // Change maximum number of module attempts allowed for a module
    void maxModuleAttempts() {
        int moduleID = -1;
        int courseID = -1;

        if(!vBR.getOpCourseCode().getText().equals("")){
            try{
                courseID = Integer.parseInt(vBR.getOpCourseCode().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vBR, "All inputs must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            try{
                moduleID = Integer.parseInt(vBR.getModuleCode().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vBR, "All inputs must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        int attempts = Integer.parseInt(vBR.getSpinner1().getValue() + "");

        if (attempts <= 0 || attempts > 10) {
            JOptionPane.showMessageDialog(vBR, "Number must be a value of 1-10.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // If a course code has been input
            if(!vBR.getOpCourseCode().getText().equals("")){
                ArrayList<Module> modules = mCourse.getCourseModules(courseID);
                if (modules != null) {
                    for (Module module : modules) {
                        mManager.addBusinessRule(module.getModuleID(), attempts, "attempts");
                    }
                    JOptionPane.showMessageDialog(vBR, "Max attempts for modules on course changed!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    vBR.clearTxts();
                } else {
                    JOptionPane.showMessageDialog(vBR, "Error updating max attempts for all modules.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {        // Just module code
                boolean success = mManager.addBusinessRule(moduleID, attempts, "attempts");
                if (success) {
                    JOptionPane.showMessageDialog(vBR, "Max attempts for module changed!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    vBR.clearTxts();
                } else {
                    JOptionPane.showMessageDialog(vBR, "Module doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    // Change number of modules allowed to be compensated for a course
    void moduleCompensations() {
        int courseID;

        try {
            courseID = Integer.parseInt(vBR.getCourseCode().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vBR, "All inputs must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int comps = Integer.parseInt(vBR.getSpinner2().getValue() + "");
        if (comps <= 0 || comps > 10) {
            JOptionPane.showMessageDialog(vBR, "Number must be a value of 1-10.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean success = mManager.addBusinessRule(courseID, comps, "compensation");
            if (success) {
                JOptionPane.showMessageDialog(vBR, "Max module compensations changed for course!", "Info", JOptionPane.INFORMATION_MESSAGE);
                vBR.clearTxts();
            } else {
                JOptionPane.showMessageDialog(vBR, "Course doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Issue a student decision
    void issueDecision() {
        int studentID;
        try {
            studentID = Integer.parseInt(vSD.getTextField1().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vSD, "Invalid Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if student exists
        if (mStudent.getStudentByID(studentID) == null) {       // Student does not exist in database
            JOptionPane.showMessageDialog(vSD, "Invalid Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if there are any unmarked modules
        boolean marked = mManager.checkMarks(studentID, -1);
        if (!marked) {      // There are unmarked modules, so don't allow a decision to be issued
            JOptionPane.showMessageDialog(vSD, "Cannot issue decision, student hasn't received all module marks.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Else if used for checkboxes so that only one check box value gets validated if multiple are selected
        if (vSD.getAwardCheckBox().isSelected()) {      // Award checked
            boolean award = mManager.checkMarks(studentID, 0);     // Check if the student has failed any modules
            if (award) {                                         // If all modules passed
                if (mManager.issueDecision(studentID, "Award")) {
                    JOptionPane.showMessageDialog(vSD, "'Award' decision successfully awarded to student!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {                            // Some other issue with database occurs
                    JOptionPane.showMessageDialog(vSD, "Error issuing award to student.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {        // At least one module failed
                JOptionPane.showMessageDialog(vSD, "Unable to issue 'Award' to this student as at least one module is set to 'Fail'.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (vSD.getResitCheckBox().isSelected()) {        // Resit checked
            boolean award = mManager.checkMarks(studentID, 0);     // Check if the student has failed any modules
            if (!award) {                                        // If any classes have been failed
                boolean success = mManager.resitAllowed(studentID);     // Check if student is allowed to resit or not
                if (success) {                                           // Student has enough attempts on a failed class
                    if (mManager.issueDecision(studentID, "Resit")) {
                        JOptionPane.showMessageDialog(vSD, "'Resit' decision successfully awarded to student!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {            // Some other issue with database occurs
                        JOptionPane.showMessageDialog(vSD, "Error issuing award to student.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {  // Not enough module attempts
                    JOptionPane.showMessageDialog(vSD, "Student is unable to 'Resit' as they have ran out of module attempts, must be set to 'Withdraw'.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {        // If student has passed all modules
                JOptionPane.showMessageDialog(vSD, "Unable to issue 'Resit' to this student as they have passed all modules.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (vSD.getWithdrawCheckBox().isSelected()) {     // Withdraw checked
            boolean award = mManager.checkMarks(studentID, 0);     // Check if the student has failed any modules
            if (!award) {                                        // If any classes have been failed
                boolean success = mManager.resitAllowed(studentID);     // Check if student is allowed to withdraw or not
                if (!success) {                                           // Student doesn't have enough attempts on a failed class
                    if (mManager.issueDecision(studentID, "Withdraw")) {
                        JOptionPane.showMessageDialog(vSD, "'Withdraw' decision successfully awarded to student!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {            // Some other database issue occurs
                        JOptionPane.showMessageDialog(vSD, "Error issuing award to student.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {  // Still enough attempts on modules
                    JOptionPane.showMessageDialog(vSD, "Student is unable to 'Withdraw' as they still have module attempts.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {        // Student has passed all their modules
                JOptionPane.showMessageDialog(vSD, "Unable to issue 'Withdraw' to this student as they have passed all modules.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {    // If no check boxes are selected, an error is displayed
            JOptionPane.showMessageDialog(vSD, "Error, no decision boxes have been checked.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    boolean validateAccount(){      // Checks account is valid on manage accounts page
        int ID;
        int confirm;
        boolean success = false;

        try {
            ID = Integer.parseInt(vMA.getEnterID().getText());
            confirm = Integer.parseInt(vMA.getReenterID().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMA, "Invalid user IDs.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(ID!=confirm){
            JOptionPane.showMessageDialog(vMA, "User IDs don't match.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void activateAccount(){
        int ID;
        boolean success = false;

        if(validateAccount()) {
            ID = Integer.parseInt(vMA.getEnterID().getText());
            if (ID == mManager.getUserID()) {
                JOptionPane.showMessageDialog(vMA, "Cannot activate your own account.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (mStudent.getStudentByID(ID) != null) {
                success = mManager.activeStudent(ID, "1");
            } else if (mLecturer.getLecturerByID(ID) != null) {
                success = mManager.activeLecturer(ID, "1");
            } else if (mManager.getManagerByID(ID) != null) {
                success = mManager.activeManager(ID, "1");
            } else {
                JOptionPane.showMessageDialog(vMA, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (success) {
                JOptionPane.showMessageDialog(vMA, "User successfully activated.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vMA, "Account unable to be activated.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    private void deactivateAccount() {
        int ID;
        if(validateAccount()) {
            ID = Integer.parseInt(vMA.getEnterID().getText());
            boolean success = false;

            if (ID == mManager.getUserID()) {
                JOptionPane.showMessageDialog(vMA, "Cannot deactivate your own account.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (mStudent.getStudentByID(ID) != null) {
                success = mManager.activeStudent(ID, "0");
            } else if (mLecturer.getLecturerByID(ID) != null) {
                success = mManager.activeLecturer(ID, "0");
            } else if (mManager.getManagerByID(ID) != null) {
                success = mManager.activeManager(ID, "0");
            } else {
                JOptionPane.showMessageDialog(vMA, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (success) {
                JOptionPane.showMessageDialog(vMA, "User successfully deactivated.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vMA, "Account unable to be deactivated.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Delete a users account
    private void deleteAccount() {
        int ID;
        boolean success = false;

        if(validateAccount()) {
            ID = Integer.parseInt(vMA.getEnterID().getText());

            if (ID == mManager.getUserID()) {
                JOptionPane.showMessageDialog(vMA, "Cannot delete your own account.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (input == JOptionPane.OK_OPTION) {
                if (mStudent.getStudentByID(ID) != null) {
                    success = mStudent.deleteStudent(ID);
                } else if (mLecturer.getLecturerByID(ID) != null) {
                    success = mLecturer.deleteLecturer(ID);
                } else if (mManager.getManagerByID(ID) != null) {
                    success = mManager.deleteManager(ID);
                } else {
                    JOptionPane.showMessageDialog(vMA, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (success) {
                    JOptionPane.showMessageDialog(vMA, "User successfully deleted.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vMA, "Account unable to be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Manager chooses to reset a user's password in Manage Accounts GUI
    void resetPassword() {
        int ID;
        boolean success = false;

        if(validateAccount()) {
            ID = Integer.parseInt(vMA.getEnterID().getText());

            String password = JOptionPane.showInputDialog("Enter new password for user:");
            if(password == null || password.isEmpty()){
                return;
            }

            if (mStudent.getStudentByID(ID) != null){
                success = mManager.resetSPassword(ID, password);
            } else if (mLecturer.getLecturerByID(ID) != null){
                success = mManager.resetLPassword(ID, password);
            } else if(mManager.getManagerByID(ID) != null) {
                success = mManager.resetMPassword(ID, password);
            } else {
                JOptionPane.showMessageDialog(vMA, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (success){
                JOptionPane.showMessageDialog(vMA, "User's password successfully reset.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vMA, "Account's password unable to be reset.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void approveUser() {
        int ID;
        boolean success = false;

        if (validateAccount()){
            ID = Integer.parseInt(vMA.getEnterID().getText());

            if (mStudent.getStudentByID(ID) != null){
                success = mManager.approveStudent(ID, "1");
            } else if (mLecturer.getLecturerByID(ID) != null){
                success = mManager.approveLecturer(ID, "1");
            } else {
                JOptionPane.showMessageDialog(vMA, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (success){
                JOptionPane.showMessageDialog(vMA, "User successfully approved", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vMA, "Account unable to be approved.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void approveUsers(){
        boolean success1 = mManager.approveAllStudents("1");
        boolean success2 = mManager.approveAllLecturers("1");
        if (success1||success2){
            JOptionPane.showMessageDialog(vMA, "All users on workflow successfully approved.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vMA, "Error approving all users.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void unapproveUsers(){
        boolean success1 = mManager.approveAllStudents("0");
        boolean success2 = mManager.approveAllLecturers("0");
        if (success1||success2){
            JOptionPane.showMessageDialog(vMA, "All users on workflow successfully denied.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vMA, "Error denying all users.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void unapproveUser() {
        int ID;
        boolean success = false;

        if (validateAccount()){
            ID = Integer.parseInt(vMA.getEnterID().getText());

            if (mStudent.getStudentByID(ID) != null){
                success = mManager.approveStudent(ID, "0");
            } else if (mLecturer.getLecturerByID(ID) != null){
                success = mManager.approveLecturer(ID, "0");
            } else {
                JOptionPane.showMessageDialog(vMA, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (success){
                JOptionPane.showMessageDialog(vMA, "User password successfully unapproved", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vMA, "Account unable to be unapproved.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void displayModule(){
        int ID;

        try {
            ID = Integer.parseInt(vMAC.getViewModuleCode().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "Invalid module ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(mModule.getModule(ID) != null){
            vMAC.displayModule(ID);
        } else {
            JOptionPane.showMessageDialog(vMAC, "Module does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void displayCourse(){
        int ID;

        try {
            ID = Integer.parseInt(vMAC.getViewCourseCode().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vMAC, "Invalid course ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(mCourse.getCourse(ID) != null){
            vMAC.displayCourse(ID);
        } else {
            JOptionPane.showMessageDialog(vMAC, "Course does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void getCurrentManager(Manager currentManager) {
        mManager = currentManager;
    }
}

