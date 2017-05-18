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

}
