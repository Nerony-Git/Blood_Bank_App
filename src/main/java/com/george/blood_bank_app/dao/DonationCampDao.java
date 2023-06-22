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

    private static final String GET_ALL_CAMPS_SQL = "SELECT * FROM donation_camp ORDER BY camp_name ASC";


    public List<DonationCamp> getAllCamps() throws SQLException {
        List<DonationCamp> donationCamps = new ArrayList<>();

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

}
