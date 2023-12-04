/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongKe.ThongKeBanHangDTO;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNhaPhatHanhDTO;
import DTO.ThongKe.ThongKeNhapHangDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import config.JDBCUtil;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThongKeDAO {

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public static HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getThongKeTonKho(String text, Date timeStart, Date timeEnd) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         WITH nhap AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_nhap
                           FROM ctphieunhap
                           JOIN phieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         xuat AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_xuat
                           FROM ctphieuxuat
                           JOIN phieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         nhap_dau AS (
                           SELECT ctphieunhap.maphienbansp, SUM(ctphieunhap.soluong) AS sl_nhap_dau
                           FROM phieunhap
                           JOIN ctphieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE phieunhap.thoigian < ?
                           GROUP BY ctphieunhap.maphienbansp
                         ),
                         xuat_dau AS (
                           SELECT ctphieuxuat.maphienbansp, SUM(ctphieuxuat.soluong) AS sl_xuat_dau
                           FROM phieuxuat
                           JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE phieuxuat.thoigian < ?
                           GROUP BY ctphieuxuat.maphienbansp
                         ),
                         dau_ky AS (
                           SELECT
                             phienbansanpham.maphienbansp,
                             COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0) AS soluongdauky
                           FROM phienbansanpham
                           LEFT JOIN nhap_dau ON phienbansanpham.maphienbansp = nhap_dau.maphienbansp
                           LEFT JOIN xuat_dau ON phienbansanpham.maphienbansp = xuat_dau.maphienbansp
                         ),
                         temp_table AS (
                         SELECT sanpham.masp, phienbansanpham.maphienbansp, sanpham.tensp, dau_ky.soluongdauky, COALESCE(nhap.sl_nhap, 0) AS soluongnhap, COALESCE(xuat.sl_xuat, 0)  AS soluongxuat, (dau_ky.soluongdauky + COALESCE(nhap.sl_nhap, 0) - COALESCE(xuat.sl_xuat, 0)) AS soluongcuoiky, ngonngu.tenngonngu, loaibia.tenloaibia, phienbansanpham.sotrang
                         FROM dau_ky
                         LEFT JOIN nhap ON dau_ky.maphienbansp = nhap.maphienbansp
                         LEFT JOIN xuat ON dau_ky.maphienbansp = xuat.maphienbansp
                         JOIN phienbansanpham ON phienbansanpham.maphienbansp = dau_ky.maphienbansp
                         JOIN sanpham ON phienbansanpham.masp = sanpham.masp
                         JOIN ngonngu ON phienbansanpham.ngonngu = ngonngu.mangonngu
                         JOIN loaibia ON phienbansanpham.loaibia = loaibia.maloaibia
                         )
                         SELECT * FROM temp_table
                         WHERE tensp LIKE ? OR masp LIKE ?
                         ORDER BY masp;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(5, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(6, new Timestamp(timeStart.getTime()));
            pst.setString(7, "%" + text + "%");
            pst.setString(8, "%" + text + "%");            


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                int maphienbansp = rs.getInt("maphienbansp");
                String tensp = rs.getString("tensp");
                int soluongdauky = rs.getInt("soluongdauky");
                int soluongnhap = rs.getInt("soluongnhap");
                int soluongxuat = rs.getInt("soluongxuat");
                int soluongcuoiky = rs.getInt("soluongcuoiky");
                String tenngonngu = rs.getString("tenngonngu");
                String tenloaibia = rs.getString("tenloaibia");
                int sotrang = rs.getInt("sotrang");
                ThongKeTonKhoDTO p = new ThongKeTonKhoDTO(masp, maphienbansp, tensp, tenngonngu, tenloaibia, sotrang, soluongdauky, soluongnhap, soluongxuat, soluongcuoiky);
                result.computeIfAbsent(masp, k -> new ArrayList<>()).add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
    //     ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
    //     try {
    //         Connection con = JDBCUtil.getConnection();
    //         String sqlSetStartYear = "SET @start_year = ?;";
    //         String sqlSetEndYear = "SET @end_year = ?;";
    //         String sqlSelect = """
    //                  WITH RECURSIVE years(year) AS (
    //                    SELECT @start_year
    //                    UNION ALL
    //                    SELECT year + 1
    //                    FROM years
    //                    WHERE year < @end_year
    //                  )
    //                  SELECT 
    //                    years.year AS nam,
    //                    COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, 
    //                    COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu
    //                  FROM years
    //                  LEFT JOIN phieuxuat ON YEAR(phieuxuat.thoigian) = years.year
    //                  LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
    //                  LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp
    //                  LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp
    //                  GROUP BY years.year
    //                  ORDER BY years.year;""";
    //         PreparedStatement pstStartYear = con.prepareStatement(sqlSetStartYear);
    //         PreparedStatement pstEndYear = con.prepareStatement(sqlSetEndYear);
    //         PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

    //         pstStartYear.setInt(1, year_start);
    //         pstEndYear.setInt(1, year_end);

    //         pstStartYear.execute();
    //         pstEndYear.execute();

    //         ResultSet rs = pstSelect.executeQuery();
    //         while (rs.next()) {
    //             int thoigian = rs.getInt("nam");
    //             Long chiphi = rs.getLong("chiphi");
    //             Long doanhthu = rs.getLong("doanhthu");
    //             ThongKeDoanhThuDTO x = new ThongKeDoanhThuDTO(thoigian, chiphi, doanhthu, doanhthu - chiphi);
    //             result.add(x);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return result;
    // }

    public static ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                          WITH kh AS (
                         SELECT khachhang.makh, khachhang.tenkhachhang , COUNT(phieuxuat.maphieuxuat ) AS tongsophieu, SUM(phieuxuat.tongtien) AS tongsotien
                         FROM khachhang
                         JOIN phieuxuat ON khachhang.makh = phieuxuat.makh
                         WHERE phieuxuat.thoigian BETWEEN ? AND ? 
                         GROUP BY khachhang.makh, khachhang.tenkhachhang)
                         SELECT makh,tenkhachhang,COALESCE(kh.tongsophieu, 0) AS soluong ,COALESCE(kh.tongsotien, 0) AS total 
                         FROM kh WHERE tenkhachhang LIKE ? OR makh LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int makh = rs.getInt("makh");
                String tenkh = rs.getString("tenkhachhang");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeKhachHangDTO x = new ThongKeKhachHangDTO(makh, tenkh, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeNhaPhatHanhDTO> getThongKeNPH(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeNhaPhatHanhDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                          WITH nph AS (
                         SELECT nhaphathanh.manhaphathanh, nhaphathanh.tennhaphathanh , COUNT(phieunhap.maphieunhap ) AS tongsophieu, SUM(phieunhap.tongtien) AS tongsotien
                         FROM nhaphathanh
                         JOIN phieunhap ON nhaphathanh.manhaphathanh = phieunhap.manhaphathanh
                         WHERE phieunhap.thoigian BETWEEN ? AND ? 
                         GROUP BY nhaphathanh.manhaphathanh, nhaphathanh.tennhaphathanh)
                         SELECT manhaphathanh,tennhaphathanh,COALESCE(nph.tongsophieu, 0) AS soluong ,COALESCE(nph.tongsotien, 0) AS total 
                         FROM nph WHERE tennhaphathanh LIKE ? OR manhaphathanh LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manph = rs.getInt("manhaphathanh");
                String tennph = rs.getString("tennhaphathanh");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeNhaPhatHanhDTO x = new ThongKeNhaPhatHanhDTO(manph, tennph, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
    //     ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
    //     try {
    //         Connection con = JDBCUtil.getConnection();
    //         String sql = "SELECT months.month AS thang, \n"
    //                 + "       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi,\n"
    //                 + "       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu\n"
    //                 + "FROM (\n"
    //                 + "       SELECT 1 AS month\n"
    //                 + "       UNION ALL SELECT 2\n"
    //                 + "       UNION ALL SELECT 3\n"
    //                 + "       UNION ALL SELECT 4\n"
    //                 + "       UNION ALL SELECT 5\n"
    //                 + "       UNION ALL SELECT 6\n"
    //                 + "       UNION ALL SELECT 7\n"
    //                 + "       UNION ALL SELECT 8\n"
    //                 + "       UNION ALL SELECT 9\n"
    //                 + "       UNION ALL SELECT 10\n"
    //                 + "       UNION ALL SELECT 11\n"
    //                 + "       UNION ALL SELECT 12\n"
    //                 + "     ) AS months\n"
    //                 + "LEFT JOIN phieuxuat ON MONTH(phieuxuat.thoigian) = months.month AND YEAR(phieuxuat.thoigian) = ? \n"
    //                 + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
    //                 + "LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp\n"
    //                 + "LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp\n"
    //                 + "GROUP BY months.month\n"
    //                 + "ORDER BY months.month;";
    //         PreparedStatement pst = con.prepareStatement(sql);
    //         pst.setInt(1, nam);
    //         ResultSet rs = pst.executeQuery();
    //         while (rs.next()) {
    //             int thang = rs.getInt("thang");
    //             int chiphi = rs.getInt("chiphi");
    //             int doanhthu = rs.getInt("doanhthu");
    //             int loinhuan = doanhthu - chiphi;
    //             ThongKeTheoThangDTO thongke = new ThongKeTheoThangDTO(thang, chiphi, doanhthu, loinhuan);
    //             result.add(thongke);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return result;
    // }


    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat() {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         WITH RECURSIVE dates(date) AS (
                           SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                           UNION ALL
                           SELECT DATE_ADD(date, INTERVAL 1 DAY)
                           FROM dates
                           WHERE date < CURDATE()
                         )
                         SELECT 
                           dates.date AS ngay,
                           COALESCE(SUM(DISTINCT phieuxuat.tongtien), 0) AS doanhthu,
                           COALESCE(SUM(DISTINCT phieunhap.tongtien), 0) AS chiphi
                         FROM dates
                         LEFT JOIN phieunhap ON DATE(phieunhap.thoigian) = dates.date
                         LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date
                         GROUP BY dates.date
                         ORDER BY dates.date;""";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeNhapHangDTO> getThongKeNhapHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeNhapHangDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                        WITH pn AS (
                            SELECT
                                phieunhap.maphieunhap,
                                phieunhap.nguoitao,
                                phieunhap.thoigian,
                                COUNT(phieunhap.maphieunhap) AS tongsophieu,
                                SUM(phieunhap.tongtien) AS tongsotien
                            FROM phieunhap
                            WHERE phieunhap.thoigian BETWEEN ? AND ? 
                            GROUP BY phieunhap.maphieunhap, phieunhap.nguoitao
                        )
                        SELECT
                            maphieunhap,
                            nguoitao,
                            thoigian,
                            COALESCE(SUM(pn.tongsophieu) OVER (), 0) AS soluong,
                            COALESCE(pn.tongsotien, 0) AS subtotal,
                            COALESCE(SUM(pn.tongsotien) OVER (), 0) AS total_amount
                        FROM pn WHERE nguoitao LIKE ? OR maphieunhap LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieunhap = rs.getInt("maphieunhap");
                int nguoitao = rs.getInt("nguoitao");
                String thoigian = rs.getString("thoigian");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total_amount");
                long tongsotien = rs.getInt("subtotal");
                ThongKeNhapHangDTO x = new ThongKeNhapHangDTO(maphieunhap, nguoitao, thoigian, soluong, tongtien, tongsotien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeBanHangDTO> getThongKeBanHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeBanHangDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                        WITH pn AS (
                            SELECT
                                phieuxuat.maphieuxuat,
                                phieuxuat.nguoitao,
                                phieuxuat.thoigian,
                                COUNT(phieuxuat.maphieuxuat) AS tongsophieu,
                                SUM(phieuxuat.tongtien) AS tongsotien
                            FROM phieuxuat
                            WHERE phieuxuat.thoigian BETWEEN ? AND ? 
                            GROUP BY phieuxuat.maphieuxuat, phieuxuat.nguoitao
                        )
                        SELECT
                            maphieuxuat,
                            nguoitao,
                            thoigian,
                            COALESCE(SUM(pn.tongsophieu) OVER (), 0) AS soluong,
                            COALESCE(pn.tongsotien, 0) AS subtotal,
                            COALESCE(SUM(pn.tongsotien) OVER (), 0) AS total_amount
                        FROM pn WHERE nguoitao LIKE ? OR maphieuxuat LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieuxuat = rs.getInt("maphieuxuat");
                int nguoitao = rs.getInt("nguoitao");
                String thoigian = rs.getString("thoigian");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total_amount");
                long tongsotien = rs.getInt("subtotal");
                ThongKeBanHangDTO x = new ThongKeBanHangDTO(maphieuxuat, nguoitao, thoigian, soluong, tongtien, tongsotien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    

}
