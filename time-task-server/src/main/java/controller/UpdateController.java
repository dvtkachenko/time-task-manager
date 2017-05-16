package controller;

import dao.DaoManager;
import dao.UserDao;
import application.exception.InternalServerErrorException;
import dao.exception.DaoException;
import entity.User;

public class UpdateController {

    public User updateUserTasks(User user) {
        UserDao userDao = null;
        try {
            userDao = DaoManager.getUserDao();
        } catch (InternalServerErrorException e) {
            e.printStackTrace();
            return null;

        }

        try {
            return userDao.updateUser(user);
        } catch (InternalServerErrorException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return null;
    }
}
