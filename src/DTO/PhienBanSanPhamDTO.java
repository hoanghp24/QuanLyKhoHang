/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;
import java.util.Objects;

public class PhienBanSanPhamDTO {

    private int maphienbansp;
    private int masp;
    private int ngonngu;
    private int loaibia;
    private int sotrang;
    private int gianhap;
    private int giaxuat;
    private Date ngayxb;
    private int soluongton;

    public PhienBanSanPhamDTO() {
    }

    public PhienBanSanPhamDTO(int maphienbansp, int masp, int ngonngu, int loaibia, int sotrang, int gianhap, int giaxuat, Date ngayxb, int soluongton) {
        this.maphienbansp = maphienbansp;
        this.masp = masp;
        this.ngonngu = ngonngu;
        this.loaibia = loaibia;
        this.sotrang = sotrang;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.ngayxb = ngayxb;
        this.soluongton = soluongton;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(int ngonngu) {
        this.ngonngu = ngonngu;
    }

    public int getLoaibia() {
        return loaibia;
    }

    public void setLoaibia(int loaibia) {
        this.loaibia = loaibia;
    }

    public int getSotrang() {
        return sotrang;
    }

    public void setSotrang(int sotrang) {
        this.sotrang = sotrang;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    public Date getNgayxb() {
        return ngayxb;
    }

    public void setNgayxb(Date ngayxb) {
        this.ngayxb = ngayxb;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.maphienbansp;
        hash = 43 * hash + this.masp;
        hash = 43 * hash + this.ngonngu;
        hash = 43 * hash + this.loaibia;
        hash = 43 * hash + this.sotrang;
        hash = 43 * hash + this.gianhap;
        hash = 43 * hash + this.giaxuat;
        hash = 43 * hash + Objects.hashCode(this.ngayxb);
        hash = 43 * hash + this.soluongton;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhienBanSanPhamDTO other = (PhienBanSanPhamDTO) obj;
        if (this.maphienbansp != other.maphienbansp) {
            return false;
        }
        if (this.masp != other.masp) {
            return false;
        }
        if (this.ngonngu != other.ngonngu) {
            return false;
        }
        if (this.loaibia != other.loaibia) {
            return false;
        }
        if (this.sotrang != other.sotrang) {
            return false;
        }
        if (this.gianhap != other.gianhap) {
            return false;
        }
        if (this.giaxuat != other.giaxuat) {
            return false;
        }
        if (this.soluongton != other.soluongton) {
            return false;
        }
        return Objects.equals(this.ngayxb, other.ngayxb);
    }

    @Override
    public String toString() {
        return "PhienBanSanPhamDTO{" + "maphienbansp=" + maphienbansp + ", masp=" + masp + ", ngonngu=" + ngonngu + ", loaibia=" + loaibia + ", sotrang=" + sotrang + ", gianhap=" + gianhap + ", giaxuat=" + giaxuat + ", ngayxb=" + ngayxb + ", soluongton=" + soluongton + '}';
    }

    
}
