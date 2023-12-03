package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.SanPhamDTO;

public class SanPhamDAO implements DAOinterface<SanPhamDTO> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `sanpham`(`masp`, `tensp`, `hinhanh`, `tacgia`, `nhaxuatban`, `theloai`, `khuvuckho`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMasp());
            pst.setString(2, t.getTensp());
            pst.setString(3, t.getHinhanh());
            pst.setString(4, t.getTacgia());
            pst.setInt(5, t.getNhaxuatban());
            // pst.setInt(6, t.getNhaphathanh());
            pst.setInt(6, t.getTheloai());
            pst.setInt(7, t.getKhuvuckho());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `tensp`=?,`hinhanh`=?,`tacgia`=?,`nhaxuatban`=?, `theloai`=?,`khuvuckho`=? WHERE `masp`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTensp());
            pst.setString(2, t.getHinhanh());
            pst.setString(3, t.getTacgia());
            pst.setInt(4, t.getNhaxuatban());
            // pst.setInt(5, t.getNhaphathanh());
            pst.setInt(5, t.getTheloai());
            pst.setInt(6, t.getKhuvuckho());
            pst.setInt(7, t.getMasp());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM `sanpham` WHERE masp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masp");
                String tendm = rs.getString("tensp");
                String hinhanh = rs.getString("hinhanh");
                String tacgia = rs.getString("tacgia");
                int nhaxuatban = rs.getInt("nhaxuatban");
                // int nhaphathanh = rs.getInt("nhaphathanh");
                int theloai = rs.getInt("theloai");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                SanPhamDTO sp = new SanPhamDTO(madm, tendm, hinhanh, tacgia, nhaxuatban, theloai, khuvuckho, soluongton);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public SanPhamDTO selectById(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE masp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masp");
                String tendm = rs.getString("tensp");
                String hinhanh = rs.getString("hinhanh");
                String tacgia = rs.getString("tacgia");
                int nhaxuatban = rs.getInt("nhaxuatban");
                // int nhaphathanh = rs.getInt("nhaphathanh");
                int theloai = rs.getInt("theloai");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                result = new SanPhamDTO(madm, tendm, hinhanh, tacgia, nhaxuatban, theloai, khuvuckho, soluongton);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public SanPhamDTO selectByPhienBan(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham sp join phienbansanpham pb on sp.masp=pb.masp WHERE maphienbansp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masp");
                String tendm = rs.getString("tensp");
                String hinhanh = rs.getString("hinhanh");
                String tacgia = rs.getString("tacgia");
                int nhaxuatban = rs.getInt("nhaxuatban");
                // int nhaphathanh = rs.getInt("nhaphathanh");
                int theloai = rs.getInt("theloai");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                result = new SanPhamDTO(madm, tendm, hinhanh, tacgia, nhaxuatban, theloai, khuvuckho, soluongton);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND   TABLE_NAME   = 'sanpham'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateSoLuongTon(int masp, int soluong) {
        int quantity_current = this.selectById(Integer.toString(masp)).getSoluongton();
        int result = 0;
        int quantity_change = quantity_current + soluong;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `soluongton`=? WHERE masp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, masp);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateSoLuongTonPN(int masp, int soluong) {
        int quantity_current = this.selectById(Integer.toString(masp)).getSoluongton();
        int result = 0;
        int quantity_change = quantity_current - soluong;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `soluongton`=? WHERE masp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, masp);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
