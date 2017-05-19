package application.view;

import application.MainController;
import application.ServerInstaller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewController {

    public final static int TOTAL_PANE = 7;

    // we have to know when scrips were changed by user
    // and we need to update our scripts
    private boolean userScriptChanged = false;
    private boolean databaseScriptChanged = false;
    private boolean tablesScriptChanged = false;

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
    private TextField textInputPassword;

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

        int activePane = panesContainer.getIndexActivePane();

        // here is a do method
        mainController.getInstallerStepAction().doNextStep(activePane);

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
        mainController.getInstallerStepAction().doBackStep(activePane);

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
        try {
//            serverInstaller.stop();
        } catch (Exception e) {

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
    private void handleOpenFileDialogButton() {
        // do some action

    }

}