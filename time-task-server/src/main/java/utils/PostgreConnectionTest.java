package utils;

import application.ApplicationContext;
import application.exception.InternalServerErrorException;
import dao.jdbc.ConnectionManager;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreConnectionTest {
    public static void main(String[] args) throws SQLException, InternalServerErrorException {

        ApplicationContext.init();

        Connection connection = ConnectionManager.getConnection();

        PreparedStatement insertPreparedStatement = connection.prepareStatement("insert into users (user_id, user_name, user_password) values (nextval('seq_users'), ?, ?)");
        insertPreparedStatement.setString(1, "testUserName");
        insertPreparedStatement.setString(2, "testUserPassword");

        insertPreparedStatement.executeUpdate();

        PreparedStatement selectPreparedStatement = connection.prepareStatement("select user_id, user_name, user_password from users where user_name = ?");
        selectPreparedStatement.setString(1, "testUserName");

        ResultSet resultSet = selectPreparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println(new User(resultSet.getLong("user_id"), resultSet.getString("user_name"), resultSet.getString("user_password"), 12));
        }
    }
}
