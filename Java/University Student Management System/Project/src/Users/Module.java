package src.Users;


import src.DAO.ModuleDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.sql.Blob;
import java.util.ArrayList;

public class Module {
    // Initialise User Variables
    private int ModuleID;
    private String ModuleName;
    private String Description;
    private int Credits;
    private int MaxModuleAttempts;
    private String ModuleMarking;
    private ArrayList<Student> Students;
    private ArrayList<Lecturer> Lecturers;
    private ModuleDAO moduleDAO;
    private Blob LectureNote;
    private Blob LabNote;
    private int weekID;
    private String Content;
    private String MarkingScheme;


    public Module() {
        this.moduleDAO = new ModuleDAO();
    }

    public Module(int ModuleID, String ModuleName, String Description, int Credits, int MaxModuleAttempts, ArrayList<Student> Students, ArrayList<Lecturer> Lecturers, String MarkingScheme) {
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
        this.Description = Description;
        this.Credits = Credits;
        this.MaxModuleAttempts = MaxModuleAttempts;
        this.ModuleMarking = ModuleMarking;
        this.Students = Students;
        this.Lecturers = Lecturers;
        this.LectureNote = LectureNote;
        this.LabNote = LabNote;
        this.MarkingScheme = MarkingScheme;
    }

    // getters and setters
    public int getModuleID() {
        return ModuleID;
    }

    public void setModuleID(int moduleID) {
        ModuleID = moduleID;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }

    public int getMaxModuleAttempts() {
        return MaxModuleAttempts;
    }

    public void setMaxModuleAttempts(int maxModuleAttempts) {
        MaxModuleAttempts = maxModuleAttempts;
    }

    public String getModuleMarking() { return ModuleMarking; }

    public void setModuleMarking(String moduleMarking) { ModuleMarking = moduleMarking; }

    public ArrayList<Student> getStudents() {
        return Students;
    }

    public void setStudents(ArrayList<Student> students) {
        Students = students;
    }

    public ArrayList<Lecturer> getLecturers() {
        return Lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        Lecturers = lecturers;
    }

    public Blob getLectureNote() {
        return LectureNote;
    }

    public void setLectureNote(Blob lectureNote) {
        this.LectureNote = lectureNote;
    }

    public Blob getLabNote() {
        return LabNote;
    }

    public void setLabNote(Blob labNote) {
        this.LabNote = labNote;
    }

    public int getWeekID() {
        return weekID;
    }

    public void setWeekID(int weekID) {
        this.weekID = weekID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public ModuleDAO getModuleDAO() {
        return moduleDAO;
    }

    public void setModuleDAO(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }

    public String getMarkingScheme() {
        return MarkingScheme;
    }

    public void setMarkingScheme(String markingScheme) {
        MarkingScheme = markingScheme;
    }

    public ArrayList<Module> getModules() {
        return moduleDAO.getAllModules();
    }

    public Module getModule(int moduleID) {
        return moduleDAO.getModule(moduleID);
    }

    public boolean updateModule(int moduleID, String columnName, String value) {
        return moduleDAO.updateModule(moduleID, columnName, value);
    }

    public boolean deleteModule(int moduleID) {
        return moduleDAO.deleteModule(moduleID);
    }

    public boolean unlinkLecturer(int moduleID, int lecturerID) {
        return moduleDAO.unlinkLecturerToModule(moduleID, lecturerID);
    }

    public boolean unlinkCourse(int courseID, int moduleID) {
        return moduleDAO.unlinkModuleToCourse(courseID, moduleID);
    }

    public boolean unlinkStudentModule(int studentID, int moduleID) {
        return moduleDAO.unlinkStudentToModule(studentID, moduleID);
    }

    public ArrayList<JButton> createWeekButtons(int moduleID) {
        ArrayList<Integer> weeks;
        ArrayList<JButton> buttons = new ArrayList<>();

        weeks = moduleDAO.getAllWeeksForModule(moduleID);


        for (int i = 0; i < weeks.size(); i++) {
            String weekNo = weeks.get(i).toString();

            JButton button = new JButton(weekNo);
            button.setBackground(Color.decode("#CAE9FF"));
            button.setFont(new Font("Calibri", Font.PLAIN, 50));
            buttons.add(button);
        }

        return buttons;
    }

    public boolean getModuleLink(int studentID, int moduleID) {
        ArrayList<Module> array = moduleDAO.getStudentModules(studentID);

        if (array == null || array.isEmpty()){
            return false;
        }

        for (Module module : array) {
            if (module.getModuleID() == moduleID) {
                return true;
            }
        }
        return false;
    }

    // Check that a lecturer teaches a particular module
    public boolean checkTeachesModule(int lecturerID, int moduleID) {
        ArrayList<Lecturer> lecturers = moduleDAO.getLecturers(moduleID);
        if (lecturers == null || lecturers.isEmpty()) {
            return false;
        }
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getUserID() == lecturerID) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> studentResult(int moduleID, int studentID) {
        return moduleDAO.getResultStudentModule(moduleID, studentID);
    }

    public boolean updateDetails(int moduleID, String columnName, String description) {
        return moduleDAO.updateModule(moduleID, columnName, description);
    }
}
