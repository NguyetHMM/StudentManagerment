package quanlyhocsinh;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HocSinh {

    public String maLop, maHS, tenHS, queQuan, gioiTinh, nTNS, theCanCuoc;

    public HocSinh() {
        maLop = maHS = tenHS = queQuan = gioiTinh = nTNS = theCanCuoc = "";
    }

    public HocSinh(String tths) throws ParseException {
        String[] tmp = tths.split("#");
        maLop = tmp[0];
        maHS = tmp[1];
        tenHS = tmp[2];
        queQuan = tmp[3];
        gioiTinh = tmp[4];
        nTNS = tmp[5];
        theCanCuoc = tmp[6];
    }

    public HocSinh(String maLop, String maHS, String tenHS, String queQuan, String gioiTinh, String nTNS, String theCanCuoc) {
        this.maLop = maLop;
        this.maHS = maHS;
        this.tenHS = tenHS;
        this.queQuan = queQuan;
        this.gioiTinh = gioiTinh;
        this.nTNS = nTNS;
        this.theCanCuoc = theCanCuoc;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaHS() {
        return maHS;
    }

    public void setMaHS(String maHS) {
        this.maHS = maHS;
    }

    public String getTenHS() {
        return tenHS;
    }

    public void setTenHS(String tenHS) {
        this.tenHS = tenHS;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getnTNS() {
        return nTNS;
    }

    public void setnTNS(String nTNS) {
        this.nTNS = nTNS;
    }

    public String getTheCanCuoc() {
        return theCanCuoc;
    }

    public void setTheCanCuoc(String theCanCuoc) {
        this.theCanCuoc = theCanCuoc;
    }

    // kiểm tra mã học sinh
    public boolean checkMaHocSinh(String maHs) throws ParseException {
        DanhSachHS ds = new DanhSachHS();
        ArrayList<HocSinh> listHS = ds.docHS();
        // tra ve true neu ma sach da ton tai, false neu chua
        for (HocSinh i : listHS) {
            if (i.getMaHS().equals(maHs)) {
                return true;
            }
        }
        return false;
    }

    //kiểm tra mã hs khi nhập
    public boolean KTmaHs(String s) throws Exception {
        try {
            if (s.matches("[0-9]{8}")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    //Kiểm tra CMT khi nhập
    public boolean KTKCMND(String s) throws Exception {
        try {
            if (s.matches("[0-9]{12}")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    // kiểm tra ngày sinh
    private boolean ngaySinhHopLe() {
        String[] tmp = nTNS.split("/");
        if (tmp.length < 3) {
            return false;
        }
        int thang = Integer.parseInt(tmp[1]);
        int nam = Integer.parseInt(tmp[2]);
        int ngay = Integer.parseInt(tmp[0]);
        int max_ngay = 30;
        if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
            max_ngay = 31;
        }
        if (thang == 2) {
            if (nam % 4 == 0 && nam % 4000 != 0) {
                max_ngay = 29;
            } else {
                max_ngay = 28;
            }
        }
        if (thang < 1 || thang > 12 || ngay < 1 || ngay > max_ngay) {
            return false;
        }
        return true;

    }

    //Nhập thông tin học sinh khi thêm 1 hs
    public void NhapHS() throws Exception {
        DanhSachLH ds = new DanhSachLH();
        ArrayList<LopHoc> listlop = ds.docLH();
        Scanner sc = new Scanner(System.in);
        int k = 0;
        int d = 0;
        int chon = 0;

        do {
            System.out.println("Nhập mã lớp '3 hoặc 4 số':");
            maLop = sc.nextLine().trim();
            d = 0;
            for (LopHoc hi : listlop) {
                if (!hi.getMaLop().equals(maLop)) {
                    d++;
                }
            }
            if (d == listlop.size()) {
                System.out.println("Nhập mã lớp không hợp lệ! Mời nhập lại!");
            }
        } while (d == listlop.size());
        //cập nhật sí số 
        capnhatSiSo(maLop);
        do {
            do {
                System.out.print("Nhập mã học sinh '8 số': ");
                maHS = sc.nextLine().trim();
                if (maHS.equals("")) {
                    System.out.println("\nBạn chưa nhập mã học sinh! Mời bạn nhập lại!");
                }
            } while (maHS.equals(""));
            if (this.checkMaHocSinh(maHS) || !KTmaHs(maHS)) {
                System.out.print("\n Mã học sinh đã tồn tại hoặc không hợp lệ! Mời nhập lại!\n");
                k = 1;
            } else {
                k = 0;
            }
        } while (k == 1);
        do {
            System.out.println("Nhập tên học sinh: ");
            tenHS = sc.nextLine();
            if (tenHS.equals("")) {
                System.out.println("Bạn chưa nhập tên học sinh! Mời nhập lại!");
            }
        } while (tenHS.equals(""));
        do {
            System.out.println("Nhập Quê quán học sinh: ");
            queQuan = sc.nextLine();
            if (queQuan.equals("")) {
                System.out.println("Bạn chưa nhập quê quán! Mời nhập lại!");
            }
        } while (queQuan.equals(""));
        do {
            try {
                System.out.println("Giới tính học sinh '1-Nam , 2-Nữ' ");
                chon = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            if (chon < 1 || chon > 2) {
                System.out.println("Bạn chọn sai! Mời chọn lại !");
            } else if (chon == 1) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
        } while (chon < 1 || chon > 2);
        do {
            System.out.println("Năm sinh của học sinh 'dd/mm/yyyy' :");
            sc.nextLine();
            nTNS = sc.nextLine();
            if (ngaySinhHopLe() == false) {
                System.out.println("Chưa nhập hoặc sai định dạng!Mời nhập lại!");
            }
        } while (ngaySinhHopLe() == false);
        do {
            System.out.println("Nhập thẻ căn cước của sinh viên '12 số' : ");
            theCanCuoc = sc.nextLine();
            if (!KTKCMND(theCanCuoc)) {
                System.out.println("Chưa nhập hoặc Sai định dạng!Mời nhập lại!");
            }
        } while (!KTKCMND(theCanCuoc));
    }

    //NHập khi sửa học sinh
    public void updatehs() throws Exception {
        DanhSachLH ds = new DanhSachLH();
        Scanner sc = new Scanner(System.in);
        int chon = 0;
        do {
            System.out.println("Nhập tên học sinh: ");
            tenHS = sc.nextLine();
        } while (tenHS.equals(""));
        do {
            System.out.println("Nhập quê quán học sinh: ");
            queQuan = sc.nextLine();
        } while (queQuan.equals(""));
        do {
            try {
                System.out.println("Giới tính học sinh '1-Nam , 2-Nữ' ");
                chon = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            if (chon < 1 || chon > 2) {
                System.out.println("Bạn chọn sai! Mời chọn lại !");
            } else if (chon == 1) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
        } while (chon < 1 || chon > 2);
        do {
            System.out.println("Năm sinh của học sinh 'dd/mm/yyyy' : ");
            nTNS = sc.nextLine();
            if (ngaySinhHopLe() == false) {
                System.out.println("Chưa nhập hoặc sai định dạng!Mời nhập lại!");
            }
        } while (ngaySinhHopLe() == false);
        do {
            System.out.println("Nhập thẻ căn cước của sinh viên '12 số' : ");
            theCanCuoc = sc.nextLine();
            if (!KTKCMND(theCanCuoc)) {
                System.out.println("Chưa nhập hoặc Sai định dạng!Mời nhập lại!");
            }
        } while (!KTKCMND(theCanCuoc));

    }

    //hiển thị ở class hs
    public void hienThi() {
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", maLop, maHS, tenHS, queQuan, gioiTinh, nTNS, theCanCuoc);
    }

    //hiển thị trong l
    public void showInLop() {
        System.out.printf("\t%-20s%-20s%-20s%-20s%-20s%-20s%n%n", maHS, tenHS, queQuan, gioiTinh, nTNS, theCanCuoc);
    }

    //hiển thị học sinh khi thống kê theo năm
    public void thongkeTheoNam() {
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", maLop, maHS, tenHS, queQuan, gioiTinh, nTNS, theCanCuoc);
    }

    //Hiển thị hs khi tke tất
    public void htTKTHS() {
        System.out.printf("\t%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", maLop, maHS, tenHS, queQuan, gioiTinh, nTNS, theCanCuoc);
    }

    //cập nhật sí số khi thêm học sinh
    public void capnhatSiSo(String t) throws ParseException, IOException {
        DanhSachLH lh = new DanhSachLH();
        ArrayList<LopHoc> lhh = lh.docLH();
        DanhSachHS hs = new DanhSachHS();
        ArrayList<HocSinh> sach = hs.docHS();
        int d;
        for (int i = 0; i < lhh.size(); i++) {
            d = 0;
            if (lhh.get(i).getMaLop().equals(t)) {
                lhh.get(i).setSiSo(lhh.get(i).getSiSo() + 1);
            }
        }
        lh.ghiLH(lhh);
    }

    public String toStringHocSinh() {
        return maLop + "#" + maHS + "#" + tenHS + "#" + queQuan + "#" + gioiTinh + "#" + nTNS + "#" + theCanCuoc;
    }
}
