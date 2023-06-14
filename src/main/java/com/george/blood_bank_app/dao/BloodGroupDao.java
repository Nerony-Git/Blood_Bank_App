package com.george.blood_bank_app.dao;

import com.george.blood_bank_app.model.BloodGroup;
import com.george.blood_bank_app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BloodGroupDao {
    
    private static final String GET_ALL_BLOOD_GROUPS = "SELECT * FROM blood_group ORDER BY blood_group_name ASC";


    public List<BloodGroup> getAllBloodGroups(){

        List<BloodGroup> bloodGroups = new ArrayList<BloodGroup>();

        try (Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BLOOD_GROUPS)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String bloodGroupName = resultSet.getString("blood_group_name");

                bloodGroups.add(new BloodGroup(bloodGroupName));
            }

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return bloodGroups;
    }

}
