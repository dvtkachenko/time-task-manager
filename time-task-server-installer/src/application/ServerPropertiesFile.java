package application;

import java.util.Arrays;
import java.util.List;

/**
 * Created by diman on 19.05.2017.
 */
public class ServerPropertiesFile {

    public List<String> properties = Arrays.asList("UserDao=dao.jdbc.JdbcUserDao",
            "TaskDao=dao.jdbc.JdbcTaskDao",
            "driverClass=",
            "jdbcUrl=",
            "user=",
            "password=");

    public String fileName = "persistence.properties";
}
