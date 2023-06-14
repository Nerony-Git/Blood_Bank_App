package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.Admin;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    private static final String ADMIN_LOGIN_SQL = "SELECT * FROM admins WHERE username = ? AND password = ?";


    public Admin validateAdmin(String username, String password) throws SQLException {
        Admin admin = null;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_LOGIN_SQL)){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                admin = new Admin();
                admin.setAdminID(resultSet.getString("admin_id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setOtherName(resultSet.getString("other_name"));
                admin.setUsername(resultSet.getString("username"));
                admin.setGender(resultSet.getString("gender"));
                admin.setDob(resultSet.getDate("dob").toLocalDate());
                admin.setContact(resultSet.getString("contact"));
                admin.setEmail(resultSet.getString("email"));
                admin.setAddress(resultSet.getString("address"));
                admin.setPostalAddress(resultSet.getString("postal_address"));
                admin.setBloodGroup(resultSet.getString("blood_group"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}
