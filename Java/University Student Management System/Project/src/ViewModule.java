package src;

import javax.swing.*;

public class ViewModule extends JFrame{
    private JPanel panelMain;
    private JLabel moduleTitle;
    private JLabel moduleDescription;
    private JPanel panelProgress;
    private JLabel progressLabel;
    private JLabel resultLabel;
    private JButton backButton1;
    private JPanel notesPanel;
    private JLabel lectureNotesLabel;
    private JPanel labPanel;
    private JLabel labLabel;
    private JScrollPane materials1;
    private JScrollPane materials2;

    public ViewModule() {
        setTitle("Module");         // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Execution stops if application window is closed
        add(panelMain);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JLabel getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(JLabel moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public JLabel getModuleDescription() { return moduleDescription;}

    public void setModuleDescription(JLabel moduleDescription) { this.moduleDescription = moduleDescription; }

    public JPanel getPanelProgress() {
        return panelProgress;
    }

    public void setPanelProgress(JPanel panelProgress) {
        this.panelProgress = panelProgress;
    }

    public JLabel getProgressLabel() { return progressLabel; }

    public void setProgressLabel(JLabel progressLabel) { this.progressLabel = progressLabel; }

    public JLabel getResultLabel() { return resultLabel; }

    public void setResultLabel(JLabel resultLabel) { this.resultLabel = resultLabel; }

    public JButton getBackButton1() {
        return backButton1;
    }

    public void setBackButton1(JButton backButton1) {
        this.backButton1 = backButton1;
    }

    public JPanel getNotesPanel() {
        return notesPanel;
    }

    public void setNotesPanel(JPanel notesPanel) {
        this.notesPanel = notesPanel;
    }

    public JLabel getLectureNotesLabel() { return lectureNotesLabel; }

    public void setLectureNotesLabel(JLabel lectureNotesLabel) { this.lectureNotesLabel = lectureNotesLabel; }

    public JPanel getLabPanel() {
        return labPanel;
    }

    public void setLabPanel(JPanel labPanel) {
        this.labPanel = labPanel;
    }

    public JLabel getLabLabel() { return labLabel; }

    public void setLabLabel(JLabel labLabel) { this.labLabel = labLabel; }

    public JScrollPane getMaterials1() {
        return materials1;
    }

    public void setMaterials1(JScrollPane materials1) {
        this.materials1 = materials1;
    }

    public JScrollPane getMaterials2() {
        return materials2;
    }

    public void setMaterials2(JScrollPane materials2) {
        this.materials2 = materials2;
    }
}
