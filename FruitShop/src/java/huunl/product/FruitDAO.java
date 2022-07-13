/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.product;

import huunl.users.UserDTO;
import huunl.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FruitDAO {
    public List<FruitDTO> getListFruit(String search) throws SQLException {
        List<FruitDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT productID, productName, image, price, quantity, categoryID, importDate, usingDate "
                        + " FROM Product "
                        + " WHERE productName like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image").trim();
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    
                    list.add(new FruitDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteFruit(String productID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DELETE Product "
                        + " WHERE productID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                int value = stm.executeUpdate();
                result = value > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public boolean updateFruit(FruitDTO fruit) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                String sql = " UPDATE Product "
                        + " SET productName=?, price=?, quantity=?, categoryID=?, importDate=?, usingDate=? "
                        + " WHERE productID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, fruit.getProductName());
                stm.setDouble(2, fruit.getPrice());
                stm.setInt(3, fruit.getQuantity());
                stm.setString(4, fruit.getCategoryID());
                stm.setString(5,  fruit.getImportDate());
                stm.setString(6,  fruit.getUsingDate());          
                stm.setString(7, fruit.getProductID());          
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        }finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicateProductID(String productID) throws SQLException {
        boolean check= false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT productID "
                        + "FROM Product "
                        + "WHERE productID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally {
            if(rs !=null) rs.close();
            if(stm !=null) stm.close();
            if(conn !=null) conn.close();
        }
        return check;
    }
    
    public boolean insertProduct(FruitDTO fruit) throws SQLException {
        boolean check = false;
        Connection conn =null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "INSERT INTO Product(productID, productName, price, quantity, categoryID, importDate, usingDate) "
                        + " VALUES(?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                    stm.setString(1, fruit.getProductID());
                    stm.setString(2, fruit.getProductName());
                    stm.setDouble(3, fruit.getPrice());
                    stm.setInt(4, fruit.getQuantity());
                    stm.setString(5, fruit.getCategoryID());
                    stm.setString(6,  fruit.getImportDate());
                    stm.setString(7,  fruit.getUsingDate());
                    check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
        }finally {
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicateFruit(String productID) throws SQLException {
        boolean check= false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT productID "
                        + "FROM Product "
                        + "WHERE productID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally {
            if(rs !=null) rs.close();
            if(stm !=null) stm.close();
            if(conn !=null) conn.close();
        }
        return check;
    }
    
    public boolean insertFruit(FruitDTO fruit) throws SQLException {
        boolean check = false;
        Connection conn =null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "INSERT INTO Product(productID, productName, price, quantity, categoryID, importDate, usingDate, image) "
                        + " VALUES(?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                    stm.setString(1, fruit.getProductID());
                    stm.setString(2, fruit.getProductName());                   
                    stm.setDouble(3, fruit.getPrice());
                    stm.setInt(4, fruit.getQuantity());
                    stm.setString(5, fruit.getCategoryID());
                    stm.setString(6, fruit.getImportDate());
                    stm.setString(7, fruit.getUsingDate());
                    stm.setString(8, fruit.getImage());
                    check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
        }finally {
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
    
    public boolean checkQuantity(String productID, int quantity) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs =null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "SELECT quantity "
                        + "FROM Product "
                        + "WHERE productID=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                
                if(rs.next()){
                    
                    int Quantity = rs.getInt("quantity");
                    if(Quantity>=quantity)
                        check = true;
                }
                
            }
        } catch (Exception e) {
        }finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }       
        return check;
    }
    
    public int getQuantityFromDB(String productID) throws SQLException{
        int quantityDB = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs =null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "SELECT quantity "
                        + "FROM Product "
                        + "WHERE productID=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                
                if(rs.next()){
                    
                    quantityDB = rs.getInt("quantity");
                    
                }
                
            }
        } catch (Exception e) {
        }finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }       
        return quantityDB;
    }
    
    public boolean updateQuantity(String productID,int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                String sql = " UPDATE Product "
                        + " SET quantity=? "
                        + " WHERE productID=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, productID);
                          
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        }finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    
}
