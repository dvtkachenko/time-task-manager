package entity;

import database.DBManager;
import database.oracle.OracleDBConnectionInfo;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by diman on 24.04.2017.
 */
public class Main {


    public static void main(String[] args) {

        try {

            System.out.println("Check session. Session is " + DBManager.isSessionCreated());
            if (!DBManager.createDBSession()) System.out.println("I can't create the database session");
            System.out.println("Check session. Session is " + DBManager.isSessionCreated());


        if (DBManager.users.addUser(new UserData("andrey15","12345"))) System.out.println("user was added");
        if (DBManager.users.addUser(new UserData("andrey9","12345"))) System.out.println("user was added");
        if (DBManager.users.addUser(new UserData("anton6","12345"))) System.out.println("user was added");
        if (DBManager.users.addUser(new UserData("gleb","12345"))) System.out.println("user was added");
        if (DBManager.users.addUser(new UserData("oksana","12345"))) System.out.println("user was added");
        if (DBManager.users.addUser(new UserData("tolik","12345"))) System.out.println("user was added");

        if (DBManager.users.deleteUser(new UserData("andrey3"))) System.out.println("user was deleted");
        if (DBManager.users.deleteUser(new UserData("andrey4"))) System.out.println("user was deleted");
        if (DBManager.users.deleteUser(new UserData("anton"))) System.out.println("user was deleted");
        if (DBManager.users.deleteUser(new UserData("gleb"))) System.out.println("user was deleted");
        if (DBManager.users.deleteUser(new UserData("oksana"))) System.out.println("user was deleted");
        if (DBManager.users.deleteUser(new UserData("tolik"))) System.out.println("user was deleted");
//
//        if (DBManager.users.changePassword(new UserData("andrey3","qwef"))) System.out.println("password was changed");
//        if (DBManager.users.changePassword(new UserData("anton","asgasdvz"))) System.out.println("password was changed");
//        if (DBManager.users.changePassword(new UserData("gleb","qq23rwasf"))) System.out.println("password was changed");
//        if (DBManager.users.changePassword(new UserData("oksana","1wvg4g"))) System.out.println("password was changed");
//        if (DBManager.users.changePassword(new UserData("tolik","54321"))) System.out.println("password was changed");

//            System.out.println(DBManager.users.getUserData(new UserData("andrey3")));
//            System.out.println(DBManager.users.getUserData(new UserData("anton")));
//            System.out.println(DBManager.users.getUserData(new UserData("gleb")));
//            System.out.println(DBManager.users.getUserData(new UserData("oksana")));
//            System.out.println(DBManager.users.getUserData(new UserData("tolik")));
//
//            System.out.println(DBManager.users.getAllUsersData());
//
//            TaskData taskData = new TaskData();
//
//            taskData.setParentTaskID(5);
//            taskData.setChildTaskID(6);
//            taskData.setTaskLevel(2);
//            taskData.setUserID(4);
//            taskData.setTaskName("Do Oracle database engine");
//            taskData.setPlanStartTime(Date.valueOf("2017-04-21"));
//            taskData.setPlanEndTime(Date.valueOf("2017-05-21"));
//            taskData.setPlanDuration(60);
//            taskData.setFactStartTime(Date.valueOf("2017-04-21"));
//            taskData.setFactEndTime(Date.valueOf("2017-05-21"));
//            taskData.setFactDuration(60);
//            taskData.setNumVersion(1);
//            taskData.setIsCompleted("N");

//            if (DBManager.tasks.addUserTask(taskData)) System.out.println("task was added");

//            taskData.setTaskID(6);
//            System.out.println(DBManager.tasks.getUserTask(taskData));

//            if (DBManager.tasks.deleteUserTask(taskData)) System.out.println("task was deleted");

//            System.out.println(DBManager.tasks.getUserAllTasks(4));
//            System.out.println();
//            // make a UserTaskTree as a tree
//            System.out.println(DBManager.tasks.getUserAllTasksAsTree(4, 1));


//            System.out.println("Fill UserDataTree by test data");
//            UserTaskTree userTaskTree = new UserTaskTree();
//            userTaskTree.testFillUserTaskTree(20);
//            System.out.println();
//            System.out.println("Convert UserDateTree to List");
//            System.out.println();
//            List<TaskData> listTasks = userTaskTree.toList();
//            int counter = 0;
//            for (TaskData td : listTasks) {
//                System.out.println(td);
//                counter++;
//            }
//            System.out.println("Counter of TaskData = " + counter);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(!DBManager.closeDBSession()) System.out.println("I can't close the database session");;
            System.out.println("Check session. Session is " + DBManager.isSessionCreated());
        }
    }

}
