package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.ThuocTinhSanPham.NhaXuatBanDTO;
import config.JDBCUtil;

public class NhaXuatBanDAO implements DAOinterface<NhaXuatBanDTO>{
  public static NhaXuatBanDAO getInstance(){
        return new NhaXuatBanDAO();
    }
    @Override
    public int insert(NhaXuatBanDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhaxuatban`(`tennhaxuatban`) VALUES (?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTennxb());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBanDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhaXuatBanDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhaxuatban` SET`tennhaxuatban`=? WHERE `manhaxuatban`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTennxb());
            pst.setInt(2, t.getManxb());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhaxuatban` SET `trangthai` = 0 WHERE `manhaxuatban`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhaXuatBanDTO> selectAll() {
        ArrayList<NhaXuatBanDTO> result = new ArrayList<NhaXuatBanDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhaxuatban WHERE `trangthai`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manhaxuatban = rs.getInt("manhaxuatban");
                String tennhaxuatban = rs.getString("tennhaxuatban");
                
                NhaXuatBanDTO lh = new NhaXuatBanDTO(manhaxuatban, tennhaxuatban);
                result.add(lh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public NhaXuatBanDTO selectById(String t) {
        NhaXuatBanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhaxuatban WHERE maNhaXuatBan=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manhaxuatban = rs.getInt("manhaxuatban");
                String tennhaxuatban = rs.getString("tennhaxuatban");
                result = new NhaXuatBanDTO(manhaxuatban, tennhaxuatban);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND   TABLE_NAME   = 'NhaXuatBan'";
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
            Logger.getLogger(NhaXuatBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
