package entity;

import database.DBManager;

/**
 * Created by diman on 26.04.2017.
 */
public class EntityManager {

    private DBManager dbManager = null;

    public EntityManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public UserTaskTree getUserTaskTreeFromDB() {
        UserTaskTree userTaskTree = null;

        return userTaskTree;
    }

    public boolean addNewUserTaskTreeToDB(UserTaskTree userTaskTree) {


        return true;
    }

    public boolean updateUserTaskTreeToDB(UserTaskTree userTaskTree) {

        return true;
    }

    public UserData getUserFromDB(String userName) throws Exception {
        return dbManager.users.getUserData(new UserData(userName));
    }

    public boolean addUserToDB(UserData userData) throws Exception {
        return dbManager.users.addUser(userData);
    }

    public boolean deleteUserFromDB(UserData userData) throws Exception {
        return dbManager.users.deleteUser(userData);
    }

    public boolean changeUserPassword(UserData userData) throws Exception {
        return dbManager.users.changePassword(userData);
    }

}
