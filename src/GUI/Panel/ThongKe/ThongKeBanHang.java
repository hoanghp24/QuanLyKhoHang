/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel.ThongKe;

import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.PhieuXuatBUS;
import BUS.ThongKeBUS;
import DAO.ThongKeDAO;
import DTO.ThongKe.ThongKeBanHangDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Dialog.ChiTietPhieuDialog;
import GUI.Dialog.ThongKePBSPTonKho;
import GUI.Dialog.ThongKe.ThongKeNhapXuatDialog;

import com.microsoft.schemas.office.word.BorderbottomDocument;
import helper.Formater;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;

public class ThongKeBanHang
        extends JPanel
        implements ActionListener, KeyListener, PropertyChangeListener {

    PanelBorderRadius nhapxuat_left, nhapxuat_center;
    JTable tblBH;
    JScrollPane scrollTblTonKho;
    DefaultTableModel tblModel;
    InputForm nhanviennhap;
    InputDate start_date, end_date;
    ButtonCustom export, reset;
    ThongKeBUS thongkebus;
    ArrayList<ThongKeBanHangDTO> list;
    NhanVienBUS nVienBUS = new NhanVienBUS();
    JLabel lbsoluong, lbtongchiphi;
    PhieuXuatBUS pxBUS = new PhieuXuatBUS();

    public ThongKeBanHang(ThongKeBUS thongkebus) {
        this.thongkebus = thongkebus;
        list = thongkebus.getAllBanHang();
        initComponent();
        loadDataTable(list);
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        nhapxuat_left = new PanelBorderRadius();
        nhapxuat_left.setPreferredSize(new Dimension(300, 100));
        nhapxuat_left.setLayout(new BorderLayout());
        nhapxuat_left.setBorder(new EmptyBorder(0, 0, 0, 5));
        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300, 360));
        nhapxuat_left.add(left_content, BorderLayout.NORTH);
        lbsoluong = new JLabel("Tổng số đơn: 0");
        lbtongchiphi = new JLabel("Tông doanh thu: 0 VND");

        nhanviennhap = new InputForm("Tìm kiếm đơn hàng");
        nhanviennhap
                .getTxtForm()
                .putClientProperty("JTextField.showClearButton", true);
        nhanviennhap.getTxtForm().addKeyListener(this);
        start_date = new InputDate("Từ ngày");
        end_date = new InputDate("Đến ngày");
        start_date.getDateChooser().addPropertyChangeListener(this);
        end_date.getDateChooser().addPropertyChangeListener(this);
        JPanel btn_layout = new JPanel(new BorderLayout());
        JPanel btninner = new JPanel(new GridLayout(1, 2));
        btninner.setOpaque(false);
        btn_layout.setPreferredSize(new Dimension(30, 36));
        btn_layout.setBorder(new EmptyBorder(20, 10, 0, 10));
        btn_layout.setBackground(Color.white);
        export = new ButtonCustom("Xuất Excel", "excel", 14);
        reset = new ButtonCustom("Làm mới", "danger", 14);

        export.addActionListener(this);
        reset.addActionListener(this);

        btninner.add(export);
        btninner.add(reset);
        btn_layout.add(btninner, BorderLayout.NORTH);

        left_content.add(nhanviennhap);
        left_content.add(start_date);
        left_content.add(end_date);
        left_content.add(btn_layout);

        nhapxuat_center = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(nhapxuat_center, BoxLayout.Y_AXIS);
        nhapxuat_center.setLayout(boxly);

        // Set the font size and color
        Font labelFont = new Font("Arial", Font.BOLD, 20); // Adjust the font size as needed
        Color labelColor = Color.RED;

        // Set properties for lbsoluong
        lbsoluong.setFont(labelFont);
        lbsoluong.setForeground(labelColor);

        // Set properties for lbtongchiphi
        lbtongchiphi.setFont(labelFont);
        lbtongchiphi.setForeground(labelColor);
        // Create the labelPanel
        JPanel totalPanel = new JPanel(new GridLayout(1, 2));
        totalPanel.setBackground(Color.white);
        totalPanel.add(lbsoluong);
        totalPanel.add(lbtongchiphi);

        // Center the text in each label
        lbsoluong.setHorizontalAlignment(JLabel.CENTER);
        lbtongchiphi.setHorizontalAlignment(JLabel.CENTER);

        // Add the totalPanel to nhapxuat_center
        nhapxuat_center.add(totalPanel);

        tblBH = new JTable();
        scrollTblTonKho = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{
            "STT",
            "Mã đơn hàng",
            "Nhân viên bán hàng",
            "Thời gian",
            "Tổng số tiền",};
        tblModel.setColumnIdentifiers(header);
        tblBH.setModel(tblModel);
        tblBH.setAutoCreateRowSorter(true);
        tblBH.setDefaultEditor(Object.class, null);
        scrollTblTonKho.setViewportView(tblBH);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblBH.setDefaultRenderer(Object.class, centerRenderer);
        tblBH.setFocusable(false);
        tblBH.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblBH.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblBH.getColumnModel().getColumn(2).setPreferredWidth(200);

        TableSorter.configureTableColumnSorter(
                tblBH,
                0,
                TableSorter.INTEGER_COMPARATOR
        );
        TableSorter.configureTableColumnSorter(
                tblBH,
                1,
                TableSorter.INTEGER_COMPARATOR
        );
        TableSorter.configureTableColumnSorter(
                tblBH,
                3,
                TableSorter.INTEGER_COMPARATOR
        );
        TableSorter.configureTableColumnSorter(
                tblBH,
                4,
                TableSorter.VND_CURRENCY_COMPARATOR
        );

        nhapxuat_center.add(scrollTblTonKho);

        tblBH.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanHangClicked(evt);
            }
        });

        this.add(nhapxuat_left, BorderLayout.WEST);
        this.add(nhapxuat_center, BorderLayout.CENTER);
    }

    private void tblBanHangClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (tblBH.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn nhập");
            } else {
                ChiTietPhieuDialog ctp = new ChiTietPhieuDialog((JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), "Thông tin đơn hàng", true, pxBUS.getSelect(getRow()));
            }
        }
    }

    public int getRow() {
        return tblBH.getSelectedRow();
    }

    public boolean validateSelectDate() throws ParseException {
        java.util.Date time_start = start_date.getDate();
        java.util.Date time_end = end_date.getDate();

        java.util.Date current_date = new java.util.Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ngày bắt đầu không được lớn hơn ngày hiện tại",
                    "Lỗi !",
                    JOptionPane.ERROR_MESSAGE
            );
            start_date.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ngày kết thúc không được lớn hơn ngày hiện tại",
                    "Lỗi !",
                    JOptionPane.ERROR_MESSAGE
            );
            end_date.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ngày kết thúc phải lớn hơn ngày bắt đầu",
                    "Lỗi !",
                    JOptionPane.ERROR_MESSAGE
            );
            end_date.getDateChooser().setCalendar(null);
            return false;
        }
        return true;
    }

    public void Fillter() throws ParseException {
        if (validateSelectDate()) {
            String input = nhanviennhap.getText() != null
                    ? nhanviennhap.getText()
                    : "";
            java.util.Date time_start = start_date.getDate() != null
                    ? start_date.getDate()
                    : new java.util.Date(0);
            java.util.Date time_end = end_date.getDate() != null
                    ? end_date.getDate()
                    : new java.util.Date(System.currentTimeMillis());
            this.list
                    = thongkebus.FilterBanHang(
                            input,
                            new Date(time_start.getTime()),
                            new Date(time_end.getTime())
                    );
            loadDataTable(list);
        }
    }

    public void loadDataTable(ArrayList<ThongKeBanHangDTO> listphieunhap) {
        tblModel.setRowCount(0);
        int size = listphieunhap.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(
                    new Object[]{
                        i + 1,
                        "PX" + listphieunhap.get(i).getMaphieuxuat(),
                        nVienBUS.getNameById(listphieunhap.get(i).getNguoitao()),
                        listphieunhap.get(i).getThoigian(),
                        Formater.FormatVND(listphieunhap.get(i).getTongsotien()),}
            );

            // Update labels with new values
            lbsoluong.setText("Tổng số đơn: " + listphieunhap.get(i).getSoluong());
            lbtongchiphi.setText(
                    "Tổng tiền: " + Formater.FormatVND(listphieunhap.get(i).getTongtien())
            );
        }
    }

    public void resetForm() throws ParseException {
        nhanviennhap.setText("");
        start_date.getDateChooser().setCalendar(null);
        end_date.getDateChooser().setCalendar(null);
        Fillter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == export) {
            try {
                JTableExporter.exportJTableToExcel(tblBH);
            } catch (IOException ex) {
                Logger
                        .getLogger(ThongKeBanHang.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        } else if (source == reset) {
            try {
                resetForm();
            } catch (ParseException ex) {
                Logger
                        .getLogger(ThongKeBanHang.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            java.util.logging.Logger
                    .getLogger(ThongKeBanHang.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            java.util.logging.Logger
                    .getLogger(ThongKeBanHang.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
