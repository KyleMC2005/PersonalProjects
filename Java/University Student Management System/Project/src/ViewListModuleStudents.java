package src;

import src.Controllers.ControllerLecturer;
import src.DAO.ModuleDAO;
import src.Users.Lecturer;
import src.Users.Manager;
import src.Users.Student;
import src.Users.User;

import javax.swing.*;
import java.util.ArrayList;

public class ViewListModuleStudents extends JFrame{
    private JPanel panelHeader;
    private JLabel titleLabel;
    private JButton homeButton;
    private JScrollPane RequestsSection;
    private JTextField textField1;
    private JPanel panelMain;
    private JTextArea StudentInformation;


    public ViewListModuleStudents() {
        setTitle("Student List");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);

        StudentInformation.setEditable(false);
        StudentInformation.setLineWrap(true);
        StudentInformation.setWrapStyleWord(true);
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

    public void getAllStudents(int LecturerID) {
        // Clear the text area everytime the StudentInfo gets reinserted
        StudentInformation.selectAll();
        StudentInformation.replaceSelection("");

        // Find all unapproved users
        ModuleDAO moduleDAO = new ModuleDAO();

        ArrayList<Student> students = moduleDAO.getAllStudentsForALecturer(LecturerID);

        for(Student student : students){ // for each student
            StudentInformation.append("Name: " + student.getForename() + " " + student.getSurname() +"\n");
            StudentInformation.append("StudentID: " + student.getUserID()+"\n\n");
        }
    }

    //
    public JScrollPane getRequestsSection() {
        return RequestsSection;
    }

    public void setRequestsSection(JScrollPane requestsSection) {
        RequestsSection = requestsSection;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public void getStudentInformation() {
        ModuleDAO moduleDAO = new ModuleDAO();
    }

    public void setStudentInformation(ModuleDAO moduleDAO) {
        StudentInformation = this.StudentInformation;
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }
}
