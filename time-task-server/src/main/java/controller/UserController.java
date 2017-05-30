package controller;

import application.exception.InternalServerErrorException;
import dao.DaoManager;
import dao.UserDao;
import dao.exception.DaoException;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController {

    private static final Logger infoLogger = LogManager.getLogger("infoLogger");
    private static final Logger errorLogger = LogManager.getLogger("errorLogger");

    public User create(User user) throws InternalServerErrorException, DaoException {
        UserDao userDao = null;
        try {
            userDao = DaoManager.getUserDao();
            user = userDao.createUser(user);
            if (user != null)
                infoLogger.info(user.getLogin() + " create successful");
            return user;
        } catch (DaoException | InternalServerErrorException e) {
            errorLogger.error(e.getStackTrace(), e);
            throw e;
        }
    }

    public User login(User user) throws InternalServerErrorException, DaoException {
        UserDao userDao = null;
        try {
            userDao = DaoManager.getUserDao();
            user = userDao.loginUser(user);
            if (user != null)
                infoLogger.info(user.getLogin() + " login successful");
            return user;
        } catch (DaoException | InternalServerErrorException e) {
            errorLogger.error(e.getStackTrace(), e);
            throw e;
        }
    }

    public User update(User user) throws InternalServerErrorException, DaoException {
        UserDao userDao = null;
        try {
            userDao = DaoManager.getUserDao();
            if (user != null)
                infoLogger.info(user.getLogin() + " update successful");
            return userDao.updateUser(user);
        } catch (InternalServerErrorException | DaoException e) {
            errorLogger.error(e.getStackTrace(), e);
            throw e;
        }
    }
}
