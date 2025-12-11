package src;
import javax.swing.*;

// For the manager user; allows them to manage users' accounts based on their ID

public class ViewManageAccounts extends JFrame {
    private JPanel panelMain;
    private JPanel panelHeader;
    private JPanel panelBody;
    private JPanel panelIDs;
    private JPanel panelAccountSettings;
    private JLabel titleLabel;
    private JButton homeButton;
    private JTextField enterID;
    private JTextField reenterID;
    private JButton submitButton;
    private JButton activateAccountButton;
    private JButton deactivateAccountButton;
    private JButton resetPasswordButton;
    private JButton approveButton;
    private JButton unapproveAccount;
    private JButton deleteAccount;


    public ViewManageAccounts() {
        setTitle("Manage Accounts");         // Window title
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

    public JPanel getPanelHeader() {
        return panelHeader;
    }

    public void setPanelHeader(JPanel panelHeader) {
        this.panelHeader = panelHeader;
    }

    public JPanel getPanelBody() {
        return panelBody;
    }

    public void setPanelBody(JPanel panelBody) {
        this.panelBody = panelBody;
    }

    public JPanel getPanelIDs() {
        return panelIDs;
    }

    public void setPanelIDs(JPanel panelIDs) {
        this.panelIDs = panelIDs;
    }

    public JPanel getPanelAccountSettings() {
        return panelAccountSettings;
    }

    public void setPanelAccountSettings(JPanel panelAccountSettings) {
        this.panelAccountSettings = panelAccountSettings;
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

    public JTextField getEnterID() {
        return enterID;
    }

    public void setEnterID(JTextField enterID) {
        this.enterID = enterID;
    }

    public JTextField getReenterID() {
        return reenterID;
    }

    public void setReenterID(JTextField reenterID) {
        this.reenterID = reenterID;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JButton getActivateAccountButton() {
        return activateAccountButton;
    }

    public void setActivateAccountButton(JButton activateAccountButton) {
        this.activateAccountButton = activateAccountButton;
    }

    public JButton getDeactivateAccountButton() {
        return deactivateAccountButton;
    }

    public void setDeactivateAccountButton(JButton deactivateAccountButton) {
        this.deactivateAccountButton = deactivateAccountButton;
    }

    public JButton getResetPasswordButton() {
        return resetPasswordButton;
    }

    public void setResetPasswordButton(JButton resetPasswordButton) {
        this.resetPasswordButton = resetPasswordButton;
    }

    public JButton getApproveButton() {
        return approveButton;
    }

    public void setApproveButton(JButton approveButton) {
        this.approveButton = approveButton;
    }

    public JButton getUnapproveAccount() {
        return unapproveAccount;
    }

    public void setUnapproveAccount(JButton unapproveAccount) {
        this.unapproveAccount = unapproveAccount;
    }

    public JButton getDeleteAccount() {
        return deleteAccount;
    }

    public void setDeleteAccount(JButton deleteAccount) {
        this.deleteAccount = deleteAccount;
    }
}
