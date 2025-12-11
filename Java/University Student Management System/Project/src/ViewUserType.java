package src;
import javax.swing.*;

// User chooses whether they are a student or lecturer before properly signing up so the correct sign-up page is displayed, and to determine their user ID

public class ViewUserType extends JFrame {
    private JPanel panelMain;
    private JButton backButton1;
    private JButton sButton;
    private JButton lButton;

    public ViewUserType() {
        setTitle("New User");         // Window title
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

    public JButton getBackButton1() {
        return backButton1;
    }

    public void setBackButton1(JButton backButton1) {
        this.backButton1 = backButton1;
    }

    public JButton getsButton() {
        return sButton;
    }

    public void setsButton(JButton sButton) {
        this.sButton = sButton;
    }

    public JButton getlButton() {
        return lButton;
    }

    public void setlButton(JButton lButton) {
        this.lButton = lButton;
    }

}
