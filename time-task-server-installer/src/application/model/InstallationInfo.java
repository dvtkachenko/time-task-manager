package application.model;

import java.net.InetAddress;
import java.net.URL;

/**
 * Created by diman on 17.05.2017.
 */
public class InstallationInfo {

    private int stepInstallation = 0;

    private String pathToInstallation;

    private DBInfo dbInfo = null;

    private boolean createUserAuto = false;

    private String adminRDBMSUsername = null;

    private String adminRDBMSPAssword = null;

    private String ttmUserName = null;

    private String ttmUserPassword = null;

    private String serverAddress = null;

    private String serverPort = null;

    private boolean launchServer = true;

    public int getStepInstallation() {
        return stepInstallation;
    }

    public void setStepInstallation(int stepInstallation) {
        this.stepInstallation = stepInstallation;
    }

    public String getPathToInstallation() {
        return pathToInstallation;
    }

    public void setPathToInstallation(String pathToInstallation) {
        this.pathToInstallation = pathToInstallation;
    }

    public DBInfo getDbInfo() {
        return dbInfo;
    }

    public void setDbInfo(DBInfo dbInfo) {
        this.dbInfo = dbInfo;
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

    public String getAdminRDBMSPAssword() {
        return adminRDBMSPAssword;
    }

    public void setAdminRDBMSPAssword(String adminRDBMSPAssword) {
        this.adminRDBMSPAssword = adminRDBMSPAssword;
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
}
