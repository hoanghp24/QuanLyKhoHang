package DAO;

import DTO.ChiTietPhieuDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhieuNhapDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PhieuNhapDAO implements DAOinterface<PhieuNhapDTO> {
    
    public static PhieuNhapDAO getInstance(){
        return new PhieuNhapDAO();
    }

    @Override
    public int insert(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `phieunhap`(`maphieunhap`, `tongtien`, `nguoitao`, `manhaphathanh`, `trangthai`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieu());
            pst.setInt(2, (int) t.getTongTien());
            pst.setInt(3, t.getManguoitao());
            pst.setInt(4, t.getManhaphathanh());
            pst.setInt(5, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(PhieuNhapDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `phieunhap` SET `thoigian`=?,`manhaphathanh`=?,`tongtien`=?,`trangthai`=? WHERE `maphieuxuat`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setTimestamp(1, t.getThoigiantao());
            pst.setLong(3, t.getTongTien());
            pst.setInt(4, t.getTrangthai());
            pst.setInt(5, t.getMaphieu());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE phieunhap SET trangthai = 0 WHERE maphieunhap = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    

    @Override
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap ORDER BY maphieunhap DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieu = rs.getInt("maphieunhap");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int manhaphathanh = rs.getInt("manhaphathanh");
                int nguoitao = rs.getInt("nguoitao");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(manhaphathanh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieunhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public PhieuNhapDTO selectById(String t) {
        PhieuNhapDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE maphieunhap=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieu = rs.getInt("maphieunhap");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int manhaphathanh = rs.getInt("manhaphathanh");
                int nguoitao = rs.getInt("nguoitao");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                result = new PhieuNhapDTO(manhaphathanh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public PhieuNhapDTO cancel(int phieu) {
        PhieuNhapDTO result = null;
        try {
            ArrayList<ChiTietSanPhamDTO> chitietsanpham = ChiTietSanPhamDAO.getInstance().selectAllByMaPhieuXuat(phieu);
            ArrayList<ChiTietPhieuDTO> chitietphieu = ChiTietPhieuNhapDAO.getInstance().selectAll(phieu+"");
            ChiTietPhieuNhapDAO.getInstance().reset(chitietphieu);
            for (ChiTietSanPhamDTO chiTietSanPhamDTO : chitietsanpham) {
                ChiTietSanPhamDAO.getInstance().reset(chiTietSanPhamDTO);
            }
            deletePhieu(phieu);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int deletePhieu(int t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM `phieunhap` WHERE maphieunhap = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> selectAllofKH(int manhaphathanh) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE manhaphathanh=? ORDER BY maphieunhap DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, manhaphathanh);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieu = rs.getInt("maphieunhap");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int nph = rs.getInt("manhaphathanh");
                int nguoitao = rs.getInt("nguoitao");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(nph, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieunhap);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND TABLE_NAME   = 'phieunhap'";
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
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
