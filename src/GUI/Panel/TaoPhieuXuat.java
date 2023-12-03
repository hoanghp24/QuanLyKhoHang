package GUI.Panel;

import BUS.ChiTietSanPhamBUS;
import BUS.PhienBanSanPhamBUS;

import BUS.KhachHangBUS;
import BUS.LoaiBiaBUS;
import BUS.NgonNguBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import DAO.ChiTietSanPhamDAO;
import DAO.NhanVienDAO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.CustomComboCheck;
import GUI.Component.InputForm;
import GUI.Component.Notification;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import GUI.Dialog.ListKhachHang;
import GUI.Dialog.QRCode_Dialog;
import GUI.Dialog.SelectIsbn;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public final class TaoPhieuXuat extends JPanel {

    PhienBanSanPhamBUS phienBanBus = new PhienBanSanPhamBUS();
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    NgonNguBUS ngonnguBUS = new NgonNguBUS();
    LoaiBiaBUS loaibiaBUS = new LoaiBiaBUS();
    PanelBorderRadius right, left;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left_top, main, content_btn;
    JTable tablePhieuXuat, tableSanPham;
    JScrollPane scrollTablePhieuNhap, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnImport, btnNhapHang;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp, txtSoluongTon, txtSoLuongMua;
    SelectForm cbxPhienBan;
    JTextField txtTimKiem;
    Color BackgroundColor = new Color(240, 247, 250);

    int sum;
    int maphieu;
    int manv;
    int makh = -1;
    String type;

    ArrayList<ChiTietSanPhamDTO> ctpb;
    SanPhamBUS spBUS = new SanPhamBUS();
    PhieuXuatBUS phieuXuatBUS = new PhieuXuatBUS();
    ChiTietSanPhamBUS chiTietSanPhamBUS = new ChiTietSanPhamBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    public JTextArea textAreaIsbn;
    private JLabel labelIsbn;
    private JPanel content_right_bottom_top;
    private JPanel content_right_bottom_bottom;
    private ArrayList<PhienBanSanPhamDTO> ch = new ArrayList<>();
    private Vector v;
    private CustomComboCheck cbxIsbn;

    ArrayList<ChiTietPhieuDTO> chitietphieu = new ArrayList<>();
    ArrayList<ChiTietSanPhamDTO> chitietsanpham = new ArrayList<>();

    TaiKhoanDTO tk;
    private int mapb;
    private JLabel lbltongtien;
    private JTextField txtKh;
    private Main mainChinh;
    private ButtonCustom btnQuayLai;
    private ButtonCustom chonIsbn;
    private InputForm txtGiaXuat;

    public TaoPhieuXuat(Main mainChinh, TaiKhoanDTO tk, String type) {
        this.mainChinh = mainChinh;
        this.tk = tk;
        this.type = type;
        initComponent(type);
        loadDataTalbeSanPham(listSP);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 5));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 5));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(5, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(5, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent(String type) {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // Phiếu nhập
        tablePhieuXuat = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Ngôn ngữ", "Loại bìa", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuXuat.setModel(tblModel);
        scrollTablePhieuNhap.setViewportView(tablePhieuXuat);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tablePhieuXuat.getColumnModel();
        for (int i = 0; i < 7; i++) {
            if (i != 2) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tablePhieuXuat.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablePhieuXuat.setFocusable(false);
        tablePhieuXuat.setDefaultEditor(Object.class, null);
        scrollTablePhieuNhap.setViewportView(tablePhieuXuat);
        // Table sản phẩm
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm", "Số lượng tồn"};
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        left_top = new JPanel(); // Chứa tất cả phần ở phía trái trên cùng
        left_top.setLayout(new BorderLayout());
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        left_top.setOpaque(false);

        JPanel content_top, content_left, content_right, content_right_top, content_right_bottom;
        content_top = new JPanel(new GridLayout(1, 2, 5, 5));
        content_top.setOpaque(false);
        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ArrayList<SanPhamDTO> rs = spBUS.search(txtTimKiem.getText());
                loadDataTalbeSanPham(rs);
            }
        });
        txtTimKiem.setPreferredSize(new Dimension(100, 40));
        content_left.add(txtTimKiem, BorderLayout.NORTH);
        content_left.add(scrollTableSanPham, BorderLayout.CENTER);

        content_right = new JPanel(new BorderLayout(5, 5));
        content_right.setOpaque(false);
        content_right.setBackground(Color.WHITE);

        content_right_top = new JPanel(new BorderLayout());
        content_right_top.setPreferredSize(new Dimension(100, 165));
        txtMaSp = new InputForm("Mã SP");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);
        String[] arrPhienban = {"Chọn sản phẩm"};
        JPanel panlePXGX = new JPanel(new GridLayout(1, 3));
        panlePXGX.setPreferredSize(new Dimension(100, 90));
        cbxPhienBan = new SelectForm("Phiên bản", arrPhienban);
        txtGiaXuat = new InputForm("Giá xuất");
        txtSoluongTon = new InputForm("Số lượng tồn");
        txtSoluongTon.setEditable(false);
        txtSoLuongMua = new InputForm("Số lượng mua");

        panlePXGX.add(cbxPhienBan);
        panlePXGX.add(txtGiaXuat);
        panlePXGX.add(txtSoluongTon);
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(panlePXGX, BorderLayout.SOUTH);
        cbxPhienBan.getCbb().addItemListener((ItemEvent e) -> {
            mapb = ch.get(cbxPhienBan.getSelectedIndex()).getMaphienbansp();
            setImeiByPb(mapb);
            if (checkTonTai()) {
                actionbtn("update");
            } else {
                actionbtn("add");
            }
        });

        content_right_bottom = new JPanel(new BorderLayout());
        content_right_bottom.setBorder(new EmptyBorder(0, 10, 10, 10));
        content_right_bottom.setBackground(Color.WHITE);
        labelIsbn = new JLabel("Mã Vạch");
        labelIsbn.setPreferredSize(new Dimension(70, 0));
        
        JPanel jpanelIsbn = new JPanel(new BorderLayout());
        jpanelIsbn.setPreferredSize(new Dimension(100, 90));
        jpanelIsbn.setBackground(Color.WHITE);
        jpanelIsbn.setBorder(new EmptyBorder(0, 0, 10, 0));
        // jpanelIsbn.add(labelIsbn, BorderLayout.WEST);
        jpanelIsbn.add(txtSoLuongMua);
        // chonIsbn = new ButtonCustom("Chọn mã vạch", "success", 14);

        // chonIsbn.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         if (ctpb == null) {
        //             JOptionPane.showMessageDialog(null, "Vui lòng chọn phiên bản!");
        //         } else {
        //             SelectIsbn selectIsbn = new SelectIsbn(owner, "Chọn mã vạch", true, TaoPhieuXuat.this, ctpb);
        //         }
        //     }
        // });
        // JPanel jPanelChonIsbn = new JPanel(new GridLayout(1, 2));
        // jPanelChonIsbn.setPreferredSize(new Dimension(200, 0));
        // jPanelChonIsbn.setOpaque(false);
        // jPanelChonIsbn.add(chonIsbn);

        // jpanelIsbn.add(jPanelChonIsbn, BorderLayout.EAST);

        
        textAreaIsbn = new JTextArea();
        // textAreaIsbn.getDocument().addDocumentListener(new DocumentListener() {
        //     @Override
        //     public void insertUpdate(DocumentEvent e) {
        //         String[] arrIsbn = textAreaIsbn.getText().split("\n");
        //         for (int i = 0; i < arrIsbn.length; i++) {
        //             boolean check = false;
        //             for (ChiTietSanPhamDTO chiTietSanPhamDTO : ctpb) {
        //                 if (arrIsbn[i].equals(chiTietSanPhamDTO.getIsbn())) {
        //                     check = true;
        //                 }
        //             }
        //             if (!check) {
        //                 String txt = textAreaIsbn.getText().replaceAll("(" + arrIsbn[i] + ")\n", "");
        //                 textAreaIsbn.setText(txt);
        //             }
        //         }
        //     }

        //     @Override
        //     public void removeUpdate(DocumentEvent e) {
        //         // Xử lý sự kiện khi có nội dung bị xóa đi
        //     }

        //     @Override
        //     public void changedUpdate(DocumentEvent e) {
        //         // Xử lý sự kiện khi có thay đổi khác
        //     }
        // });
        // textAreaIsbn.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        // this.textAreaIsbn.setEditable(false);
        content_right_bottom_top = new JPanel(new BorderLayout());
        content_right_bottom_top.setSize(new Dimension(0, 100));
        content_right_bottom_top.setBackground(Color.white);
        content_right_bottom_top.add(jpanelIsbn, BorderLayout.NORTH);
        content_right_bottom_top.add(textAreaIsbn, BorderLayout.CENTER);
        content_right_bottom_bottom = new JPanel(new BorderLayout());
        content_right_bottom_bottom.setSize(new Dimension(0, 50));
        content_right_bottom_bottom.setBorder(new EmptyBorder(20, 0, 0, 0));

        content_right_bottom.add(content_right_bottom_top, BorderLayout.CENTER);

        content_right.add(content_right_top, BorderLayout.NORTH);
        content_right.add(content_right_bottom, BorderLayout.CENTER);

        content_top.add(content_left);
        content_top.add(content_right);

        content_btn = new JPanel();
        content_btn.setPreferredSize(new Dimension(0, 47));
        content_btn.setLayout(new GridLayout(1, 4, 5, 5));
        content_btn.setBorder(new EmptyBorder(8, 5, 0, 10));
        content_btn.setOpaque(false);
        btnAddSp = new ButtonCustom("Thêm sản phẩm", "success", 14);

        btnEditSP = new ButtonCustom("Sửa sản phẩm", "warning", 14);
        btnDelete = new ButtonCustom("Xoá sản phẩm", "danger", 14);
        // btnImport = new ButtonCustom("Nhập Excel", "excel", 14);
        content_btn.add(btnAddSp);
        // content_btn.add(btnImport);
        content_btn.add(btnEditSP);
        content_btn.add(btnDelete);

        btnAddSp.addActionListener((ActionEvent e) -> {
            if (checkInfo()) {
                int soLuongMua = Integer.parseInt(txtSoLuongMua.getText());
                int soLuongTon = Integer.parseInt(txtSoluongTon.getText());
                if (soLuongMua > soLuongTon) {
                    JOptionPane.showMessageDialog(null, "Số lượng mua không được lớn hơn số lượng tồn");
                } else if (soLuongMua <= 0){
                    JOptionPane.showMessageDialog(null, "Số lượng mua phải lớn hơn 0");
                } else {
                    getInfo();
                    Notification notification = new Notification(mainChinh, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Thêm sản phẩm thành công!");
                    notification.showNotification();
                    loadDataTableChiTietPhieu(chitietphieu);
                    actionbtn("update");
                    resetForm();
                }
            }
        });

        btnEditSP.addActionListener((ActionEvent e) -> {
            int index = tablePhieuXuat.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiên bản cần sửa");
            } else {
                ChiTietPhieuDTO ctPhieuEdit = chitietphieu.get(index);
                int maphienban = ctPhieuEdit.getMaphienbansp();
                ArrayList<ChiTietSanPhamDTO> ctSpEdit = new ArrayList<>();
                for (ChiTietSanPhamDTO chiTietSanPhamDTO : chitietsanpham) {
                    if (chiTietSanPhamDTO.getMaphienbansp() != maphienban) {
                        ctSpEdit.add(chiTietSanPhamDTO);
                    }
                } 
                chitietphieu.remove(index);
                chitietsanpham = ctSpEdit;
                if (checkInfo()) {
                    getInfo();
                    Notification notification = new Notification(mainChinh, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Sửa sản phẩm thành công!");
                    notification.showNotification();
                    loadDataTableChiTietPhieu(chitietphieu);
                    actionbtn("update");
                    resetForm();

                }

            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tablePhieuXuat.getSelectedRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiên bản cần xóa");
                } else {
                    ChiTietPhieuDTO ctPhieuDel = chitietphieu.get(index);
                    int maphienban = ctPhieuDel.getMaphienbansp();
                    ArrayList<ChiTietSanPhamDTO> ctSpDel = new ArrayList<>();
                    for (ChiTietSanPhamDTO chiTietSanPhamDTO : chitietsanpham) {
                        if (chiTietSanPhamDTO.getMaphienbansp() != maphienban) {
                            ctSpDel.add(chiTietSanPhamDTO);
                        }
                    }
                    chitietphieu.remove(index);
                    chitietsanpham = ctSpDel;
                    loadDataTableChiTietPhieu(chitietphieu);
                    resetForm();
                }
            }
        });
        

        // btnImport.addActionListener((ActionEvent e) -> {
        //     JOptionPane.showMessageDialog(this, "Chức năng không khả dụng !", "Thông báo", JOptionPane.WARNING_MESSAGE);
        // });

        left_top.add(content_top, BorderLayout.CENTER);
        left_top.add(content_btn, BorderLayout.SOUTH);

        main = new JPanel();
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(0, 280));
        main.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.add(scrollTablePhieuNhap);
        left.add(left_top, BorderLayout.CENTER);
        left.add(main, BorderLayout.SOUTH);

        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(320, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_tongtien;
        right_top = new JPanel(new GridLayout(2, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 180));
        txtMaphieu = new InputForm("Mã phiếu xuất");
        txtMaphieu.setEditable(false);
        txtNhanVien = new InputForm("Nhân viên xuất");
        txtNhanVien.setEditable(false);
        maphieu = PhieuXuatDAO.getInstance().getAutoIncrement();
        manv = tk.getManv();
        txtMaphieu.setText("PX" + PhieuXuatDAO.getInstance().getAutoIncrement());
        NhanVienDTO nhanvien = NhanVienDAO.getInstance().selectById(tk.getManv() + "");
        txtNhanVien.setText(nhanvien.getHoten());

        right_top.add(txtMaphieu);
        right_top.add(txtNhanVien);

        right_center = new JPanel(new BorderLayout());
        JPanel khachJPanel = new JPanel(new BorderLayout());
        khachJPanel.setPreferredSize(new Dimension(0, 40));
        khachJPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        khachJPanel.setOpaque(false);
        JPanel kJPanelLeft = new JPanel(new GridLayout(1, 1));
        kJPanelLeft.setOpaque(false);
        kJPanelLeft.setPreferredSize(new Dimension(40, 0));
        ButtonCustom btnKh = new ButtonCustom("Chọn khách hàng", "success", 14);
        kJPanelLeft.add(btnKh);
        btnKh.addActionListener((ActionEvent e) -> {
            ListKhachHang listkh = new ListKhachHang(TaoPhieuXuat.this, owner, "Chọn khách hàng", true);
        });

        txtKh = new JTextField("");
        txtKh.setEditable(false);
        khachJPanel.add(kJPanelLeft, BorderLayout.EAST);
        khachJPanel.add(txtKh, BorderLayout.CENTER);
        JPanel khPanel = new JPanel(new GridLayout(2, 1, 5, 0));
        khPanel.setBackground(Color.WHITE);
        khPanel.setPreferredSize(new Dimension(0, 80));
        JLabel khachKhangJLabel = new JLabel("Khách hàng");
        khachKhangJLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        khPanel.add(khachKhangJLabel);
        khPanel.add(khachJPanel);

        right_center.add(khPanel, BorderLayout.NORTH);
        right_center.setOpaque(false);

        right_bottom = new JPanel(new GridLayout(2, 1));
        right_bottom.setPreferredSize(new Dimension(300, 100));
        right_bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        right_bottom.setOpaque(false);

        pn_tongtien = new JPanel(new FlowLayout(1, 20, 0));
        pn_tongtien.setOpaque(false);
        JLabel lbltien = new JLabel("TỔNG TIỀN: ");
        lbltien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltongtien = new JLabel("0đ");
        lbltongtien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltien.setForeground(new Color(255, 51, 51));
        pn_tongtien.add(lbltien);
        pn_tongtien.add(lbltongtien);

        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableSanPham.getSelectedRow();
                if (index != -1) {
                    setInfoSanPham(listSP.get(index));
                }
                if (!checkTonTai()) {
                    actionbtn("add");
                } else {
                    actionbtn("update");
                }
            }
        });

        tablePhieuXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tablePhieuXuat.getSelectedRow();
                if (index != -1) {
                    actionbtn("update");
                    setPhieuSelected();
                }
            }
        });
        btnNhapHang = new ButtonCustom("Xuất hàng", "excel", 14);
        btnQuayLai = new ButtonCustom("Quay lại", "excel", 14);
        right_bottom.add(pn_tongtien);
        if (type.equals("create")) {
            right_bottom.add(btnNhapHang);
        } else if (type.equals("detail")) {
            right_bottom.add(btnQuayLai);
        }

        btnNhapHang.addActionListener((ActionEvent e) -> {
            if (chitietphieu.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
            } else if (makh == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng");
            } else {
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo phiếu xuất !", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    long now = System.currentTimeMillis();
                    Timestamp currenTime = new Timestamp(now);
                    PhieuXuatDTO phieuXuat = new PhieuXuatDTO(makh, maphieu, tk.getManv(), currenTime, sum, 1);
                    phieuXuatBUS.insert(phieuXuat, chitietphieu);
                    chiTietSanPhamBUS.updateXuat(chitietsanpham);
                    JOptionPane.showMessageDialog(null, "Xuất hàng thành công !");
                    mainChinh.setPanel(new PhieuXuat(mainChinh, tk));
                }
            }
        });

        btnQuayLai.addActionListener((ActionEvent e) -> {
            PhieuXuat phieuXuatPanel = new PhieuXuat(mainChinh, tk);
            mainChinh.setPanel(phieuXuatPanel);
        });

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
        actionbtn("add");
    }

    public void loadDataTalbeSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSP.setRowCount(0);
        for (DTO.SanPhamDTO sp : result) {
            tblModelSP.addRow(new Object[]{sp.getMasp(), sp.getTensp(), sp.getSoluongton()});
        }
    }

    public void setInfoSanPham(SanPhamDTO sp) {
        this.txtMaSp.setText(Integer.toString(sp.getMasp()));
        this.txtTenSp.setText(sp.getTensp());
        this.textAreaIsbn.setText("");
        ch = phienBanBus.getAll(sp.getMasp());
        int size = ch.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ngonnguBUS.getNgonNguById(ch.get(i).getNgonngu()) + "-" 
                    + loaibiaBUS.getLoaiBiaById(ch.get(i).getLoaibia()) + "-" + ch.get(i).getSotrang();
        }
        this.cbxPhienBan.setArr(arr);
        mapb = ch.get(0).getMaphienbansp();
        setImeiByPb(mapb);
    }

    public boolean checkInfo() {
        boolean check = true;
        if (txtMaSp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
            check = false;
        } else if (txtSoLuongMua.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng mua");
            check = false;
        }

        return check;
    }

    public ChiTietPhieuDTO getInfo() {
        int masp = Integer.parseInt(txtMaSp.getText());
        int macauhinh = mapb;
        int dongia = Integer.parseInt(txtGiaXuat.getText());
        int soLuongMua = Integer.parseInt(txtSoLuongMua.getText());
        int soLuong = soLuongMua;
        ChiTietPhieuDTO ctpx = new ChiTietPhieuDTO(maphieu, mapb, soLuong, dongia);
        chitietphieu.add(ctpx);
        return null;
    }

    public int getChiTietSp() {
        String[] arrisbn = textAreaIsbn.getText().split("\n");
        for (int i = 0; i < arrisbn.length; i++) {
            ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(arrisbn[i], mapb, 0, maphieu, 0);
            chitietsanpham.add(ct);
        }
        return arrisbn.length;
    }

    public void actionbtn(String type) {
        boolean val_1 = type.equals("add");
        boolean val_2 = type.equals("update");
        btnAddSp.setEnabled(val_1);
        // btnImport.setEnabled(val_1);
        btnEditSP.setEnabled(val_2);
        btnDelete.setEnabled(val_2);
        content_btn.revalidate();
        content_btn.repaint();
    }

    public boolean checkTonTai() {
        boolean check = false;
        int pb = ch.get(cbxPhienBan.getSelectedIndex()).getMaphienbansp();
        for (ChiTietPhieuDTO chiTietPhieu : chitietphieu) {
            if (chiTietPhieu.getMaphienbansp() == pb) {
                return true;
            }
        }
        return check;
    }

    public void setImeiByPb(int mapb) {
        ctpb = ChiTietSanPhamDAO.getInstance().selectAllbyPb(mapb);
        PhienBanSanPhamDTO pbsp = phienBanBus.getByMaPhienBan(mapb);
        txtGiaXuat.setText(pbsp.getGiaxuat() + "");
        txtSoluongTon.setText(pbsp.getSoluongton()+"");
        textAreaIsbn.setText("");
        for (int i = 0; i < ctpb.size(); i++) {
            for (ChiTietSanPhamDTO chiTietSanPhamDTO : chitietsanpham) {
                if (chiTietSanPhamDTO.getIsbn().equals(ctpb.get(i).getIsbn())) {
                    textAreaIsbn.append(chiTietSanPhamDTO.getIsbn() + "\n");
                }
            }
        }
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuDTO> ctPhieu) {
        tblModel.setRowCount(0);
        int size = ctPhieu.size();
        sum = 0;
        for (int i = 0; i < size; i++) {
            PhienBanSanPhamDTO phienban = phienBanBus.getByMaPhienBan(ctPhieu.get(i).getMaphienbansp());
            sum += ctPhieu.get(i).getDongia() * ctPhieu.get(i).getSoluong();
            tblModel.addRow(new Object[]{
                i + 1, phienban.getMasp(), spBUS.getByMaSP(phienban.getMasp()).getTensp(), ngonnguBUS.getNgonNguById(phienban.getNgonngu()),
                loaibiaBUS.getLoaiBiaById(phienban.getLoaibia()),
                Formater.FormatVND(ctPhieu.get(i).getDongia()), ctPhieu.get(i).getSoluong()
            });
        }
        lbltongtien.setText(Formater.FormatVND(sum));
    }

    public void setKhachHang(int index) {
        makh = index;
        KhachHangDTO khachhang = khachHangBUS.selectKh(makh);
        txtKh.setText(khachhang.getHoten());
    }

    public void setPhieuSelected() {
        ChiTietPhieuDTO ctphieu = chitietphieu.get(tablePhieuXuat.getSelectedRow());
        SanPhamDTO spSel = spBUS.getSp(ctphieu.getMaphienbansp());
        setInfoSanPham(spSel);
        cbxPhienBan.setSelectedItem(ctphieu.getMaphienbansp() + "");
    }

    public void resetForm() {
        this.txtMaSp.setText("");
        this.txtTenSp.setText("");
        String[] arr = {"Chọn sản phẩm"};
        this.cbxPhienBan.setArr(arr);
        this.txtGiaXuat.setText("");
        this.txtSoluongTon.setText("");
        this.textAreaIsbn.setText("");
    }
}
