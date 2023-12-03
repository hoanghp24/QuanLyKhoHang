/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

public class NhaXuatBanDTO {
    private int manxb;
    private String tennxb;

    public NhaXuatBanDTO() {
    }

    public NhaXuatBanDTO(int manxb, String tennxb) {
        this.manxb = manxb;
        this.tennxb = tennxb;
    }

    public int getManxb() {
        return manxb;
    }

    public void setManxb(int manxb) {
        this.manxb = manxb;
    }

    public String getTennxb() {
        return tennxb;
    }

    public void setTennxb(String tennxb) {
        this.tennxb = tennxb;
    }

    @Override
    public String toString() {
        return "NhaXuatBanDTO{" + "manxb=" + manxb + ", tennxb=" + tennxb + '}';
    }
    
}
