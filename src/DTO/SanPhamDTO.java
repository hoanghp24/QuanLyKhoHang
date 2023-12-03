/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;


public class SanPhamDTO {

    private int masp;
    private String tensp;
    private String hinhanh;
    private String tacgia;
    private int nhaxuatban;
    private int theloai;
    private int khuvuckho;
    private int soluongton;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int masp, String tensp, String hinhanh, String tacgia, int nhaxuatban, 
            int theloai, int khuvuckho, int soluongton) {
        this.masp = masp;
        this.tensp = tensp;
        this.hinhanh = hinhanh;
        this.tacgia = tacgia;
        this.nhaxuatban = nhaxuatban;
        this.theloai = theloai;
        this.khuvuckho = khuvuckho;
        this.soluongton = soluongton;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getNhaxuatban() {
        return nhaxuatban;
    }

    public void setNhaxuatban(int nhaxuatban) {
        this.nhaxuatban = nhaxuatban;
    }

    
    public int getTheloai() {
        return theloai;
    }

    public void setTheloai(int theloai) {
        this.theloai = theloai;
    }

    public int getKhuvuckho() {
        return khuvuckho;
    }

    public void setKhuvuckho(int khuvuckho) {
        this.khuvuckho = khuvuckho;
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
        hash = 79 * hash + this.masp;
        hash = 79 * hash + Objects.hashCode(this.tensp);
        hash = 79 * hash + Objects.hashCode(this.hinhanh);
        hash = 79 * hash + Objects.hashCode(this.tacgia);
        hash = 79 * hash + this.nhaxuatban;
        hash = 79 * hash + this.theloai;
        hash = 79 * hash + this.khuvuckho;
        hash = 79 * hash + this.soluongton;
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
        final SanPhamDTO other = (SanPhamDTO) obj;
        if (this.masp != other.masp) {
            return false;
        }
        if (this.nhaxuatban != other.nhaxuatban) {
            return false;
        }
       
        if (this.theloai != other.theloai) {
            return false;
        }
        if (this.khuvuckho != other.khuvuckho) {
            return false;
        }
        if (this.soluongton != other.soluongton) {
            return false;
        }
        if (!Objects.equals(this.tensp, other.tensp)) {
            return false;
        }
        if (!Objects.equals(this.hinhanh, other.hinhanh)) {
            return false;
        }
        return Objects.equals(this.tacgia, other.tacgia);
    }

    

    @Override
    public String toString() {
        return "SanPhamDTO [masp=" + masp + ", tensp=" + tensp + ", hinhanh=" + hinhanh + ", tacgia=" + tacgia
                + ", nhaxuatban=" + nhaxuatban + ", theloai=" + theloai
                + ", khuvuckho=" + khuvuckho + ", soluongton=" + soluongton + "]";
    }

}
