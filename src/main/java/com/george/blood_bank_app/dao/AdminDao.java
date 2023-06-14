package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.Admin;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    private static final String ADMIN_LOGIN_SQL = "SELECT * FROM admins WHERE username = ? AND password = ?";
    private static final String ADMIN_UPDATE_PROFILE_SQL = "UPDATE admins SET first_name = ?, last_name = ?, other_name = ?, gender = ?, dob = ?, contact = ?, email = ?, address = ?, postal_address = ?, blood_group = ? WHERE admin_id = ?";
    private static final String GET_ADMIN_BY_ID_SQL = "SELECT * FROM admins WHERE admin_id = ?";


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

    public boolean updateAdmin(Admin admin) throws SQLException {
        boolean u;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_UPDATE_PROFILE_SQL)){
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getOtherName());
            preparedStatement.setString(4, admin.getGender());
            preparedStatement.setDate(5, JDBCUtils.getSQLDate(admin.getDob()));
            preparedStatement.setString(6, admin.getContact());
            preparedStatement.setString(7, admin.getEmail());
            preparedStatement.setString(8, admin.getAddress());
            preparedStatement.setString(9, admin.getPostalAddress());
            preparedStatement.setString(10, admin.getBloodGroup());
            preparedStatement.setString(11, admin.getAdminID());

            u = preparedStatement.executeUpdate() > 0;

        }

        return u;
    }

    public Admin getAdminByID(String adminID) throws SQLException {
        Admin admin = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_BY_ID_SQL)){
            preparedStatement.setString(1, adminID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
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
        } 
        return admin;
    }
}
