package controller;

import dao.DaoManager;
import dao.UserDao;
import application.exception.InternalServerErrorException;
import dao.exception.DaoException;
import entity.User;

public class CreateUserController {

    public User createUser(User user) {
        UserDao userDao = null;
        try {
            userDao = DaoManager.getUserDao();
        } catch (InternalServerErrorException e) {
            e.printStackTrace();
            return null;
        }

        try {
            user = userDao.createUser(user);
            return user;
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (InternalServerErrorException e) {
            e.printStackTrace();
        }

        return null;

    }
}
