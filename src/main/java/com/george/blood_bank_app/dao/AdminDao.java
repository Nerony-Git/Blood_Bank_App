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
    private static final String GET_LAST_ADMIN_ID_SQL = "SELECT admin_id FROM admins ORDER BY admin_id DESC LIMIT 1";
    private static final String ADD_NEW_ADMIN_SQL = "INSERT INTO admins (admin_id, first_name, last_name, other_name, username, gender, dob, contact, email, address, postal_address, blood_group, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String VALIDATE_OLD_PASSWORD_SQL = "SELECT * FROM admins WHERE admin_id = ? AND password = ?";
    private static final String CHANGE_PASSWORD_SQL = "UPDATE admins SET password = ? WHERE admin_id = ?";


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

    public String getLastAdminID() throws SQLException {
        String lastAdminID = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_ADMIN_ID_SQL)){

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastAdminID = resultSet.getString("admin_id");
            }

        }
        return lastAdminID;
    }

    public boolean registerAdmin(Admin admin) throws SQLException {
        boolean u;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ADMIN_SQL)){
            String lastAdminID = getLastAdminID();
            String newAdminID;

            if (lastAdminID == null) {
                newAdminID = "BBA0001"; // Default ID if no records exist
            } else {
                int id = Integer.parseInt(lastAdminID.substring(3)) + 1;
                newAdminID = "BBA" + String.format("%04d", id);
            }

            preparedStatement.setString(1, newAdminID);
            preparedStatement.setString(2, admin.getFirstName());
            preparedStatement.setString(3, admin.getLastName());
            preparedStatement.setString(4, admin.getOtherName());
            preparedStatement.setString(5, admin.getUsername());
            preparedStatement.setString(6, admin.getGender());
            preparedStatement.setDate(7, JDBCUtils.getSQLDate(admin.getDob()));
            preparedStatement.setString(8, admin.getContact());
            preparedStatement.setString(9, admin.getEmail());
            preparedStatement.setString(10, admin.getAddress());
            preparedStatement.setString(11, admin.getPostalAddress());
            preparedStatement.setString(12, admin.getBloodGroup());
            preparedStatement.setString(13, admin.getPassword());

            u = preparedStatement.executeUpdate() > 0;

        }
        return u;
    }

    public boolean validateOldPassword(String adminID, String oldPassword) throws SQLException {
        boolean u = false;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_OLD_PASSWORD_SQL)){
            preparedStatement.setString(1, adminID);
            preparedStatement.setString(2, oldPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                u = true;
            }
        }
        return u;
    }

    public boolean changePassword(String adminID, String newPassword) throws SQLException {
        boolean u = false;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD_SQL)){
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, adminID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                u = true;
            }
        }
        return u;
    }
}
