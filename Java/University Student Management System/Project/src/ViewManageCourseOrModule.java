package src;

import src.Users.Lecturer;
import src.Users.Module;
import src.Users.Course;
import javax.swing.*;
import java.util.ArrayList;

public class ViewManageCourseOrModule extends JFrame {
    private JTabbedPane courseModuleOptions;
    private JPanel panelMain;
    private JLabel titleLabel;
    private JTextField nameField1;
    private JTextField codeDrop;
    private JTextField creditField1;
    private JTextField onCourse;
    private JTextField onCourse2;
    private JButton updateButton1;
    private JTextField assignedLec;
    private JTextField creditField2;
    private JTextField nameField2;
    private JTextField codeField;
    private JTextField assignedLec2;
    private JTextField nameField3;
    private JTextField descField1;
    private JTextField codeDrop2;
    private JTextField semField;
    private JButton updateButton2;
    private JTextField nameField4;
    private JTextField descField2;
    private JTextField codeField2;
    private JTextField semField2;
    private JTextField studIdField;
    private JTextField studIdField2;
    private JTextField courseEnroll;
    private JButton enrollButton;
    private JButton addButton1;
    private JPanel panelHeader;
    private JButton homeButton;
    private JTextField modDescField2;
    private JTextField modDescField1;
    private JButton addCourse;
    private JTextField studIDField3;
    private JTextField studIDField4;
    private JTextField moduleEnroll;
    private JButton enrollButton2;
    private JButton unenrollButton;
    private JTextArea textArea1;
    private JTextField viewModuleCode;
    private JButton viewModuleButton;
    private JTextField viewCourseCode;
    private JButton viewCourseButton;
    private JTextArea textArea2;
    private JComboBox markingScheme;
    private JComboBox markingScheme2;
    private JComboBox courseTypeSelection;
    private JComboBox courseTypeSelection2;

    public ViewManageCourseOrModule() {
        setTitle("Course/Module Options");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);

