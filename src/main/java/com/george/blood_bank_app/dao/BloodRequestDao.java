package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.BloodDonation;
import com.george.blood_bank_app.model.BloodRequest;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BloodRequestDao {

    private static final String GET_LAST_REQUEST_ID_SQL = "SELECT request_id FROM blood_request ORDER BY request_id DESC LIMIT 1";
    private static final String INSERT_NEW_REQUEST_SQL = "INSERT INTO blood_request (request_id, donor_id, blood_group, request_date, comment, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_REQUESTS_BY_DONOR_SQL = "SELECT * FROM blood_request WHERE donor_id = ?";
    private static final String GET_REQUEST_BY_ID_SQL = "SELECT * FROM blood_request WHERE request_id = ?";
    private static final String GET_ALL_REQUESTS_SQL = "SELECT * FROM blood_request ORDER BY request_id ASC";
    private static final String GET_ALL_NEW_REQUESTS_SQL = "SELECT * FROM blood_request WHERE status = ? ORDER BY request_id ASC";
    private static final String UPDATE_REQUEST_BY_ID_SQL = "UPDATE blood_request SET blood_group = ?, request_date = ?, status = ?, response = ? WHERE request_id = ?";


    private UserDao userDao = new UserDao();

    public String getLastRequestID() throws SQLException {
        String lastRequestID = null;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_REQUEST_ID_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastRequestID = resultSet.getString("request_id");
            }
        }
        return lastRequestID;
    }

    public boolean registerRequest(BloodRequest bloodRequest) throws SQLException {
        boolean u;
        String status = "Pending";

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_REQUEST_SQL)){
            String lastRequestID = getLastRequestID();
            String newRequestID;

            if (lastRequestID == null) {
                newRequestID = "BRI0001"; // Default ID if no records exist
            } else {
                int id = Integer.parseInt(lastRequestID.substring(3)) + 1;
                newRequestID = "BRI" + String.format("%04d", id);
            }

            preparedStatement.setString(1, newRequestID);
            preparedStatement.setString(2, bloodRequest.getDonorID());
            preparedStatement.setString(3, bloodRequest.getBloodGroup());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(bloodRequest.getRequestDate()));
            preparedStatement.setString(5, bloodRequest.getComment());
            preparedStatement.setString(6, status);

            u = preparedStatement.executeUpdate() > 0;
        }
        return u;
    }

    public List<BloodRequest> getRequestsByDonor(String donorsID) throws SQLException {
        List<BloodRequest> donorBloodRequests = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_REQUESTS_BY_DONOR_SQL)){
            preparedStatement.setString(1, donorsID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String requestID = resultSet.getString("request_id");
                String donorID = resultSet.getString("donor_id");
                donorID = userDao.getDonorsName(donorID);
                String bloodGroup = resultSet.getString("blood_group");
                LocalDate requestDate = resultSet.getDate("request_date").toLocalDate();
                String comment = resultSet.getString("comment");
                String status = resultSet.getString("status");

                donorBloodRequests.add(new BloodRequest(requestID, donorID, bloodGroup, requestDate, comment, status));

            }
        }
        return donorBloodRequests;
    }

    public BloodRequest getRequestByID(String requestID) throws SQLException {
        BloodRequest bloodRequest = null;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_REQUEST_BY_ID_SQL)){
            preparedStatement.setString(1, requestID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bloodRequest = new BloodRequest();

                bloodRequest.setRequestID(resultSet.getString("request_id"));
                bloodRequest.setDonorID(resultSet.getString("donor_id"));
                bloodRequest.setBloodGroup(resultSet.getString("blood_group"));
                bloodRequest.setRequestDate(resultSet.getDate("request_date").toLocalDate());
                bloodRequest.setComment(resultSet.getString("comment"));
                bloodRequest.setStatus(resultSet.getString("status"));
                bloodRequest.setResponse(resultSet.getString("response"));
            }
        }
        return bloodRequest;
    }

    public List<BloodRequest> getAllRequests() throws SQLException {
        List<BloodRequest> bloodRequests = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REQUESTS_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String requestID = resultSet.getString("request_id");
                String donorID = resultSet.getString("donor_id");
                donorID = userDao.getDonorsName(donorID);
                String bloodGroup = resultSet.getString("blood_group");
                LocalDate requestDate = resultSet.getDate("request_date").toLocalDate();
                String comment = resultSet.getString("comment");
                String status = resultSet.getString("status");

                bloodRequests.add(new BloodRequest(requestID, donorID, bloodGroup, requestDate, comment, status));

            }
        }
        return bloodRequests;
    }

    public List<BloodRequest> getAllNewRequests(String stat) throws SQLException {
        List<BloodRequest> newBloodRequests = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NEW_REQUESTS_SQL)){
            preparedStatement.setString(1, stat);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String requestID = resultSet.getString("request_id");
                String donorID = resultSet.getString("donor_id");
                donorID = userDao.getDonorsName(donorID);
                String bloodGroup = resultSet.getString("blood_group");
                LocalDate requestDate = resultSet.getDate("request_date").toLocalDate();
                String comment = resultSet.getString("comment");
                String status = resultSet.getString("status");

                newBloodRequests.add(new BloodRequest(requestID, donorID, bloodGroup, requestDate, comment, status));

            }
        }
        return newBloodRequests;
    }

    public boolean updateRequest(BloodRequest bloodRequest) throws SQLException {
        boolean u;

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_BY_ID_SQL)){
            preparedStatement.setString(1, bloodRequest.getBloodGroup());
            preparedStatement.setDate(2, JDBCUtils.getSQLDate(bloodRequest.getRequestDate()));
            preparedStatement.setString(3, bloodRequest.getStatus());
            preparedStatement.setString(4, bloodRequest.getResponse());
            preparedStatement.setString(5, bloodRequest.getRequestID());

            u = preparedStatement.executeUpdate() > 0;
        }
        return u;
    }
}
