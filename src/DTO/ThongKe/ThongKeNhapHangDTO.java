/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;
import DTO.PhieuXuatDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;


public class ThongKeNhapHangDTO {
    int maphieunhap;
    int nguoitao;
    String thoigian;
    int soluong;
    long tongtien;
    long tongsotien;

    public ThongKeNhapHangDTO() {
    }

    

    public ThongKeNhapHangDTO(int maphieunhap, int nguoitao, String thoigian, int soluong, long tongtien,
            long tongsotien) {
        this.maphieunhap = maphieunhap;
        this.nguoitao = nguoitao;
        this.thoigian = thoigian;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.tongsotien = tongsotien;
    }



    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public int getNguoitao() {
        return nguoitao;
    }

    public void setNguoitao(int nguoitao) {
        this.nguoitao = nguoitao;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

    public long getTongsotien() {
        return tongsotien;
    }

    public void setTongsotien(long tongsotien) {
        this.tongsotien = tongsotien;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maphieunhap;
        result = prime * result + nguoitao;
        result = prime * result + ((thoigian == null) ? 0 : thoigian.hashCode());
        result = prime * result + soluong;
        result = prime * result + (int) (tongtien ^ (tongtien >>> 32));
        result = prime * result + (int) (tongsotien ^ (tongsotien >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ThongKeNhapHangDTO other = (ThongKeNhapHangDTO) obj;
        if (maphieunhap != other.maphieunhap)
            return false;
        if (nguoitao != other.nguoitao)
            return false;
        if (thoigian == null) {
            if (other.thoigian != null)
                return false;
        } else if (!thoigian.equals(other.thoigian))
            return false;
        if (soluong != other.soluong)
            return false;
        if (tongtien != other.tongtien)
            return false;
        if (tongsotien != other.tongsotien)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ThongKeNhapHangDTO [maphieunhap=" + maphieunhap + ", nguoitao=" + nguoitao + ", thoigian=" + thoigian
                + ", soluong=" + soluong + ", tongtien=" + tongtien + ", tongsotien=" + tongsotien + "]";
    }

    
}
