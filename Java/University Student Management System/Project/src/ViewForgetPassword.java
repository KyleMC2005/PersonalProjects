package src;

import javax.swing.*;

public class ViewForgetPassword extends JFrame {
    private JPanel panelMain;
    private JPanel panelSub;
    private JTextField emailField;
    private JPasswordField newPassword;
    private JLabel email;
    private JLabel lock1;
    private JLabel panelTitle;
    private JPasswordField confirmPassword;
    private JButton submitButton;
    private JLabel lock2;
    private JButton backButton1;

    public ViewForgetPassword() {
        setTitle("Forget Password");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public JButton getBackButton1() {
        return backButton1;
    }

    public void setBackButton1(JButton backButton1) {
        this.backButton1 = backButton1;
    }

    public JLabel getLock2() {
        return lock2;
    }

    public void setLock2(JLabel lock2) {
        this.lock2 = lock2;
    }

    public JPasswordField getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(JPasswordField confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public JLabel getEmail() {
        return email;
    }

    public void setEmail(JLabel email) {
        this.email = email;
    }

    public JLabel getLock1() {
        return lock1;
    }

    public void setLock1(JLabel lock1) {
        this.lock1 = lock1;
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

    public JLabel getPanelTitle() {
        return panelTitle;
    }

    public void setPanelTitle(JLabel panelTitle) {
        this.panelTitle = panelTitle;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public JPasswordField getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(JPasswordField newPassword) {
        this.newPassword = newPassword;
    }


    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public void clearTxts() {
        emailField.setText("");
        newPassword.setText("");
        confirmPassword.setText("");
    }

}
