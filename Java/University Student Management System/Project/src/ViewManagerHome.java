package src;

import src.Users.User;
import src.Users.Manager;

import javax.swing.*;
import java.util.ArrayList;
import src.Users.Manager;

public class ViewManagerHome extends JFrame {
    private JPanel panelMain;
    private JButton manageAccountsButton;
    private JButton issueDecisionButton;
    private JButton addBusinessRuleButton;
    private JButton HomeButton;
    private JButton courseModuleButton;
    private JScrollPane RequestsSection;
    private JButton logOutButton;
    private JTextArea signUpWorkFlow;
    private JButton approveButton;
    private JButton denyButton;


    public ViewManagerHome() {
        setTitle("Manager Home");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);

        signUpWorkFlow.setEditable(false);
        signUpWorkFlow.setLineWrap(true);
        signUpWorkFlow.setWrapStyleWord(true);

    }

    // Displays users whose accounts have not been approved
    public void getSignUpWorkFlow() {

        // Clear the text area everytime the workflow gets reinserted
        signUpWorkFlow.selectAll();
        signUpWorkFlow.replaceSelection("");

        Manager mManager = new Manager();
        // Find all unapproved users
        ArrayList<User> unapproved = mManager.findUnapproved();

        if(unapproved == null){
            signUpWorkFlow.append("There are currently no unapproved users.");
        } else {
            for(User user : unapproved){ // for each unapproved user
                signUpWorkFlow.append("Name: " + user.getForename() + " " + user.getSurname() + " ID:" + user.getUserID() +"\n");
            }
        }

    }


    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JButton getManageAccountsButton() {
        return manageAccountsButton;
    }

    public void setManageAccountsButton(JButton manageAccountsButton) {
        this.manageAccountsButton = manageAccountsButton;
    }

    public JButton getIssueDecisionButton() {
        return issueDecisionButton;
    }

    public void setIssueDecisionButton(JButton issueDecisionButton) {
        this.issueDecisionButton = issueDecisionButton;
    }

    public JButton getAddBusinessRuleButton() {
        return addBusinessRuleButton;
    }

    public void setAddBusinessRuleButton(JButton addBusinessRuleButton) {
        this.addBusinessRuleButton = addBusinessRuleButton;
    }

    public JButton getHomeButton() {
        return HomeButton;
    }

    public void setHomeButton(JButton homeButton) {
        HomeButton = homeButton;
    }

    public JButton getCourseModuleButton() {
        return courseModuleButton;
    }

    public void setCourseModuleButton(JButton courseModuleButton) {
        this.courseModuleButton = courseModuleButton;
    }

    public JScrollPane getRequestsSection() {
        return RequestsSection;
    }

    public void setRequestsSection(JScrollPane requestsSection) {
        RequestsSection = requestsSection;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public void setLogOutButton(JButton logOutButton) {
        this.logOutButton = logOutButton;
    }

    public void setSignUpWorkFlow(JTextArea signUpWorkFlow) {
        this.signUpWorkFlow = signUpWorkFlow;

    }

    public JButton getApproveButton() {
        return approveButton;
    }

    public void setApproveButton(JButton approveButton) {
        this.approveButton = approveButton;
    }

    public JButton getDenyButton() {
        return denyButton;
    }

    public void setDenyButton(JButton denyButton) {
        this.denyButton = denyButton;
    }
}
