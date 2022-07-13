/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.users;

import huunl.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT fullName, roleID, gmail "
                        + " FROM Users "
                        + " WHERE userID=? AND password=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String gmail = rs.getString("gmail");
                    user = new UserDTO(userID, fullName, roleID, "", "", "", "", gmail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID, fullName, roleID,address,birthday,phone,gmail "
                        + " FROM Users "
                        + " WHERE fullName like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    String address = rs.getString("address");
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    String gmail = rs.getString("gmail");
                    list.add(new UserDTO(userID, fullName, roleID, password, address, birthday, phone, gmail));
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

    public boolean deleteUser(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DELETE Users "
                        + " WHERE userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
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
    
    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                String sql = " UPDATE Users "
                        + " SET fullName=?, roleID=?, address=?, birthday=?, phone=?, gmail=? "
                        + " WHERE userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getFullName());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getAddress());
                stm.setString(4, user.getBirthday());
                stm.setString(5, user.getPhone());
                stm.setString(6, user.getGmail());
                stm.setString(7, user.getUserID());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        }finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check= false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                String sql = "SELECT userID "
                        + "FROM Users "
                        + "WHERE userID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
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
    
    public boolean insert(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn =null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "INSERT INTO Users(userID, fullName, password, roleID, address, birthday, phone, gmail) "
                        + " VALUES(?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                    stm.setString(1, user.getUserID());
                    stm.setString(2, user.getFullName());
                    stm.setString(3, user.getPassword());
                    stm.setString(4, user.getRoleID());
                    stm.setString(5, user.getAddress());
                    stm.setString(6, user.getBirthday());
                    stm.setString(7, user.getPhone());
                    stm.setString(8, user.getGmail());
                    check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
        }finally {
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
}
