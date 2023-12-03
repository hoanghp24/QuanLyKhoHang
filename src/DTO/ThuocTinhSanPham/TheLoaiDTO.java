/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

import java.util.Objects;


public class TheLoaiDTO {

  private int matheloai;
  private String tentheloai;

  public TheLoaiDTO(int matheloai, String tentheloai) {
    this.matheloai = matheloai;
    this.tentheloai = tentheloai;
  }

  public TheLoaiDTO() {}

  public int getMatheloai() {
    return matheloai;
  }

  public void setMatheloai(int matheloai) {
    this.matheloai = matheloai;
  }

  public String getTentheloai() {
    return tentheloai;
  }

  public void setTentheloai(String tentheloai) {
    this.tentheloai = tentheloai;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 67 * hash + this.matheloai;
    hash = 67 * hash + Objects.hashCode(this.tentheloai);
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
    final TheLoaiDTO other = (TheLoaiDTO) obj;
    if (this.matheloai != other.matheloai) {
      return false;
    }
    return Objects.equals(this.tentheloai, other.tentheloai);
  }

  @Override
  public String toString() {
    return "TheLoaiDTO{" + "matheloai=" + matheloai + ", tentheloai=" + tentheloai + '}';
  }
}
