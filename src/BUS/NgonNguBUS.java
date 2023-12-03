/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NgonNguDAO;
import DTO.ThuocTinhSanPham.NgonNguDTO;

import java.util.ArrayList;


public class NgonNguBUS {

    private final NgonNguDAO nnDAO = new NgonNguDAO();
    private ArrayList<NgonNguDTO> listNN = new ArrayList<>();

    public NgonNguBUS() {
        listNN = nnDAO.selectAll();
    }

    public ArrayList<NgonNguDTO> getAll() {
        return this.listNN;
    }

    public NgonNguDTO getByIndex(int index) {
        return this.listNN.get(index);
    }

    public int getIndexByMaNN(int mangonngu) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNN.size() && vitri == -1) {
            if (listNN.get(i).getMangonngu() == mangonngu) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getNgonNguById(int mangonngu) {
        return this.listNN.get(this.getIndexByMaNN(mangonngu)).getTenngonngu();
    }

    public Boolean add(String name) {
        NgonNguDTO nn = new NgonNguDTO(nnDAO.getAutoIncrement(), name);
        boolean check = nnDAO.insert(nn) != 0;
        if (check) {
            this.listNN.add(nn);
        }
        return check;
    }

    public Boolean delete(NgonNguDTO nn) {
        boolean check = nnDAO.delete(Integer.toString(nn.getMangonngu())) != 0;
        if (check) {
            this.listNN.remove(nn);
        }
        return check;
    }

    public Boolean update(NgonNguDTO nn) {
        boolean check = nnDAO.update(nn) != 0;
        if (check) {
            this.listNN.set(getIndexByMaNN(nn.getMangonngu()), nn);
        }
        return check;
    }

    public ArrayList<NgonNguDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<NgonNguDTO> result = new ArrayList<>();
        for (NgonNguDTO i : this.listNN) {
            if (Integer.toString(i.getMangonngu()).toLowerCase().contains(text) || i.getTenngonngu().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public String[] getArrTenNgonNgu() {
        int size = listNN.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNN.get(i).getTenngonngu();
        }
        return result;
    }

    public String getTenNgonNgu(int mangonngu) {
        return this.listNN.get(this.getIndexByMaNN(mangonngu)).getTenngonngu();
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i <= this.listNN.size() && check == true) {
            if (this.listNN.get(i).getTenngonngu().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }
}
