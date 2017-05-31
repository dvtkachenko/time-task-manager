package application.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by diman on 19.05.2017.
 *
 * This class consists information about server's properties.
 * It is needed for server application and it is saved in installation directory
 */

public class ServerPropertiesFile {

    public static List<String> properties = Arrays.asList("UserDao=dao.jdbc.JdbcUserDao",
            "TaskDao=dao.jdbc.JdbcTaskDao",
            "driverClass=",
            "jdbcUrl=",
            "user=",
            "password=");

    public static String fileName = "persistence.properties";
}
