package src;
import javax.swing.*;

// Initial user log-in form. User can sign in, choose "forgot password" or create a new account from here.

public class ViewLogin extends JFrame {
    private JPanel panelMain;
    private JPanel panelSub;
    private JLabel person;
    private JLabel lock;
    private JLabel role;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JButton createAccButton;
    private JButton forgotButton;
    private JRadioButton studentRadioButton;
    private JRadioButton lecturerRadioButton;
    private JRadioButton managerRadioButton;


    public ViewLogin() {
        setTitle("Login");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);
    }

    // getters and setters


    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JPanel getPanelSub() {
        return panelSub;
    }

    public void setPanelSub(JPanel panelSub) {
        this.panelSub = panelSub;
    }

    public JLabel getPerson() {
        return person;
    }

    public void setPerson(JLabel person) {
        this.person = person;
    }

    public JTextField getIdField() {
        return idField;
    }

    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    public JLabel getLock() {
        return lock;
    }

    public void setLock(JLabel lock) {
        this.lock = lock;
    }

    public JLabel getRole() { return role; }

    public void setRole(JLabel role) { this.role = role; }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getSignInButton() {
        return signInButton;
    }

    public void setSignInButton(JButton signInButton) {
        this.signInButton = signInButton;
    }

    public JButton getCreateAccButton() {
        return createAccButton;
    }

    public void setCreateAccButton(JButton createAccButton) {
        this.createAccButton = createAccButton;
    }

    public JButton getForgotButton() {
        return forgotButton;
    }

    public void setForgotButton(JButton forgotButton) {
        this.forgotButton = forgotButton;
    }

    public JRadioButton getStudentRadioButton() { return studentRadioButton; }

    public void setStudentRadioButton(JRadioButton studentRadioButton) { this.studentRadioButton = studentRadioButton; }

    public JRadioButton getLecturerRadioButton() { return lecturerRadioButton;
    }

    public void setLecturerRadioButton(JRadioButton lecturerRadioButton) { this.lecturerRadioButton = lecturerRadioButton; }

    public JRadioButton getManagerRadioButton() { return managerRadioButton; }

    public void setManagerRadioButton(JRadioButton managerRadioButton) { this.managerRadioButton = managerRadioButton; }

    public void clearTxts() {       // Makes sure the text fields have empty values
        idField.setText("");
        passwordField.setText("");
    }

}
