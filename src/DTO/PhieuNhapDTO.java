/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;


public class PhieuNhapDTO extends PhieuDTO{
    private int manhaphathanh;

    public PhieuNhapDTO(int manhaphathanh) {
        this.manhaphathanh = manhaphathanh;
    }

    public PhieuNhapDTO(int manhaphathanh, int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien, int trangthai) {
        super(maphieu, manguoitao, thoigiantao, tongTien, trangthai);
        this.manhaphathanh = manhaphathanh;
    }

    public int getManhaphathanh() {
        return manhaphathanh;
    }

    public void setManhaphathanh(int manhaphathanh) {
        this.manhaphathanh = manhaphathanh;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.manhaphathanh;
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
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        return this.manhaphathanh == other.manhaphathanh;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "manhaphathanh=" + manhaphathanh +'}'+super.toString();
    }
}
