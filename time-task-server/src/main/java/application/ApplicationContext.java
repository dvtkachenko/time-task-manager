package application;

import application.exception.PropertyNotFoundException;
import dao.DaoManager;
import dao.UserDao;
import dao.jdbc.ConnectionManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static String propertyFilePath = "src\\main\\resources\\postgrePersistence.properties"; // after packaging path will change!!!

    private static Map<String, String> propertyMap = new HashMap<>();

    public static void init() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(propertyFilePath))){
            System.out.println("initializing application context...");

            String propertyLine;
            while ((propertyLine = bufferedReader.readLine()) != null) {
                // можно ввести проверку на пустые строки и строки-комментарии
                String propertyName = propertyLine.split("=")[0];
                String propertyValue = propertyLine.split("=")[1];
                propertyMap.put(propertyName, propertyValue);
            }

            System.out.println("application context initialized");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find property file");
        } catch (IOException e) {
            throw new RuntimeException("Can't read property file");
        }
    }

    public static String getProperty(String propertyName) throws PropertyNotFoundException {
        String propertyValue = propertyMap.get(propertyName);
        if (propertyValue == null) {
            throw new PropertyNotFoundException("Can't find property " + propertyName);
        }
        return propertyValue;
    }


}
