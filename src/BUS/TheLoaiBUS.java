/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TheLoaiDAO;
import DTO.ThuocTinhSanPham.TheLoaiDTO;
import java.util.ArrayList;

public class TheLoaiBUS {

  private final TheLoaiDAO tlDAO = new TheLoaiDAO();
  private ArrayList<TheLoaiDTO> listTL = new ArrayList<>();

  public TheLoaiBUS() {
    listTL = tlDAO.selectAll();
  }

  public ArrayList<TheLoaiDTO> getAll() {
    return this.listTL;
  }

  public TheLoaiDTO getByIndex(int index) {
    return this.listTL.get(index);
  }

  public int getIndexByMaTL(int matheloai) {
      int i = 0;
      int vitri = -1;
      while (i < this.listTL.size() && vitri == -1) {
          if (listTL.get(i).getMatheloai() == matheloai) {
              vitri = i;
          } else {
              i++;
          }
      }
      
      return vitri;
  }
  
  public Boolean add(String name) {
    TheLoaiDTO tl = new TheLoaiDTO(tlDAO.getAutoIncrement(), name);
    boolean check = tlDAO.insert(tl) != 0;
    if (check) {
      this.listTL.add(tl);
    }
    return check;
  }

  public Boolean delete(TheLoaiDTO tl) {
    boolean check = tlDAO.delete(Integer.toString(tl.getMatheloai())) != 0;
    if (check) {
      this.listTL.remove(tl);
    }
    return check;
  }

  public Boolean update(TheLoaiDTO tl) {
    boolean check = tlDAO.update(tl) != 0;
    if (check) {
      this.listTL.set(getIndexByMaTL(tl.getMatheloai()), tl);
    }
    return check;
  }

  public ArrayList<TheLoaiDTO> search(String text) {
    text = text.toLowerCase();
    ArrayList<TheLoaiDTO> result = new ArrayList<>();
    for (TheLoaiDTO i : this.listTL) {
      if (
        Integer.toString(i.getMatheloai()).toLowerCase().contains(text) ||
        i.getTentheloai().toLowerCase().contains(text)
      ) {
        result.add(i);
      }
    }
    return result;
  }

  public String[] getArrTheLoai() {
    int size = listTL.size();
    String[] result = new String[size];
    for (int i = 0; i < size; i++) {
      result[i] = listTL.get(i).getTentheloai();
    }
    return result;
  }

  public String getTheLoai(int matheloai) {
    return this.listTL.get(this.getIndexByMaTL(matheloai)).getTentheloai();
  }

  public boolean checkDup(String name) {
    boolean check = true;
    int i = 0;
    while (i <= this.listTL.size() && check == true) {
      if (
        this.listTL.get(i)
          .getTentheloai()
          .toLowerCase()
          .contains(name.toLowerCase())
      ) {
        check = false;
      } else {
        i++;
      }
    }
    return check;
  }
}
