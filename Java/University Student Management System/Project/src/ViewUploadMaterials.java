package src;

import src.DAO.ModuleDAO;

import javax.swing.*;
import java.io.File;

public class ViewUploadMaterials extends JFrame{
    private JPanel panelHeader;
    private JLabel titleLabel;
    private JButton homeButton;
    private JTextField moduleField;
    private JButton updateButton;
    private JTextField weekField;
    private JTextField lecNoteField;
    private JTextField labNoteField;
    private JPanel panelMain;
    private JButton createMaterialsButton;
    private JTextField ContentField;
    private JButton uploadLabPdfButton;
    private JButton uploadLecturePdfButton;

    public ViewUploadMaterials() {
        setTitle("Upload Materials");         // Window title
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

    public JTextField getModuleField() {
        return moduleField;
    }

    public void setModuleField(JTextField moduleField) {
        this.moduleField = moduleField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public JButton uploadLabPdfButton() {
        return uploadLabPdfButton;
    }

    public void setUploadLabPdfButton(JButton uploadLabPdfButton) {
        this.uploadLabPdfButton = uploadLabPdfButton;
    }

    public JButton uploadLecturePdfButton() {
        return uploadLecturePdfButton;
    }

    public void setUploadLecturePdfButton(JButton uploadLecturePdfButton) {
        this.uploadLecturePdfButton = uploadLecturePdfButton;
    }

    // Method for Updating Materials
    public boolean getUpdateMaterials(int weekID, int moduleID, String content, File labNote, File lectureNote) {
        // run ModuleDAO method for updating materials
        ModuleDAO moduleDAO = new ModuleDAO();
        return moduleDAO.UpdateMaterialsForModule(weekID, moduleID, content, labNote, lectureNote);
    }

    public JButton getCreateMaterialsButton() {
        return createMaterialsButton;
    }

    public void setCreateMaterialsButton(JButton createMaterialsButton) {
        this.createMaterialsButton = createMaterialsButton;
    }

    // Method for Making new Materials
    public boolean getCreateMaterials(int weekID, int moduleID, String content, File labNote, File lectureNote) {
        // run ModuleDAO method for creating new materials
        ModuleDAO moduleDAO = new ModuleDAO();
        return moduleDAO.UpdateMaterialsForModule(weekID, moduleID, content, labNote, lectureNote);
    }

    public JTextField getWeekField() {
        return weekField;
    }

    public void setWeekField(JTextField weekField) {
        this.weekField = weekField;
    }

    public JTextField getLecNoteField() {
        return lecNoteField;
    }

    public void setLecNoteField(JTextField lecNoteField) {
        this.lecNoteField = lecNoteField;
    }

    public JTextField getLabNoteField() {
        return labNoteField;
    }

    public void setLabNoteField(JTextField labNoteField) {
        this.labNoteField = labNoteField;
    }

    public JTextField getContentField() {
        return ContentField;
    }

    public void setContentField(JTextField ContentField) {
        this.ContentField = ContentField;
    }
}
