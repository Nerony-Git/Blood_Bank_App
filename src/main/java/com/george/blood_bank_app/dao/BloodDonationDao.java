package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.BloodDonation;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BloodDonationDao {
    private static final String INSERT_NEW_DONATION_SQL = "INSERT INTO blood_donation (donation_id, donor_id, camp, donation_date, blood_units, comments) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_LAST_DONATION_ID_SQL = "SELECT donation_id FROM blood_donation ORDER BY donation_id DESC LIMIT 1";
    private static final String GET_DONATIONS_BY_DONOR_SQL = "SELECT * FROM blood_donation WHERE donor_id = ?";
    private static final String GET_DONATIONS_BY_ID_SQL = "SELECT * FROM blood_donation WHERE donation_id = ?";


    private UserDao userDao = new UserDao();

    public String getLastDonationID() throws SQLException {
        String lastDonationID = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_DONATION_ID_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastDonationID = resultSet.getString("donation_id");
            }
        }
        return lastDonationID;
    }

    public boolean registerDonation(BloodDonation bloodDonation) throws SQLException {
        boolean u;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_DONATION_SQL)){
            String lastDonationID = getLastDonationID();
            String newDonationID;

            if (lastDonationID == null) {
                newDonationID = "BDI0001"; // Default ID if no records exist
            } else {
                int id = Integer.parseInt(lastDonationID.substring(3)) + 1;
                newDonationID = "BDI" + String.format("%04d", id);
            }

            preparedStatement.setString(1, newDonationID);
            preparedStatement.setString(2, bloodDonation.getDonorID());
            preparedStatement.setString(3, bloodDonation.getCamp());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(bloodDonation.getDonationDate()));
            preparedStatement.setInt(5, bloodDonation.getBloodUnit());
            preparedStatement.setString(6, bloodDonation.getComment());

            u = preparedStatement.executeUpdate() > 0;
        }
        return u;
    }

    public List<BloodDonation> getDonationsByDonor(String donorsID) throws SQLException {
        List<BloodDonation> donorBloodDonations = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_DONATIONS_BY_DONOR_SQL)){
            preparedStatement.setString(1, donorsID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String donationID = resultSet.getString("donation_id");
                String donorID = resultSet.getString("donor_id");
                donorID = userDao.getDonorsName(donorID);
                String donationCamp = resultSet.getString("camp");
                LocalDate donationDate = resultSet.getDate("donation_date").toLocalDate();
                int bloodUnits = resultSet.getInt("blood_units");
                String comment = resultSet.getString("comments");

                donorBloodDonations.add(new BloodDonation(donationID, donorID, donationCamp, donationDate, bloodUnits, comment));
            }
        }
        return donorBloodDonations;
    }

    public BloodDonation getDonationByID(String donationID) throws SQLException {
        BloodDonation bloodDonation = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_DONATIONS_BY_ID_SQL)){
            preparedStatement.setString(1, donationID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bloodDonation = new BloodDonation();

                bloodDonation.setDonationID(resultSet.getString("donation_id"));
                bloodDonation.setDonorID(resultSet.getString("donor_id"));
                bloodDonation.setCamp(resultSet.getString("camp"));
                bloodDonation.setDonationDate(resultSet.getDate("donation_date").toLocalDate());
                bloodDonation.setBloodUnit(resultSet.getInt("blood_units"));
                bloodDonation.setComment(resultSet.getString("comments"));

            }
        }

        return bloodDonation;
    }
}
