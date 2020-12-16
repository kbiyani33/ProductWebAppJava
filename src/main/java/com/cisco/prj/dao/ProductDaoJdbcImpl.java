/**
 * 
 */
package com.cisco.prj.dao;

/**
 * @author kbiyani
 *
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.cisco.prj.entity.Product;

public class ProductDaoJdbcImpl implements ProductDao {
 
        @Override
        public List<Product> getProducts() throws DaoException {
                String SQL = "SELECT id, name, price, quantity FROM products";
                List<Product> products = new ArrayList<Product>();
                Connection con = null;
                try {
                        con = DBUtil.getConnection();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(SQL);
//                        ResultSetMetaData rsmd = rs.getMetaData();
//                        rsmd.getColumnCount();
//                        rsmd.getColumnName(1);
                        while(rs.next()) {
                                products.add(new Product(rs.getInt("id"), 
                                                rs.getString("name"), rs.getDouble("price"),
                                                rs.getInt("quantity")));
                        }
                } catch (SQLException e) {
                         System.out.println(e.getErrorCode());
                        throw new DaoException("unable to get products", e);
                } finally {
                        DBUtil.closeConnection(con);
                }
                
                return products;
        }

        @Override
        public void addProduct(Product p) throws DaoException {
                String SQL = "INSERT INTO products (id, name, price, quantity) VALUES (0, ?, ?, ?)";
                Connection con = null;
                try {
                        con = DBUtil.getConnection();
                        PreparedStatement ps = con.prepareStatement(SQL);
                        ps.setString(1, p.getName());
                        ps.setDouble(2, p.getPrice());
                        ps.setInt(3, p.getQuantity());
                        ps.executeUpdate(); // INSERT, DELETE, UPDATE
                } catch (SQLException e) {
                        throw new DaoException("unable to add product " + e.getErrorCode() , e);
                } finally {
                        DBUtil.closeConnection(con);
                }
        }

        @Override
        public Product getProduct(int id) throws DaoException {
                Product p = null;
                String SQL = "SELECT id, name, price, quantity FROM products WHERE id = ?";
                Connection con = null;
                try {
                        con = DBUtil.getConnection();
                        PreparedStatement ps = con.prepareStatement(SQL);
                        ps.setInt(1, id);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()) {
                                p = new Product(rs.getInt("id"), 
                                                rs.getString("name"), rs.getDouble("price"),
                                                rs.getInt("quantity"));
                        }
                } catch (SQLException e) {
                        throw new DaoException("unable to get products", e);
                } finally {
                        DBUtil.closeConnection(con);
                }
                return p;
        }
}