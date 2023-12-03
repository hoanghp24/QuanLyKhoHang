/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

import java.util.Objects;


public class NgonNguDTO{
    private int mangonngu;
    private String tenngonngu;

    public NgonNguDTO(){

    }

    public NgonNguDTO(int mangonngu, String tenngonngu) {
        this.mangonngu = mangonngu;
        this.tenngonngu = tenngonngu;
    }

    public int getMangonngu() {
        return mangonngu;
    }

    public void setMangonngu(int mangonngu) {
        this.mangonngu = mangonngu;
    }

    public String getTenngonngu() {
        return tenngonngu;
    }

    public void setTenngonngu(String tenngonngu) {
        this.tenngonngu = tenngonngu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.mangonngu;
        hash = 79 * hash + Objects.hashCode(this.tenngonngu);
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
        final NgonNguDTO other = (NgonNguDTO) obj;
        if (this.mangonngu != other.mangonngu) {
            return false;
        }
        return Objects.equals(this.tenngonngu, other.tenngonngu);
    }

    @Override
    public String toString() {
        return "NgonNgu [mangonngu=" + mangonngu + ", tenngonngu=" + tenngonngu + "]";
    }

    
}
