package application.controller;

import application.MyUtil;
import application.exception.ExitFromInstallerException;
import application.model.DBConnectionInfo;
import application.model.InstallationInfo;
import application.model.ServerPropertiesFile;
import application.view.ViewController;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.application.Platform.exit;

import javafx.application.Platform;
import javafx.concurrent.Task;

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
    public final static String EXIT_FROM_INSTALLER = "EXITFROMINSTALLER";


//    private static MainController instance = new MainController();
//    private ServerInstaller serverInstaller;
    private ViewController viewController;
    private Stage mainStage;
    private Thread mainThread;

    private DBController dbController;

    private InstallationInfo installationInfo;

    private ModelViewDataUpdater modelViewDataUpdater;

//    public MainController(Stage mainStage, ServerInstaller serverInstaller, ViewController viewController) {
    public MainController(Stage mainStage, ViewController viewController) {
        this.mainStage = mainStage;
//        this.serverInstaller = serverInstaller;
        this.viewController = viewController;
        this.mainThread = Thread.currentThread();

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

    private void createMySQLDB() throws Exception, ExitFromInstallerException {

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "?allowMultiQueries=true");

        if (installationInfo.isCreateUserAuto()) {

            getDbConnectionInfo().setUserName(installationInfo.getAdminRDBMSUsername());
            getDbConnectionInfo().setUserPassword(installationInfo.getAdminRDBMSPassword());

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

                dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getUserScript());

            } catch (Exception e) {
                String errMsg = e.getMessage();
//                e.printStackTrace();
                // if there is a "Access denied for user" in the error message then do not exit from application
                // and user can try one more
                if (!errMsg.contains("Access denied for user") && !errMsg.contains("Communications link failure")) {
                    throw new ExitFromInstallerException(ERROR_DB_USER + "\n" + e.getMessage());
                } else {
                    throw new Exception(ERROR_DB_USER + "\n" + e.getMessage());
                }
            }
        }

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getDatabaseScript());

        } catch (Exception e) {
//                e.printStackTrace();
            throw new ExitFromInstallerException(ERROR_DB_DATABASE + "\n" + e.getMessage());
        }

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/" +
                installationInfo.getDbConnectionInfo().getDatabaseName() + "?allowMultiQueries=true");

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getTablesScript());

        } catch (Exception e) {
//                e.printStackTrace();
            throw new ExitFromInstallerException(ERROR_DB_TABLES + "\n" + e.getMessage());
        }

    }

    private void createPostgreSQLDB() throws Exception, ExitFromInstallerException {

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/postgres");

        if (installationInfo.isCreateUserAuto()) {

            getDbConnectionInfo().setUserName(installationInfo.getAdminRDBMSUsername());
            getDbConnectionInfo().setUserPassword(installationInfo.getAdminRDBMSPassword());

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

                dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getUserScript());

            } catch (Exception e) {
                String errMsg = e.getMessage();
                // if there is a "password authentication failed" in the error message then do not exit from application
                // and user can try one more
                if (!errMsg.contains("password authentication failed") && !errMsg.contains("Check that the hostname and port are correct")) {
                    throw new ExitFromInstallerException(ERROR_DB_USER + "\n" + e.getMessage());
                } else {
                    throw new Exception(ERROR_DB_USER + "\n" + e.getMessage());
                }
            }
        }

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getDatabaseScript());

        } catch (Exception e) {
            throw new ExitFromInstallerException(ERROR_DB_DATABASE + "\n" + e.getMessage());
        }

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix() + "//" +
                installationInfo.getServerAddress() + ":" + installationInfo.getServerPort() + "/" +
                installationInfo.getDbConnectionInfo().getDatabaseName());

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

            dbController.doCreationScript(dbConnection, installationInfo.getCreationDBScript().getTablesScript());

        } catch (Exception e) {
            throw new ExitFromInstallerException(ERROR_DB_TABLES + "\n" + e.getMessage());
        }

    }

    private void createOracleDB() throws Exception, ExitFromInstallerException {

        getDbConnectionInfo().setDbFullPathConnection(getDbConnectionInfo().getDbConnectionPrefix());

        if (installationInfo.isCreateUserAuto()) {

            getDbConnectionInfo().setUserName(installationInfo.getAdminRDBMSUsername());
            getDbConnectionInfo().setUserPassword(installationInfo.getAdminRDBMSPassword());

            try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {

                List<String> stmts = installationInfo.getCreationDBScript().getUserScriptAsList();
                for (String stmt : stmts) {
                    dbController.doCreationScript(dbConnection, stmt);
                }

            } catch (Exception e) {
                String errMsg = e.getMessage();
                // if there is an ORA-01017 in the error message then do not exit from application
                // and user can try one more
                if (!errMsg.contains("ORA-01017")) {
                    throw new ExitFromInstallerException(ERROR_DB_USER + "\n" + e.getMessage());
                } else {
                    throw new Exception(ERROR_DB_USER + "\n" + e.getMessage());
                }
            }
        }

        getDbConnectionInfo().setUserName(installationInfo.getTtmUserName());
        getDbConnectionInfo().setUserPassword(installationInfo.getTtmUserPassword());

        try (Connection dbConnection = dbController.openConnection(getDbConnectionInfo())) {
            List<String> stmts = installationInfo.getCreationDBScript().getTablesScriptAsList();
            for (String stmt : stmts) {
                dbController.doCreationScript(dbConnection, stmt);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExitFromInstallerException(ERROR_DB_TABLES + "\n" + e.getMessage());
        }

    }

    private void doFirstStep() {
        // update view information from model
        // adjust behavior of databases script TextArea controls
        viewController.getTextAreaCreateUserScript().setEditable(!installationInfo.isDisableEditScripts());
        viewController.getTextAreaCreateDBScript().setEditable(!installationInfo.isDisableEditScripts());
        viewController.getTextAreaCreateDBTablesScript().setEditable(!installationInfo.isDisableEditScripts());
    }

    // it is called from Thread
    public void doSecondStep() throws Exception{

    	// copy server application file to destination folder
        MyUtil.copyFileFromTo(new File("").getAbsolutePath() + "\\resources\\" + installationInfo.getServerAppFileName(),
                installationInfo.getInstallationPath()+ "\\" + installationInfo.getServerAppFileName());

    	// copy client application file to destination folder
        MyUtil.copyFileFromTo(new File("").getAbsolutePath() + "\\resources\\" + installationInfo.getClientAppFileName(),
                installationInfo.getInstallationPath()+ "\\" + installationInfo.getClientAppFileName());
        
      // just for test abnormal installation process termination
//        viewController.showWarningDialog(new File("").getAbsolutePath());
//        viewController.showWarningDialog(ERROR_HEADER + ERROR_DB_USER + "\n" + ERROR_INSTALLATION_INTERRUPTED);

    }

    private void doThirdStep() throws Exception {

        installationInfo.initInstallationInfoFromFile();
    }

    private void doFourthStep() {

        // it makes some changes in behavior of textfield
        if (installationInfo.isCreateUserAuto()) {
            viewController.getLabelInputUsername().setText("Enter admin username for creating new user");
            viewController.getTextInputUsername().setDisable(false);
//            viewController.getTextInputPassword().setDisable(false);
        } else {
            viewController.getLabelInputUsername().setText("Database username");
            viewController.getTextInputUsername().setDisable(true);
//            viewController.getTextInputPassword().setDisable(true);
        }

    }

    private void doFifthStep() {
    }

    private void doSixthStep() throws Exception, ExitFromInstallerException {

        if (installationInfo.getDbConnectionInfo() == null) {

            throw new ExitFromInstallerException(ERROR_HEADER + "\n" + ERROR_INSTALLATION_INTERRUPTED);

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

            } catch (ExitFromInstallerException e) {
                throw new ExitFromInstallerException(e.getMessage());
            } catch(Exception e) {
                throw new Exception(e.getMessage());
            } finally {
                Locale.setDefault(defaultLocale);
            }
        }
    }

    private void doSeventhStep() {

        if (installationInfo.isLaunchServer()) {
            try {
//                Runtime.getRuntime().exec("java -jar " + new File("").getAbsolutePath()  + "\\resources\\" + installationInfo.getServerAppFileName());
//                Runtime.getRuntime().exec(new File("").getAbsolutePath()  + "\\resources\\" + installationInfo.getServerAppFileName());
                Runtime.getRuntime().exec(installationInfo.getInstallationPath()  + "\\" + installationInfo.getServerAppFileName());
                Runtime.getRuntime().exec(installationInfo.getInstallationPath()  + "\\" + installationInfo.getClientAppFileName());
            } catch (IOException e) {
                viewController.showWarningDialog(ERROR_HEADER + "\n" + ERROR_RUN_SERVER);
            }
        }

        exitFromInstaller();
        exitFromInstaller();
    }

    public void doNextStep() {

        int activePane = viewController.getActivePage();

        // if data in pane's controls are incorrect we return to edit
        if (!checkInputData(activePane)) {
            viewController.showWarningDialog("You have to enter correct installation information");
            return;
        }

        // synhronize data between Model and View
        modelViewDataUpdater.updateModel(activePane);

        // i have to invoke one the do....Step method
        switch (activePane) {

            case 0:
                doFirstStep();
                viewController.setBackButtonMode(true);
                viewController.setActivePage(activePane + 1);
                break;

		    case 1:

				viewController.showInstallationProgressPage("Server file is being copied now to installation directory ...");
	
				// for correct working progress bar
				Thread do2Step = new Thread(new Task() {
					@Override
					protected Void call() {
						
						try {
							doSecondStep();
							Platform.runLater(() -> {
								viewController.hideInstallationProgressPage();
								viewController.setBackButtonDisable(true);
								viewController.setActivePage(viewController.getActivePage() + 1);
							});
						} catch (Exception e) {
							Platform.runLater(() -> {
								viewController.hideInstallationProgressPage();
								viewController.showWarningDialog(
										ERROR_HEADER + "\n" + e.getMessage() + "\n" + "Please fix this trouble !");
							});
						}
	
						return null;
					}
				});
				
				do2Step.setDaemon(true);
				do2Step.start();
	
				break;
	
            case 2:
            	
				try {
					doThirdStep();
					viewController.setBackButtonDisable(false);
					viewController.setActivePage(activePane + 1);
				} catch (Exception e) {
					viewController.showWarningDialog(ERROR_HEADER + "\n" + e.getMessage() + "\n" + "Please fix this trouble !");
				}
	
				break;

            case 3:
                doFourthStep();
                viewController.setActivePage(activePane + 1);
                break;

            case 4:
                viewController.setActivePage(activePane + 1);
                break;

            case 5:
            	
				viewController.showInstallationProgressPage("Database structure is being created now ...");
				// for correct working progress bar
				Thread do6Step = new Thread(new Task() {
					@Override
					protected Void call() {
						try {
							doSixthStep();
							Platform.runLater(() -> {
								viewController.hideInstallationProgressPage();
								viewController.setNextButtonText("Finish");
								viewController.setCancelButtonMode(false);
								viewController.setActivePage(activePane + 1);
								viewController.setBackButtonMode(false);
							});
						} catch (ExitFromInstallerException e) {
							Platform.runLater(() -> {
								viewController.hideInstallationProgressPage();
								viewController.showWarningDialog(
										ERROR_HEADER + e.getMessage() + "\n" + "Please fix this trouble !");
								// i had created this part of code and i realised
								// what installer should continue working
								// and user can try fix troubles
								// so this code was commented 
								// exitFromInstaller();
							});
	
						} catch (Exception e) {
							Platform.runLater(() -> {
								viewController.hideInstallationProgressPage();
								viewController.showWarningDialog(
										ERROR_HEADER + e.getMessage() + "\n" + "Please enter correct information !");
							});
						}
	
						return null;
					}
				});
				do6Step.setDaemon(true);
				do6Step.start();
	
				break;

            case 6:
                doSeventhStep();
                break;
        }

        // synhronize data between Model and View
        modelViewDataUpdater.updateView(activePane);

    }

    public void doBackStep() {

        int activePane = viewController.getActivePage();
        // i have to invoke one the do....Step method
        switch (activePane) {

            case 0:
                break;

            case 1:
                viewController.setBackButtonMode(false);
                viewController.setActivePage(activePane - 1);
                break;

            case 2:
                viewController.setActivePage(activePane - 1);
                break;

            case 3:
                viewController.setActivePage(activePane - 1);
                viewController.setBackButtonDisable(true);
                break;

            case 4:
                viewController.setActivePage(activePane - 1);
                modelViewDataUpdater.updateModel(activePane);
                break;

            case 5:
                viewController.setActivePage(activePane - 1);
                break;

            case 6:
                viewController.setNextButtonText("Next >");
                viewController.setCancelButtonMode(true);
                viewController.setActivePage(activePane - 1);
                break;
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

            case 0:
                break;

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

                if (result) {
                    final String IPADDRESS_PATTERN =
                            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

                    Pattern p = Pattern.compile(IPADDRESS_PATTERN);
                    Matcher m = p.matcher(viewController.getTextInputConnectionIP().getText());
                    if (!m.matches()) {
                        result = false;
                    }
                    p = Pattern.compile("[0-9]+");
                    m = p.matcher(viewController.getTextInputPort().getText());
                    if (!m.matches()) {
                        result = false;
                    }
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

            case 6:
                break;
        }

        return result;
    }

}
