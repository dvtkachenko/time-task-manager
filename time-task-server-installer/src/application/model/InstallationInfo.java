package application.model;

/**
 * Created by diman on 17.05.2017.
 *
 *  This class consists all information about installation process.
 *
 */

public class InstallationInfo {

    private DBConnectionInfo dbConnectionInfo;

    private CreationDBScript creationDBScript;
    private boolean disableEditScripts = true;

    //    private final String PATH_TO_SCRIPT = "d:\\MyDevelopment\\JavaCourse\\TTMServerInstaller\\out\\production\\TTMServerInstaller\\application\\resources\\";
    public static final String PATH_TO_RESOURCES = "out\\production\\TTMServerInstaller\\application\\resources\\";

//    private int stepInstallation = 0;

    // default value can be changed by user during installation process
    private String installationPath = "D:\\Temp\\TTMServer";
    private String serverAppFileName = "notepad.exe";

    private DBConnectionInfo.RDBMS selectedRDBMS = DBConnectionInfo.RDBMS.PostgreSQL;

    private boolean createUserAuto = true;

    private String selectedRDBMSName;

    private String adminRDBMSUsername = null;

    private String adminRDBMSPassword = null;

    private String ttmUserName = null;

    private String ttmUserPassword = null;

//    private String serverAddress = null;
    private String serverAddress = "178.62.247.230";

    private String serverPort = null;

    private boolean launchServer = true;

    public CreationDBScript getCreationDBScript() {
        return creationDBScript;
    }

    public boolean isDisableEditScripts() {
        return disableEditScripts;
    }

    public DBConnectionInfo getDbConnectionInfo() {
        return dbConnectionInfo;
    }

//    public void setDbConnectionInfo(DBConnectionInfo dbConnectionInfo) {
//        this.dbConnectionInfo = dbConnectionInfo;
//    }

//    public void setCreationDBScript(CreationDBScript creationDBScript) {
//        this.creationDBScript = creationDBScript;
//    }
//
//    public int getStepInstallation() {
//        return stepInstallation;
//    }
//
//    public void setStepInstallation(int stepInstallation) {
//        this.stepInstallation = stepInstallation;
//    }

    public String getInstallationPath() {
        return installationPath;
    }

    public String getServerAppFileName() {
        return serverAppFileName;
    }

    public void setInstallationPath(String installationPath) {
        this.installationPath = installationPath;
    }

    public String getSelectedRDBMSName() {
        return selectedRDBMSName;
    }

    public DBConnectionInfo.RDBMS getSelectedRDBMS() {
        return selectedRDBMS;
    }

    public void setSelectedRDBMS(DBConnectionInfo.RDBMS selectedRDBMS) {
        this.selectedRDBMS = selectedRDBMS;
    }

    public boolean isCreateUserAuto() {
        return createUserAuto;
    }

    public void setCreateUserAuto(boolean createUserAuto) {
        this.createUserAuto = createUserAuto;
    }

    public String getAdminRDBMSUsername() {
        return adminRDBMSUsername;
    }

    public void setAdminRDBMSUsername(String adminRDBMSUsername) {
        this.adminRDBMSUsername = adminRDBMSUsername;
    }

    public String getAdminRDBMSPassword() {
        return adminRDBMSPassword;
    }

    public void setAdminRDBMSPassword(String adminRDBMSPAssword) {
        this.adminRDBMSPassword = adminRDBMSPAssword;
    }

    public String getTtmUserName() {
        return ttmUserName;
    }

    public void setTtmUserName(String ttmUserName) {
        this.ttmUserName = ttmUserName;
    }

    public String getTtmUserPassword() {
        return ttmUserPassword;
    }

    public void setTtmUserPassword(String ttmUserPassword) {
        this.ttmUserPassword = ttmUserPassword;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public boolean isLaunchServer() {
        return launchServer;
    }

    public void setLaunchServer(boolean launchServer) {
        this.launchServer = launchServer;
    }

    public void initInstallationInfoFromFile() {
        dbConnectionInfo = new DBConnectionInfo(selectedRDBMS);
        selectedRDBMSName = dbConnectionInfo.getRdbmsName();
        serverPort = dbConnectionInfo.getDbDefaultPort();
        ttmUserName = dbConnectionInfo.getUserName();
        ttmUserPassword = dbConnectionInfo.getUserPassword();
        creationDBScript = new CreationDBScript();
        creationDBScript.readScriptFiles(dbConnectionInfo.getScriptSuffixFileName());
    }
}
