package application.controller;

import application.model.DBConnectionInfo;
import application.model.InstallationInfo;
import application.view.ViewController;

/**
 * Created by diman on 19.05.2017.
 *
 *  This is class synchronizes data of installation between application
 *  model and view sections
 *
 */


public class ModelViewDataUpdater {

    private InstallationInfo installationInfo;
    private ViewController viewController;

    public ModelViewDataUpdater(InstallationInfo installationInfo, ViewController viewController) {
        this.installationInfo = installationInfo;
        this.viewController = viewController;
    }

    public void updateModelInstallationPath() {
        installationInfo.setInstallationPath(viewController.getTextInputInstallationPath().getText());
    }

    public void updateViewInstallationPath() {
        viewController.setInstallationPath(installationInfo.getInstallationPath());
    }

    public void updateModelSelectedRDBMS() {

        DBConnectionInfo.RDBMS selectedRDBMS = null;

        if (viewController.getRadioButtonOracle().isSelected()) selectedRDBMS = DBConnectionInfo.RDBMS.Oracle;
        if (viewController.getRadioButtonPostgreSQL().isSelected()) selectedRDBMS = DBConnectionInfo.RDBMS.PostgreSQL;
        if (viewController.getRadioButtonMySQL().isSelected()) selectedRDBMS = DBConnectionInfo.RDBMS.MySQL;

        if (selectedRDBMS != null) {
            installationInfo.setSelectedRDBMS(selectedRDBMS);
        }
    }

    public void updateViewDefaultRDBMS() {

        viewController.setDefaultRDBMS(installationInfo.getSelectedRDBMS());
    }

    public void updateModelAutoCreatingUser() {

        installationInfo.setCreateUserAuto(viewController.getAllowAutoCreatingUserCheckBox().isSelected());
    }

    public void updateViewAutoCreatingUserCheckBox() {

        // this snippet of code should be placed in view controller
        // but i am lazy to do it right now
        viewController.setAllowAutoCreatingUserCheckBox(installationInfo.isCreateUserAuto());
//        if (viewController.getAllowAutoCreatingUserCheckBox().isSelected()) {
//            viewController.getLabelInputUsername().setText("Enter admin username for creating new user");
//            viewController.getTextInputUsername().setDisable(false);
////            viewController.getTextInputPassword().setDisable(false);
//        } else {
//            viewController.getLabelInputUsername().setText("Database username");
//            viewController.getTextInputUsername().setDisable(true);
////            viewController.getTextInputPassword().setDisable(true);
//        }
    }

    public void updateModelUserScript() {
        installationInfo.getCreationDBScript().setUserScript(viewController.getTextAreaCreateUserScript().getText());
    }

    public void updateViewUserScript() {
        viewController.setTextAreaCreateUserScript(installationInfo.getCreationDBScript().getUserScript());
//        viewController.getTextAreaCreateUserScript().setEditable(!installationInfo.isDisableEditScripts());
    }

    public void updateModelDBScript() {
        installationInfo.getCreationDBScript().setDatabaseScript(viewController.getTextAreaCreateDBScript().getText());
    }

    public void updateViewDBScript() {
        viewController.setTextAreaCreateDBScript(installationInfo.getCreationDBScript().getDatabaseScript());
//        viewController.getTextAreaCreateDBScript().setEditable(!installationInfo.isDisableEditScripts());
    }

    public void updateModelDBTablesScript() {
        installationInfo.getCreationDBScript().setTablesScript(viewController.getTextAreaCreateDBTablesScript().getText());
    }

    public void updateViewDBTablesScript() {
        viewController.setTextAreaCreateDBTablesScript(installationInfo.getCreationDBScript().getTablesScript());
//        viewController.getTextAreaCreateDBTablesScript().setEditable(!installationInfo.isDisableEditScripts());
    }

    public void updateModelConnectionIP() {

        installationInfo.setServerAddress(viewController.getTextInputConnectionIP().getText());
    }

    public void updateViewConnectionIP() {

        viewController.setTextInputConnectionIP(installationInfo.getServerAddress());
    }

    public void updateModelPort() {

        installationInfo.setServerPort(viewController.getTextInputPort().getText());
    }

    public void updateViewPort() {

        viewController.setTextInputPort(installationInfo.getServerPort());
    }

    public void updateModelUsername() {

        if (installationInfo.isCreateUserAuto()) {
            installationInfo.setAdminRDBMSUsername(viewController.getTextInputUsername().getText());
        } else {
            installationInfo.setTtmUserName(viewController.getTextInputUsername().getText());
        }
    }

    public void updateViewUsername() {

        if (installationInfo.isCreateUserAuto()) {
            viewController.setTextInputUsername(installationInfo.getAdminRDBMSUsername());
        } else {
            viewController.setTextInputUsername(installationInfo.getTtmUserName());
        }
    }

    public void updateModelPassword() {

        if (installationInfo.isCreateUserAuto()) {
            installationInfo.setAdminRDBMSPassword(viewController.getTextInputPassword().getText());
        } else {
            installationInfo.setTtmUserPassword(viewController.getTextInputPassword().getText());
        }
    }

    public void updateViewPassword() {

        if (installationInfo.isCreateUserAuto()) {
            viewController.setTextInputPassword(installationInfo.getAdminRDBMSPassword());
        } else {
            viewController.setTextInputPassword(installationInfo.getTtmUserPassword());
        }
    }

    public void updateViewLabelRDBMS() {

        viewController.setLabelRDBMS(installationInfo.getSelectedRDBMSName());
    }

    public void updateViewLabelConnectionIP() {

        viewController.setLabelConnectionIP(installationInfo.getServerAddress());
    }

    public void updateViewLabelPort() {

        viewController.setLabelPort(installationInfo.getServerPort());
    }

    public void updateViewLabelDBUsername() {

        viewController.setLabelDBUsername(installationInfo.getTtmUserName());
    }

    public void updateModelLaunchServer() {

        installationInfo.setLaunchServer(viewController.getLaunchCheckBox().isSelected());
    }

    public void updateViewLaunchServer() {

        viewController.setLaunchCheckBox(installationInfo.isLaunchServer());
    }

    public void updateModel(int activePane) {
        switch (activePane) {

            case 0:
                break;

            case 1:
                updateModelInstallationPath();
                break;

            case 2:
                updateModelSelectedRDBMS();
                break;

            case 3:
                updateModelUserScript();
                updateModelAutoCreatingUser();
                break;

            case 4:
                updateModelAutoCreatingUser();
                updateModelConnectionIP();
                updateModelPort();
                updateModelUsername();
                updateModelPassword();
                break;

            case 5:
                updateModelDBScript();
                updateModelDBTablesScript();
                break;

            case 6: updateModelLaunchServer();
                break;
        }

    }

    public void updateView(int activePane) {
        switch (activePane) {

            case 0:
                updateViewInstallationPath();
                break;

            case 1:
                updateViewDefaultRDBMS();
                break;

            case 2:
                updateViewUserScript();
                updateViewAutoCreatingUserCheckBox();
                break;

            case 3:
                updateViewAutoCreatingUserCheckBox();
                updateViewConnectionIP();
                updateViewPort();
                updateViewUsername();
                updateViewPassword();
                break;

            case 4:
                updateViewLabelRDBMS();
                updateViewLabelConnectionIP();
                updateViewLabelPort();
                updateViewLabelDBUsername();
                updateViewDBScript();
                updateViewDBTablesScript();
                break;

            case 5:
                updateViewLaunchServer();
                break;

            case 6:
                break;
        }

    }

}
