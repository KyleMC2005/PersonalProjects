package src;

import javax.swing.*;

public class ViewLecturerHome extends JFrame {
    private JButton logOutButton;
    private JButton issueExamResultsButton;
    private JButton viewStudentsButton;
    private JButton updateModuleButton;
    private JButton updateModuleDetailsButton;
    private JButton homeButton;
    private JLabel LecturerHome;
    private JPanel panelMain;
    private JLabel BookIcon;
    private JLabel StarIcon;
    private JLabel CapIcon;


    public ViewLecturerHome() {
        setTitle("Lecturer Home");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public void setLogOutButton(JButton logOutButton) {
        this.logOutButton = logOutButton;
    }

    public JButton getIssueExamResultsButton() {
        return issueExamResultsButton;
    }

    public void setIssueExamResultsButton(JButton issueExamResultsButton) {
        this.issueExamResultsButton = issueExamResultsButton;
    }

    public JButton getViewStudentsButton() {
        return viewStudentsButton;
    }

    public void setViewStudentsButton(JButton viewStudentsButton) {
        this.viewStudentsButton = viewStudentsButton;
    }

    public JButton getUpdateModuleButton() {
        return updateModuleButton;
    }

    public void setUpdateModuleButton(JButton updateModuleButton) {
        this.updateModuleButton = updateModuleButton;
    }

    public JButton getUpdateModuleDetailsButton() { return updateModuleDetailsButton; }

    public void setUpdateModuleDetailsButton(JButton updateModuleDescriptionButton) { this.updateModuleDetailsButton = updateModuleDescriptionButton; }

    public JButton getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(JButton homeButton) {
        this.homeButton = homeButton;
    }

    public JLabel getLecturerHome() {
        return LecturerHome;
    }

    public void setLecturerHome(JLabel lecturerHome) {
        LecturerHome = lecturerHome;
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JLabel getBookIcon() {
        return BookIcon;
    }

    public void setBookIcon(JLabel bookIcon) {
        BookIcon = bookIcon;
    }

    public JLabel getStarIcon() {
        return StarIcon;
    }

    public void setStarIcon(JLabel starIcon) {
        StarIcon = starIcon;
    }

    public JLabel getCapIcon() {
        return CapIcon;
    }

    public void setCapIcon(JLabel capIcon) {
        CapIcon = capIcon;
    }
}
