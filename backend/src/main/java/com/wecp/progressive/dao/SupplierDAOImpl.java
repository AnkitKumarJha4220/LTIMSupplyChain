package com.wecp.progressive.dao;


import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {


    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        int generatedID = -1;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "INSERT INTO supplier (supplier_name, email, phone, username, password, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getSupplierName());
            statement.setString(2, supplier.getEmail());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getUsername());
            statement.setString(5, supplier.getPassword());
            statement.setString(6, supplier.getAddress());
            statement.setString(7, supplier.getRole());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getInt(1);
                supplier.setSupplierId(generatedID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            // Close resources in the reverse order of opening
            if (statement != null) {
                statement.close();
            }
        }
        return generatedID;
    }

    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM supplier WHERE supplier_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, supplierId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String supplierName = resultSet.getString("supplier_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String role = resultSet.getString("role");
                return new Supplier(supplierId, supplierName, email, phone, username, password, address, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "UPDATE supplier SET supplier_name =?, email =?, phone =?, username =?, password =?, address =?, role =? WHERE supplier_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, supplier.getSupplierName());
            statement.setString(2, supplier.getEmail());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getUsername());
            statement.setString(5, supplier.getPassword());
            statement.setString(6, supplier.getAddress());
            statement.setString(7, supplier.getRole());
            statement.setInt(8, supplier.getSupplierId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteSupplier(int supplierId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "DELETE FROM supplier WHERE supplier_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, supplierId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM supplier";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int supplierId = resultSet.getInt("supplier_id");
                String supplierName = resultSet.getString("supplier_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String role = resultSet.getString("role");
                suppliers.add(new Supplier(supplierId, supplierName, email, phone, username, password, address, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return suppliers;
    }
}

