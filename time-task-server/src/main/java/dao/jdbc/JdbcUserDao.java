package dao.jdbc;

import application.exception.InternalServerErrorException;
import dao.DaoManager;
import dao.TaskDao;
import dao.UserDao;
import dao.exception.*;
import entity.Task;
import entity.User;

import java.sql.*;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final String SELECT_USER_BY_LOGIN_SQL = "SELECT id, login, password, taskListVersion FROM Users WHERE login=?";
    private static final String INSERT_USER_SQL = "INSERT INTO Users (login, password) VALUES (?, ?)";
    private static final String UPDATE_USER_SQL = "UPDATE Users SET password=?, taskListVersion=? WHERE id=?";


    @Override
    public User createUser(User user) throws InternalServerErrorException, DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {

            PreparedStatement selectUserPreparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_SQL);
            selectUserPreparedStatement.setString(1, user.getLogin());

            ResultSet resultSet = selectUserPreparedStatement.executeQuery();
            if (resultSet.next()) {
                return null;
            }

            PreparedStatement insertUserPreparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            insertUserPreparedStatement.setString(1, user.getLogin());
            insertUserPreparedStatement.setString(2, user.getPassword());

            if (insertUserPreparedStatement.executeUpdate() != 1) {
                throw new DaoException("Can't save user to database");
            }

            resultSet = selectUserPreparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new DaoException("Can't get created user from database");
            }

            User createdUser = new User(resultSet.getLong("id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getLong("taskListVersion"));
            return createdUser;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Connection with database failed", e);
        }
    }

    @Override
    public User loginUser(User user) throws InternalServerErrorException, DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {

            PreparedStatement selectUserPreparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_SQL);
            selectUserPreparedStatement.setString(1, user.getLogin());

            ResultSet resultSet = selectUserPreparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            String password = resultSet.getString("password");
            if (!user.getPassword().equals(password)) {
                return null;
            }

            User persistedUser = new User(resultSet.getLong("id"), resultSet.getString("login"), password, resultSet.getLong("taskListVersion"));

//            if (persistedUser.getTaskListVersion() == user.getTaskListVersion()) {
//                return persistedUser;
//            }

            TaskDao taskDao = DaoManager.getTaskDao();
            List<Task> taskList = taskDao.getTaskListByUserId(persistedUser.getId());
            persistedUser.setTaskList(taskList);
            return persistedUser;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Connection with database failed", e);
        }
    }

    @Override
    public User updateUser(User user) throws InternalServerErrorException, DaoException {
        try (Connection connection = ConnectionManager.getConnection()){

            TaskDao taskDao = DaoManager.getTaskDao();
            List<Task> updatedTaskList = taskDao.updateTaskList(user.getTaskList());

            PreparedStatement updateUserPreparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            updateUserPreparedStatement.setString(1, user.getPassword());
            updateUserPreparedStatement.setLong(2, user.getTaskListVersion());
            updateUserPreparedStatement.setLong(3, user.getId());

            if (updateUserPreparedStatement.executeUpdate() != 1) {
                throw new DaoException("User update failed");
            }

            user.setTaskList(updatedTaskList);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Connection with database failed", e);
        }
    }
}
