/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DAO.KhachHangDAO;
import DAO.NhaPhatHanhDAO;
import DAO.NhanVienDAO;
import DTO.KhachHangDTO;
import DTO.NhaPhatHanhDTO;
import DTO.KhachHangDTO;
import GUI.Main;
import GUI.Component.ButtonCustom;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Panel.KhachHang;
import GUI.Panel.TaiKhoan;
import GUI.Panel.TaoPhieuNhap;
import GUI.Panel.TaoPhieuXuat;
import helper.JTableExporter;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import BUS.KhachHangBUS;
import BUS.NhaPhatHanhBUS;

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


public class ListNhaPhatHanh extends JDialog implements MouseListener, ItemListener {

    private TaoPhieuNhap taoPhieuNhap;
    private JTable tableNhaPH;
    private JScrollPane scrollTableSanPham;
    private DefaultTableModel tblModel;
    private ArrayList<NhaPhatHanhDTO> listNph = NhaPhatHanhDAO.getInstance().selectAll();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    public NhaPhatHanhBUS nhaPhatHanhBUS = new NhaPhatHanhBUS();
    public ArrayList<NhaPhatHanhDTO> listnph = nhaPhatHanhBUS.getAll();
    Main m;
    PanelBorderRadius main, functionBar, btnBar;
    MainFunction mainFunction;
    IntegratedSearch search;

    public ListNhaPhatHanh(TaoPhieuNhap taoPhieuNhap, JFrame owner, String title, boolean modal){
        super(owner, title, modal);
        this.taoPhieuNhap=taoPhieuNhap;
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
        

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã nhà phát hành", "Tên nhà phát hành", "Địa chỉ", "Số điện thoại", "Email"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listNph = nhaPhatHanhBUS.search(txt, type);
                loadDataTable(listNph);
            }
        });

        ButtonCustom buttonChoose = new ButtonCustom("Chọn nhà phát hành", "success", 14);
        ButtonCustom buttonAdd = new ButtonCustom("Thêm nhà phát hành", "success", 14);
        buttonChoose.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getRow()<0){
                    int input = JOptionPane.showConfirmDialog(null, 
                "Vui lòng chọn nhà phát hành!", "Thông báo", JOptionPane.DEFAULT_OPTION);
                } else{
                    taoPhieuNhap.setNhaPhatHanh(listNph.get(getRow()).getManph());
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
        tableNhaPH = new JTable();
        tableNhaPH.setFocusable(false);
        scrollTableSanPham = new JScrollPane();
        tableNhaPH.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tableNhaPH = new JTable();
        tableNhaPH.setFocusable(false);
        tableNhaPH.setDefaultEditor(Object.class, null);
        tableNhaPH.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã KH","Họ tên","Địa chỉ", "Số điện thoại", "Email"};
        tblModel.setColumnIdentifiers(header);
        tableNhaPH.setDefaultRenderer(Object.class, centerRenderer);
        tableNhaPH.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableNhaPH);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableNhaPH.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableNhaPH.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableNhaPH.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableNhaPH.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);        
        tableNhaPH.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        jPanelTable.add(scrollTableSanPham);
        this.add(jPanelTable,BorderLayout.CENTER);
        
    }
    public ListNhaPhatHanh(Main m) {
        this.m = m;
        init();
        tableNhaPH.setDefaultEditor(Object.class, null);
        loadDataTable(listnph);
    }

   
    public int getRow(){
        return tableNhaPH.getSelectedRow();
    }
    
    public void loadDataTable(ArrayList<NhaPhatHanhDTO> list) {
        listNph = list;
        tblModel.setRowCount(0);
        for (NhaPhatHanhDTO nph : listNph) {
            tblModel.addRow(new Object[]{
                nph.getManph(),nph.getTennph(),nph.getDiachi(),nph.getSdt(), nph.getEmail()
            });
        }
    }

    private void openKhachHangDialog() {
        ListNhaPhatHanhDialog ListNhaPhatHanhDialog = new ListNhaPhatHanhDialog(this, "Thêm Nhà Phát Hành", true, "create");
    }
    
    
    
    public ArrayList<NhaPhatHanhDTO> search(String text) {
        ArrayList<NhaPhatHanhDTO> result = new ArrayList<>();
        if(text.length()>0){
            text = text.toLowerCase();
        for(NhaPhatHanhDTO i : listNph) {
           if(i.getTennph().toLowerCase().contains(text) || i.getDiachi().toLowerCase().contains(text)
                   || i.getSdt().toLowerCase().contains(text)){
               result.add(i);
           }
        }
        return result;
        } else {
            return NhaPhatHanhDAO.getInstance().selectAll();
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
        loadDataTable(listnph);
    }
}