package GUI.Dialog;

import BUS.PhienBanSanPhamBUS;
import BUS.TheLoaiBUS;
import BUS.KhuVucKhoBUS;
import BUS.LoaiBiaBUS;
import BUS.NgonNguBUS;
import BUS.NhaPhatHanhBUS;
import BUS.NhaXuatBanBUS;
import DAO.PhienBanSanPhamDAO;
import DAO.SanPhamDAO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import GUI.Panel.SanPham;
import helper.Formater;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

public final class SanPhamDialog extends JDialog implements ActionListener {

    private HeaderTitle titlePage;
    private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain, pncard2;
    private ButtonCustom btnThemCHMS, btnHuyBo, btnAddCauHinh, btnEditCTCauHinh, btnDeleteCauHinh, btnResetCauHinh, btnAddSanPham, btnBack, btnViewCauHinh;
    InputForm tenSP, tacgia;
    InputForm txtgianhap, txtgiaxuat, txtsotrang;
    SelectForm cbxngonngu, cbxloaibia, nhaxuatban, theloai;
    SelectForm khuvuc;
    InputImage hinhanh;
    JTable tblcauhinh;
    JLabel lbngayxb;
    InputDate jdngayxb;
    JScrollPane scrolltblcauhinh;
    DefaultTableModel tblModelch;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    GUI.Panel.SanPham jpSP;

    // NhaPhatHanhBUS nhaPhatHanhBUS = new NhaPhatHanhBUS();
    TheLoaiBUS theLoaiBUS = new TheLoaiBUS();
    NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS();
    KhuVucKhoBUS kvkhoBus = new KhuVucKhoBUS();
    LoaiBiaBUS loaiBiaBUS = new LoaiBiaBUS();
    NgonNguBUS ngonNguBUS = new NgonNguBUS();

    ArrayList<PhienBanSanPhamDTO> listch = new ArrayList<>();
    SanPhamDTO sp;
    String[] arrkhuvuc;
    String[] arrnhaxuatban;
    // String[] arrnhaphathanh;
    String[] arrtheloai;
    int masp;
    int mapb;
    private ButtonCustom btnEditCT;
    private ButtonCustom btnSaveCH;
    private ButtonCustom btnAddCauHinhEdit;
    private ButtonCustom btnEditCTCauHinhEdit;
    private ButtonCustom btnDeleteCauHinhEdit;
    private ButtonCustom btnResetCauHinhEdit;

    public void init(SanPham jpSP) {
        this.jpSP = jpSP;
        masp = jpSP.spBUS.spDAO.getAutoIncrement();
        mapb = PhienBanSanPhamDAO.getInstance().getAutoIncrement();
        arrkhuvuc = kvkhoBus.getArrTenKhuVuc();
        // arrnhaphathanh = nhaPhatHanhBUS.getArrTenNhaPhatHanh();
        arrnhaxuatban = nhaXuatBanBUS.getArrNhaXuatBan();
        arrtheloai = theLoaiBUS.getArrTheLoai();

    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        init(jpSP);
        initComponents(title, type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
        super(owner, title, modal);
        init(jpSP);
        this.sp = sp;
        this.listch = jpSP.spBUS.cauhinhBus.getAll(sp.getMasp());
        initComponents(title, type);
    }

    public void initCardOne(String type) {
        pnCenter = new JPanel(new BorderLayout());
        pninfosanpham = new JPanel(new GridLayout(3, 4, 0, 0));
        pninfosanpham.setBackground(Color.WHITE);
        pnCenter.add(pninfosanpham, BorderLayout.CENTER);

        pninfosanphamright = new JPanel();
        pninfosanphamright.setBackground(Color.WHITE);
        pninfosanphamright.setPreferredSize(new Dimension(300, 600));
        pninfosanphamright.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnCenter.add(pninfosanphamright, BorderLayout.WEST);

        tenSP = new InputForm("Tên sản phẩm");
        tacgia = new InputForm("Tác giả");
        // nhaphathanh = new SelectForm("Nhà phát hành", arrnhaphathanh);
        nhaxuatban = new SelectForm("Nhà xuất bản", arrnhaxuatban);
        theloai = new SelectForm("Thể loại", arrtheloai);
        khuvuc = new SelectForm("Chi nhánh", arrkhuvuc);
        hinhanh = new InputImage("Hình minh họa");

        pninfosanpham.add(tenSP);
        pninfosanpham.add(tacgia);
        // pninfosanpham.add(nhaphathanh);
        pninfosanpham.add(nhaxuatban);
        pninfosanpham.add(theloai);
        pninfosanpham.add(khuvuc);
        pninfosanphamright.add(hinhanh);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        switch (type) {
            case "view" -> {
                btnViewCauHinh = new ButtonCustom("Xem phiên bản", "warning", 14);
                btnViewCauHinh.addActionListener(this);
                pnbottom.add(btnViewCauHinh);
            }
            case "update" -> {
                btnSaveCH = new ButtonCustom("Lưu thông tin", "success", 14);
                btnEditCT = new ButtonCustom("Sửa phiên bản", "warning", 14);
                btnSaveCH.addActionListener(this);
                btnEditCT.addActionListener(this);
                pnbottom.add(btnSaveCH);
                pnbottom.add(btnEditCT);
            }
            case "create" -> {
                btnThemCHMS = new ButtonCustom("Tạo phiên bản", "success", 14);
                btnThemCHMS.addActionListener(this);
                pnbottom.add(btnThemCHMS);
            }
        }

        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);
        pnbottom.add(btnHuyBo);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
    }

