package controller;

import application.exception.InternalServerErrorException;
import dao.DaoManager;
import dao.UserDao;
import dao.exception.DaoException;
import entity.User;

public class LoginController {

    // возможноб имеет смысл сделать методы статическими

    public User login(User user) {
        UserDao userDao = null;
        try {
            userDao = DaoManager.getUserDao();
        } catch (InternalServerErrorException e) {
            e.printStackTrace();
            return null;

        }

        try {
            user = userDao.loginUser(user);
            return user;
        } catch (DaoException e) {
            return null;
        } catch (InternalServerErrorException e) {
            e.printStackTrace();
        }

        return null;
    }
}
