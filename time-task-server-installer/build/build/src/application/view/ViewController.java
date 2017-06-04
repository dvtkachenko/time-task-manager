package application.view;

import application.controller.MainController;
import application.model.DBConnectionInfo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Optional;

/**
 * Created by diman on 19.05.2017.
 *
 * This class is a view controller for this application
 * It keeps and handles view JavaFX fxml files
 *
 */

public class ViewController {

    public final static int TOTAL_PANE = 8;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Pane firstStepPane;

    @FXML
    private Pane secondStepPane;

    @FXML
    private Pane thirdStepPane;

    @FXML
    private Pane fourthStepPane;

    @FXML
    private Pane fifthStepPane;

    @FXML
    private Pane sixthStepPane;

    @FXML
    private Pane seventhStepPane;

    @FXML
    private Pane installationProgressPane;

    @FXML
    private TextField textInputInstallationPath;

    @FXML
    private ToggleGroup radioButtonToggleGroup;

    @FXML
    private RadioButton radioButtonOracle;

    @FXML
    private RadioButton radioButtonPostgreSQL;

    @FXML
    private RadioButton radioButtonMySQL;

    @FXML
    private CheckBox allowAutoCreatingUserCheckBox;

    @FXML
    private TextField textInputConnectionIP;

    @FXML
    private TextField textInputPort;

    // text of label can change - depends on way of user creation
    @FXML
    private Label labelInputUsername;

    @FXML
    private TextField textInputUsername;

    @FXML
    private PasswordField textInputPassword;

    @FXML
    private TextArea textAreaCreateUserScript;

    @FXML
    private TextArea textAreaCreateDBScript;

    @FXML
    private TextArea textAreaCreateDBTablesScript;

    @FXML
    private Label labelRDBMS;

    @FXML
    private Label labelConnectionIP;

    @FXML
    private Label labelPort;

    @FXML
    private Label labelDBUsername;

    @FXML
    private CheckBox launchCheckBox;
    
    @FXML
    private Label labelProgressIndicator;
    
    @FXML
    private ProgressBar installationProgressBar;
    
    private MainController mainController;

    private PanesContainer panesContainer;

    public ViewController() {
    }

    public Pane getFirstStepPane() {
        return firstStepPane;
    }

    public Pane getSecondStepPane() {
        return secondStepPane;
    }

    public Pane getThirdStepPane() {
        return thirdStepPane;
    }

    public Pane getFourthStepPane() {
        return fourthStepPane;
    }

    public Pane getFifthStepPane() {
        return fifthStepPane;
    }

    public Pane getSixthStepPane() {
        return sixthStepPane;
    }

    public Pane getSeventhStepPane() {
        return seventhStepPane;
    }

    public Pane getInstallationProgressPane() {
        return installationProgressPane;
    }

    public TextField getTextInputInstallationPath() {
        return textInputInstallationPath;
    }

    public RadioButton getRadioButtonOracle() {
        return radioButtonOracle;
    }

    public RadioButton getRadioButtonPostgreSQL() {
        return radioButtonPostgreSQL;
    }

    public RadioButton getRadioButtonMySQL() {
        return radioButtonMySQL;
    }

    public TextField getTextInputConnectionIP() {
        return textInputConnectionIP;
    }

    public TextField getTextInputPort() {
        return textInputPort;
    }

    public Label getLabelInputUsername() {
        return labelInputUsername;
    }

    public TextField getTextInputUsername() {
        return textInputUsername;
    }

    public TextField getTextInputPassword() {
        return textInputPassword;
    }

    public TextArea getTextAreaCreateUserScript() {
        return textAreaCreateUserScript;
    }

    public TextArea getTextAreaCreateDBScript() {
        return textAreaCreateDBScript;
    }

    public TextArea getTextAreaCreateDBTablesScript() {
        return textAreaCreateDBTablesScript;
    }

    public CheckBox getLaunchCheckBox() {
        return launchCheckBox;
    }
    
    public Label getLabelProgressIndicator() {
        return labelProgressIndicator;
    }

    public CheckBox getAllowAutoCreatingUserCheckBox() {
        return allowAutoCreatingUserCheckBox;
    }

    public void setInstallationPath(String installationPath) {
        textInputInstallationPath.setText(installationPath);
    }

