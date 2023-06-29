package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.Admin;
import com.george.blood_bank_app.model.User;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String DONOR_LOGIN_SQL = "SELECT * FROM donors WHERE username = ? AND password = ?";
    private static final String DONOR_UPDATE_PROFILE_SQL = "UPDATE donors SET first_name = ?, last_name = ?, other_name = ?, gender = ?, dob = ?, contact = ?, email = ?, address = ?, postal_address = ?, blood_group = ? WHERE donor_id = ?";
    private static final String GET_DONOR_BY_ID_SQL = "SELECT * FROM donors WHERE donor_id = ?";
    private static final String GET_LAST_DONOR_ID_SQL = "SELECT donor_id FROM donors ORDER BY donor_id DESC LIMIT 1";
    private static final String ADD_NEW_DONOR_SQL = "INSERT INTO donors (donor_id, first_name, last_name, other_name, username, gender, dob, contact, email, address, postal_address, blood_group, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String VALIDATE_OLD_PASSWORD_SQL = "SELECT * FROM donors WHERE donor_id = ? AND password = ?";
    private static final String CHANGE_PASSWORD_SQL = "UPDATE donors SET password = ? WHERE donor_id = ?";
    private static final String GET_ALL_DONORS_SQL = "SELECT * FROM donors ORDER BY donor_id ASC";
    private static final String GET_DONOR_NAME_SQL = "SELECT first_name, last_name, other_name FROM donors WHERE donor_id = ?";
    private static final String DELETE_DONOR_BY_ID_SQL = "UPDATE donors SET deleted = ? WHERE donor_id = ?";


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

    public boolean updateUser(User user) throws SQLException {
        boolean u;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DONOR_UPDATE_PROFILE_SQL)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getOtherName());
            preparedStatement.setString(4, user.getGender());
            preparedStatement.setDate(5, JDBCUtils.getSQLDate(user.getDob()));
            preparedStatement.setString(6, user.getContact());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setString(9, user.getPostalAddress());
            preparedStatement.setString(10, user.getBloodGroup());
            preparedStatement.setString(11, user.getDonorID());

            u = preparedStatement.executeUpdate() > 0;

        }

        return u;
    }

    public User getDonorByID(String donorID) throws SQLException {
        User user = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_DONOR_BY_ID_SQL)){
            preparedStatement.setString(1, donorID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
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
                user.setBloodGroup(resultSet.getString("blood_group"));

            }
        } 
        return user;
    }

    public String getLastDonorID() throws SQLException {
        String lastDonorID = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_DONOR_ID_SQL)){

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastDonorID = resultSet.getString("donor_id");
            }

        }
        return lastDonorID;
    }

    public boolean registerDonor(User user) throws SQLException {
        boolean u;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_DONOR_SQL)){
            String lastDonorID = getLastDonorID();
            String newDonorID;

            if (lastDonorID == null) {
                newDonorID = "BBD0001"; // Default ID if no records exist
            } else {
                int id = Integer.parseInt(lastDonorID.substring(3)) + 1;
                newDonorID = "BBD" + String.format("%04d", id);
            }

            preparedStatement.setString(1, newDonorID);
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getOtherName());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setDate(7, JDBCUtils.getSQLDate(user.getDob()));
            preparedStatement.setString(8, user.getContact());
            preparedStatement.setString(9, user.getEmail());
            preparedStatement.setString(10, user.getAddress());
            preparedStatement.setString(11, user.getPostalAddress());
            preparedStatement.setString(12, user.getBloodGroup());
            preparedStatement.setString(13, user.getPassword());

            u = preparedStatement.executeUpdate() > 0;

        }
        return u;
    }

    public boolean validateOldPassword(String donorID, String oldPassword) throws SQLException {
        boolean u = false;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_OLD_PASSWORD_SQL)){
            preparedStatement.setString(1, donorID);
            preparedStatement.setString(2, oldPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                u = true;
            }
        }
        return u;
    }

    public boolean changePassword(String donorID, String newPassword) throws SQLException {
        boolean u = false;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD_SQL)){
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, donorID);

            preparedStatement.executeUpdate();
            u = true;

        }
        return u;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> allDonors = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DONORS_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String donorID = resultSet.getString("donor_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String otherName = resultSet.getString("other_name");
                String username = resultSet.getString("username");
                String gender = resultSet.getString("gender");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String contact = resultSet.getString("contact");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String postalAddress = resultSet.getString("postal_address");
                String bloodGroup = resultSet.getString("blood_group");

                allDonors.add(new User(donorID, firstName, lastName, otherName, username, gender, dob, contact, email, address, postalAddress, bloodGroup));
            }

        }
        return allDonors;
    }

    public String getDonorsName(String donorID) throws SQLException {
        String donorsName = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_DONOR_NAME_SQL)){
            preparedStatement.setString(1, donorID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String otherName = resultSet.getString("other_name");

                donorsName = firstName + " " + otherName + " " + lastName;
            }
        }
        return donorsName;
    }

    public boolean deleteDonorByID(String donorID) throws SQLException {
        boolean u;
        String status = "Yes";

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DONOR_BY_ID_SQL)){
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, donorID);

            u = preparedStatement.executeUpdate() > 0;
        }
        return u;
    }

}
