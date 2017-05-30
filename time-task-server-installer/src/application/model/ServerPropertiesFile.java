package application.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by diman on 19.05.2017.
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
