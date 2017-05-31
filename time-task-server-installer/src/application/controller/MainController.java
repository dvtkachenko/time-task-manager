package application.controller;

import application.MyUtil;
import application.model.DBConnectionInfo;
import application.model.InstallationInfo;
import application.model.ServerPropertiesFile;
import application.view.ViewController;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static javafx.application.Platform.exit;

/**
 * Created by diman on 19.05.2017.
 *
 * This class is a main controller for this application
 * It keeps and handles other instances of objects and controllers
 *
 */

public class MainController {

    public final static String ERROR_HEADER = "Something must have gone wrong. ";
    public final static String ERROR_INSTALLATION_INTERRUPTED = "The installation has interrupted !";

    public final static String ERROR_DB_USER = "User could not be created !";
    public final static String ERROR_DB_DATABASE = "User could not be created !";
    public final static String ERROR_DB_TABLES = "User could not be created !";
    public final static String ERROR_RUN_SERVER = "Server could not be started !";
    public final static String ERROR_DB_SCRIPT = "Database script could not be performed !";


//    private static MainController instance = new MainController();
//    private ServerInstaller serverInstaller;
    private ViewController viewController;
    private Stage mainStage;

    private DBController dbController;

    private InstallationInfo installationInfo;

    private ModelViewDataUpdater modelViewDataUpdater;

//    public MainController(Stage mainStage, ServerInstaller serverInstaller, ViewController viewController) {
    public MainController(Stage mainStage, ViewController viewController) {
        this.mainStage = mainStage;
//        this.serverInstaller = serverInstaller;
        this.viewController = viewController;

        dbController = new DBController();
        installationInfo = new InstallationInfo();
        modelViewDataUpdater = new ModelViewDataUpdater(installationInfo, viewController);
        viewController.setMainController(this);
//        viewController.setModelUpdater(modelViewDataUpdater);
    };


    public DBConnectionInfo getDbConnectionInfo() {
        return installationInfo.getDbConnectionInfo();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void exitFromInstaller() {
        // it might be some actions here
        //
        exit();
    }

    private void createMySQLDB() throws Exception {

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort());

        if (installationInfo.isCreateUserAuto()) {

            getDbConnectionInfo().setUserName(installationInfo.getAdminRDBMSUsername());
            getDbConnectionInfo().setUserPassword(installationInfo.getAdminRDBMSPassword());

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

                dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getUserScript());

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(ERROR_DB_USER);
            }
        }

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getDatabaseScript());

        } catch (Exception e) {
//                e.printStackTrace();
            throw new Exception(ERROR_DB_DATABASE);
        }

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/" +
                installationInfo.getDbConnectionInfo().getDatabaseName());

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getTablesScript());

        } catch (Exception e) {
//                e.printStackTrace();
            throw new Exception(ERROR_DB_TABLES);
        }

    }

    private void createPostgreSQLDB() throws Exception {

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/postgres");

        if (installationInfo.isCreateUserAuto()) {

            getDbConnectionInfo().setUserName(installationInfo.getAdminRDBMSUsername());
            getDbConnectionInfo().setUserPassword(installationInfo.getAdminRDBMSPassword());

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

                dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getUserScript());

            } catch (Exception e) {
//                e.printStackTrace();
                throw new Exception(ERROR_DB_USER);
            }
        }

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getDatabaseScript());

        } catch (Exception e) {
//                e.printStackTrace();
            throw new Exception(ERROR_DB_DATABASE);
        }

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "//" +
                installationInfo.getDbConnectionInfo().getDatabaseName());

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getTablesScript());

        } catch (Exception e) {
//                e.printStackTrace();
            throw new Exception(ERROR_DB_TABLES);
        }

    }

    private void createOracleDB() throws Exception {

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix());

        if (installationInfo.isCreateUserAuto()) {

            getDbConnectionInfo().setUserName(installationInfo.getAdminRDBMSUsername());
            getDbConnectionInfo().setUserPassword(installationInfo.getAdminRDBMSPassword());

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

                dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getUserScript());

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(ERROR_DB_USER);
            }
        }

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

//            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getDatabaseScript());
            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getTablesScript());

        } catch (Exception e) {
//                e.printStackTrace();
            throw new Exception(ERROR_DB_DATABASE);
        }

