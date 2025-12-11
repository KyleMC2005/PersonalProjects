package src;

import javax.swing.*;

public class ViewLecturerModuleEdit extends JFrame {
    private JPanel panelMain;
    private JTextField idTextField;
    private JTextField titleTextField;
    private JTextField descTextField;
    private JButton submitButton;
    private JButton homeButton;
    private JPanel panelBody;
    private JLabel newTitleLabel;
    private JLabel moduleIDLabel;
    private JLabel newDescriptionLabel;
    private JLabel pageTitleLabel;

    public JPanel getPanelMain() { return panelMain; }

    public void setPanelMain(JPanel panelMain) { this.panelMain = panelMain; }

    public JTextField getIdTextField() { return idTextField; }

    public void setIdTextField(JTextField idTextField) { this.idTextField = idTextField; }

    public JTextField getTitleTextField() { return titleTextField; }

    public void setTitleTextField(JTextField titleTextField) { this.titleTextField = titleTextField; }

    public JTextField getDescTextField() { return descTextField; }

    public void setDescTextField(JTextField descTextField) { this.descTextField = descTextField; }

    public JButton getSubmitButton() {return submitButton; }

    public void setSubmitButton(JButton submitButton) { this.submitButton = submitButton; }

    public JButton getHomeButton() {return homeButton; }

    public void setHomeButton(JButton homeButton) { this.homeButton = homeButton; }

    public JPanel getPanelBody() { return panelBody; }

    public void setPanelBody(JPanel panelBody) { this.panelBody = panelBody; }

    public JLabel getNewTitleLabel() { return newTitleLabel; }

    public void setNewTitleLabel(JLabel newTitleLabel) { this.newTitleLabel = newTitleLabel; }

    public JLabel getModuleIDLabel() { return moduleIDLabel; }

    public void setModuleIDLabel(JLabel moduleIDLabel) { this.moduleIDLabel = moduleIDLabel; }

    public JLabel getNewDescriptionLabel() { return newDescriptionLabel; }

    public void setNewDescriptionLabel(JLabel newDescriptionLabel) { this.newDescriptionLabel = newDescriptionLabel; }

    public JLabel getPageTitleLabel() { return pageTitleLabel; }

    public void setPageTitleLabel(JLabel pageTitleLabel) { this.pageTitleLabel = pageTitleLabel; }

    public void clearTxts() {
        idTextField.setText("");
        titleTextField.setText("");
        descTextField.setText("");
    }
}
