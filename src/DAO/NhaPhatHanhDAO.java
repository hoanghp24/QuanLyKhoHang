/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.NhaPhatHanhDTO;

public class NhaPhatHanhDAO implements DAOinterface<NhaPhatHanhDTO>{
    public static NhaPhatHanhDAO getInstance(){
        return new NhaPhatHanhDAO();
    }

    @Override
    public int insert(NhaPhatHanhDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhaphathanh`(`manhaphathanh`, `tennhaphathanh`, `diachi`, `email`, `sdt`, `trangthai`) VALUES (?,?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getManph());
            pst.setString(2, t.getTennph());
            pst.setString(3, t.getDiachi());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getSdt());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaPhatHanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhaPhatHanhDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhaphathanh` SET `tennhaphathanh`=?,`diachi`=?,`email`=?,`sdt`=? WHERE `manhaphathanh`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTennph());
            pst.setString(2, t.getDiachi());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getSdt());
            pst.setInt(5, t.getManph());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaPhatHanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE nhaphathanh SET trangthai = 0 WHERE manhaphathanh = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaPhatHanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhaPhatHanhDTO> selectAll() {
        ArrayList<NhaPhatHanhDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhaphathanh WHERE trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manph = rs.getInt("manhaphathanh");
                String tennph = rs.getString("tennhaphathanh");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                NhaPhatHanhDTO ncc = new NhaPhatHanhDTO(manph, tennph, diachi, email, sdt);
                result.add(ncc);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public NhaPhatHanhDTO selectById(String t) {
        NhaPhatHanhDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhaphathanh WHERE manhaphathanh=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manph = rs.getInt("manhaphathanh");
                String tennph = rs.getString("tennhaphathanh");
                String diachi = rs.getString("diachi");
                String email = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                
                result = new NhaPhatHanhDTO(manph,tennph,diachi,email,sdt);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND   TABLE_NAME   = 'nhaphathanh'";
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
            Logger.getLogger(NhaPhatHanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