//        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
//                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/" +
//                installationInfo.getDbConnectionInfo().getDatabaseName());
//
//        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
//        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());
//
//        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {
//
//            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getTablesScript());
//
//        } catch (Exception e) {
////                e.printStackTrace();
//            throw new Exception(ERROR_DB_TABLES);
//        }

    }

    private void doFirstStep() {
        // update view information from model

    }

    private void doSecondStep() {
        MyUtil.copyFileFromTo("C:\\Windows\\System32\\" + installationInfo.getServerAppFileName(),
                installationInfo.getInstallationPath()+ "\\" + installationInfo.getServerAppFileName());

        // just for test abnormal installation process termination
//        viewController.showWarningDialog(ERROR_HEADER + ERROR_DB_USER + "\n" + ERROR_INSTALLATION_INTERRUPTED);
//        exitFromInstaller();

    }

    private void doThirdStep() {
        installationInfo.initInstallationInfoFromFile();
    }

    private void doFourthStep() {
    }

    private void doFifthStep() {
    }

    private void doSixthStep() {

        if (installationInfo.getDbConnectionInfo() == null) {

            viewController.showWarningDialog(ERROR_HEADER + "\n" + ERROR_INSTALLATION_INTERRUPTED);
            exitFromInstaller();

        } else {

            Locale defaultLocale = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);

            try {
                switch (installationInfo.getSelectedRDBMS()) {
                    case MySQL: createMySQLDB(); break;
                    case PostgreSQL: createPostgreSQLDB(); break;
                    case Oracle: createOracleDB(); break;
                }

                List<String> propertiesList = new ArrayList<>();
                propertiesList.add(ServerPropertiesFile.properties.get(0));
                propertiesList.add(ServerPropertiesFile.properties.get(1));
                propertiesList.add(ServerPropertiesFile.properties.get(2) + installationInfo.getDbConnectionInfo().getDbDriver());
                propertiesList.add(ServerPropertiesFile.properties.get(3) + installationInfo.getDbConnectionInfo().getDbFullPathConnection());
                propertiesList.add(ServerPropertiesFile.properties.get(4) + installationInfo.getDbConnectionInfo().getUserName());
                propertiesList.add(ServerPropertiesFile.properties.get(5) + installationInfo.getDbConnectionInfo().getUserPassword());
                MyUtil.saveToFile(installationInfo.getInstallationPath() + "\\" + ServerPropertiesFile.fileName, propertiesList);

            } catch(Exception e) {
                viewController.showWarningDialog(ERROR_HEADER + e.getMessage() + "\n" + ERROR_INSTALLATION_INTERRUPTED);
                exitFromInstaller();
            } finally {
                Locale.setDefault(defaultLocale);
            }
        }
    }

    private void doSeventhStep() {

        if (installationInfo.isLaunchServer()) {
            try {
//                Runtime.getRuntime().exec("java -jar " + installationInfo.getInstallationPath() + "\\" + installationInfo.getServerAppFileName());
                Runtime.getRuntime().exec(installationInfo.getServerAppFileName());
            } catch (IOException e) {
                viewController.showWarningDialog(ERROR_HEADER + "\n" + ERROR_RUN_SERVER);
            }
        }

        exitFromInstaller();
    }

    public void doNextStep(int activePane) {

        // synhronize data between Model and View
        modelViewDataUpdater.updateModel(activePane);

        // i have to invoke one the do....Step method
        switch (activePane) {

//            case 0:
//                break;

            case 1:
                doSecondStep();
                break;

            case 2:
                doThirdStep();
                break;

//            case 3:
//                break;
//
//            case 4:
//                break;

            case 5:
                doSixthStep();
                break;

            case 6:
                doSeventhStep();
                break;
        }

        // synhronize data between Model and View
        modelViewDataUpdater.updateView(activePane);

    }

    public void doBackStep(int activePane) {
        // i have to invoke one the do....Step method
        switch (activePane) {

//            case 0:
//                break;
//
//            case 1:
//                break;
//
//            case 2:
//                break;
//
//            case 3:
//                break;

            case 4:
                modelViewDataUpdater.updateModel(activePane);
                break;

//            case 5:
//                break;
//
//            case 6:
//                break;
        }
    }

    /**
     * This method checks user's input data
     * @param activePane  it keeps current num active page
     * @return
     */

    public boolean checkInputData(int activePane) {

        boolean result = true;

        // in the switch area we check for null text area
        // if we don't check it will throw null pointer exception in equals statement

        switch (activePane) {

//            case 0:
//                break;

            case 1:
                if ((viewController.getTextInputInstallationPath().getText() == null) ? true :
                        viewController.getTextInputInstallationPath().getText().equals("")) {
                    result = false;
                }
                break;

            case 2:
                if (!viewController.getRadioButtonMySQL().isSelected() &&
                        !viewController.getRadioButtonPostgreSQL().isSelected() &&
                        !viewController.getRadioButtonOracle().isSelected()) {
                    result = false;
                }
                break;

            case 3:
                if ((viewController.getTextAreaCreateUserScript().getText() == null) ? true :
                        viewController.getTextAreaCreateUserScript().getText().equals("")) {
                    result = false;
                }
                break;

            case 4:
                if (((viewController.getTextInputConnectionIP().getText() == null) ? true :
                        viewController.getTextInputConnectionIP().getText().equals("")) ||
                        ((viewController.getTextInputPort().getText() == null) ? true :
                                viewController.getTextInputPort().getText().equals("")) ||
                        ((viewController.getTextInputUsername().getText() == null) ? true :
                                viewController.getTextInputUsername().getText().equals("")) ||
                        ((viewController.getTextInputPassword().getText() == null) ? true :
                                viewController.getTextInputPassword().getText().equals(""))) {
                    result = false;
                }
                break;

            case 5:
                if (((viewController.getTextAreaCreateDBScript().getText() == null) ? true :
                        viewController.getTextAreaCreateDBScript().getText().equals("")) ||
                        ((viewController.getTextAreaCreateDBTablesScript().getText() == null) ? true :
                                viewController.getTextAreaCreateDBTablesScript().getText().equals(""))) {
                    result = false;
                }
                break;

//            case 6:
//                break;
        }

        return result;
    }

}
