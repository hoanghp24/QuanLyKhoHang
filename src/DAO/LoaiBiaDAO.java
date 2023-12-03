package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.ThuocTinhSanPham.LoaiBiaDTO;
import config.JDBCUtil;

public class LoaiBiaDAO implements DAOinterface<LoaiBiaDTO>{
    public static LoaiBiaDAO getInstance(){
        return new LoaiBiaDAO();
    }
    @Override
    public int insert(LoaiBiaDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `loaibia`(`tenloaibia`) VALUES (?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenloaibia());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiBiaDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(LoaiBiaDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `loaibia` SET`tenloaibia`=? WHERE `maloaibia`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenloaibia());
            pst.setInt(2, t.getMaloaibia());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiBiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `loaibia` SET `trangthai` = 0 WHERE `maloaibia`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiBiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<LoaiBiaDTO> selectAll() {
        ArrayList<LoaiBiaDTO> result = new ArrayList<LoaiBiaDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaibia WHERE `trangthai`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maloaibia = rs.getInt("maloaibia");
                String tenloaibia = rs.getString("tenloaibia");
                
                LoaiBiaDTO lh = new LoaiBiaDTO(maloaibia, tenloaibia);
                result.add(lh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public LoaiBiaDTO selectById(String t) {
        LoaiBiaDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaibia WHERE maloaibia=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maloaibia = rs.getInt("maloaibia");
                String tenloaibia = rs.getString("tenloaibia");
                result = new LoaiBiaDTO(maloaibia, tenloaibia);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND   TABLE_NAME   = 'loaibia'";
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
            Logger.getLogger(LoaiBiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
