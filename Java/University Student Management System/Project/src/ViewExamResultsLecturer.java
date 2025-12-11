package src;

import javax.swing.*;

//Exam Results Form, for the lecturer to submit a students results in a specific exam
//Includes StudentID, Module, Class and Grade
public class ViewExamResultsLecturer extends JFrame {
    private JButton submitGradeButton;
    private JTextField enterStudentID;
    private JTextField SelectModuleCombo;
    private JTextField EnterGradeText;
    private JPanel panelMain;
    private JLabel ExamResultsLabel;
    private JLabel StudentIDLabel;
    private JLabel ModuleSelectLabel;
    private JLabel ClassSelectLabel;
    private JLabel EnterGradeLabel;
    private JLabel BookIcon;
    private JButton homeButton;
    private JComboBox examOrLab;

    public ViewExamResultsLecturer() {
        setTitle("Exam Results");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public JButton getSubmitGradeButton() {
        return submitGradeButton;
    }

    public void setSubmitGradeButton(JButton submitGradeButton) {
        this.submitGradeButton = submitGradeButton;
    }

    public JTextField getEnterStudentID() {
        return enterStudentID;
    }

    public void setEnterStudentID(JTextField enterStudentID) {
        this.enterStudentID = enterStudentID;
    }

    public JTextField getSelectModuleCombo() {
        return SelectModuleCombo;
    }

    public void setSelectModuleCombo(JTextField selectModuleCombo) {
        this.SelectModuleCombo = selectModuleCombo;
    }

    public JTextField getEnterGradeText() {
        return EnterGradeText;
    }

    public void setEnterGradeText(JTextField enterGradeText) {
        this.EnterGradeText = enterGradeText;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JLabel getExamResultsLabel() {
        return ExamResultsLabel;
    }

    public void setExamResultsLabel(JLabel examResultsLabel) {
        this.ExamResultsLabel = examResultsLabel;
    }

    public JLabel getStudentIDLabel() {
        return StudentIDLabel;
    }

    public void setStudentIDLabel(JLabel studentIDLabel) {
        this.StudentIDLabel = studentIDLabel;
    }

    public JLabel getModuleSelectLabel() {
        return ModuleSelectLabel;
    }

    public void setModuleSelectLabel(JLabel moduleSelectLabel) {
        this.ModuleSelectLabel = moduleSelectLabel;
    }

    public JLabel getClassSelectLabel() {
        return ClassSelectLabel;
    }

    public void setClassSelectLabel(JLabel classSelectLabel) {
        this.ClassSelectLabel = classSelectLabel;
    }

    public JLabel getEnterGradeLabel() {
        return EnterGradeLabel;
    }

    public void setEnterGradeLabel(JLabel enterGradeLabel) {
        this.EnterGradeLabel = enterGradeLabel;
    }

    public JLabel getBookIcon() {
        return BookIcon;
    }

    public void setBookIcon(JLabel bookIcon) {
        this.BookIcon = bookIcon;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(JButton homeButton) {
        this.homeButton = homeButton;
    }

    public JComboBox getExamOrLab() {
        return examOrLab;
    }

    public void setExamOrLab(JComboBox examOrLab) {
        this.examOrLab = examOrLab;
    }

    public void clearFields() {   //make sure all text fields/combo boxes are empty
        EnterGradeText.setText("");
        enterStudentID.setText("");
        SelectModuleCombo.setText("");
        examOrLab.setSelectedIndex(0);
    }
}

