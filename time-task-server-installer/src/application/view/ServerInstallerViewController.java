package application.view;

import application.ServerInstaller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServerInstallerViewController {

    public final static int TOTAL_PANE = 7;

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
    private TextArea textAreaCreateUserScript;

    @FXML
    private TextArea textAreaCreateDBScript;

    @FXML
    private Label labelRDBMS;

    @FXML
    private Label labelConnectinIP;

    @FXML
    private Label labelPort;

    @FXML
    private Label labelDBUsername;

    private ServerInstaller serverInstaller;

    private Stage mainStage;

    public PanesContainer panesContainer;

    public ServerInstallerViewController() {
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

    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setSetupWizard(ServerInstaller ServerInstaller) {
        this.serverInstaller = ServerInstaller;

    }

    @FXML
    private void handlePressNextButton() {

        int activePane = panesContainer.getIndexActivePane();

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

        // here will be a method
        // something like - doAction
    }

    @FXML
    private void handlePressBackButton() {

        int activePane = panesContainer.getIndexActivePane();

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

        // here will be a method
        // something like - doAction
    }

    @FXML
    private void handleCancelBackButton() {
        try {
            serverInstaller.stop();
        } catch (Exception e) {

        }
    }

    @FXML
    private void handleOpenFileDialogButton() {
        // do some action
    }

}