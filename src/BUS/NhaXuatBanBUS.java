/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaXuatBanDAO;
import DTO.ThuocTinhSanPham.NhaXuatBanDTO;

import java.util.ArrayList;


public class NhaXuatBanBUS {

    private final NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
    private ArrayList<NhaXuatBanDTO> listNXB = new ArrayList<>();

    public NhaXuatBanBUS() {
        listNXB = nxbDAO.selectAll();
    }

    public ArrayList<NhaXuatBanDTO> getAll() {
        return this.listNXB;
    }

    public NhaXuatBanDTO getByIndex(int index) {
        return this.listNXB.get(index);
    }

    public int getIndexByMaNXB(int manxb) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNXB.size() && vitri == -1) {
            if (listNXB.get(i).getManxb() == manxb) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(String name) {
        NhaXuatBanDTO nxb = new NhaXuatBanDTO(nxbDAO.getAutoIncrement(), name);
        boolean check = nxbDAO.insert(nxb) != 0;
        if (check) {
            this.listNXB.add(nxb);
        }
        return check;
    }

    public Boolean delete(NhaXuatBanDTO nxb) {
        boolean check = nxbDAO.delete(Integer.toString(nxb.getManxb())) != 0;
        if (check) {
            this.listNXB.remove(nxb);
        }
        return check;
    }

    public Boolean update(NhaXuatBanDTO nxb) {
        boolean check = nxbDAO.update(nxb) != 0;
        if (check) {
            this.listNXB.set(getIndexByMaNXB(nxb.getManxb()), nxb);
        }
        return check;
    }

    public ArrayList<NhaXuatBanDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<NhaXuatBanDTO> result = new ArrayList<>();
        for (NhaXuatBanDTO i : this.listNXB) {
            if (Integer.toString(i.getManxb()).toLowerCase().contains(text) || i.getTennxb().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public String[] getArrNhaXuatBan() {
        int size = listNXB.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNXB.get(i).getTennxb();
        }
        return result;
    }

    public String getNhaXuatBan(int manxb) {
        return this.listNXB.get(this.getIndexByMaNXB(manxb)).getTennxb();
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i <= this.listNXB.size() && check == true) {
            if (this.listNXB.get(i).getTennxb().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }
}
