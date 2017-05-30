package application.controller;

import application.MyUtil;
import application.ServerInstaller;
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
 */


public class MainController {

//    private static MainController instance = new MainController();
    private ServerInstaller serverInstaller;
    private ViewController viewController;
    private Stage mainStage;

    private DBController dbController;

    private InstallationInfo installationInfo;

    private ModelViewDataUpdater modelViewDataUpdater;

    public MainController(Stage mainStage, ServerInstaller serverInstaller, ViewController viewController) {
        this.mainStage = mainStage;
        this.serverInstaller = serverInstaller;
        this.viewController = viewController;

        dbController = new DBController();
        installationInfo = new InstallationInfo();
        modelViewDataUpdater = new ModelViewDataUpdater(installationInfo, viewController);
        viewController.setMainController(this);
//        viewController.setModelUpdater(modelViewDataUpdater);
    };

//    public static MainController getInstance() {
//        return instance;
//    }

//    public void setViewController(ViewController viewController) {
//        this.viewController = viewController;
//    }


    public DBConnectionInfo getDbConnectionInfo() {
        return installationInfo.getDbConnectionInfo();
    }

//    public DBController getDbController() {
//        return dbController;
//    }
//
//    public ViewController getViewController() {
//        return viewController;
//    }

//    public void setServerInstaller(ServerInstaller serverInstaller) {
//        this.serverInstaller = serverInstaller;
//    }
//
//    public void setStage(Stage mainStage) {
//        this.mainStage = mainStage;
//    }

    public Stage getMainStage() {
        return mainStage;
    }

    // i am not sure about it
//    public InstallationInfo getInstallationInfo() {
//        return installationInfo;
//    }
//
//    public ModelViewDataUpdater getModelViewDataUpdater() {
//        return modelViewDataUpdater;
//    }

    public void exitFromInstaller() {
        // it might be some actions here
        //
        exit();
    }

    private void doFirstStep() {
        // update view information from model

    }

    private void doSecondStep() {
        MyUtil.copyFileFromTo("C:\\Windows\\System32\\" + installationInfo.getServerAppFileName(),
                installationInfo.getInstallationPath()+ "\\" + installationInfo.getServerAppFileName());
    }

    private void doThirdStep() {
        installationInfo.initInstallationInfoFromFile();
    }

    private void doFourthStep() {
    }

    private void doFifthStep() {
    }

    private void doSixthStep() {
        if (installationInfo.getDbConnectionInfo() != null) {

            Locale defaultLocale = Locale.getDefault();

            getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                    installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/postgres");

            if (installationInfo.isCreateUserAuto()) {
                getDbConnectionInfo().setUserName(installationInfo.getAdminRDBMSUsername());
                getDbConnectionInfo().setUserPassword(installationInfo.getAdminRDBMSPassword());

                Locale.setDefault(Locale.ENGLISH);

                try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {
                    // for correct connection to Oracle database
                    dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getUserScript());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Locale.setDefault(defaultLocale);
                }

            }

            getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
            getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

//            Locale defaultLocale = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {
                // for correct connection to Oracle database
                dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getDatabaseScript());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Locale.setDefault(defaultLocale);
            }

            getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                    installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/timetaskmanager3");

            getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
            getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

//            Locale defaultLocale = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {
                // for correct connection to Oracle database
                dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getTablesScript());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Locale.setDefault(defaultLocale);
            }


            List<String> propertiesList = new ArrayList<>();
            propertiesList.add(ServerPropertiesFile.properties.get(0));
            propertiesList.add(ServerPropertiesFile.properties.get(1));
            propertiesList.add(ServerPropertiesFile.properties.get(2) + installationInfo.getDbConnectionInfo().getDbDriver());
            propertiesList.add(ServerPropertiesFile.properties.get(3) + installationInfo.getDbConnectionInfo().getDbFullPathConnection());
            propertiesList.add(ServerPropertiesFile.properties.get(4) + installationInfo.getDbConnectionInfo().getUserName());
            propertiesList.add(ServerPropertiesFile.properties.get(5) + installationInfo.getDbConnectionInfo().getUserPassword());
            MyUtil.saveToFile(installationInfo.getInstallationPath() + "\\" + ServerPropertiesFile.fileName, propertiesList);
        }
    }

    private void doSeventhStep() {

        if (installationInfo.isLaunchServer()) {
            try {
//                Runtime.getRuntime().exec("java -jar " + installationInfo.getInstallationPath() + "\\" + installationInfo.getServerAppFileName());
                Runtime.getRuntime().exec(installationInfo.getServerAppFileName());
            } catch (IOException e) {
                e.printStackTrace();
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

    public boolean checkInputData(int activePane) {

        boolean result = true;

        switch (activePane) {

//            case 0:
//                break;

            case 1:
                String temp = viewController.getTextInputInstallationPath().getText();
                if (viewController.getTextInputInstallationPath().getText().equals("")) {
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
                if (viewController.getTextAreaCreateUserScript().getText().equals("")) {
                    result = false;
                }
                break;

            case 4:
                if (viewController.getTextInputConnectionIP().getText().equals("") ||
                        viewController.getTextInputPort().getText().equals("")  ||
                        viewController.getTextInputUsername().getText().equals("") ||
                        viewController.getTextInputPassword().getText().equals("")) {
                    result = false;
                }
                break;

            case 5:
                if (viewController.getTextAreaCreateDBScript().getText().equals("") ||
                        viewController.getTextAreaCreateDBTablesScript().getText().equals("")) {
                    result = false;
                }
                break;

//            case 6:
//                break;
        }

        return result;
    }

}
