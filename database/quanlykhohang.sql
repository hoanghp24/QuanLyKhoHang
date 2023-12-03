-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 03, 2023 lúc 03:18 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlykhohang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctkiemke`
--

CREATE TABLE `ctkiemke` (
  `maphieukiemmke` int(11) NOT NULL COMMENT 'Mã phiếu kiểm kê',
  `masanpham` int(11) NOT NULL COMMENT 'Mã sản phẩm',
  `soluong` int(11) NOT NULL,
  `chenhlech` int(11) NOT NULL,
  `ghichu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`maphieunhap`, `maphienbansp`, `soluong`, `dongia`) VALUES
(39, 149, 50, 72000),
(39, 151, 95, 120000),
(39, 153, 100, 120000),
(39, 155, 55, 120000),
(39, 157, 90, 150000),
(40, 159, 150, 95000),
(40, 161, 45, 550000),
(41, 163, 150, 190000),
(41, 165, 154, 130000),
(42, 167, 110, 150000),
(42, 169, 300, 73000),
(42, 171, 200, 145000),
(43, 173, 80, 190000),
(43, 175, 180, 120000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieuxuat`
--

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieuxuat`
--

INSERT INTO `ctphieuxuat` (`maphieuxuat`, `maphienbansp`, `soluong`, `dongia`) VALUES
(47, 165, 1, 135500),
(47, 167, 1, 152000),
(48, 149, 1, 74500),
(48, 151, 1, 123000),
(48, 155, 1, 122300);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctquyen`
--

CREATE TABLE `ctquyen` (
  `manhomquyen` int(11) NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `hanhdong` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctquyen`
--

INSERT INTO `ctquyen` (`manhomquyen`, `machucnang`, `hanhdong`) VALUES
(1, 'khachhang', 'create'),
(1, 'khachhang', 'delete'),
(1, 'khachhang', 'update'),
(1, 'khachhang', 'view'),
(1, 'khuvuckho', 'create'),
(1, 'khuvuckho', 'delete'),
(1, 'khuvuckho', 'update'),
(1, 'khuvuckho', 'view'),
(1, 'nhanvien', 'create'),
(1, 'nhanvien', 'delete'),
(1, 'nhanvien', 'update'),
(1, 'nhanvien', 'view'),
(1, 'nhaphang', 'create'),
(1, 'nhaphang', 'delete'),
(1, 'nhaphang', 'update'),
(1, 'nhaphang', 'view'),
(1, 'nhaphathanh', 'create'),
(1, 'nhaphathanh', 'delete'),
(1, 'nhaphathanh', 'update'),
(1, 'nhaphathanh', 'view'),
(1, 'nhomquyen', 'create'),
(1, 'nhomquyen', 'delete'),
(1, 'nhomquyen', 'update'),
(1, 'nhomquyen', 'view'),
(1, 'sanpham', 'create'),
(1, 'sanpham', 'delete'),
(1, 'sanpham', 'update'),
(1, 'sanpham', 'view'),
(1, 'taikhoan', 'create'),
(1, 'taikhoan', 'delete'),
(1, 'taikhoan', 'update'),
(1, 'taikhoan', 'view'),
(1, 'thongke', 'create'),
(1, 'thongke', 'delete'),
(1, 'thongke', 'update'),
(1, 'thongke', 'view'),
(1, 'thuoctinh', 'create'),
(1, 'thuoctinh', 'delete'),
(1, 'thuoctinh', 'update'),
(1, 'thuoctinh', 'view'),
(1, 'xuathang', 'create'),
(1, 'xuathang', 'delete'),
(1, 'xuathang', 'update'),
(1, 'xuathang', 'view'),
(11, 'khuvuckho', 'view'),
(11, 'nhanvien', 'view'),
(11, 'nhaphang', 'create'),
(11, 'nhaphang', 'delete'),
(11, 'nhaphang', 'update'),
(11, 'nhaphang', 'view'),
(11, 'nhaphathanh', 'create'),
(11, 'nhaphathanh', 'delete'),
(11, 'nhaphathanh', 'update'),
(11, 'nhaphathanh', 'view'),
(11, 'sanpham', 'create'),
(11, 'sanpham', 'delete'),
(11, 'sanpham', 'update'),
(11, 'sanpham', 'view'),
(11, 'taikhoan', 'view'),
(11, 'thongke', 'view'),
(11, 'thuoctinh', 'view'),
(12, 'khachhang', 'create'),
(12, 'khachhang', 'delete'),
(12, 'khachhang', 'update'),
(12, 'khachhang', 'view'),
(12, 'khuvuckho', 'view'),
(12, 'nhaphathanh', 'view'),
(12, 'sanpham', 'view'),
(12, 'taikhoan', 'view'),
(12, 'thongke', 'view'),
(12, 'thuoctinh', 'view'),
(12, 'xuathang', 'create'),
(12, 'xuathang', 'delete'),
(12, 'xuathang', 'update'),
(12, 'xuathang', 'view');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctsanpham`
--

CREATE TABLE `ctsanpham` (
  `maisbn` varchar(255) NOT NULL DEFAULT 'AUTO_INCREMENT' COMMENT 'Mã isbn của sản phẩm',
  `maphienbansp` int(11) NOT NULL,
  `maphieunhap` int(11) NOT NULL,
  `maphieuxuat` int(11) DEFAULT NULL,
  `tinhtrang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhmucchucnang`
--

CREATE TABLE `danhmucchucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `danhmucchucnang`
--

INSERT INTO `danhmucchucnang` (`machucnang`, `tenchucnang`, `trangthai`) VALUES
('khachhang', 'Quản lý khách hàng', 0),
('khuvuckho', 'Quản lý chi nhánh', 0),
('nhanvien', 'Quản lý nhân viên', 0),
('nhaphang', 'Quản lý nhập hàng', 0),
('nhaphathanh', 'Quản lý nhà phát hành', 0),
('nhomquyen', 'Quản lý nhóm quyền', 0),
('sanpham', 'Quản lý sản phẩm', 0),
('taikhoan', 'Quản lý tài khoản', 0),
('thongke', 'Quản lý thống kê', 0),
('thuoctinh', 'Quản lý thuộc tính', 0),
('xuathang', 'Quản lý Đơn hàng', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL,
  `tenkhachhang` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  `ngaythamgia` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `tenkhachhang`, `diachi`, `sdt`, `trangthai`, `ngaythamgia`) VALUES
(54, 'Tuấn', '', '0398576889', 1, '2023-12-03 19:00:38'),
(55, 'Minh', '', '0398665765', 1, '2023-12-03 19:01:16'),
(56, 'Thảo', '', '0789864675', 1, '2023-12-03 19:01:27'),
(57, 'Nhung', '', '0986754665', 1, '2023-12-03 19:01:39'),
(58, 'Tân', '', '0956765434', 1, '2023-12-03 20:30:47'),
(59, 'Thuý', '', '0965667887', 1, '2023-12-03 20:32:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuvuckho`
--

CREATE TABLE `khuvuckho` (
  `makhuvuc` int(11) NOT NULL,
  `tenkhuvuc` varchar(255) NOT NULL,
  `ghichu` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khuvuckho`
--

INSERT INTO `khuvuckho` (`makhuvuc`, `tenkhuvuc`, `ghichu`, `trangthai`) VALUES
(1, 'Hồ Chí Minh', 'ho_chi_minh', 1),
(2, 'Hà Nội', 'ha_noi', 1),
(3, 'Đà Nẵng', 'da_nang', 1),
(4, 'Huế', 'hue', 1),
(5, 'Đà Lạt', 'da_lat', 1),
(6, 'Vũng Tàu', '', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaibia`
--

CREATE TABLE `loaibia` (
  `maloaibia` int(11) NOT NULL,
  `tenloaibia` varchar(255) NOT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loaibia`
--

INSERT INTO `loaibia` (`maloaibia`, `tenloaibia`, `trangthai`) VALUES
(1, 'Bìa mềm', 1),
(2, 'Bìa cứng', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ngonngu`
--

CREATE TABLE `ngonngu` (
  `mangonngu` int(11) NOT NULL,
  `tenngonngu` varchar(255) NOT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ngonngu`
--

INSERT INTO `ngonngu` (`mangonngu`, `tenngonngu`, `trangthai`) VALUES
(1, 'Tiếng Việt', 1),
(2, 'Tiếng Anh', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `hoten` varchar(255) NOT NULL,
  `gioitinh` int(11) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `trangthai`) VALUES
(1, 'Phạm Huy Hoàng', 1, '2002-06-24', '0965595435', 'hoangds246@gmail.com', 1),
(7, 'Trần Ngọc Duy', 1, '2002-10-08', '0987654554', 'duytran@gmail.com', 1),
(8, 'Trần Minh Quy', 1, '2002-10-28', '0987665678', 'quytran@gmail.com', 1),
(9, 'Huỳnh Ngọc Ánh', 0, '2003-12-08', '0355786567', 'ngocanh@gmail.com', 1),
(10, 'Nguyễn Ngọc Hải My', 0, '2002-01-12', '0387665667', 'haimy@gmail.com', 1),
(11, 'Nguyễn Minh Tuấn', 1, '2001-11-13', '0976567656', 'tuan@gmail.com', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhaphathanh`
--

CREATE TABLE `nhaphathanh` (
  `manhaphathanh` int(11) NOT NULL,
  `tennhaphathanh` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhaphathanh`
--

INSERT INTO `nhaphathanh` (`manhaphathanh`, `tennhaphathanh`, `diachi`, `email`, `sdt`, `trangthai`) VALUES
(1, 'Công Ty Cổ Phần Phát Hành Sách Tp. HCM', '60-62 Lê Lợi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh', 'fahasa-sg@hcm.vnn.vn', '02838225798', 1),
(2, 'Công Ty TNHH Văn Hóa Việt Long', '14/35, Đào Duy Anh, P.9, Q. Phú Nhuận, Tp. Hồ Chí Minh', 'info@vietlonbook.com', '02838452708', 1),
(3, 'Công Ty Cổ Phần Sách Mcbooks', 'Lô 34E, Khu Đấu Giá 3ha, P. Phúc Diễm, Q. Bắc Từ Liêm, Hà Nội', 'thongtinsach@mcbooks.vn', '0986066630', 1),
(4, 'Công Ty Cổ Phần Sách Giáo Dục Tại Thành Phố Hà Nội', '289A Khuất Duy Tiến, P. Trung Hòa, Q. Cầu Giấy, Hà Nội', 'hongtran27979@gmail.com', '02862534308', 1),
(5, 'Nhà Sách Trực Tuyến BOOKBUY.VN', '147 Pasteur, P. 6, Q. 3, Tp. Hồ Chí Minh', 'info@bookbuy.vn', '02838207153', 1),
(6, 'Công Ty Cổ Phần Dịch Vụ Xuất Bản Giáo Dục Hà Nội', 'Tầng 4 Tòa Nhà Diamond Flower Tower 48 Lê Văn Lương, P. Nhân Chính, Q. Thanh Xuân Hà Nội', 'info@xbgdhn.vn', '02835121977', 1),
(7, 'Công Ty TNHH Thương Mại Dịch Vụ Quỳnh Phát', '232 Trần Thủ Độ, P. Phú Thạnh, Q. Tân Phú, Tp. Hồ Chí Minh', 'oppovietnam@oppo.vn', '02838612167', 1),
(9, 'SaiGon Books', '222Đ.Điện Biên Phủ', 'saigonbooks@gmail.com', '0344344344', 1),
(12, 'Công ty Cổ phần Công nghệ TAKI Việt Nam', '', '', '0254667665', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `manhaxuatban` int(11) NOT NULL,
  `tennhaxuatban` varchar(50) NOT NULL DEFAULT '0',
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`manhaxuatban`, `tennhaxuatban`, `trangthai`) VALUES
(1, 'Nhà Xuất Bản Thế Giới', 1),
(2, 'Nhà Xuất Bản Dân Trí', 1),
(3, 'Nhà Xuất Bản Tổng hợp TP.HCM', 1),
(4, 'Nhà Xuất Bản Thanh Niên', 1),
(5, 'Nhà Xuất Bản Hồng Đức', 1),
(6, 'Nhà Xuất Bản Trẻ', 1),
(7, 'Nhiều Nhà Xuất Bản', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `tennhomquyen` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhomquyen`
--

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `trangthai`) VALUES
(1, 'Quản lý cửa hàng', 1),
(11, 'Nhân viên kho', 1),
(12, 'Nhân viên bán hàng', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phienbansanpham`
--

CREATE TABLE `phienbansanpham` (
  `maphienbansp` int(11) NOT NULL,
  `masp` int(11) NOT NULL,
  `ngonngu` int(11) DEFAULT NULL,
  `loaibia` int(11) DEFAULT 0,
  `sotrang` int(11) DEFAULT NULL,
  `gianhap` int(11) DEFAULT NULL,
  `giaxuat` int(11) DEFAULT NULL,
  `ngayxuatban` date NOT NULL,
  `soluongton` int(11) DEFAULT 0,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phienbansanpham`
--

INSERT INTO `phienbansanpham` (`maphienbansp`, `masp`, `ngonngu`, `loaibia`, `sotrang`, `gianhap`, `giaxuat`, `ngayxuatban`, `soluongton`, `trangthai`) VALUES
(149, 25, 1, 1, 224, 72000, 74500, '2022-06-01', 49, 1),
(151, 26, 1, 1, 322, 120000, 123000, '2022-12-21', 94, 1),
(153, 27, 1, 1, 450, 120000, 122300, '2022-12-12', 100, 1),
(155, 28, 1, 1, 384, 120000, 122300, '2021-01-01', 54, 1),
(157, 29, 1, 1, 296, 150000, 155000, '2020-04-14', 90, 1),
(159, 30, 1, 1, 340, 95000, 98000, '2020-02-02', 150, 1),
(161, 31, 1, 1, 750, 550000, 569000, '2022-09-28', 45, 1),
(163, 32, 1, 1, 552, 190000, 194000, '2019-07-01', 150, 1),
(165, 33, 1, 1, 248, 130000, 135500, '2022-01-14', 153, 1),
(167, 34, 1, 1, 604, 150000, 152000, '2023-05-01', 109, 1),
(169, 35, 1, 1, 224, 73000, 75800, '2021-09-15', 300, 1),
(171, 36, 1, 1, 432, 145000, 148000, '2020-08-01', 200, 1),
(173, 37, 1, 1, 713, 190000, 200000, '2022-01-01', 80, 1),
(175, 38, 1, 1, 374, 120000, 149000, '2021-07-08', 180, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieukiemke`
--

CREATE TABLE `phieukiemke` (
  `maphieu` int(11) NOT NULL,
  `thoigian` date NOT NULL DEFAULT curdate(),
  `nguoitaophieukiemke` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manhaphathanh` int(11) NOT NULL,
  `nguoitao` int(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `thoigian`, `manhaphathanh`, `nguoitao`, `tongtien`, `trangthai`) VALUES
(39, '2023-12-03 20:23:09', 5, 1, 47100000, 1),
(40, '2023-12-03 20:24:01', 4, 1, 39000000, 1),
(41, '2023-12-03 20:25:15', 12, 1, 48520000, 1),
(42, '2023-12-03 20:26:39', 12, 1, 67400000, 1),
(43, '2023-12-03 20:28:03', 9, 1, 36800000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `thoigian` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(20) DEFAULT NULL,
  `nguoitao` int(11) DEFAULT NULL,
  `makh` int(11) DEFAULT NULL,
  `trangthai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieuxuat`
--

INSERT INTO `phieuxuat` (`maphieuxuat`, `thoigian`, `tongtien`, `nguoitao`, `makh`, `trangthai`) VALUES
(47, '2023-12-03 20:31:35', 287500, 1, 57, 1),
(48, '2023-12-03 20:32:05', 319800, 1, 59, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL,
  `tensp` varchar(255) DEFAULT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `tacgia` varchar(255) DEFAULT NULL,
  `nhaxuatban` int(11) DEFAULT NULL,
  `theloai` int(11) DEFAULT NULL,
  `khuvuckho` int(11) DEFAULT NULL,
  `soluongton` int(11) DEFAULT 0,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `hinhanh`, `tacgia`, `nhaxuatban`, `theloai`, `khuvuckho`, `soluongton`, `trangthai`) VALUES
(25, 'Không Diệt Không Sinh Đừng Sợ Hãi ', '239khong_diet_khong_sinh.png', 'Thiền sư Thích Nhất Hạnh', 1, 6, 1, 49, 1),
(26, 'Một Đời Được Mất', '804motdoiduocmat.png', 'Vãn Tình', 1, 6, 2, 94, 1),
(27, 'Nói Chuyện Là Bản Năng, Giữ Miệng Là Tu Dưỡng, Im Lặng Là Trí Tuệ', '27noichuyenlabannang.png', 'Trương Tiếu Hằng', 4, 6, 1, 100, 1),
(28, 'Tâm Lý Học Về Tiền', '607tamlyhoctiente.png', 'Morgan Housel', 2, 3, 2, 54, 1),
(29, 'Khởi Nghiệp Du Kích', '879khoinghiepdukich.png', 'TRẦN THANH PHONG', 4, 3, 3, 90, 1),
(30, 'Tiền Không Mua Được Gì', '774tienkhongmuaduocgi.png', 'Michael J. Sandel', 6, 4, 1, 150, 1),
(31, 'Basic Economics: Kinh tế học cơ bản', '372kinhtecoban.png', 'Thomas Sowell', 1, 3, 1, 45, 1),
(32, 'Khoa Học Khám Phá - Mật Mã - Từ Cổ Điển Đến Lượng Tử', '805matma.png', 'Simon Singh', 6, 7, 4, 150, 1),
(33, 'Dòng chảy ý thức (The River Of Consciousness)', '542dongchaythuc.png', 'Oliver Sacks', 1, 7, 5, 153, 1),
(34, 'Muôn Kiếp Nhân Sinh 3', '90muonkiepnhansinh.png', 'Nguyên Phong (John Vu)', 3, 5, 2, 109, 1),
(35, 'Fear - Sợ Hãi (Hóa Giải Sợ Hãi Bằng Tình Thương)', '484fear.png', 'Thiền sư Thích Nhất Hạnh', 1, 5, 1, 300, 1),
(36, 'Dấu Chân Trên Cát', '563dauchantrencat.png', 'Mika Waltari', 3, 5, 5, 200, 1),
(37, 'Đường Xưa Mây Trắng - Theo Gót Chân Bụt', '408duongxuamaytrang.png', 'Thiền sư Thích Nhất Hạnh', 1, 5, 1, 80, 1),
(38, 'Xây dụng đế chế kinh doanh 1 người', '930dechekinhdoanh1nguoi.png', 'Nguyễn Tất Kiểm', 4, 4, 1, 180, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `tendangnhap` varchar(50) NOT NULL DEFAULT '',
  `trangthai` int(11) NOT NULL,
  `otp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `tendangnhap`, `trangthai`, `otp`) VALUES
(1, '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 1, 'admin', 1, 'null'),
(7, '$2a$12$RKQXcaIcx77mnUC/PLlOwOyTrIUBGT.RAvwEXP14lpxVI3CQe91DC', 1, 'duytran', 1, NULL),
(8, '$2a$12$2RbDrC2gQIAheuRLOFeure6GFvIHbXOU9OvhB3X7YZB7n5Rda3koC', 1, 'quytran', 1, NULL),
(9, '$2a$12$BJi6kW5vGHdYht8Ltl083OJNipMshe2uBcFjNUI4aD/.Kvxt9XYh6', 11, 'ngocanh', 1, NULL),
(10, '$2a$12$9xror7Ops384s2bKYVLbZ.kK/Eq.HRjzYZ0SzqGZyMCiExnPcvubG', 12, 'haimyy', 1, NULL),
(11, '$2a$12$bjvUdeP.feQ5R3VUBIzNYuBg50I2G7fXjBWjC2Y0TkHC3pmvMyK3G', 12, 'minhtuan', 1, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloai`
--

CREATE TABLE `theloai` (
  `matheloai` int(11) NOT NULL,
  `tentheloai` varchar(50) NOT NULL DEFAULT '0',
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `theloai`
--

INSERT INTO `theloai` (`matheloai`, `tentheloai`, `trangthai`) VALUES
(1, 'Chính trị – pháp luật', 1),
(2, 'Khoa học công nghệ – Kinh tế', 1),
(3, 'Tài chính - Tiền tệ', 1),
(4, 'Kinh doanh', 1),
(5, 'Tôn giáo - Tâm linh', 1),
(6, 'Tư duy - Kỹ năng sống', 1),
(7, 'Khoa học - Kỹ thuật', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD PRIMARY KEY (`maphieukiemmke`,`masanpham`);

--
-- Chỉ mục cho bảng `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`maphienbansp`);

--
-- Chỉ mục cho bảng `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`,`maphienbansp`);

--
-- Chỉ mục cho bảng `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD PRIMARY KEY (`manhomquyen`,`machucnang`,`hanhdong`) USING BTREE;

--
-- Chỉ mục cho bảng `danhmucchucnang`
--
ALTER TABLE `danhmucchucnang`
  ADD PRIMARY KEY (`machucnang`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Chỉ mục cho bảng `khuvuckho`
--
ALTER TABLE `khuvuckho`
  ADD PRIMARY KEY (`makhuvuc`);

--
-- Chỉ mục cho bảng `loaibia`
--
ALTER TABLE `loaibia`
  ADD PRIMARY KEY (`maloaibia`);

--
-- Chỉ mục cho bảng `ngonngu`
--
ALTER TABLE `ngonngu`
  ADD PRIMARY KEY (`mangonngu`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Chỉ mục cho bảng `nhaphathanh`
--
ALTER TABLE `nhaphathanh`
  ADD PRIMARY KEY (`manhaphathanh`);

--
-- Chỉ mục cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`manhaxuatban`);

--
-- Chỉ mục cho bảng `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`manhomquyen`);

--
-- Chỉ mục cho bảng `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  ADD PRIMARY KEY (`maphienbansp`) USING BTREE,
  ADD KEY `FK_phienbansanpham_sanpham` (`masp`);

--
-- Chỉ mục cho bảng `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD PRIMARY KEY (`maphieu`),
  ADD KEY `FK_phieukiemke_taikhoan` (`nguoitaophieukiemke`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_phieunhap_nhaphathanh` (`manhaphathanh`),
  ADD KEY `FK_phieunhap_taikhoan` (`nguoitao`);

--
-- Chỉ mục cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`),
  ADD KEY `FK_phieuxuat_khachhang` (`makh`),
  ADD KEY `FK_phieuxuat_taikhoan` (`nguoitao`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`) USING BTREE;

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`manv`),
  ADD UNIQUE KEY `tendangnhap` (`tendangnhap`),
  ADD KEY `FK_taikhoan_nhomquyen` (`manhomquyen`);

--
-- Chỉ mục cho bảng `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`matheloai`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT cho bảng `khuvuckho`
--
ALTER TABLE `khuvuckho`
  MODIFY `makhuvuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `nhaphathanh`
--
ALTER TABLE `nhaphathanh`
  MODIFY `manhaphathanh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  MODIFY `manhaxuatban` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  MODIFY `maphienbansp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=176;

--
-- AUTO_INCREMENT cho bảng `phieukiemke`
--
ALTER TABLE `phieukiemke`
  MODIFY `maphieu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `theloai`
--
ALTER TABLE `theloai`
  MODIFY `matheloai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD CONSTRAINT `FK_ctkiemke_phieukiemke` FOREIGN KEY (`maphieukiemmke`) REFERENCES `phieukiemke` (`maphieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD CONSTRAINT `FK__phieuxuat` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD CONSTRAINT `FK__nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  ADD CONSTRAINT `FK_phienbansanpham_sanpham` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD CONSTRAINT `FK_phieukiemke_taikhoan` FOREIGN KEY (`nguoitaophieukiemke`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_phieunhap_nhaphathanh` FOREIGN KEY (`manhaphathanh`) REFERENCES `nhaphathanh` (`manhaphathanh`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieunhap_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_phieuxuat_khachhang` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieuxuat_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_taikhoan_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_taikhoan_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
