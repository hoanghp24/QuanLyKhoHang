
package DTO.ThongKe;

import java.util.Objects;

public class ThongKeNhaPhatHanhDTO {
    int manph;
    String tennph;
    int soluong;
    long tongtien;
    
    public ThongKeNhaPhatHanhDTO(){}

    public ThongKeNhaPhatHanhDTO(int manph, String tennph, int soluong, long tongtien) {
        this.manph = manph;
        this.tennph = tennph;
        this.soluong = soluong;
        this.tongtien = tongtien;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.manph;
        hash = 59 * hash + Objects.hashCode(this.tennph);
        hash = 59 * hash + this.soluong;
        hash = 59 * hash + (int) (this.tongtien ^ (this.tongtien >>> 32));
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
        final ThongKeNhaPhatHanhDTO other = (ThongKeNhaPhatHanhDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "ThongKeNhaCungCapDTO{" + "manph=" + manph + ", tennph=" + tennph + ", soluong=" + soluong + ", tongtien=" + tongtien + '}';
    }
    
    
    
}
