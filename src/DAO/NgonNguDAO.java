package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.ThuocTinhSanPham.NgonNguDTO;
import config.JDBCUtil;

public class NgonNguDAO implements DAOinterface<NgonNguDTO>{
  public static NgonNguDAO getInstance(){
        return new NgonNguDAO();
    }
    @Override
    public int insert(NgonNguDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ngonngu`(`tenngonngu`) VALUES (?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenngonngu());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NgonNguDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NgonNguDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ngonngu` SET`tenngonngu`=? WHERE `mangonngu`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenngonngu());
            pst.setInt(2, t.getMangonngu());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NgonNguDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ngonngu` SET `trangthai` = 0 WHERE `mangonngu`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NgonNguDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NgonNguDTO> selectAll() {
        ArrayList<NgonNguDTO> result = new ArrayList<NgonNguDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ngonngu WHERE `trangthai`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mangonngu = rs.getInt("mangonngu");
                String tenngonngu = rs.getString("tenngonngu");
                
                NgonNguDTO lh = new NgonNguDTO(mangonngu, tenngonngu);
                result.add(lh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public NgonNguDTO selectById(String t) {
        NgonNguDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ngonngu WHERE mangonngu=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mangonngu = rs.getInt("mangonngu");
                String tenngonngu = rs.getString("tenngonngu");
                result = new NgonNguDTO(mangonngu, tenngonngu);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND   TABLE_NAME   = 'NgonNgu'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NgonNguDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
