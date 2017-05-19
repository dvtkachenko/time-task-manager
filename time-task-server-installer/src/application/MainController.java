package application;

import application.model.DBController;
import application.model.CreationDBScript;
import application.model.InstallationInfo;
import application.view.ViewController;
import javafx.stage.Stage;

/**
 * Created by diman on 19.05.2017.
 */


public class MainController {

    private static MainController instance = new MainController();

    private ServerInstaller serverInstaller;
    private ViewController viewController;
    private Stage mainStage;

    private DBController dbController;

    private CreationDBScript creationDBScript;
    private InstallationInfo installationInfo;

    private ModelUpdater modelUpdater;
    private InstallerStepAction installerStepAction;

    private MainController() {
        dbController = DBController.getInstance();
        installationInfo = new InstallationInfo();
        ModelUpdater modelUpdater = new ModelUpdater();
        InstallerStepAction installerStepAction = new InstallerStepAction();
    };

    public static MainController getInstance() {
        return instance;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void setServerInstaller(ServerInstaller serverInstaller) {
        this.serverInstaller = serverInstaller;
    }

    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    // i am not sure about it
    public InstallationInfo getInstallationInfo() {
        return installationInfo;
    }

    public ModelUpdater getModelUpdater() {
        return modelUpdater;
    }

    public InstallerStepAction getInstallerStepAction() {
        return installerStepAction;
    }

    // i am not sure about this method
    public String showFileDialog() {
        String pathToInstall = null;

        return pathToInstall;
    }

}
