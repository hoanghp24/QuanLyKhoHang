package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class PhieuNhapBUS {

    private final PhieuNhapDAO phieuNhapDAO = PhieuNhapDAO.getInstance();

    private final ChiTietPhieuNhapDAO chiTietPhieuNhapDAO = ChiTietPhieuNhapDAO.getInstance();
    private final ChiTietSanPhamDAO chiTietSanPhamDAO = ChiTietSanPhamDAO.getInstance();
    private final ArrayList<PhieuNhapDTO> listPhieuNhap;

    NhanVienBUS nvBUS = new NhanVienBUS();
    NhaPhatHanhBUS nphBUS = new NhaPhatHanhBUS();

    public PhieuNhapBUS() {
        this.listPhieuNhap = phieuNhapDAO.selectAll();
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        return this.listPhieuNhap;
    }

    public PhieuNhapDTO getSelect(int index) {
        return listPhieuNhap.get(index);
    }

    public void cancel(int px) {
        phieuNhapDAO.cancel(px);
    }

    public void remove(int px) {
        listPhieuNhap.remove(px);
    }

    public void insert(PhieuNhapDTO px, ArrayList<ChiTietPhieuDTO> ct) {
        phieuNhapDAO.insert(px);
        chiTietPhieuNhapDAO.insert(ct);
    }

    public ArrayList<ChiTietPhieuDTO> selectCTP(int maphieu) {
        return chiTietPhieuNhapDAO.selectAll(Integer.toString(maphieu));
    }

    public ArrayList<PhieuNhapDTO> fillerPhieuNhap(int type, String input, int manph, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_e.getTime());

        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());

        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        for (PhieuNhapDTO phieuNhap : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)
                            || nphBUS.getTenNhaPhatHanh(phieuNhap.getManhaphathanh()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nphBUS.getTenNhaPhatHanh(phieuNhap.getManhaphathanh()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || phieuNhap.getManguoitao() == manv) && (manph == 0 || phieuNhap.getManhaphathanh() == manph)
                    && (phieuNhap.getThoigiantao().compareTo(time_start) >= 0)
                    && (phieuNhap.getThoigiantao().compareTo(time_end) <= 0)
                    && phieuNhap.getTongTien() >= price_min
                    && phieuNhap.getTongTien() <= price_max) {
                result.add(phieuNhap);
            }
        }

        return result;
    }

}