    public void initCardTwo(String type) {
        pncard2 = new JPanel(new BorderLayout());
        JPanel cauhinhtop = new JPanel(new GridLayout(1, 6));
        cbxngonngu = new SelectForm("Ngôn ngữ", ngonNguBUS.getArrTenNgonNgu());
        cbxloaibia = new SelectForm("Loại bìa", loaiBiaBUS.getArrTenLoaibia());
        txtsotrang = new InputForm("Số trang");
        PlainDocument trang = (PlainDocument) txtsotrang.getTxtForm().getDocument();
        trang.setDocumentFilter((new NumericDocumentFilter()));
        txtgianhap = new InputForm("Giá nhập");
        PlainDocument nhap = (PlainDocument) txtgianhap.getTxtForm().getDocument();
        nhap.setDocumentFilter((new NumericDocumentFilter()));
        txtgiaxuat = new InputForm("Giá bán");
        PlainDocument xuat = (PlainDocument) txtgiaxuat.getTxtForm().getDocument();
        xuat.setDocumentFilter((new NumericDocumentFilter()));
        JPanel jpaneljd = new JPanel();
        jpaneljd.setBorder(new EmptyBorder(10, 10, 10, 10));
        lbngayxb = new JLabel("Ngày xuất bản");
        lbngayxb.setSize(new Dimension(100, 100));
        jpaneljd.setSize(new Dimension(500, 100));
        jpaneljd.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpaneljd.setBackground(Color.white);
        jdngayxb = new InputDate("Ngày xuất bản");
        jdngayxb.setSize(new Dimension(100, 100));
        jpaneljd.add(lbngayxb);
        jpaneljd.add(jdngayxb);
        cauhinhtop.add(cbxngonngu);
        cauhinhtop.add(cbxloaibia);
        cauhinhtop.add(txtsotrang);
        cauhinhtop.add(txtgianhap);
        cauhinhtop.add(txtgiaxuat);
        cauhinhtop.add(jdngayxb);

        JPanel cauhinhcenter = new JPanel(new BorderLayout());

        JPanel cauhinhcenter_left = new JPanel();
        BoxLayout bl = new BoxLayout(cauhinhcenter_left, BoxLayout.Y_AXIS);
        cauhinhcenter_left.setPreferredSize(new Dimension(100, 226));
        cauhinhcenter_left.setBorder(new EmptyBorder(10, 10, 10, 10));
        cauhinhcenter_left.setLayout(bl);
        cauhinhcenter_left.setBackground(Color.WHITE);
        tblcauhinh = new JTable();
        tblcauhinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = getRowCauHinh();
                if (index != -1) {
                    setInfoCauHinh(listch.get(index));
                }
            }
        });

        scrolltblcauhinh = new JScrollPane(tblcauhinh);
        tblModelch = new DefaultTableModel();
        String[] header = new String[]{"STT", "Ngôn ngữ", "Loại bìa", "Số trang", "Giá nhập", "Giá bán", "Ngày xuất bản", "Số lượng"};
        tblModelch.setColumnIdentifiers(header);
        tblcauhinh.setModel(tblModelch);
        scrolltblcauhinh.setViewportView(tblcauhinh);
        tblcauhinh.setDefaultRenderer(Object.class, centerRenderer);
        cauhinhcenter_left.add(scrolltblcauhinh);

        JPanel cauhinhcenter_right = new JPanel(new FlowLayout());
        cauhinhcenter_right.setPreferredSize(new Dimension(180, 10));
        cauhinhcenter_right.setBackground(Color.white);
        cauhinhcenter_right.setBorder(new EmptyBorder(0, 0, 0, 10));
        if (!type.equals("update")) {
            btnAddCauHinh = new ButtonCustom("Thêm phiên bản", "success", 14);
            btnEditCTCauHinh = new ButtonCustom("Sửa phiên bản", "warning", 14);
            btnDeleteCauHinh = new ButtonCustom("Xoá phiên bản", "danger", 14);
            btnResetCauHinh = new ButtonCustom("Làm mới", "excel", 14);

            btnAddCauHinh.addActionListener(this);
            btnEditCTCauHinh.addActionListener(this);
            btnDeleteCauHinh.addActionListener(this);
            btnResetCauHinh.addActionListener(this);
            cauhinhcenter_right.add(btnAddCauHinh);
            cauhinhcenter_right.add(btnEditCTCauHinh);
            cauhinhcenter_right.add(btnDeleteCauHinh);
            cauhinhcenter_right.add(btnResetCauHinh);
        } else {
            btnAddCauHinhEdit = new ButtonCustom("Thêm phiên bản", "success", 14);
            btnEditCTCauHinhEdit = new ButtonCustom("Sửa phiên bản", "warning", 14);
            btnDeleteCauHinhEdit = new ButtonCustom("Xoá phiên bản", "danger", 14);
            btnResetCauHinhEdit = new ButtonCustom("Làm mới", "excel", 14);

            btnAddCauHinhEdit.addActionListener(this);
            btnEditCTCauHinhEdit.addActionListener(this);
            btnDeleteCauHinhEdit.addActionListener(this);
            btnResetCauHinhEdit.addActionListener(this);

            cauhinhcenter_right.add(btnAddCauHinhEdit);
            cauhinhcenter_right.add(btnEditCTCauHinhEdit);
            cauhinhcenter_right.add(btnDeleteCauHinhEdit);
            cauhinhcenter_right.add(btnResetCauHinhEdit);
        }

        cauhinhcenter.add(cauhinhcenter_left, BorderLayout.CENTER);
        cauhinhcenter.add(cauhinhcenter_right, BorderLayout.EAST);

        JPanel cauhinhbottom = new JPanel(new FlowLayout());
        cauhinhbottom.setBackground(Color.white);
        cauhinhbottom.setBorder(new EmptyBorder(0, 0, 10, 0));

        switch (type) {
            case "view" -> {
                loadDataToTablePhienBan(listch);
                btnAddCauHinh.setVisible(false);
                btnEditCTCauHinh.setVisible(false);
                btnDeleteCauHinh.setVisible(false);
                btnResetCauHinh.setVisible(false);
                cauhinhcenter.remove(cauhinhcenter_right);
            }
            case "update" ->
                loadDataToTablePhienBan(listch);
            case "create" -> {
                btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnAddSanPham.addActionListener(this);
                cauhinhbottom.add(btnAddSanPham);
            }
        }

        btnBack = new ButtonCustom("Quay lại trang trước", "warning", 14);
        btnBack.addActionListener(this);
        cauhinhbottom.add(btnBack);

        pncard2.add(cauhinhtop, BorderLayout.NORTH);
        pncard2.add(cauhinhcenter, BorderLayout.CENTER);
        pncard2.add(cauhinhbottom, BorderLayout.SOUTH);

    }

    public void initComponents(String title, String type) {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(1150, 480));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new CardLayout());

        initCardOne(type);
        initCardTwo(type);

        pnmain.add(pnCenter);
        pnmain.add(pncard2);

        switch (type) {
            case "view" -> {
                tenSP.setDisable();
                tacgia.setDisable();
                nhaxuatban.setDisable();
                theloai.setDisable();
                khuvuc.setDisable();
                cbxngonngu.setDisable();
                cbxloaibia.setDisable();
                txtgianhap.setDisable();
                txtgiaxuat.setDisable();
                jdngayxb.setDisable();
                txtsotrang.setDisable();
                hinhanh.setUnable();
                setInfo(sp);
            }
            case "update" ->
                setInfo(sp);
            default -> {
            }
        }
