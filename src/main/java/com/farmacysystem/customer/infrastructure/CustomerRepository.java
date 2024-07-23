package com.farmacysystem.customer.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.customer.domain.entity.CustomerDto;
import com.farmacysystem.customer.domain.service.CustomeService;
import com.farmacysystem.customer.domain.service.CustomeService.CustomerService;

public class CustomerRepository implements CustomerService {
    private Connection connection;

    public CustomerRepository() {
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
    public void createCustomer(Customer customer) {
        try {
            String query = "INSERT INTO customers (identificationNumber,typeID,firstName,lastName,age,birthDate,cityID,neighborhoodID) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getIdentificationNumber());
            ps.setString(2, customer.getTypeID());
            ps.setString(3, customer.getFirstName());
            ps.setString(4, customer.getLastName());
            ps.setInt(5, customer.getAge());
            ps.setString(6, customer.getBirthDate());
            ps.setInt(7, customer.getCityID());
            ps.setInt(8, customer.getNeighborhoodID());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("Customer addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
        public void updateCustomer(Customer customer) {
            String query = "UPDATE customers SET typeID = ?, firstName = ?, lastName = ?, age = ?, birthDate = ?, cityID = ?, neighborhoodID = ? WHERE identificationNumber = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, customer.getTypeID());
                ps.setString(2, customer.getFirstName());
                ps.setString(3, customer.getLastName());
                ps.setInt(4, customer.getAge());
                ps.setString(5, customer.getBirthDate());
                ps.setInt(6, customer.getCityID());
                ps.setInt(7, customer.getNeighborhoodID());
                ps.setString(8, customer.getIdentificationNumber());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer updated successfully!");
                } else {
                    System.out.println("Customer update failed!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Customer deleteCustomer(String id) {
            Customer customer = null;
            String selectQuery = "SELECT * FROM customers WHERE identificationNumber = ?";
            String deleteQuery = "DELETE FROM customers WHERE identificationNumber = ?";
            
            try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
                PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
                
                // First, fetch the customer
                selectPs.setString(1, id);
                try (ResultSet rs = selectPs.executeQuery()) {
                    if (rs.next()) {
                        customer = new Customer(
                            rs.getString("identificationNumber"),
                            rs.getString("typeID"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getString("birthDate"),
                            rs.getInt("cityID"),
                            rs.getInt("neighborhoodID"),
                            rs.getString("registrationDate")
                        );
                    }
                }
                
                // If customer exists, delete it
                if (customer != null) {
                    deletePs.setString(1, id);
                    int rowsAffected = deletePs.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Customer deleted successfully!");
                        return customer;
                    }
                }
                
                System.out.println("Customer deletion failed. Customer not found.");
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    @Override
        public Optional<CustomerDto> findCustomerById(String id) {
            String query = "SELECT c.identificationNumber, c.typeID, c.firstName, c.lastName, c.age, c.birthDate,c.cityID,c.neighborhoodID," +
                        "ci.cityName, n.neighborhoodName " +
                        "FROM customers c " +
                        "JOIN cities ci ON c.cityID = ci.cityID " +
                        "JOIN neighborhoods n ON c.neighborhoodID = n.neighborhoodID " +
                        "WHERE c.identificationNumber = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        CustomerDto customerDto = new CustomerDto(
                            rs.getString("identificationNumber"),
                            rs.getString("typeID"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getString("birthDate"),
                            rs.getInt("cityID"),
                            rs.getInt("neighborhoodID"),
                            rs.getString("cityName"),
                            rs.getString("neighborhoodName")
                        );
                        return Optional.of(customerDto);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }


    @Override
    public List<CustomerDto> findAllCustomer() {
        List<CustomerDto> customers = new ArrayList<>();
        String query = "SELECT c.identificationNumber, c.typeID, c.firstName, c.lastName, c.age, c.birthDate, c.cityID, c.neighborhoodID, " +
                    "ci.cityName, n.neighborhoodName " +
                    "FROM customers c " +
                    "JOIN cities ci ON c.cityID = ci.cityID " +
                    "JOIN neighborhoods n ON c.neighborhoodID = n.neighborhoodID";
        
        try (PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                CustomerDto customerDto = new CustomerDto(
                    rs.getString("identificationNumber"),
                    rs.getString("typeID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("age"),
                    rs.getString("birthDate"),
                    rs.getInt("cityID"),
                    rs.getInt("neighborhoodID"),
                    rs.getString("cityName"),
                    rs.getString("neighborhoodName")
                );
                customers.add(customerDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
}
