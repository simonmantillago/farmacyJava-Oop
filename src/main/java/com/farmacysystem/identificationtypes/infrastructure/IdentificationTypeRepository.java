package com.farmacysystem.identificationtypes.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.farmacysystem.identificationtypes.domain.entity.IdentificationType;
import com.farmacysystem.identificationtypes.domain.service.IdentificationTypeService;

public class IdentificationTypeRepository implements IdentificationTypeService {
    private Connection connection;

    public IdentificationTypeRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createIdentificationType(IdentificationType customer) {
        try {
            String query = "INSERT INTO identificationtypes (description) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IdentificationType deleteIdentificationType(String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIdentificationType'");
    }

    @Override
    public List<IdentificationType> findAllIdentificationTypes() {
        String query = "SELECT description FROM identificationtypes";
        List<IdentificationType> lstIdentificationTypes = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    IdentificationType identificationType = new IdentificationType(rs.getString("description"));
                    lstIdentificationTypes.add(identificationType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstIdentificationTypes;
    }
}
