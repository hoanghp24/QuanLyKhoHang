package DAO;

import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhienBanSanPhamDAO implements ChiTietInterface<PhienBanSanPhamDTO> {

    public static PhienBanSanPhamDAO getInstance() {
        return new PhienBanSanPhamDAO();
    }

    @Override
    public int insert(ArrayList<PhienBanSanPhamDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `phienbansanpham`(`maphienbansp`, `masp`, `ngonngu`, `loaibia`, `sotrang`, `gianhap`, `giaxuat`, `ngayxuatban`, `soluongton`) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphienbansp());
                pst.setInt(2, t.get(i).getMasp());
                pst.setInt(3, t.get(i).getNgonngu());
                pst.setInt(4, t.get(i).getLoaibia());
                pst.setInt(5, t.get(i).getSotrang());
                pst.setInt(6, t.get(i).getGianhap());
                pst.setInt(7, t.get(i).getGiaxuat());
                pst.setDate(8, (Date) (t.get(i).getNgayxb()));
                pst.setInt(9, t.get(i).getSoluongton());

                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int insert(PhienBanSanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `phienbansanpham`(`maphienbansp`, `masp`, `ngonngu`, `loaibia`, `sotrang`, `gianhap`, `giaxuat`, `ngayxuatban`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphienbansp());
            pst.setInt(2, t.getMasp());
            pst.setInt(3, t.getNgonngu());
            pst.setInt(4, t.getLoaibia());
            pst.setInt(5, t.getSotrang());
            pst.setInt(6, t.getGianhap());
            pst.setInt(7, t.getGiaxuat());
            pst.setDate(8, (Date) (t.getNgayxb()));
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `phienbansanpham` SET `trangthai`= 0 WHERE `maphienbansp`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<PhienBanSanPhamDTO> t, String pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int update(PhienBanSanPhamDTO ch) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `phienbansanpham` SET `ngonngu`=?,`loaibia`=?,`sotrang`=?,`gianhap`=?,`giaxuat`=?,`ngayxuatban`=? WHERE maphienbansp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, ch.getNgonngu());
            pst.setInt(2, ch.getLoaibia());
            pst.setInt(3, ch.getSotrang());
            pst.setInt(4, ch.getGianhap());
            pst.setInt(5, ch.getGiaxuat());
            pst.setDate(6, (Date) (ch.getNgayxb()));
            pst.setInt(7, ch.getMaphienbansp());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    @Override
    public ArrayList<PhienBanSanPhamDTO> selectAll(String t) {
        ArrayList<PhienBanSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phienbansanpham WHERE masp = ? and trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphienbansp = rs.getInt("maphienbansp");
                int masp = rs.getInt("masp");
                int ngonngu = rs.getInt("ngonngu");
                int loaibia = rs.getInt("loaibia");
                int sotrang = rs.getInt("sotrang");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                Date ngayxb = rs.getDate("ngayxuatban");
                int soluongton = rs.getInt("soluongton");
                PhienBanSanPhamDTO ch = new PhienBanSanPhamDTO(maphienbansp, masp, ngonngu, loaibia, sotrang, gianhap, giaxuat, ngayxb, soluongton);
                result.add(ch);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    public ArrayList<PhienBanSanPhamDTO> selectAllpb(String t) {
        ArrayList<PhienBanSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phienbansanpham WHERE masp = ? and trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphienbansp = rs.getInt("maphienbansp");
                int masp = rs.getInt("masp");
                int ngonngu = rs.getInt("ngonngu");
                int loaibia = rs.getInt("loaibia");
                int sotrang = rs.getInt("sotrang");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                Date ngayxb = rs.getDate("ngayxuatban");
                int soluongton = rs.getInt("soluongton");
                PhienBanSanPhamDTO ch = new PhienBanSanPhamDTO(maphienbansp, masp, ngonngu, loaibia, sotrang, gianhap, giaxuat, ngayxb, soluongton);
                result.add(ch);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    public PhienBanSanPhamDTO selectById(int mapb) {
        PhienBanSanPhamDTO ch = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phienbansanpham WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, mapb);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphienbansp = rs.getInt("maphienbansp");
                int masp = rs.getInt("masp");
                int ngonngu = rs.getInt("ngonngu");
                int loaibia = rs.getInt("loaibia");
                int sotrang = rs.getInt("sotrang");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                Date ngayxb = rs.getDate("ngayxuatban");
                int soluongton = rs.getInt("soluongton");
                ch = new PhienBanSanPhamDTO(maphienbansp, masp, ngonngu, loaibia, sotrang, gianhap, giaxuat, ngayxb, soluongton);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return ch;
    }

    public boolean checkIsbnExists(ArrayList<ChiTietSanPhamDTO> arr) {
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : arr) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "SELECT * FROM ctsanpham WHERE maisbn = ?";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, chiTietSanPhamDTO.getIsbn());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Connection con = (Connection) JDBCUtil.getConnection();
        return true;
    }

    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlykhohang' AND TABLE_NAME = 'phienbansanpham'";
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
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateSoLuongTon(int maphienban, int soluong) {
        PhienBanSanPhamDTO pbsp = this.selectById(maphienban);
        int result = 0;
        int quantity_change = pbsp.getSoluongton() + soluong;
        System.out.println("Update:" + quantity_change);
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `phienbansanpham` SET `soluongton`=? WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, pbsp.getMaphienbansp());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        SanPhamDAO.getInstance().updateSoLuongTon(pbsp.getMasp(), soluong);
        return result;
    }

    public int updateSoLuongTonCancelPN(int maphienban, int soluong) {
        PhienBanSanPhamDTO pbsp = this.selectById(maphienban);
        int result = 0;
        int quantity_change = pbsp.getSoluongton() - soluong;
        System.out.println("Update:" + quantity_change);
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `phienbansanpham` SET `soluongton`=? WHERE maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, pbsp.getMaphienbansp());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhienBanSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        SanPhamDAO.getInstance().updateSoLuongTonPN(pbsp.getMasp(), soluong);
        return result;
    }
}
