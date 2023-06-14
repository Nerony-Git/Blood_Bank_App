package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.User;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static final String DONOR_LOGIN_SQL = "SELECT * FROM donors WHERE username = ? AND password = ?";


    public User validateUser(String username, String password) throws SQLException {
        User user = null;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DONOR_LOGIN_SQL)){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setDonorID(resultSet.getString("donor_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setOtherName(resultSet.getString("other_name"));
                user.setUsername(resultSet.getString("username"));
                user.setGender(resultSet.getString("gender"));
                user.setDob(resultSet.getDate("dob").toLocalDate());
                user.setContact(resultSet.getString("contact"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setPostalAddress(resultSet.getString("postal_address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