        textArea1.setEditable(false);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        textArea2.setEditable(false);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
    }

    public void displayModule(int moduleID) {
        textArea1.selectAll();
        textArea1.replaceSelection("");
        Module module = new Module();
        module = module.getModule(moduleID);

        textArea1.append("Module ID: " + module.getModuleID() + "\nModule Name: " + module.getModuleName() + "\nCredits: " +
                module.getCredits() + "\nModule Info: " + module.getDescription() + "\n");

        ArrayList<Lecturer> lecturers = module.getLecturers();
        if (lecturers != null) {
            for (Lecturer lecturer : lecturers) {
                textArea1.append("Lecturer:" + lecturer.getForename() + " " + lecturer.getSurname() + "\n");
            }
        }
    }

    public void displayCourse(int courseID) {
        textArea2.selectAll();
        textArea2.replaceSelection("");
        Course course = new Course();
        course = course.getCourse(courseID);

        textArea2.append("Course ID: " + course.getCourseID() + "\nCourse Name: " + course.getCourseName() + "\nSemesters: " +
                course.getSemesters() + "\nCourse Info: " + course.getDescription() + "\n");
        ArrayList<Module> modules = course.getModules();
        if (modules != null) {
            for (Module module : modules) {
                textArea2.append("Module: " + module.getModuleName() + " ID: " + module.getModuleID() + "\n");
            }
        }
    }

    public JTextField getModDescField2() {
        return modDescField2;
    }

    public void setModDescField2(JTextField modDescField2) {
        this.modDescField2 = modDescField2;
    }

    public JTextField getModDescField1() {
        return modDescField1;
    }

    public void setModDescField1(JTextField modDescField1) {
        this.modDescField1 = modDescField1;
    }

    public JTabbedPane getCourseModuleOptions() {
        return courseModuleOptions;
    }

    public void setCourseModuleOptions(JTabbedPane courseModuleOptions) {
        this.courseModuleOptions = courseModuleOptions;
    }

    public JPanel getPanelHeader() {
        return panelHeader;
    }

    public void setPanelHeader(JPanel panelHeader) {
        this.panelHeader = panelHeader;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(JButton homeButton) {
        this.homeButton = homeButton;
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JTextField getNameField1() {
        return nameField1;
    }

    public void setNameField1(JTextField nameField1) {
        this.nameField1 = nameField1;
    }

    public JTextField getCodeDrop() {
        return codeDrop;
    }

    public void setCodeDrop(JTextField codeDrop) {
        this.codeDrop = codeDrop;
    }

    public JTextField getCreditField1() {
        return creditField1;
    }

    public void setCreditField1(JTextField creditField1) {
        this.creditField1 = creditField1;
    }

    public JTextField getOnCourse() {
        return onCourse;
    }

    public void setOnCourse(JTextField onCourse) {
        this.onCourse = onCourse;
    }

    public JButton getUpdateButton1() {
        return updateButton1;
    }

    public void setUpdateButton1(JButton updateButton1) {
        this.updateButton1 = updateButton1;
    }

    public JTextField getAssignedLec() {
        return assignedLec;
    }

    public void setAssignedLec(JTextField assignedLec) {
        this.assignedLec = assignedLec;
    }

    public JTextField getCreditField2() {
        return creditField2;
    }

    public void setCreditField2(JTextField creditField2) {
        this.creditField2 = creditField2;
    }

    public JTextField getNameField2() {
        return nameField2;
    }

    public void setNameField2(JTextField nameField2) {
        this.nameField2 = nameField2;
    }

    public JTextField getCodeField() {
        return codeField;
    }

    public void setCodeField(JTextField codeField) {
        this.codeField = codeField;
    }

    public JTextField getAssignedLec2() {
        return assignedLec2;
    }

    public void setAssignedLec2(JTextField assignedLec2) {
        this.assignedLec2 = assignedLec2;
    }

    public JTextField getNameField3() {
        return nameField3;
    }

    public void setNameField3(JTextField nameField3) {
        this.nameField3 = nameField3;
    }

    public JTextField getDescField1() {
        return descField1;
    }

    public void setDescField1(JTextField descField1) {
        this.descField1 = descField1;
    }

    public JTextField getCodeDrop2() {
        return codeDrop2;
    }

    public void setCodeDrop2(JTextField codeDrop2) {
        this.codeDrop2 = codeDrop2;
    }

    public JTextField getSemField() {
        return semField;
    }

    public void setSemField(JTextField semField) {
        this.semField = semField;
    }

    public JButton getUpdateButton2() {
        return updateButton2;
    }

    public void setUpdateButton2(JButton updateButton2) {
        this.updateButton2 = updateButton2;
    }

    public JTextField getNameField4() {
        return nameField4;
    }

    public void setNameField4(JTextField nameField4) {
        this.nameField4 = nameField4;
    }

    public JTextField getDescField2() {
        return descField2;
    }

    public void setDescField2(JTextField descField2) {
        this.descField2 = descField2;
    }

    public JTextField getCodeField2() {
        return codeField2;
    }

    public void setCodeField2(JTextField codeField2) {
        this.codeField2 = codeField2;
    }

    public JTextField getSemField2() {
        return semField2;
    }

    public void setSemField2(JTextField semField2) {
        this.semField2 = semField2;
    }

    public JTextField getStudIdField() {
        return studIdField;
    }

    public void setStudIdField(JTextField studIdField) {
        this.studIdField = studIdField;
    }

    public JTextField getStudIdField2() {
        return studIdField2;
    }

    public void setStudIdField2(JTextField studIdField2) {
        this.studIdField2 = studIdField2;
    }

    public JTextField getCourseEnroll() {
        return courseEnroll;
    }

    public void setCourseEnroll(JTextField courseEnroll) {
        this.courseEnroll = courseEnroll;
    }

    public JButton getEnrollButton() {
        return enrollButton;
    }

    public void setEnrollButton(JButton enrollButton) {
        this.enrollButton = enrollButton;
    }

    public JTextField getOnCourse2() {
        return onCourse2;
    }

    public void setOnCourse2(JTextField onCourse2) {
        this.onCourse2 = onCourse2;
    }

    public JButton getAddButton1() {
        return addButton1;
    }

    public void setAddButton1(JButton addButton1) {
        this.addButton1 = addButton1;
    }

    public JButton getAddCourse() {
        return addCourse;
    }

    public void setAddCourse(JButton addCourse) {
        this.addCourse = addCourse;
    }

    public JButton getEnrollButton2() {
        return enrollButton2;
    }

    public void setEnrollButton2(JButton enrollButton2) {
        this.enrollButton2 = enrollButton2;
    }

    public JTextField getModuleEnroll() {
        return moduleEnroll;
    }

    public void setModuleEnroll(JTextField moduleEnroll) {
        this.moduleEnroll = moduleEnroll;
    }

    public JTextField getStudIDField4() {
        return studIDField4;
    }

    public void setStudIDField4(JTextField studIDField4) {
        this.studIDField4 = studIDField4;
    }

    public JTextField getStudIDField3() {
        return studIDField3;
    }

    public void setStudIDField3(JTextField studIDField3) {
        this.studIDField3 = studIDField3;
    }

    public JButton getUnenrollButton() {
        return unenrollButton;
    }

    public void setUnenrollButton(JButton unenrollButton) {
        this.unenrollButton = unenrollButton;
    }

    public JTextField getViewModuleCode() {
        return viewModuleCode;
    }

    public void setViewModuleCode(JTextField viewModuleCode) {
        this.viewModuleCode = viewModuleCode;
    }

    public JButton getViewModuleButton() {
        return viewModuleButton;
    }

    public void setViewModuleButton(JButton viewModuleButton) {
        this.viewModuleButton = viewModuleButton;
    }

    public JTextField getViewCourseCode() {
        return viewCourseCode;
    }

    public void setViewCourseCode(JTextField viewCourseCode) {
        this.viewCourseCode = viewCourseCode;
    }

    public JButton getViewCourseButton() {
        return viewCourseButton;
    }

    public void setViewCourseButton(JButton viewCourseButton) {
        this.viewCourseButton = viewCourseButton;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JTextArea getTextArea2() {
        return textArea2;
    }

    public void setTextArea2(JTextArea textArea2) {
        this.textArea2 = textArea2;
    }

    public JComboBox getMarkingScheme() {
        return markingScheme;
    }

    public void setMarkingScheme(JComboBox markingScheme) {
        this.markingScheme = markingScheme;
    }

    public JComboBox getMarkingScheme2() {
        return markingScheme2;
    }

    public void setMarkingScheme2(JComboBox markingScheme2) {
        this.markingScheme2 = markingScheme2;
    }

    public JComboBox getCourseTypeSelection() {
        return courseTypeSelection;
    }

    public void setCourseTypeSelection(JComboBox courseTypeSelection) {
        this.courseTypeSelection = courseTypeSelection;
    }

    public JComboBox getCourseTypeSelection2() {
        return courseTypeSelection2;
    }

    public void setCourseTypeSelection2(JComboBox courseTypeSelection2) {
        this.courseTypeSelection2 = courseTypeSelection2;
    }

    public void clearTxts() {       // Makes sure the text fields have empty values
        nameField1.setText("");
        codeDrop.setText("");
        creditField1.setText("");
        creditField2.setText("");
        modDescField1.setText("");
        modDescField2.setText("");
        assignedLec.setText("");
        nameField2.setText("");
        codeField.setText("");
        assignedLec2.setText("");
        nameField3.setText("");
        descField1.setText("");
        codeDrop2.setText("");
        semField.setText("");
        studIdField.setText("");
        onCourse2.setText("");
        courseEnroll.setText("");
        onCourse.setText("");
        descField2.setText("");
        nameField4.setText("");
        semField2.setText("");
        codeField2.setText("");
        studIdField2.setText("");
        studIdField.setText("");
        studIDField3.setText("");
        studIDField4.setText("");
        moduleEnroll.setText("");
        courseTypeSelection.setSelectedIndex(0);
        courseTypeSelection2.setSelectedIndex(0);
    }

}
