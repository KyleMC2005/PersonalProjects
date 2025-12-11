package src;

import javax.swing.*;

public class ViewStudentDecisions extends JFrame {
    private JPanel panelMain;
    private JPanel panelHeader;
    private JLabel titleLabel;
    private JButton homeButton;
    private JTextField textField1;
    private JCheckBox awardCheckBox;
    private JCheckBox withdrawCheckBox;
    private JButton issueDecisionButton;
    private JCheckBox resitCheckBox;
    private JPanel panelSub;

    public ViewStudentDecisions() {
        setTitle("Manager Issue Decisions");         // Window title
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

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JCheckBox getAwardCheckBox() {
        return awardCheckBox;
    }

    public void setAwardCheckBox(JCheckBox awardCheckBox) {
        this.awardCheckBox = awardCheckBox;
    }

    public JCheckBox getWithdrawCheckBox() {
        return withdrawCheckBox;
    }

    public void setWithdrawCheckBox(JCheckBox withdrawCheckBox) {
        this.withdrawCheckBox = withdrawCheckBox;
    }

    public JButton getIssueDecisionButton() {
        return issueDecisionButton;
    }

    public void setIssueDecisionButton(JButton issueDecisionButton) {
        this.issueDecisionButton = issueDecisionButton;
    }

    public JCheckBox getResitCheckBox() {
        return resitCheckBox;
    }

    public void setResitCheckBox(JCheckBox resitCheckBox) {
        this.resitCheckBox = resitCheckBox;
    }

    public JPanel getPanelSub() {
        return panelSub;
    }

    public void setPanelSub(JPanel panelSub) {
        this.panelSub = panelSub;
    }
}
