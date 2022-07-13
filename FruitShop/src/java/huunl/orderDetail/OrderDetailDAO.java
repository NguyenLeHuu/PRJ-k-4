/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.orderDetail;

import huunl.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO {
    public boolean checkDuplicate(int detailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT detailID "
                        + "FROM OrderDetail "
                        + "WHERE detailID=? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, detailID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }
    
    public boolean createOrderDetail(OrderDetailDTO orderDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO OrderDetail(detailID, price, quantity, orderID, productID) "
                        + " VALUES(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, orderDetail.getDetailID());
                stm.setDouble(2, orderDetail.getPrice());
                stm.setInt(3, orderDetail.getQuantity());
                stm.setInt(4, orderDetail.getOrderID());
                stm.setString(5, orderDetail.getProductID());
                check = stm.executeUpdate() > 0;
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
        return check;
    }
}