//                throw new AssertionError();

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public String addImage(String urlImg) {
        Random randomGenerator = new Random();
        int ram = randomGenerator.nextInt(1000);
        File sourceFile = new File(urlImg);
        String destPath = "./src/media/product";
        File destFolder = new File(destPath);
        String newName = ram + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
        } catch (IOException e) {
        }
        return newName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnThemCHMS && validateCardOne()) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnBack) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.previous(pnmain);
        } else if (source == btnAddCauHinh) {
            try {
                if (validateCardTwo() && checkTonTai()) {
                    listch.add(getCauHinh());
                    loadDataToTablePhienBan(this.listch);
                    resetFormCauHinh();
                }
            } catch (HeadlessException | ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (source == btnResetCauHinh) {
            resetFormCauHinh();
            loadDataToTablePhienBan(this.listch);
        } else if (source == btnDeleteCauHinh) {
            int index = getRowCauHinh();
            this.listch.remove(index);
            loadDataToTablePhienBan(this.listch);
            resetFormCauHinh();
        } else if (source == btnEditCTCauHinh) {
            eventEditCauHinh();
            loadDataToTablePhienBan(this.listch);
        } else if (source == btnAddSanPham) {
            eventAddSanPham();
        } else if (source == btnViewCauHinh) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnEditCT) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnSaveCH) {
            SanPhamDTO snNew = getInfo();
            
            if (!snNew.getHinhanh().equals(this.sp.getHinhanh())) {
                snNew.setHinhanh(addImage(snNew.getHinhanh()));
            }
            snNew.setMasp(this.sp.getMasp());
            SanPhamDAO.getInstance().update(sp);
            this.jpSP.spBUS.update(snNew);
            this.jpSP.loadDataTalbe(this.jpSP.spBUS.getAll());
            JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!");
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        }
        if (source == btnEditCTCauHinhEdit) {
            if (validateCardTwo()) {
                int index = getRowCauHinh();
                if (index < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn phiên bản");
                } else {
                    try {
                        listch.get(index).setNgonngu(ngonNguBUS.getByIndex(cbxngonngu.getSelectedIndex()).getMangonngu());
                        listch.get(index).setLoaibia(loaiBiaBUS.getByIndex(cbxloaibia.getSelectedIndex()).getMaloaibia());
                        listch.get(index).setSotrang(Integer.parseInt(txtsotrang.getText()));
                        listch.get(index).setGianhap(Integer.parseInt(txtgianhap.getText()));
                        listch.get(index).setGiaxuat(Integer.parseInt(txtgiaxuat.getText()));
        
                        // Handle date parsing
                        java.util.Date date = jdngayxb.getDate();
                        java.sql.Date ngayxb = new java.sql.Date(date.getTime());
                        listch.get(index).setNgayxb(ngayxb);
        
                        PhienBanSanPhamDAO.getInstance().update(listch.get(index));
                        loadDataToTablePhienBan(this.listch);
                        resetFormCauHinh();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        // Handle the ParseException, e.g., show an error message
                        JOptionPane.showMessageDialog(this, "Ngày xuất bản không hợp lệ");
                    }
                }

            }
        }
        if (source == btnDeleteCauHinhEdit) {
            int index = getRowCauHinh();
            PhienBanSanPhamDAO.getInstance().delete(this.listch.get(index).getMaphienbansp() + "");
            this.listch.remove(index);
            loadDataToTablePhienBan(this.listch);
            resetFormCauHinh();
        }
        if (source == btnAddCauHinhEdit) {
            try {
                if (validateCardTwo() && checkTonTai()) {
                    PhienBanSanPhamDAO.getInstance().insert(getCauHinh(sp.getMasp()));
                    loadDataToTablePhienBan(this.listch);
                    resetFormCauHinh();
                }
            } catch (HeadlessException | ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if (source == btnResetCauHinhEdit) {
            resetFormCauHinh();
            loadDataToTablePhienBan(this.listch);
        }
        if (source == btnHuyBo) {
            dispose();
        }
    }

    public void eventAddSanPham() {
        SanPhamDTO sp = getInfo();
        sp.setHinhanh(addImage(sp.getHinhanh()));
        if (jpSP.spBUS.add(sp, listch)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        }
    }

    public void eventEditCauHinh() {
        if (validateCardTwo()) {
            int index = getRowCauHinh();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phiên bản");
            } else {
                try {
                    listch.get(index).setNgonngu(ngonNguBUS.getByIndex(cbxngonngu.getSelectedIndex()).getMangonngu());
                    listch.get(index).setLoaibia(loaiBiaBUS.getByIndex(cbxloaibia.getSelectedIndex()).getMaloaibia());
                    listch.get(index).setSotrang(Integer.parseInt(txtsotrang.getText()));
                    listch.get(index).setGianhap(Integer.parseInt(txtgianhap.getText()));
                    listch.get(index).setGiaxuat(Integer.parseInt(txtgiaxuat.getText()));
    
                    // Handle date parsing
                    java.util.Date date = jdngayxb.getDate();
                    java.sql.Date ngayxb = new java.sql.Date(date.getTime());
                    listch.get(index).setNgayxb(ngayxb);
    
                    PhienBanSanPhamDAO.getInstance().update(listch.get(index));
                    loadDataToTablePhienBan(this.listch);
                    resetFormCauHinh();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    // Handle the ParseException, e.g., show an error message
                    JOptionPane.showMessageDialog(this, "Ngày xuất bản không hợp lệ");
                }
            }

        }
    }

    public int getRowCauHinh() {
        int index = tblcauhinh.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiên bản !");
        }
        return index;
    }

    public SanPhamDTO getInfo() {
        String hinhanh = this.hinhanh.getUrl_img();
        String vtensp = tenSP.getText();
        String vtacgia = tacgia.getText();
        int vnhaxuatban = nhaXuatBanBUS.getAll().get(this.nhaxuatban.getSelectedIndex()).getManxb();
        // int vnhaphathanh = nhaPhatHanhBUS.getAll().get(this.nhaphathanh.getSelectedIndex()).getManph();
        int vtheloai = theLoaiBUS.getAll().get(this.theloai.getSelectedIndex()).getMatheloai();
        int khuvuckho = kvkhoBus.getAll().get(this.khuvuc.getSelectedIndex()).getMakhuvuc();
        SanPhamDTO result = new SanPhamDTO(masp, vtensp, hinhanh, vtacgia, vnhaxuatban, vtheloai, khuvuckho, 0);
        return result;
    }

    public void setInfo(SanPhamDTO sp) {
        hinhanh.setUrl_img(sp.getHinhanh());
        tenSP.setText(sp.getTensp());
        tacgia.setText(sp.getTacgia());
        nhaxuatban.setSelectedIndex(nhaXuatBanBUS.getIndexByMaNXB(sp.getNhaxuatban()));
        // nhaphathanh.setSelectedIndex(sp.getNhaphathanh());
        theloai.setSelectedIndex(theLoaiBUS.getIndexByMaTL(sp.getTheloai()));
        khuvuc.setSelectedIndex(kvkhoBus.getIndexByMaKVK(sp.getKhuvuckho()));
        
    }

    public PhienBanSanPhamDTO getCauHinh() throws ParseException {
        int ngonngu = ngonNguBUS.getByIndex(cbxngonngu.getSelectedIndex()).getMangonngu();
        int loaibia = loaiBiaBUS.getByIndex(cbxloaibia.getSelectedIndex()).getMaloaibia();
        int sotrang = Integer.parseInt(txtsotrang.getText());
        int gianhap = Integer.parseInt(txtgianhap.getText());
        int giaban = Integer.parseInt(txtgiaxuat.getText());
        java.util.Date date = jdngayxb.getDate();
        java.sql.Date ngayxb = new java.sql.Date(date.getTime());
        int soLuongton = 0;
        PhienBanSanPhamDTO chsp = new PhienBanSanPhamDTO(mapb, masp, ngonngu, loaibia, sotrang, gianhap, giaban, ngayxb, soLuongton);
        mapb++;
        return chsp;
    }

    public PhienBanSanPhamDTO getCauHinh(int masanpham) throws ParseException {
        int ngonngu = ngonNguBUS.getByIndex(cbxngonngu.getSelectedIndex()).getMangonngu();
        int loaibia = loaiBiaBUS.getByIndex(cbxloaibia.getSelectedIndex()).getMaloaibia();
        int sotrang = Integer.parseInt(txtsotrang.getText());
        int gianhap = Integer.parseInt(txtgianhap.getText());
        int giaban = Integer.parseInt(txtgiaxuat.getText());
        java.util.Date date = jdngayxb.getDate();
        java.sql.Date ngayxb = new java.sql.Date(date.getTime());
        PhienBanSanPhamDTO chsp = new PhienBanSanPhamDTO(PhienBanSanPhamDAO.getInstance().getAutoIncrement(), masanpham, ngonngu, loaibia, sotrang, gianhap, giaban, ngayxb, 0);
        this.listch.add(chsp);
        return chsp;
    }

    public boolean validateCardOne() {
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText()) || Validation.isEmpty(tacgia.getText()) 
                || Validation.isEmpty((String) nhaxuatban.getSelectedItem()) || Validation.isEmpty((String) theloai.getSelectedItem())
                || Validation.isEmpty((String) khuvuc.getSelectedItem())) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            // Check number 
        }
        return check;
    }

    public boolean validateCardTwo() {
        boolean check = true;
        if (Validation.isEmpty(txtgianhap.getText()) && Validation.isEmpty(txtgiaxuat.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
            check = false;
        } else {

        }
        return check;
    }

    public boolean checkTonTai() throws HeadlessException, ParseException {
        boolean check = true;
        if (PhienBanSanPhamBUS.checkDuplicate(listch, getCauHinh())) {
            JOptionPane.showMessageDialog(this, "Phiên bản đã tồn tại !");
            check = false;
        }
        return check;
    }

    public void loadDataToTablePhienBan(ArrayList<PhienBanSanPhamDTO> ch) {
        tblModelch.setRowCount(0);
        for (int i = 0; i < ch.size(); i++) {
            String ngonngu = ngonNguBUS.getTenNgonNgu(ch.get(i).getNgonngu());
            String loaibia = loaiBiaBUS.getTenLoaibia(ch.get(i).getLoaibia());
            tblModelch.addRow(new Object[]{i + 1, ngonngu, loaibia, ch.get(i).getSotrang(),
                Formater.FormatVND(ch.get(i).getGianhap()), Formater.FormatVND(ch.get(i).getGiaxuat()),
                ch.get(i).getNgayxb(), ch.get(i).getSoluongton()
            });
        }
    }

    public void resetFormCauHinh() {
        cbxngonngu.setSelectedIndex(0);
        cbxloaibia.setSelectedIndex(0);
        txtsotrang.setText("");
        txtgianhap.setText("");
        txtgiaxuat.setText("");
        lbngayxb.setText("");
    }

    public void setInfoCauHinh(PhienBanSanPhamDTO ch) {
        cbxngonngu.setSelectedIndex(ngonNguBUS.getIndexByMaNN(ch.getNgonngu()));
        cbxloaibia.setSelectedIndex(loaiBiaBUS.getIndexByMaLB(ch.getLoaibia()));
        txtsotrang.setText(Integer.toString(ch.getSotrang()));
        txtgianhap.setText(Integer.toString(ch.getGianhap()));
        txtgiaxuat.setText(Integer.toString(ch.getGiaxuat()));
        jdngayxb.setDate(ch.getNgayxb());
    }
}
