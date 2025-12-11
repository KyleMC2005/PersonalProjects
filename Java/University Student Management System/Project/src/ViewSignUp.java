package src;

import javax.swing.*;

// Student sign up form, required details including first name, surname, gender, email and DOB.

public class ViewSignUp extends JFrame {

    private JPanel panelMain;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JComboBox gender;
    private JComboBox qual;
    private JTextField fnField;
    private JTextField snField;
    private JButton createAccButton;
    private JTextField emailField;
    private JButton backButton2;
    private JLabel accTitle;
    private JLabel iconImage;
    private JLabel qualText;
    private JSpinner daySpin;
    private JSpinner monthSpin;
    private JSpinner yearSpin;
    private JComboBox studentType;
    private JLabel studentTypeLabel;

    public JComboBox getStudentType() {
        return studentType;
    }

    public void setStudentType(JComboBox studentType) {
        this.studentType = studentType;
    }

    private String role = "";  // for the create account button

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }


    public void setIconImage(JLabel iconImage) {
        this.iconImage = iconImage;
    }

    public JSpinner getDaySpin() {
        return daySpin;
    }

    public void setDaySpin(JSpinner daySpin) {
        this.daySpin = daySpin;
    }

    public JSpinner getMonthSpin() {
        return monthSpin;
    }

    public void setMonthSpin(JSpinner monthSpin) {
        this.monthSpin = monthSpin;
    }

    public JSpinner getYearSpin() {
        return yearSpin;
    }

    public void setYearSpin(JSpinner yearSpin) {
        this.yearSpin = yearSpin;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JLabel getQualText() {
        return qualText;
    }

    public void setQualText(JLabel qualText) {
        this.qualText = qualText;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public void setPasswordField2(JPasswordField passwordField2) {
        this.passwordField2 = passwordField2;
    }

    public JComboBox getGender() {
        return gender;
    }

    public void setGender(JComboBox gender) {
        this.gender = gender;
    }

    public JComboBox getQual() {
        return qual;
    }

    public void setQual(JComboBox qual) {
        this.qual = qual;
    }

    public JTextField getFnField() {
        return fnField;
    }

    public void setFnField(JTextField fnField) {
        this.fnField = fnField;
    }

    public JTextField getSnField() {
        return snField;
    }

    public void setSnField(JTextField snField) {
        this.snField = snField;
    }

    public JButton getCreateAccButton() {
        return createAccButton;
    }

    public void setCreateAccButton(JButton createAccButton) {
        this.createAccButton = createAccButton;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public JButton getBackButton2() {
        return backButton2;
    }

    public void setBackButton2(JButton backButton2) {
        this.backButton2 = backButton2;
    }

    public JLabel getAccTitle() {
        return accTitle;
    }

    public void setAccTitle(JLabel accTitle) {
        this.accTitle = accTitle;
    }

    public JLabel getCap() {
        return iconImage;
    }

    public void setCap(JLabel cap) {
        this.iconImage = cap;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public void setStudentSignUp(){     // set the sign up page to be student sign up view
        qualText.setVisible(false);
        qual.setVisible(false);
        iconImage.setIcon(new ImageIcon(getClass().getResource("/imageIcons/studentCap.png")));
        studentType.setVisible(true);
        studentTypeLabel.setVisible(true);
        role = "Student";

    }

    public void setLecturerSignUp() {   // set the sign up page to be lecturer sign up view
        qualText.setVisible(true);
        qual.setVisible(true);
        iconImage.setIcon(new ImageIcon(getClass().getResource("/imageIcons/book.png")));
        studentType.setVisible(false);
        studentTypeLabel.setVisible(false);
        role = "Lecturer";
    }

    // Sets logical limits for the spinners (date is between 1-31, month between 1-12, year between 1900-2025
    public void setSpinners(){
        SpinnerModel smDay = new SpinnerNumberModel(1, 1, 31, 1); //default value, min value, max value
        SpinnerModel smMonth = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerModel smYear = new SpinnerNumberModel(1999, 1900, 2025, 1);
        daySpin.setModel(smDay);
        monthSpin.setModel(smMonth);
        yearSpin.setModel(smYear);

    }

    public JLabel getIconImagee() {
        return iconImage;
    }

    public JLabel getStudentTypeLabel() {
        return studentTypeLabel;
    }

    public void setStudentTypeLabel(JLabel studentTypeLabel) {
        this.studentTypeLabel = studentTypeLabel;
    }

    public void clearTxts() {       // Makes sure the text fields have empty values
        fnField.setText("");
        snField.setText("");
        emailField.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
        daySpin.setValue(1);
        monthSpin.setValue(1);
        yearSpin.setValue(1);
        gender.setSelectedIndex(0);
        qual.setSelectedIndex(0);
        studentType.setSelectedIndex(0);
    }

    public ViewSignUp() {
        setTitle("Student Sign Up");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);
        setSpinners();
    }

}
