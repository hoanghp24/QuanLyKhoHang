/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaPhatHanhDAO;
import DTO.KhachHangDTO;
import DTO.NhaPhatHanhDTO;
import java.util.ArrayList;


public class NhaPhatHanhBUS {

    private final NhaPhatHanhDAO NphDAO = new NhaPhatHanhDAO();
    private ArrayList<NhaPhatHanhDTO> listNph = new ArrayList<>();

    public NhaPhatHanhBUS() {
        this.listNph = NphDAO.selectAll();
    }

    public ArrayList<NhaPhatHanhDTO> getAll() {
        return this.listNph;
    }

    public NhaPhatHanhDTO getByIndex(int index) {
        return this.listNph.get(index);
    }

    public boolean add(NhaPhatHanhDTO nph) {
        boolean check = NphDAO.insert(nph) != 0;
        if (check) {
            this.listNph.add(nph);
        }
        return check;
    }

    public boolean delete(NhaPhatHanhDTO nph, int index) {
        boolean check = NphDAO.delete(Integer.toString(nph.getManph())) != 0;
        if (check) {
            this.listNph.remove(index);
        }
        return check;
    }

    public boolean update(NhaPhatHanhDTO ncc) {
        boolean check = NphDAO.update(ncc) != 0;
        if (check) {
            this.listNph.set(getIndexByMaNPH(ncc.getManph()), ncc);
        }
        return check;
    }

    public int getIndexByMaNPH(int manph) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNph.size() && vitri == -1) {
            if (listNph.get(i).getManph() == manph) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public ArrayList<NhaPhatHanhDTO> search(String txt, String type) {
        ArrayList<NhaPhatHanhDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (NhaPhatHanhDTO i : listNph) {
                    if (Integer.toString(i.getManph()).contains(txt) || i.getTennph().contains(txt) || i.getDiachi().contains(txt) || i.getEmail().contains(txt) || i.getSdt().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhà cung cấp" -> {
                for (NhaPhatHanhDTO i : listNph) {
                    if (Integer.toString(i.getManph()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên nhà cung cấp" -> {
                for (NhaPhatHanhDTO i : listNph) {
                    if (i.getTennph().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (NhaPhatHanhDTO i : listNph) {
                    if (i.getDiachi().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (NhaPhatHanhDTO i : listNph) {
                    if (i.getSdt().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Email" -> {
                for (NhaPhatHanhDTO i : listNph) {
                    if (i.getEmail().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }

        }
        return result;
    }

    public String[] getArrTenNhaPhatHanh() {
        int size = listNph.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNph.get(i).getTennph();
        }
        return result;
    }

    public String getTenNhaPhatHanh(int mancc) {
        return this.listNph.get(getIndexByMaNPH(mancc)).getTennph();
    }

    public NhaPhatHanhDTO findCT(ArrayList<NhaPhatHanhDTO> ncc, String tenncc) {
        NhaPhatHanhDTO p = null;
        int i = 0;
        while (i < ncc.size() && p == null) {
            if (ncc.get(i).getTennph().equals(tenncc)) {
                p = ncc.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

     public NhaPhatHanhDTO selectNph(int manph) {
        return NphDAO.selectById(manph + "");
    }
}
