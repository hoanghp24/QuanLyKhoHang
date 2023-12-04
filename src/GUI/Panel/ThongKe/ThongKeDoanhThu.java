package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ThongKeDoanhThu extends JPanel {

    JTabbedPane tabbedPane;
    ThongKeNhapHang thongkenhaphang;
    // ThongKeDoanhThuTungNam thongketungnam;
    // ThongKeDoanhThuTungThang thongkedoanhthutungthang;
    ThongKeBanHang thongkedoanhthu;
    Color BackgroundColor = new Color(240, 247, 250);
    ThongKeBUS thongkeBUS;

    public ThongKeDoanhThu(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        thongkenhaphang = new ThongKeNhapHang(thongkeBUS);
        // thongketungnam = new ThongKeDoanhThuTungNam(thongkeBUS);
        // thongkedoanhthutungthang = new ThongKeDoanhThuTungThang(thongkeBUS);
        thongkedoanhthu = new ThongKeBanHang(thongkeBUS);

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        // tabbedPane.addTab("Thống kê theo năm", thongketungnam);
        // tabbedPane.addTab("Thống kê từng tháng trong năm", thongkedoanhthutungthang);
        tabbedPane.addTab("Thống kê nhập hàng", thongkenhaphang);
        tabbedPane.addTab("Thống kê bán hàng", thongkedoanhthu);

        this.add(tabbedPane);
    }
}
