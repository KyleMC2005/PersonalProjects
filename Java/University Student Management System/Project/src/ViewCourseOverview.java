package src;
import javax.swing.*;

// Overview for a student's course including all their modules and their results

public class ViewCourseOverview extends JFrame {
    private JPanel panelMain;
    private JPanel panelResults;
    private JPanel panelModuleList;
    private JLabel courseTitle;
    private JLabel starIcon;
    private JLabel resultsLabel;
    private JLabel noResultsLabel;
    private JButton module1;
    private JButton module2;
    private JButton module3;
    private JButton module4;
    private JButton module5;
    private JButton module6;
    private JButton logOutButton;
    private JButton backButton1;
    private JLabel modulesLabel;

    public ViewCourseOverview() {
        setTitle("Course Overview");         // Window title
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

    public JPanel getPanelResults() {
        return panelResults;
    }

    public void setPanelResults(JPanel panelResults) {
        this.panelResults = panelResults;
    }

    public JPanel getPanelModuleList() {
        return panelModuleList;
    }

    public void setPanelModuleList(JPanel panelModuleList) {
        this.panelModuleList = panelModuleList;
    }

    public JLabel getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(JLabel courseTitle) {
        this.courseTitle = courseTitle;
    }

    public JLabel getStarIcon() {
        return starIcon;
    }

    public void setStarIcon(JLabel starIcon) {
        this.starIcon = starIcon;
    }

    public JLabel getResultsLabel() {
        return resultsLabel;
    }

    public void setResultsLabel(JLabel resultsLabel) {
        this.resultsLabel = resultsLabel;
    }

    public JLabel getNoResultsLabel() {
        return noResultsLabel;
    }

    public void setNoResultsLabel(JLabel noResultsLabel) {
        this.noResultsLabel = noResultsLabel;
    }

    public JButton getModule1() {
        return module1;
    }

    public void setModule1(JButton module1) {
        this.module1 = module1;
    }

    public JButton getModule2() {
        return module2;
    }

    public void setModule2(JButton module2) {
        this.module2 = module2;
    }

    public JButton getModule3() {
        return module3;
    }

    public void setModule3(JButton module3) {
        this.module3 = module3;
    }

    public JButton getModule5() {
        return module5;
    }

    public void setModule5(JButton module5) {
        this.module5 = module5;
    }

    public JButton getModule4() {
        return module4;
    }

    public void setModule4(JButton module4) {
        this.module4 = module4;
    }

    public JButton getModule6() {
        return module6;
    }

    public void setModule6(JButton module6) {
        this.module6 = module6;
    }

    public JButton getLogOutButton() { return logOutButton;}

    public void setLogOutButton(JButton logOutButton) { this.logOutButton = logOutButton;}


    public JButton getBackButton1() { return backButton1; }

    public void setBackButton1(JButton backButton1) { this.backButton1 = backButton1; }

    public JLabel getModulesLabel() { return modulesLabel; }

    public void setModulesLabel(JLabel modulesLabel) { this.modulesLabel = modulesLabel; }

}