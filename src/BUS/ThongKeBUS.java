package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKe.ThongKeBanHangDTO;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNhaPhatHanhDTO;
import DTO.ThongKe.ThongKeNhapHangDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class ThongKeBUS {

    ThongKeDAO thongkeDAO = new ThongKeDAO();
    ArrayList<ThongKeKhachHangDTO> tkkh;    
    ArrayList<ThongKeNhapHangDTO> tknh;
    ArrayList<ThongKeBanHangDTO> tkbh;


    ArrayList<ThongKeNhaPhatHanhDTO> tknph;
    HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> listTonKho;

    public ThongKeBUS() {
        listTonKho = ThongKeDAO.getThongKeTonKho("",new Date(0), new Date(System.currentTimeMillis()));
    }

    public ArrayList<ThongKeKhachHangDTO> getAllKhachHang() {
        this.tkkh = ThongKeDAO.getThongKeKhachHang("",new Date(0), new Date(System.currentTimeMillis()));
        return this.tkkh;
    }

    public ArrayList<ThongKeKhachHangDTO> FilterKhachHang(String text,Date start, Date end) {
        this.tkkh = ThongKeDAO.getThongKeKhachHang(text,start, end);
        return this.tkkh;
    }

    public ArrayList<ThongKeNhaPhatHanhDTO> getAllNPH() {
        this.tknph=ThongKeDAO.getThongKeNPH("",new Date(0), new Date(System.currentTimeMillis()));
        return this.tknph;
    }

    public ArrayList<ThongKeNhaPhatHanhDTO> FilterNPH(String text,Date start, Date end) {
        this.tknph = ThongKeDAO.getThongKeNPH(text,start, end);
        return this.tknph;
    }

    public HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getTonKho() {
        return this.listTonKho;
    }

    public HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> filterTonKho(String text, Date time_start, Date time_end) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = ThongKeDAO.getThongKeTonKho(text, time_start, time_end);
        return result;
    }

    public int[] getSoluong(ArrayList<ThongKeTonKhoDTO> list) {
        int[] result = {0, 0, 0, 0};
        for (int i = 0; i < list.size(); i++) {
            result[0] += list.get(i).getTondauky();
            result[1] += list.get(i).getNhaptrongky();
            result[2] += list.get(i).getXuattrongky();
            result[3] += list.get(i).getToncuoiky();
        }
        return result;
    }
    
    // public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
    //     return this.thongkeDAO.getDoanhThuTheoTungNam(year_start, year_end);
    // }
    
    // public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam){
    //     return thongkeDAO.getThongKeTheoThang(nam);
    // }
    
    public ArrayList<ThongKeNhapHangDTO> getAllNhapHang() {
        this.tknh = ThongKeDAO.getThongKeNhapHang("", new Date(0), new Date(System.currentTimeMillis()));
        return this.tknh;
    }

    public ArrayList<ThongKeNhapHangDTO> FilterNhapHang(String text,Date start, Date end) {
        this.tknh = ThongKeDAO.getThongKeNhapHang(text,start, end);
        return this.tknh;
    }
    
    public ArrayList<ThongKeBanHangDTO> getAllBanHang() {
        this.tkbh = ThongKeDAO.getThongKeBanHang("", new Date(0), new Date(System.currentTimeMillis()));
        return this.tkbh;
    }

    public ArrayList<ThongKeBanHangDTO> FilterBanHang(String text,Date start, Date end) {
        this.tkbh = ThongKeDAO.getThongKeBanHang(text,start, end);
        return this.tkbh;
    }
        
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat(){
        return thongkeDAO.getThongKe7NgayGanNhat();
    }
}
