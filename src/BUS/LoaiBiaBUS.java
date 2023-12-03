/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoaiBiaDAO;
import DTO.ThuocTinhSanPham.LoaiBiaDTO;

import java.util.ArrayList;


public class LoaiBiaBUS {

    private final LoaiBiaDAO lbDAO = new LoaiBiaDAO();
    private ArrayList<LoaiBiaDTO> listLB = new ArrayList<>();

    public LoaiBiaBUS() {
        listLB = lbDAO.selectAll();
    }

    public ArrayList<LoaiBiaDTO> getAll() {
        return this.listLB;
    }

    public LoaiBiaDTO getByIndex(int index) {
        return this.listLB.get(index);
    }

    public int getIndexByMaLB(int maloaibia) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLB.size() && vitri == -1) {
            if (listLB.get(i).getMaloaibia() == maloaibia) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getLoaiBiaById(int maloaibia) {
        return this.listLB.get(this.getIndexByMaLB(maloaibia)).getTenloaibia();
    }

    public Boolean add(String name) {
        LoaiBiaDTO lb = new LoaiBiaDTO(lbDAO.getAutoIncrement(), name);
        boolean check = lbDAO.insert(lb) != 0;
        if (check) {
            this.listLB.add(lb);
        }
        return check;
    }

    public Boolean delete(LoaiBiaDTO lb) {
        boolean check = lbDAO.delete(Integer.toString(lb.getMaloaibia())) != 0;
        if (check) {
            this.listLB.remove(lb);
        }
        return check;
    }

    public Boolean update(LoaiBiaDTO lb) {
        boolean check = lbDAO.update(lb) != 0;
        if (check) {
            this.listLB.set(getIndexByMaLB(lb.getMaloaibia()), lb);
        }
        return check;
    }

    public ArrayList<LoaiBiaDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<LoaiBiaDTO> result = new ArrayList<>();
        for (LoaiBiaDTO i : this.listLB) {
            if (Integer.toString(i.getMaloaibia()).toLowerCase().contains(text) || i.getTenloaibia().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public String[] getArrTenLoaibia() {
        int size = listLB.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listLB.get(i).getTenloaibia();
        }
        return result;
    }

    public String getTenLoaibia(int maloaibia) {
        return this.listLB.get(this.getIndexByMaLB(maloaibia)).getTenloaibia();
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i <= this.listLB.size() && check == true) {
            if (this.listLB.get(i).getTenloaibia().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }
}
