package utils;

import application.exception.InternalServerErrorException;
import application.exception.PropertyNotFoundException;
import dao.DaoManager;
import dao.UserDao;
import dao.exception.DaoException;
import entity.User;

public class DaoManagerTest {
    public static void main(String[] args) throws ClassNotFoundException, PropertyNotFoundException, InternalServerErrorException, DaoException {
        UserDao userDao = DaoManager.getUserDao();

        User user = new User("qwerty", "qwerty");

        user = userDao.loginUser(user);

        System.out.println(user);
    }
}
