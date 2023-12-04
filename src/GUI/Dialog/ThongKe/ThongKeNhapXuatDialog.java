package GUI.Dialog.ThongKe;

import BUS.ChiTietSanPhamBUS;
import BUS.PhienBanSanPhamBUS;
import BUS.PhieuNhapBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import DAO.KhachHangDAO;
import DAO.LoaiBiaDAO;
import DAO.NgonNguDAO;
import DAO.NhaPhatHanhDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.ThongKe.ThongKeBanHangDTO;
import DTO.ThongKe.ThongKeNhapHangDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.ThongKe.ThongKeKhachHang;
import helper.Formater;
import helper.writePDF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class ThongKeNhapXuatDialog extends JDialog implements ActionListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_bottom_right, pnmain_bottom_left, pnmain_btn;
    InputForm txtMaPhieu, txtNhanVien, txtNhaPhatHanh, txtThoiGian;
    DefaultTableModel tblModel, tblModelIsbn;
    JTable table, tblIsbn;
    JScrollPane scrollTable, scrollTableIsbn;

    ThongKeNhapHangDTO phieunhap;
    ThongKeBanHangDTO phieuxuat;
    PhienBanSanPhamBUS phienbanBus = new PhienBanSanPhamBUS();
    ChiTietSanPhamBUS ctspBus = new ChiTietSanPhamBUS();
    PhieuNhapBUS phieunhapBus;
    PhieuXuatBUS phieuxuatBus;

    ButtonCustom btnPdf, btnHuyBo;

    ArrayList<ChiTietPhieuDTO> chitietphieu;

    HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> chitietsanpham = new HashMap<>();

    public ThongKeNhapXuatDialog(JFrame owner, String title, boolean modal, ThongKeNhapHangDTO phieunhapDTO) {
        super(owner, title, modal);
        this.phieunhap = phieunhapDTO;
        phieunhapBus = new PhieuNhapBUS();
        chitietphieu = phieunhapBus.selectCTP(phieunhapDTO.getMaphieunhap());
        chitietsanpham = ctspBus.getChiTietSanPhamFromMaPN(phieunhapDTO.getMaphieunhap());
        initComponentNhap(title);
        initPhieuNhap();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }

    public ThongKeNhapXuatDialog(JFrame owner, String title, boolean modal, ThongKeBanHangDTO phieuxuatDTO) {
        super(owner, title, modal);
        this.phieuxuat = phieuxuatDTO;
        phieuxuatBus = new PhieuXuatBUS();
        chitietphieu = phieuxuatBus.selectCTP(phieuxuatDTO.getMaphieuxuat());
        chitietsanpham = ctspBus.getChiTietSanPhamFromMaPX(phieuxuatDTO.getMaphieuxuat());
        initComponentXuat(title);
        initPhieuXuat();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }

    public void initPhieuNhap() {
        txtMaPhieu.setText("PN" + Integer.toString(this.phieunhap.getMaphieunhap()));
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(phieunhap.getNguoitao() + "").getHoten());
        txtThoiGian.setText(phieunhap.getThoigian());
    }

    public void initPhieuXuat() {
        txtMaPhieu.setText("PX" + Integer.toString(this.phieuxuat.getMaphieuxuat()));
        txtNhaPhatHanh.setTitle("Khách hàng");
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(phieuxuat.getNguoitao() + "").getHoten());
        txtThoiGian.setText(phieuxuat.getThoigian());
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuDTO> ctPhieu) {
        tblModel.setRowCount(0);
        int size = ctPhieu.size();
        for (int i = 0; i < size; i++) {
            PhienBanSanPhamDTO pb = phienbanBus.getByMaPhienBan(ctPhieu.get(i).getMaphienbansp());
            tblModel.addRow(new Object[]{
                i + 1, pb.getMasp(), SanPhamDAO.getInstance().selectById(pb.getMasp()+"").getTensp(), 
                NgonNguDAO.getInstance().selectById(pb.getNgonngu()+"").getTenngonngu(), LoaiBiaDAO.getInstance().selectById(pb.getLoaibia()+"").getTenloaibia(),
                pb.getSotrang(), Formater.FormatVND(ctPhieu.get(i).getDongia()), ctPhieu.get(i).getSoluong()
            });
        }
    }

    public void loadDataTableIsbn(ArrayList<ChiTietSanPhamDTO> dssp) {
        tblModelIsbn.setRowCount(0);
        int size = dssp.size();
        for (int i = 0; i < size; i++) {
            tblModelIsbn.addRow(new Object[]{
                i + 1, dssp.get(i).getIsbn()
            });
        }
    }

    public void initComponentXuat(String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaPhieu = new InputForm("Mã đơn hàng");
        txtNhanVien = new InputForm("Nhân viên bán hàng");
        txtNhaPhatHanh = new InputForm("khách hàng");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaPhatHanh.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaPhatHanh);
        pnmain_top.add(txtThoiGian);

        pnmain_bottom = new JPanel(new BorderLayout(5, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên SP", "Ngôn ngữ", "Loại bìa", "Số trang", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index != -1) {
                    loadDataTableIsbn(chitietsanpham.get(chitietphieu.get(index).getMaphienbansp()));
                }
            }
        });
        pnmain_bottom_left.add(scrollTable);

        pnmain_bottom_right = new JPanel(new GridLayout(1, 1));
        pnmain_bottom_right.setPreferredSize(new Dimension(200, 10));
        tblIsbn = new JTable();
        scrollTableIsbn = new JScrollPane();
        tblModelIsbn = new DefaultTableModel();
        tblModelIsbn.setColumnIdentifiers(new String[]{"STT", "Mã Vạch"});
        tblIsbn.setModel(tblModelIsbn);
        tblIsbn.setFocusable(false);
        tblIsbn.setDefaultRenderer(Object.class, centerRenderer);
        tblIsbn.getColumnModel().getColumn(1).setPreferredWidth(170);
        scrollTableIsbn.setViewportView(tblIsbn);
        // pnmain_bottom_right.add(scrollTableIsbn);

        pnmain_bottom.add(pnmain_bottom_left, BorderLayout.CENTER);
        // pnmain_bottom.add(pnmain_bottom_right, BorderLayout.EAST);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);
        pnmain.add(pnmain_btn, BorderLayout.SOUTH);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    public void initComponentNhap(String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaPhieu = new InputForm("Mã đơn nhập");
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhaPhatHanh = new InputForm("Nhà phát hành");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaPhatHanh.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaPhatHanh);
        pnmain_top.add(txtThoiGian);

        pnmain_bottom = new JPanel(new BorderLayout(5, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên SP", "Ngôn ngữ", "Loại bìa", "Số trang", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index != -1) {
                    loadDataTableIsbn(chitietsanpham.get(chitietphieu.get(index).getMaphienbansp()));
                }
            }
        });
        pnmain_bottom_left.add(scrollTable);

        pnmain_bottom_right = new JPanel(new GridLayout(1, 1));
        pnmain_bottom_right.setPreferredSize(new Dimension(200, 10));
        tblIsbn = new JTable();
        scrollTableIsbn = new JScrollPane();
        tblModelIsbn = new DefaultTableModel();
        tblModelIsbn.setColumnIdentifiers(new String[]{"STT", "Mã Vạch"});
        tblIsbn.setModel(tblModelIsbn);
        tblIsbn.setFocusable(false);
        tblIsbn.setDefaultRenderer(Object.class, centerRenderer);
        tblIsbn.getColumnModel().getColumn(1).setPreferredWidth(170);
        scrollTableIsbn.setViewportView(tblIsbn);
        // pnmain_bottom_right.add(scrollTableIsbn);

        pnmain_bottom.add(pnmain_bottom_left, BorderLayout.CENTER);
        // pnmain_bottom.add(pnmain_bottom_right, BorderLayout.EAST);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);
        pnmain.add(pnmain_btn, BorderLayout.SOUTH);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo) {
            dispose();
        }
        if (source == btnPdf) {
            writePDF w = new writePDF();
            if (this.phieuxuat != null) {
                w.writePX(phieuxuat.getMaphieuxuat());
            }
            if (this.phieunhap != null) {
                w.writePN(phieunhap.getMaphieunhap());
            }
        }
    }
}
