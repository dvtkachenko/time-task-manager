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

    public final static int TOTAL_PANE = 7;

    // we have to know when text fields were changed by user
    // and we need to update our information in InstallationInfo
    private boolean userScriptChanged = false;
    private boolean databaseScriptChanged = false;
    private boolean tablesScriptChanged = false;
    private boolean installationPathChanged = false;

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

    private MainController mainController;
//    private ModelViewDataUpdater modelUpdater;

    public PanesContainer panesContainer;

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

//    // needs changes
//    public void setRadioButtonOracle(RadioButton radioButtonOracle) {
//        this.radioButtonOracle = radioButtonOracle;
//    }
//
//    // needs changes
//    public void setRadioButtonPostgreSQL(RadioButton radioButtonPostgreSQL) {
//        this.radioButtonPostgreSQL = radioButtonPostgreSQL;
//    }
//
//    // needs changes
//    public void setRadioButtonMySQL(RadioButton radioButtonMySQL) {
//        this.radioButtonMySQL = radioButtonMySQL;
//    }

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

//    public void setModelUpdater(ModelViewDataUpdater modelUpdater) {
//        this.modelUpdater = modelUpdater;
//    }

    @FXML
    private void handlePressNextButton() {

        int activePane = panesContainer.getIndexActivePane();

        // update model data information
//        modelUpdater.updateModelView(activePane);

        // if data in pane's controls are incorrect we return to edit
        if (!mainController.checkInputData(activePane)) {
            showWarningDialog("You have to enter correct installation information");
            return;
        }
        mainController.doNextStep(activePane);
//        invoke model updater
//        modelUpdater.....

        // show hide controls and panes
        switch (activePane) {

            case 0 : backButton.setDisable(false);
                     backButton.setVisible(true);
                     panesContainer.setActivePane(activePane + 1);
                     break;

            case 1 :
            case 2 :
            case 3 :
            case 4 :
                     panesContainer.setActivePane(activePane + 1);
                     break;

            case 5 : nextButton.setText("Finish");
                     cancelButton.setVisible(false);
                     cancelButton.setDisable(true);
                     panesContainer.setActivePane(activePane + 1);
                     break;

            case 6 : break;

        }
    }

    @FXML
    private void handlePressBackButton() {

        int activePane = panesContainer.getIndexActivePane();

        // here is a do method
        mainController.doBackStep(activePane);

        // show hide controls and panes
        switch (activePane) {

            case 0 : break;

            case 1 : backButton.setDisable(true);
                     backButton.setVisible(false);
                     panesContainer.setActivePane(activePane - 1);
                     break;

            case 2 :
            case 3 :
            case 4 :
            case 5 :
                     panesContainer.setActivePane(activePane - 1);
                     break;

            case 6 : nextButton.setText("Next >");
                     cancelButton.setVisible(true);
                     cancelButton.setDisable(false);
                     panesContainer.setActivePane(activePane - 1);
                     break;

        }
    }

    @FXML
    private void handleCancelBackButton() {
        if (showConfirmationDialog("     Are you sure you want to exit ?")) {
            mainController.exitFromInstaller();
        }
    }

    @FXML
    private void textAreaCreateUserScriptTextChanged() {
        if (!userScriptChanged) userScriptChanged = true;
    }

    @FXML
    private void textAreaCreateDBScriptTextChanged() {
        if (!databaseScriptChanged) databaseScriptChanged = true;
    }

    @FXML
    private void textAreaCreateDBTablesScriptTextChanged() {
        if (!tablesScriptChanged) tablesScriptChanged = true;
    }

    @FXML
    private void textInputInstallationPathChanged() {
        if (!installationPathChanged) installationPathChanged = true;
    }

    @FXML
    private void handleOpenFileDialogButton() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        File directory = directoryChooser.showDialog(mainController.getMainStage());

        if (directory != null) {
            textInputInstallationPath.setText(directory.getPath());
            installationPathChanged = true;
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