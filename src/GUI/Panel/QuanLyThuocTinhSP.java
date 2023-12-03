package GUI.Panel;

import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.itemTaskbar;
import GUI.Dialog.ThuocTinhSanPham.LoaiBiaDialog;
import GUI.Dialog.ThuocTinhSanPham.NgonNguDialog;
import GUI.Dialog.ThuocTinhSanPham.NhaXuatBanDialog;
import GUI.Dialog.ThuocTinhSanPham.TheLoaiDialog;
import GUI.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyThuocTinhSP extends JPanel {

    private final int n = 20;
    JPanel box[], pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;
    JLabel lbl[], lblIcon[], info;
    JScrollPane scrPane;
    NgonNguDialog nn;
    TheLoaiDialog tl;
    NhaXuatBanDialog nxb;
    LoaiBiaDialog lb;
    Main m;
    public itemTaskbar[] listitem;

    String iconst[] = {"earth.svg", "category.svg", "book.svg", "publishing-house.svg"};

    String header[] = {"Ngôn ngữ", "Thể loại", "Loại bìa", "Nhà xuất bản"};
    Color BackgroundColor = new Color(240, 247, 250);
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    
    public QuanLyThuocTinhSP(Main m) {
        this.m = m;
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[header.length];

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new GridLayout(3, 2, 20, 20));

//        scrPane = new JScrollPane(contentCenter);
//        scrPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(contentCenter, BorderLayout.CENTER);

        box = new JPanel[n];
        lbl = new JLabel[n];
        lblIcon = new JLabel[n];
        for (int i = 0; i < header.length; i++) {
            listitem[i] = new itemTaskbar(iconst[i], header[i], header[i]);
            contentCenter.add(listitem[i]);
        }

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nn = new NgonNguDialog(owner, QuanLyThuocTinhSP.this, "Quản lý ngôn ngữ", true,m.user.getManhomquyen());
                nn.setVisible(true);
            }
        });
        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                tl = new TheLoaiDialog(owner, QuanLyThuocTinhSP.this, "Quản lý thể loại", true,m.user.getManhomquyen());
                tl.setVisible(true);
            }
        });
        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                lb = new LoaiBiaDialog(owner, QuanLyThuocTinhSP.this, "Quản lý loại bìa", true,m.user.getManhomquyen());
                lb.setVisible(true);
            }
        });

        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nxb = new NhaXuatBanDialog(owner, QuanLyThuocTinhSP.this, "Quản lý nhà xuất bản", true,m.user.getManhomquyen());
                nxb.setVisible(true);
            }
        });

    }

    public void Mouseopress(MouseEvent evt) {
        for (int i = 0; i < listitem.length; i++) {
            if (evt.getSource() == listitem[i]) {

            }
        }
    }

    

    public void initPadding() {

        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 40));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 40));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(40, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(40, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

    }

}
