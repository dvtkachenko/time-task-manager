package entity;

import database.oracle.OracleDB;
import database.oracle.OracleDBConnectionInfo;

import java.sql.Date;
import java.util.Locale;

/**
 * Created by diman on 24.04.2017.
 */
public class Main {


    public static void main(String[] args) {

        Locale defaultLocale = Locale.getDefault();

        // for correct connection to Oracle database
        Locale.setDefault(Locale.ENGLISH);
        OracleDB oracleDB = new OracleDB();
        try {
//            OracleDB oracleDB = new OracleDB();
            oracleDB.openDBConnection(new OracleDBConnectionInfo());
            // ??? i need to find out about this line code ???
            Locale.setDefault(defaultLocale);

//        if (oracleDB.users.addUser(new UserData("andrey15","12345"))) System.out.println("user was added");
//        if (oracleDB.users.addUser(new UserData("andrey9","12345"))) System.out.println("user was added");
//        if (oracleDB.users.addUser(new UserData("anton6","12345"))) System.out.println("user was added");
//        if (oracleDB.users.addUser(new UserData("gleb","12345"))) System.out.println("user was added");
//        if (oracleDB.users.addUser(new UserData("oksana","12345"))) System.out.println("user was added");
//        if (oracleDB.users.addUser(new UserData("tolik","12345"))) System.out.println("user was added");
//
//        if (oracleDB.users.deleteUser(new UserData("andrey3"))) System.out.println("user was deleted");
//        if (oracleDB.users.deleteUser(new UserData("andrey4"))) System.out.println("user was deleted");
//        if (oracleDB.users.deleteUser(new UserData("anton"))) System.out.println("user was deleted");
//        if (oracleDB.users.deleteUser(new UserData("gleb"))) System.out.println("user was deleted");
//        if (oracleDB.users.deleteUser(new UserData("oksana"))) System.out.println("user was deleted");
//        if (oracleDB.users.deleteUser(new UserData("tolik"))) System.out.println("user was deleted");
//
//        if (oracleDB.users.changePassword(new UserData("andrey3","qwef"))) System.out.println("password was changed");
//        if (oracleDB.users.changePassword(new UserData("anton","asgasdvz"))) System.out.println("password was changed");
//        if (oracleDB.users.changePassword(new UserData("gleb","qq23rwasf"))) System.out.println("password was changed");
//        if (oracleDB.users.changePassword(new UserData("oksana","1wvg4g"))) System.out.println("password was changed");
//        if (oracleDB.users.changePassword(new UserData("tolik","54321"))) System.out.println("password was changed");

//            System.out.println(oracleDB.users.getUserData(new UserData("andrey3")));
//            System.out.println(oracleDB.users.getUserData(new UserData("anton")));
//            System.out.println(oracleDB.users.getUserData(new UserData("gleb")));
//            System.out.println(oracleDB.users.getUserData(new UserData("oksana")));
//            System.out.println(oracleDB.users.getUserData(new UserData("tolik")));
//
//            System.out.println(oracleDB.users.getAllUsersData());

            TaskData taskData = new TaskData();

            taskData.setParentTaskID(5);
            taskData.setChildTaskID(6);
            taskData.setTaskLevel(2);
            taskData.setUserID(4);
            taskData.setTaskName("Do Oracle database engine");
            taskData.setPlanStartTime(Date.valueOf("2017-04-21"));
            taskData.setPlanEndTime(Date.valueOf("2017-05-21"));
            taskData.setPlanDuration(60);
            taskData.setFactStartTime(Date.valueOf("2017-04-21"));
            taskData.setFactEndTime(Date.valueOf("2017-05-21"));
            taskData.setFactDuration(60);
            taskData.setNumVersion(1);
            taskData.setIsCompleted("N");

//            if (oracleDB.tasks.addUserTask(taskData)) System.out.println("task was added");

//            taskData.setTaskID(6);
//            System.out.println(oracleDB.tasks.getUserTask(taskData));

//            if (oracleDB.tasks.deleteUserTask(taskData)) System.out.println("task was deleted");

            System.out.println(oracleDB.tasks.getUserAllTasks(4));
            System.out.println();
            // make a UserTaskDataModel as a tree
            System.out.println(oracleDB.tasks.getUserAllTasksAsTree(4, 1));




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
            try {
                oracleDB.closeDBConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
