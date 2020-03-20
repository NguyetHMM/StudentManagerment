package quanlyhocsinh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachHS {
    String fileHS = "D:\\QLHS\\HocSinh.txt";
    private ArrayList<HocSinh> HSlist;

    public DanhSachHS() {
        HSlist = new ArrayList<HocSinh>();
    }

    public ArrayList<HocSinh> getHSlist() {
        return HSlist;
    }

    public void setHSlist(ArrayList<HocSinh> HSlist) {
        this.HSlist = HSlist;
    }
    
    //tìm kiếm học sinh theo tên
    public void searchTenhHS(int i) throws ParseException {
        DanhSachLH ds = new DanhSachLH();
        ArrayList<LopHoc> listLH = ds.docLH();
        DanhSachHS HS = new DanhSachHS();
        ArrayList<HocSinh> lisths = HS.docHS();
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        if (lisths.isEmpty()) {
            System.out.println("Không có dữ liệu!");
        } else {
            Scanner scr = new Scanner(System.in);
            System.out.print("Nhập tên học sinh cần tìm kiếm:");
            String m = scr.nextLine().trim();
            int d = 0;
            for (HocSinh x : lisths) {
                if ((x.getTenHS().toLowerCase().endsWith((m.toLowerCase())))) {
                    System.out.println("\n******************************************");
                    System.out.printf("Kết quả tìm kiếm học sinh có tên: %s%n", m);
                    System.out.printf("%n\t%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", "Mã lớp", "Mã học sinh", "Tên học sinh", "Quê quán", "giới tính", "Năm sinh", "Thẻ căn cước");
                    x.htTKTHS();
                    String t = x.getMaHS();
                    System.out.println("\n\n\t\t\t\t\t  Bảng điểm và đánh giá xếp loại học sinh");
                    System.out.println("\t\t\t------------------------------------------------------------------------------------");
                    System.out.printf("\t\t\t%-20s%-20s%-20s%-20s%n", "Năm học", "Hạnh kiểm cả năm", "Điểm TB cả năm", "Xếp loại cả năm");
                    for (int j = 0; j < listDiem.size(); j++) {
                        if (listDiem.get(j).getMaHs().equals(t)) {
                            listDiem.get(j).showDiemLop();
                        }
                    }
                    System.out.println("\t\t\t------------------------------------------------------------------------------------\n");
                    d++;
                    break;
                }
            }
            if (d == 0) {
                System.out.println("Không tìm thấy học sinh nào có tên:" + m);
            }
        }
    }

    //thêm học sinh
    public void themDSHS() throws Exception {
        Scanner sc = new Scanner(System.in);
        DanhSachHS ds = new DanhSachHS();
        ArrayList<HocSinh> lisths = ds.docHS();
        String t;
        do {
            System.out.println("_________________________________\nNhập thông tin học sinh\n");
            // tao lớp tạm thời và nhập
            HocSinh temp = new HocSinh();
            temp.NhapHS();
            lisths.add(temp);
            ds.ghiHS(lisths);
            System.out.print("\nGõ 'C' để nhập thêm học sinh, gõ bất kỳ để thoát: ");
            t = sc.nextLine().trim();
            t = t.toUpperCase();
        } while (t.equals("C"));
    }

    //Hiển thị danh sách học sinh
    public void hienDSHS() throws ParseException {
        DanhSachHS ds = new DanhSachHS();
        ArrayList<HocSinh> lisths = ds.docHS();
        if (lisths.isEmpty()) {
            System.out.print("Chưa có học sinh nào!");
        } else {
            System.out.println("********************************************************");
            System.out.println("\n---------------------------------------------------------Thông tin học sinh---------------------------------------------------------\n");
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","Mã lớp","Mã học sinh","Tên học sinh","Quê quán","giới tính","Năm sinh","Thẻ căn cước");
            for (int i = 0; i < lisths.size(); i++) {
                lisths.get(i).hienThi();
            }
        }
    }

    //Xóa học sinh pử class hs
    public void xoahss() throws ParseException, IOException {
        Scanner scr = new Scanner(System.in);
        DanhSachLH dsl = new DanhSachLH();
        ArrayList<LopHoc> listlop = dsl.docLH();
        DanhSachHS DS = new DanhSachHS();
        ArrayList<HocSinh> lisths = DS.docHS();
        String mahs = "";
        String malop = "";
        int d = 0;

        do {
            System.out.println("Nhập mã lớp muốn xóa học sinh '3 hoặc 4 số' :");
            malop = scr.nextLine().trim();
            d = 0;
            for (int i = 0; i < listlop.size(); i++) {
                if (listlop.get(i).getMaLop().equals(malop)) {
                    do {
                        try {
                            int cs = -1;
                            int lop = -1;
                            System.out.println("Nhập mã học sinh muốn xóa hoặc Enter để kết thúc xóa: ");
                            mahs = scr.nextLine();
                            for (int j = 0; j < lisths.size(); j++) {
                                if (lisths.get(j).getMaHS().equals(mahs) && lisths.get(j).getMaLop().equals(malop)) {
                                    cs = j;
                                }
                            }
                            if (cs != -1) {
                                lisths.remove(cs);
                                //cập nhật sí số
                                capnhatSiSotru(malop);
                                DS.ghiHS(lisths);
                                System.out.println("Xóa thành công");
                            } else {
                                throw new Exception("Mã học sinh không tồn tại! Mời nhập lại!");
                            }
                        } catch (Exception e) {
                            if (mahs.equals("")) {
                                break;
                            } else {
                                System.out.println(e.getMessage());
                            }
                        }
                    } while (true);
                    System.out.println("Xóa học sinh kết thúc!");
                    System.out.println("\nNhấn Enter de tiep tuc!");
                    String r = scr.nextLine();
                    d++;
                }
            }
            if (d == 0) {
                System.out.println("Nhập mã lớp không hợp lệ! Mời nhập lại!");
            }
        } while (!checkMaLop(malop));
    }
    
    //cập nhật sĩ số khi xóa học sinh
    public void capnhatSiSotru(String t) throws ParseException, IOException {
        DanhSachLH lh = new DanhSachLH();
        ArrayList<LopHoc> lhh = lh.docLH();
        DanhSachHS hs = new DanhSachHS();
        ArrayList<HocSinh> sach = hs.docHS();
        int d;
        for (int i = 0; i < lhh.size(); i++) {
            d = 0;
            if (lhh.get(i).getMaLop().equals(t)) {
                lhh.get(i).setSiSo(lhh.get(i).getSiSo() - 1);
            }
        }
        lh.ghiLH(lhh);
    }
    
    //Sửa thông tin học sinh
    public void suaHocsinh() throws Exception {
        DanhSachLH hslh = new DanhSachLH();
        ArrayList<LopHoc> listlh = hslh.docLH();
        DanhSachHS hs = new DanhSachHS();
        ArrayList<HocSinh> lisths = hs.docHS();
        Scanner sc = new Scanner(System.in);
        String t, t1;
        int k = 0, l = 0;
        if (listlh.isEmpty()) {
            System.out.println("Không có lớp nào!");
        } else {
            System.out.println("Nhập mã lớp cần sửa thông tin học sinh:");
            t = sc.nextLine();
            for (int i = 0; i < listlh.size(); i++) {
                if (listlh.get(i).getMaLop().equals(t)) {
                    do {
                        System.out.println("Nhập mã học sinh cần sửa:");
                        t1 = sc.nextLine();
                        if (t1.equals("")) {
                            System.out.println("Bạn chưa nhập mã học cần sửa!");
                        }
                    } while (t1.equals(""));

                    for (int j = 0; j < lisths.size(); j++) {

                        if (lisths.get(j).getMaLop().equals(t)) {
                            if (lisths.get(j).getMaHS().equals(t1)) {
                                lisths.get(j).updatehs();
                                System.out.println("Sửa thành công!");
                                hs.ghiHS(lisths);
                                l++;
                            }
                        }
                    }
                    k++;
                }
            }
        }
        if (k == 0 || l == 0) {
            System.out.println("Mã lớp không tồn tại hoặc mã học sinh không tồn tại!!");
        }
    }
    
    //Xóa học sinh ở class lớp
    public void deletehs(ArrayList<LopHoc> listlop) throws ParseException, IOException {
        Scanner scr = new Scanner(System.in);
        DanhSachHS DS = new DanhSachHS();
        ArrayList<HocSinh> lisths = DS.docHS();
        String mahs = "";
        String malop = "";
        int d = 0;

        do {
            System.out.println("Nhập mã lớp muốn xóa học sinh '3 hoặc 4 số':");
            malop = scr.nextLine().trim();
            d = 0;
            for (int i = 0; i < listlop.size(); i++) {
                if (listlop.get(i).getMaLop().equals(malop)) {
                    do {
                        try {
                            int cs = -1;
                            int lop = -1;
                            System.out.println("Nhập mã học sinh muốn xóa hoặc Enter để kết thúc xóa: ");
                            mahs = scr.nextLine();
                            for (int j = 0; j < lisths.size(); j++) {
                                if (lisths.get(j).getMaHS().equals(mahs) && lisths.get(j).getMaLop().equals(malop)) {
                                    cs = j;
                                }
                            }
                            if (cs != -1) {
                                lisths.remove(cs);
                                listlop.get(i).setSiSo(listlop.get(i).getSiSo() - 1);
                                DS.ghiHS(lisths);
                                System.out.println("Xóa thành công");
                            } else {
                                throw new Exception("Mã học sinh không tồn tại! Mời nhập lại!");
                            }
                        } catch (Exception e) {
                            if (mahs.equals("")) {
                                break;
                            } else {
                                System.out.println(e.getMessage());
                            }
                        }
                    } while (true);
                    System.out.println("Xóa học sinh kết thúc!");
                    System.out.println("\nNhấn Enter để tiếp tục!");
                    String r = scr.nextLine();
                    d++;
                }
            }
            if (d == 0) {
                System.out.println("Nhập mã lớp không hợp lệ! Mời nhập lại!");
            }
        } while (!checkMaLop(malop));
    }

    //ghi học sinh
    public void ghiHS(ArrayList<HocSinh> listHS) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("D:\\QLHS\\HocSinh.txt"));
            for (int i = 0; i < listHS.size(); i++) {
                bw.write(listHS.get(i).toStringHocSinh());
                bw.newLine();
            }
        } catch (IOException e) {
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
            }
        }
    }

    //đọc học sinh
    public ArrayList<HocSinh> docHS() throws ParseException {
        BufferedReader br = null;
        ArrayList<HocSinh> listHS = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("D:\\QLHS\\HocSinh.txt"));
            String tthss = br.readLine();
            while (tthss != null) {
                HocSinh hs = new HocSinh(tthss);
                listHS.add(hs);
                tthss = br.readLine();
            }
        } catch (IOException e) {
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return listHS;
    }
    
    //check mã lớp
    public boolean checkMaLop(String m) throws ParseException {
        DanhSachLH dslh = new DanhSachLH();
        ArrayList<LopHoc> lisl = dslh.docLH();
        if (lisl.isEmpty()) {
            return false;
        } else {
            for (LopHoc x : lisl) {
                if (x.getMaLop().equals(m)) {
                    return true;
                }
            }
        }
        return false;
    } 
}