    public void setDefaultRDBMS(DBConnectionInfo.RDBMS defaultRDBMS) {

        switch (defaultRDBMS) {
            case MySQL: radioButtonMySQL.setSelected(true); break;
            case PostgreSQL: radioButtonPostgreSQL.setSelected(true); break;
            case Oracle: radioButtonOracle.setSelected(true); break;
        }
    }

    public void setAllowAutoCreatingUserCheckBox(boolean allowAutoCreatingUser) {
        allowAutoCreatingUserCheckBox.setSelected(allowAutoCreatingUser);
    }

    public void setTextInputConnectionIP(String inputConnectionIP) {
        textInputConnectionIP.setText(inputConnectionIP);
    }

    public void setTextInputPort(String inputPort) {
        textInputPort.setText(inputPort);
    }

    public void setLabelInputUsername(String titleUsername) {
        labelInputUsername.setText(titleUsername);
    }

    public void setTextInputUsername(String username) {
        textInputUsername.setText(username);
    }

    public void setTextInputPassword(String password) {
        textInputPassword.setText(password);
    }

    public void setTextAreaCreateUserScript(String createUserScript) {
        textAreaCreateUserScript.setText(createUserScript);
    }

    public void setTextAreaCreateDBScript(String createDBScript) {
        textAreaCreateDBScript.setText(createDBScript);
    }

    public void setTextAreaCreateDBTablesScript(String createDBTablesScript) {
        textAreaCreateDBTablesScript.setText(createDBTablesScript);
    }

    public void setLabelRDBMS(String selectedRDBMS) {
        labelRDBMS.setText(selectedRDBMS);
    }

    public void setLabelConnectionIP(String connectionIP) {
        labelConnectionIP.setText(connectionIP);
    }

    public void setLabelPort(String port) {
        labelPort.setText(port);
    }

    public void setLabelDBUsername(String dbUsername) {
        labelDBUsername.setText(dbUsername);
    }

    public void setLaunchCheckBox(boolean launch) {
        launchCheckBox.setSelected(launch);
    }

    public void setActivePage(int activePage) {
        panesContainer.setActivePane(activePage);
    }

    public int getActivePage() {
        return panesContainer.getIndexActivePane();
    }

    public void setNextButtonText(String text) {
        nextButton.setText(text);
    }

    public void setCancelButtonMode(boolean buttonMode) {
        cancelButton.setDisable(!buttonMode);
        cancelButton.setVisible(buttonMode);
    }

    public void setBackButtonMode(boolean buttonMode) {
        backButton.setDisable(!buttonMode);
        backButton.setVisible(buttonMode);
    }

    public void setBackButtonDisable(boolean buttonMode) {
        backButton.setDisable(buttonMode);
    }

    public void setLabelProgressIndicator(String text) {
    	labelProgressIndicator.setText(text);
    }
    
    public void startInstallationProgressBar() {
    	installationProgressBar.setProgress(0);
    }
    
    @FXML
    private void initialize() {
        panesContainer = new PanesContainer();
        panesContainer.initPanes(this);

        backButton.setDisable(true);
        backButton.setVisible(false);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handlePressNextButton() {

        mainController.doNextStep();

    }

    @FXML
    private void handlePressBackButton() {

        mainController.doBackStep();

    }

    @FXML
    private void handleCancelBackButton() {
        if (showConfirmationDialog("     Are you sure you want to exit ?")) {
            mainController.exitFromInstaller();
        }
    }

    @FXML
    private void handleOpenFileDialogButton() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        File directory = directoryChooser.showDialog(mainController.getMainStage());

        if (directory != null) {
            textInputInstallationPath.setText(directory.getPath());
        }
//        modelUpdater.updateIIInstallationPath(textInputInstallationPath.getText());
    }

    public boolean showConfirmationDialog(String dialogMessage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(mainController.getMainStage());
        alert.setTitle(" -- Confirmation Dialog --");
        alert.setHeaderText(dialogMessage);
        alert.initStyle(StageStyle.UTILITY);
//        alert.setContentText(dialogMessage);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void showWarningDialog(String warningMessage) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainController.getMainStage());
        alert.setTitle(" -- Warning Dialog -- ");
        alert.setHeaderText(warningMessage);
        alert.initStyle(StageStyle.UTILITY);
//        alert.setContentText("Careful with the next step!");

        alert.showAndWait();

    }
}