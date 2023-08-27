package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.DonationCamp;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationCampDao {

    private static final String GET_ALL_CAMPS_SQL = "SELECT * FROM donation_camp WHERE (deleted IS NULL OR deleted = '')  ORDER BY camp_name ASC";
    private static final String GET_CAMP_NAME_SQL = "SELECT camp_name FROM donation_camp WHERE camp_id = ?";
    private static final String INSERT_CAMP_SQL = "INSERT INTO donation_camp (camp_id, camp_name, organizers, address, postal_address, details) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_LAST_CAMP_ID_SQL = "SELECT camp_id FROM donation_camp ORDER BY camp_id DESC LIMIT 1";
    private static final String GET_CAMP_BY_ID_SQL = "SELECT * FROM donation_camp WHERE camp_id = ?";
    private static final String UPDATE_CAMP_SQL = "UPDATE donation_camp SET camp_name = ?, organizers = ?, address = ?, postal_address = ?, details = ? WHERE camp_id = ?";
    private static final String DELETE_CAMP_BY_ID_SQL = "UPDATE donation_camp SET deleted = ? WHERE camp_id = ?";


    public List<DonationCamp> getAllCamps() throws SQLException {
        List<DonationCamp> donationCamps = new ArrayList<>();
        String status = "";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CAMPS_SQL)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String campID = resultSet.getString("camp_id");
                String campName = resultSet.getString("camp_name");
                String organizers = resultSet.getString("organizers");
                String address = resultSet.getString("address");
                String postalAddress = resultSet.getString("postal_address");
                String details = resultSet.getString("details");

                donationCamps.add(new DonationCamp(campID, campName, organizers, address, postalAddress, details));
            }

        }
        return donationCamps;
    }

    public String getCampName(String campID) throws SQLException {
        String campName = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CAMP_NAME_SQL)){
            preparedStatement.setString(1, campID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                campName = resultSet.getString("camp_name");
            }
        }
        return campName;
    }

    public String getLastCampID() throws SQLException {
        String lastCampID = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_CAMP_ID_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                lastCampID = resultSet.getString("camp_id");
            }
        }
        return lastCampID;
    }

    public boolean newCamp(DonationCamp donationCamp) throws SQLException {
        boolean u;
        String lastCampID = getLastCampID();
        String newCampID;

        if (lastCampID == null) {
            newCampID = "BDC0001"; // Default ID if no records exist
        } else {
            int id = Integer.parseInt(lastCampID.substring(3)) + 1;
            newCampID = "BDC" + String.format("%04d", id);
        }

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAMP_SQL)){
            preparedStatement.setString(1, newCampID);
            preparedStatement.setString(2, donationCamp.getCampName());
            preparedStatement.setString(3, donationCamp.getOrganizers());
            preparedStatement.setString(4, donationCamp.getAddress());
            preparedStatement.setString(5, donationCamp.getPostal_address());
            preparedStatement.setString(6, donationCamp.getDetails());

            u = preparedStatement.executeUpdate() > 0;
        }
        return u;
    }

    public DonationCamp getDonationCampByID(String donationCampID) throws SQLException {
        DonationCamp donationCamp = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CAMP_BY_ID_SQL)){
            preparedStatement.setString(1, donationCampID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                donationCamp = new DonationCamp();

                donationCamp.setCampID(resultSet.getString("camp_id"));
                donationCamp.setCampName(resultSet.getString("camp_name"));
                donationCamp.setOrganizers(resultSet.getString("organizers"));
                donationCamp.setAddress(resultSet.getString("address"));
                donationCamp.setPostal_address(resultSet.getString("postal_address"));
                donationCamp.setDetails(resultSet.getString("details"));

            }
        }
        return donationCamp;
    }

    public boolean updateDonationCamp(DonationCamp donationCamp) throws SQLException {
        boolean u;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAMP_SQL)){
            preparedStatement.setString(1, donationCamp.getCampName());
            preparedStatement.setString(2, donationCamp.getOrganizers());
            preparedStatement.setString(3, donationCamp.getAddress());
            preparedStatement.setString(4, donationCamp.getPostal_address());
            preparedStatement.setString(5, donationCamp.getDetails());
            preparedStatement.setString(6, donationCamp.getCampID());

            u = preparedStatement.executeUpdate() > 0;
        }
        return u;
    }

    public boolean deleteDonationCamp(String campID) throws SQLException {
        boolean u;
        String status = "Yes";

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAMP_BY_ID_SQL)){
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, campID);

            u = preparedStatement.executeUpdate() > 0;
        }
        return u;
    }

}
