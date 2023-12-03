/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class NhaPhatHanhDTO {
    private int manph;
    private String tennph;
    private String diachi;
    private String email;
    private String sdt;

    public NhaPhatHanhDTO() {
    }

    public NhaPhatHanhDTO(int manph, String tennph, String diachi, String email, String sdt) {
        this.manph = manph;
        this.tennph = tennph;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
    }

    public int getManph() {
        return manph;
    }

    public void setManph(int manph) {
        this.manph = manph;
    }

    public String getTennph() {
        return tennph;
    }

    public void setTennph(String tennph) {
        this.tennph = tennph;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.manph;
        hash = 67 * hash + Objects.hashCode(this.tennph);
        hash = 67 * hash + Objects.hashCode(this.diachi);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.sdt);
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
        final NhaPhatHanhDTO other = (NhaPhatHanhDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "NhaPhatHanh{" + "manph=" + manph + ", tennph=" + tennph + ", diachi=" + diachi + ", email=" + email + ", sdt=" + sdt + '}';
    }
    
}
