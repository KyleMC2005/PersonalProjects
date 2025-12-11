package src;

import javax.swing.*;

public class ViewBusinessRule extends JFrame {
    private JPanel panelMain;
    private JPanel panelHeader;
    private JLabel titleLabel;
    private JButton homeButton;
    private JTabbedPane courseModuleOptions;
    private JSpinner spinner1;
    private JButton updateButton1;
    private JButton updateButton2;
    private JSpinner spinner2;
    private JTextField moduleCode;
    private JTextField courseCode;
    private JTextField opCourseCode;

    public ViewBusinessRule() {
        setTitle("Business Rules");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JPanel getPanelHeader() {
        return panelHeader;
    }

    public void setPanelHeader(JPanel panelHeader) {
        this.panelHeader = panelHeader;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(JButton homeButton) {
        this.homeButton = homeButton;
    }

    public JTabbedPane getCourseModuleOptions() {
        return courseModuleOptions;
    }

    public void setCourseModuleOptions(JTabbedPane courseModuleOptions) {
        this.courseModuleOptions = courseModuleOptions;
    }

    public JSpinner getSpinner1() {
        return spinner1;
    }

    public void setSpinner1(JSpinner spinner1) {
        this.spinner1 = spinner1;
    }

    public JButton getUpdateButton1() {
        return updateButton1;
    }

    public void setUpdateButton1(JButton updateButton1) {
        this.updateButton1 = updateButton1;
    }

    public JButton getUpdateButton2() {
        return updateButton2;
    }

    public void setUpdateButton2(JButton updateButton2) {
        this.updateButton2 = updateButton2;
    }

    public JSpinner getSpinner2() {
        return spinner2;
    }

    public void setSpinner2(JSpinner spinner2) {
        this.spinner2 = spinner2;
    }

    public JTextField getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(JTextField moduleCode) {
        this.moduleCode = moduleCode;
    }

    public JTextField getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(JTextField courseCode) {
        this.courseCode = courseCode;
    }

    public JTextField getOpCourseCode() {
        return opCourseCode;
    }

    public void setOpCourseCode(JTextField opCourseCode) {
        this.opCourseCode = opCourseCode;
    }

    public void clearTxts(){
        moduleCode.setText("");
        opCourseCode.setText("");
        courseCode.setText("");
        spinner2.setValue(1);
        spinner1.setValue(1);
    }
}
