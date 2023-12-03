/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DTO.KhachHangDTO;
import DTO.KhachHangDTO;
import GUI.Main;
import GUI.Component.ButtonCustom;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Panel.KhachHang;
import GUI.Panel.TaiKhoan;
import GUI.Panel.TaoPhieuXuat;
import helper.JTableExporter;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import BUS.KhachHangBUS;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;


public class ListKhachHang extends JDialog implements MouseListener, ItemListener {

    private TaoPhieuXuat taoPhieuXuat;
    private JTable tableKhachHang;
    private JScrollPane scrollTableSanPham;
    private DefaultTableModel tblModel;
    private ArrayList<KhachHangDTO> listKh = KhachHangDAO.getInstance().selectAll();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    public KhachHangBUS khachhangBUS = new KhachHangBUS();
    public ArrayList<KhachHangDTO> listkh = khachhangBUS.getAll();
    Main m;
    PanelBorderRadius main, functionBar, btnBar;
    MainFunction mainFunction;
    IntegratedSearch search;

    public ListKhachHang(TaoPhieuXuat taoPhieuXuat, JFrame owner, String title, boolean modal){
        super(owner, title, modal);
        this.taoPhieuXuat=taoPhieuXuat;
        init();
        loadDataTable(search(""));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init(){
        this.setSize(new Dimension(850,600));
        this.setLayout(new BorderLayout());
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 5, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 0));
        

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listKh = khachhangBUS.search(txt, type);
                loadDataTable(listKh);
            }
        });

        ButtonCustom buttonChoose = new ButtonCustom("Chọn khách hàng", "success", 14);
        ButtonCustom buttonAdd = new ButtonCustom("Thêm khách hàng", "success", 14);
        buttonChoose.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getRow()<0){
                    int input = JOptionPane.showConfirmDialog(null, 
                "Vui lòng chọn khách hàng!", "Thông báo", JOptionPane.DEFAULT_OPTION);
                } else{
                    taoPhieuXuat.setKhachHang(listKh.get(getRow()).getMaKH());
                    dispose();
                }
            }
            
        });
        
        buttonAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.out.println("ok");

                    openKhachHangDialog();
            }
            
        });


        btnBar = new PanelBorderRadius();
        btnBar.setBorder(new EmptyBorder(10, 10, 10, 0));
        btnBar.add(buttonChoose);        
        btnBar.add(buttonAdd);
        
        functionBar.add(search, BorderLayout.CENTER);
        this.add(btnBar, BorderLayout.PAGE_END);
        this.add(functionBar,BorderLayout.NORTH);
        JPanel jPanelTable = new JPanel();
        jPanelTable.setLayout(new GridLayout(1,1));
        tableKhachHang = new JTable();
        tableKhachHang.setFocusable(false);
        scrollTableSanPham = new JScrollPane();
        tableKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tableKhachHang = new JTable();
        tableKhachHang.setFocusable(false);
        tableKhachHang.setDefaultEditor(Object.class, null);
        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã KH","Họ tên","Địa chỉ","Số điện thoại"};
        tblModel.setColumnIdentifiers(header);
        tableKhachHang.setDefaultRenderer(Object.class, centerRenderer);
        tableKhachHang.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableKhachHang);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jPanelTable.add(scrollTableSanPham);
        this.add(jPanelTable,BorderLayout.CENTER);
        
    }
    public ListKhachHang(Main m) {
        this.m = m;
        init();
        tableKhachHang.setDefaultEditor(Object.class, null);
        loadDataTable(listkh);
    }

   
    public int getRow(){
        return tableKhachHang.getSelectedRow();
    }
    
    public void loadDataTable(ArrayList<KhachHangDTO> list) {
        listKh = list;
        tblModel.setRowCount(0);
        for (KhachHangDTO kh : listKh) {
            tblModel.addRow(new Object[]{
                kh.getMaKH(),kh.getHoten(),kh.getDiachi(),kh.getSdt()
            });
        }
    }

    private void openKhachHangDialog() {
        ListKhachHangDialog listKhachHangDialog = new ListKhachHangDialog(this, "Thêm Khách Hàng", true, "create");
    }
    
    
    
    public ArrayList<KhachHangDTO> search(String text) {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        if(text.length()>0){
            text = text.toLowerCase();
        for(KhachHangDTO i : listKh) {
           if(i.getHoten().toLowerCase().contains(text) || i.getDiachi().toLowerCase().contains(text)
                   || i.getSdt().toLowerCase().contains(text)){
               result.add(i);
           }
        }
        return result;
        } else {
            return KhachHangDAO.getInstance().selectAll();
        }
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        loadDataTable(listkh);
    }
}