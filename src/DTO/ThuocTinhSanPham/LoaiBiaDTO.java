/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

public class LoaiBiaDTO {
    private int maloaibia;
    private String tenloaibia;

    public LoaiBiaDTO() {
    }

    public LoaiBiaDTO(int maloaibia, String tenloaibia) {
        this.maloaibia = maloaibia;
        this.tenloaibia = tenloaibia;
    }

    public int getMaloaibia() {
        return maloaibia;
    }

    public void setMaloaibia(int maloaibia) {
        this.maloaibia = maloaibia;
    }

    public String getTenloaibia() {
        return tenloaibia;
    }

    public void setTenloaibia(String tenloaibia) {
        this.tenloaibia = tenloaibia;
    }

    @Override
    public String toString() {
        return "LoaiBiaDTO{" + "maloaibia=" + maloaibia + ", tenloaibia=" + tenloaibia + '}';
    }
    
    
}
