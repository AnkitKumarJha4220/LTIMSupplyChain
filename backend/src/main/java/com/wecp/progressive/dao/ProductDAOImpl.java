package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public int addProduct(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        int generatedID = -1;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "INSERT INTO product (warehouse_id, product_name, product_description, quantity, price) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, product.getWarehouse().getWarehouseId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductDescription());
            statement.setInt(4, product.getQuantity());
            statement.setDouble(5, product.getPrice());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getInt(1);
                product.setProductId(generatedID);
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
    public Product getProductById(int productId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM product WHERE product_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int warehouseId = resultSet.getInt("warehouse_id");
                String productName = resultSet.getString("product_name");
                String productDescription = resultSet.getString("product_description");
                int quantity = resultSet.getInt("quantity");
                Long price = (long) resultSet.getDouble("price");
                return new Product(productId, warehouseId, productName, productDescription, quantity, price);
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
    public void updateProduct(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "UPDATE product SET warehouse_id = ?, product_name = ?, product_description = ?, quantity =?, price =? WHERE product_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getWarehouse().getWarehouseId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductDescription());
            statement.setInt(4, product.getQuantity());
            statement.setDouble(5, product.getPrice());
            statement.setInt(6, product.getProductId());
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
    public void deleteProduct(int productId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "DELETE FROM product WHERE product_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
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
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM product";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int warehouseId = resultSet.getInt("warehouse_id");
                String productName = resultSet.getString("product_name");
                String productDescription = resultSet.getString("product_description");
                int quantity = resultSet.getInt("quantity");
                Long price = (long) resultSet.getDouble("price");
                products.add(new Product(productId, warehouseId, productName, productDescription, quantity, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return products;
    }
}
