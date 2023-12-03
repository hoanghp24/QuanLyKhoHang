package GUI.Dialog;

import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.ButtonCustom;
import DAO.KhachHangDAO;
import DAO.NhaPhatHanhDAO;
import DTO.KhachHangDTO;
import DTO.NhaPhatHanhDTO;
import GUI.Panel.KhachHang;
import BUS.KhachHangBUS;
import GUI.Component.NumericDocumentFilter;
import helper.Validation;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import org.apache.commons.codec.language.bm.Rule;

public class ListNhaPhatHanhDialog extends JDialog implements MouseListener {

    ListNhaPhatHanh jpNPH;    

    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenNph, sdtNph, diachiNph, emailNph;
    private JTextField maKH;
    NhaPhatHanhDTO nph;



    public ListNhaPhatHanhDialog(ListNhaPhatHanh jpNPH, String title, boolean modal, String type) {
        super(jpNPH, title, modal);
        this.jpNPH = jpNPH;
        tenNph = new InputForm("Tên nhà phát hành");
        sdtNph = new InputForm("Số điện thoại");
        diachiNph = new InputForm("Địa chỉ");
        emailNph = new InputForm("Email");
        PlainDocument phonex = (PlainDocument)sdtNph.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        initComponents(title, type);
    }

    public ListNhaPhatHanhDialog(ListNhaPhatHanh jpNPH, String title, boolean modal, String type, DTO.NhaPhatHanhDTO nph) {
        super(jpNPH, title, modal);
        this.nph=nph;
        maKH = new JTextField("");
        setMaKH(Integer.toString(nph.getManph()));
        tenNph = new InputForm("Tên nhà phát hành");
        setTenNph(nph.getTennph());
        diachiNph = new InputForm("Địa chỉ");
        setDiaChiNph(nph.getDiachi());
        emailNph = new InputForm("Email");
        setEmailNph(nph.getEmail());
        sdtNph = new InputForm("Số điện thoại");
        setSdtNph(nph.getSdt());
        this.jpNPH = jpNPH;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(3, 1, 20, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(tenNph);
        pnlMain.add(diachiNph);
        pnlMain.add(emailNph);
        pnlMain.add(sdtNph);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm nhà phát hành", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" ->
                pnlButtom.add(btnThem);
            case "update" ->
                pnlButtom.add(btnCapNhat);
            case "view" -> {
                tenNph.setDisable();
                sdtNph.setDisable();                
                emailNph.setDisable();
                diachiNph.setDisable();
            }
            default ->
                throw new AssertionError();
        }
        pnlButtom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setTenNph(String name) {
        tenNph.setText(name);
    }

    public String getTenNph() {
        return tenNph.getText();
    }

    public String getMaKH() {
        return maKH.getText();
    }

    public void setMaKH(String id) {
        this.maKH.setText(id);
    }

    public String getSdtNph() {
        return sdtNph.getText();
    }

    public void setSdtNph(String id) {
        this.sdtNph.setText(id);
    }

    public String getEmailNph() {
        return emailNph.getText();
    }

    public void setEmailNph(String id) {
        this.emailNph.setText(id);
    }

    public String getDiaChiNph() {
        return diachiNph.getText();
    }

    public void setDiaChiNph(String id) {
        this.diachiNph.setText(id);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean Validation(){
        if (Validation.isEmpty(tenNph.getText())) {
            JOptionPane.showMessageDialog(this, "Tên nhà phát hành không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
         else if (Validation.isEmpty(sdtNph.getText()) || !Validation.isNumber(sdtNph.getText()) && sdtNph.getText().length()!=10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải là 10 ký tự số", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
        // else  if (Validation.isEmpty(emailNph.getText())) {
        //     JOptionPane.showMessageDialog(this, "Email không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
        //     return false;
        //  }
        // else  if (Validation.isEmpty(diachiNph.getText())) {
        //     JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
        //     return false;
        //  }
          return true;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThem && Validation()) {
                int id=NhaPhatHanhDAO.getInstance().getAutoIncrement();
                jpNPH.nhaPhatHanhBUS.add(new DTO.NhaPhatHanhDTO(id, tenNph.getText(), diachiNph.getText(), emailNph.getText(),sdtNph.getText()));
                jpNPH.loadDataTable(jpNPH.listnph);
                dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && Validation()) {
            jpNPH.nhaPhatHanhBUS.update(new NhaPhatHanhDTO(nph.getManph(), tenNph.getText(),diachiNph.getText(), emailNph.getText(), sdtNph.getText()));
            jpNPH.loadDataTable(jpNPH.listnph);
            dispose();
        }
    }

    public static boolean isPhoneNumber(String str) {
        // Loại bỏ khoảng trắng và dấu ngoặc đơn nếu có
        str = str.replaceAll("\\s+", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "");

        // Kiểm tra xem chuỗi có phải là một số điện thoại hợp lệ hay không
        if (str.matches("\\d{10}")) { // Kiểm tra số điện thoại 10 chữ số
            return true;
        } else if (str.matches("\\d{3}-\\d{3}-\\d{4}")) { // Kiểm tra số điện thoại có dấu gạch ngang
            return true;
        } else if (str.matches("\\(\\d{3}\\)\\d{3}-\\d{4}")) { // Kiểm tra số điện thoại có dấu ngoặc đơn
            return true;
        } else {
            return false; // Trả về false nếu chuỗi không phải là số điện thoại hợp lệ
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
