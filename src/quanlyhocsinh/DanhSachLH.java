package quanlyhocsinh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachLH {

    String fileHS = "D:\\QLHS\\LopHoc.txt";
    public ArrayList<LopHoc> DSLopHoc;

    public DanhSachLH() {
        DSLopHoc = new ArrayList<LopHoc>();
    }

    public ArrayList<LopHoc> getDSLopHoc() {
        return DSLopHoc;
    }

    public void setDSLopHoc(ArrayList<LopHoc> DSLopHoc) {
        this.DSLopHoc = DSLopHoc;
    }

    //ghi lớp học
    public void ghiLH(ArrayList<LopHoc> listLop) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("D:\\QLHS\\LopHoc.txt"));
            for (int i = 0; i < listLop.size(); i++) {
                bw.write(listLop.get(i).toStringLH());
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

    //đọc lớp học
    public ArrayList<LopHoc> docLH() {
        BufferedReader br = null;
        ArrayList<LopHoc> listLop = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("D:\\QLHS\\LopHoc.txt"));
            String ttlh = br.readLine();
            while (ttlh != null) {
                LopHoc lh = new LopHoc(ttlh);
                listLop.add(lh);
                ttlh = br.readLine();
            }
        } catch (IOException e) {
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return listLop;
    }

    //thêm lớp học
    public void themLop(ArrayList<LopHoc> listLop) throws Exception {
        DanhSachLH dslh = new DanhSachLH();
        Scanner sc = new Scanner(System.in);
        String t1;
        do {
            String t;
            int k = 0;
            LopHoc tmp = new LopHoc();
            do {
                do {
                    System.out.println("Nhập mã lớp (Từ 3 đến 4 số):");
                    t = sc.nextLine().trim();
                    if (t.equals("")) {
                        System.out.println("Chưa nhập mã lớp! Mời bạn nhập mã lớp!");
                    }
                } while (t.equals(""));

                for (int i = 0; i < listLop.size(); i++) {
                    if (listLop.get(i).getMaLop().equals(t) || (!KTma(t))) {
                        System.out.println("Đã tồn tại mã lớp này hoặc mã lớp không hợp lệ!");
                        k = 1;
                    } else {
                        k = 0;
                    }
                }
            } while (k == 1);
            tmp.setMaLop(t);
            tmp.nhapLop();
            listLop.add(tmp);

            System.out.println("Gõ C để thêm học lớp,gõ phím bất kì để thoát!");
            t1 = sc.nextLine().trim();
            t1 = t1.toUpperCase();
        } while (t1.equals("C"));

    }

    // Hiển thị danh sách lớp học
    public void hienDSLop(ArrayList<LopHoc> listLop) throws ParseException {
        HocSinh hs;
        DanhSachHS ds = new DanhSachHS();
        ArrayList<HocSinh> lisths = ds.docHS();
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        if (listLop.isEmpty()) {
            System.out.println("No data !");
        } else {
            System.out.println("********************************************************");
            System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ THÔNG TIN CÁC LỚP HỌC ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
            hs = new HocSinh();
            for (int i = 0; i < listLop.size(); i++) {
                listLop.get(i).printLopHoc();

                String t = listLop.get(i).getMaLop();

                for (int j = 0; j < lisths.size(); j++) {
                    if (lisths.get(j).getMaLop().equals(t)) {
                        System.out.println("\t*****************************************************************************************************************");
                        System.out.printf("\t%-20s%-20s%-20s%-20s%-20s%-20s%n", "Mã học sinh", "Tên học sinh", "Quê quán", "giới tính", "Năm sinh", "Thẻ căn cước");
                        int b = j + 1;
                        lisths.get(j).showInLop();

                        String a = lisths.get(j).getMaHS();
                        System.out.println("\n\t\t\t\t\tBảng điểm và đánh giá xếp loại của học sinh");
                        System.out.println("\t\t\t----------------------------------------------------------------------------");
                        System.out.printf("\t\t\t%-20s%-20s%-20s%-20s%n", "Năm học", "Hạnh kiểm cả năm", "Điểm TB cả năm", "Xếp loại cả năm");
                        if (!listDiem.isEmpty()) {
                            for (int k = 0; k < listDiem.size(); k++) {
                                if (listDiem.get(k).getMaHs().equals(a)) {
                                    listDiem.get(k).showDiemLop();
                                }
                            }
                        } else {
                            System.out.println("\t\t\tChưa có điểm !!!");
                        }
                        System.out.println("\t\t\t----------------------------------------------------------------------------\n\n");
                    }
                }
                System.out.println("_________________________________________________________________________________________________________________________________________________________\n");
            }
        }
    }

    //Sửa thông tin lớp học
    public void suaLop(ArrayList<LopHoc> listlop) throws Exception {
        Scanner sc = new Scanner(System.in);
        String t, t1;
        do {
            int k = 0;
            if (listlop.isEmpty()) {
                System.out.println("Không có dữ liệu!");
                break;
            } else {
                System.out.println("Nhập mã lớp cần sửa ' 3 hoặc 4 số ':");
                t = sc.nextLine();
                for (int i = 0; i < listlop.size(); i++) {
                    if (listlop.get(i).getMaLop().equals(t)) {
                        listlop.get(i).nhapLop();
                        k++;
                        System.out.println("Sửa thông tin lớp thành công!\n");
                    }
                }
            }
            if (k == 0) {
                System.out.println("Mã lớp không tồn tại!\n");
            }
            System.out.println("Gõ C để để sửa thông tin lớp,gõ phím bất kì để thoát!");
            t1 = sc.nextLine().trim();
            t1 = t1.toUpperCase();
        } while (t1.equals("C"));

    }

    //xóa lớp
    public void xoaLop(ArrayList<LopHoc> listLop) throws ParseException, IOException {
        DanhSachHS ds = new DanhSachHS();
        ArrayList<HocSinh> lisths = ds.docHS();
        String t = "";
        int d = 0;
        Scanner sc = new Scanner(System.in);
        if (listLop.isEmpty()) {
            System.out.println("Không có lớp học nào!");
        } else {
            System.out.println("Nhập mã lớp cần xóa '3 hoặc 4 số' :");
            t = sc.nextLine();
            for (int i = 0; i < listLop.size(); i++) {
                if (listLop.get(i).getMaLop().equals(t)) {
                    listLop.remove(i);
                    System.out.println("Xóa thành công!");
                    d++;
                }
            }

        }
        if (d == 0) {
            System.out.println("Không có lớp nào có mã:" + t);
        }
    }

    //thống kê tất cả
    public void thogke(ArrayList<LopHoc> listLop) throws ParseException {
        HocSinh hs;
        DanhSachHS ds = new DanhSachHS();
        ArrayList<HocSinh> lisths = ds.docHS();
        DanhSachDiem dsdiem = new DanhSachDiem();
        Diem diem = new Diem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        if (listLop.isEmpty()) {
            System.out.println("No data !");
        } else {
            System.out.println("********************************************************");
            System.out.println("\n-------------------------------------Thống kê tất cả học sinh giỏi, tiên tiến, trung bình, yếu---------------------------------------\n");
            diem.thongke();

            hs = new HocSinh();
            for (int i = 0; i < listLop.size(); i++) {
                listLop.get(i).printLopHoc();

                String t = listLop.get(i).getMaLop();

                for (int j = 0; j < lisths.size(); j++) {
                    if (lisths.get(j).getMaLop().equals(t)) {
                        System.out.println("\t*****************************************************************************************************************");
                        System.out.printf("\t%-20s%-20s%-20s%-20s%-20s%-20s%n", "Mã học sinh", "Tên học sinh", "Quê quán", "giới tính", "Năm sinh", "Thẻ căn cước");
                        lisths.get(j).showInLop();

                        String a = lisths.get(j).getMaHS();
                        System.out.println("\n\t\t\t\t\tBảng điểm và đánh giá xếp loại của học sinh");
                        System.out.println("\t\t\t----------------------------------------------------------------------------");
                        System.out.printf("\t\t\t%-20s%-20s%-20s%-20s%n", "Năm học", "Hạnh kiểm cả năm", "Điểm TB cả năm", "Xếp loại cả năm");
                        for (int k = 0; k < listDiem.size(); k++) {
                            if (listDiem.get(k).getMaHs().equals(a)) {
                                listDiem.get(k).showDiemLop();
                            }
                        }
                        System.out.println("\t\t\t----------------------------------------------------------------------------\n\n");
                    }
                }
                System.out.println("_________________________________________________________________________________________________________________________________________________________\n");
            }
        }
    }

    //Thống kê theo năm học
    public void thogkeNamHoc(ArrayList<LopHoc> listLop) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String t = "";
        int k = 0;
        HocSinh hs;
        DanhSachHS ds = new DanhSachHS();
        ArrayList<HocSinh> lisths = ds.docHS();
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        Diem hi = new Diem();
        if (listLop.isEmpty()) {
            System.out.println("No data !");
        } else {
            System.out.println("********************************************************");
            System.out.println("\n----------------------------------------------Thống kê số học sinh giỏi,tiên tiến,trung bình yếu theo năm học-------------------------------------------\n");
            hs = new HocSinh();
            System.out.print("Nhập năm học muốn thống kê 'yyyy-yyyy':");
            t = sc.nextLine();
            hi.thongkee(t);
        }
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", "Mã lớp", "Mã học sinh", "Tên học sinh", "Quê quán", "Giới tính", "Năm sinh", "Thẻ căn cước", "Năm học", "Hạnh kiểm", "Điểm TB cả năm", "Xếp loại");
        for (int j = 0; j < listDiem.size(); j++) {
            if (listDiem.get(j).getNamHoc().equals(t)) {
                for (int i = 0; i < lisths.size(); i++) {
                    if (lisths.get(i).getMaHS().endsWith(listDiem.get(j).getMaHs()) && listDiem.get(j).getNamHoc().equals(t)) {
                        lisths.get(i).thongkeTheoNam();
                        listDiem.get(j).thongkeNam();
                        k++;
                    }
                }
            }
        }
        if (k == 0) {
            System.out.println("\n=>>>>>> Không có năm học " + t + ". Mời nhập nhập lại\n\n");
        }
    }

    //Tim kiem theo mã lớp
    public void searchmaLop(int i) throws ParseException {
        DanhSachLH ds = new DanhSachLH();
        ArrayList<LopHoc> listLH = ds.docLH();
        DanhSachHS HS = new DanhSachHS();
        ArrayList<HocSinh> listds = HS.docHS();
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        if (listLH.isEmpty()) {
            System.out.println("No data !");
        } else {
            Scanner scr = new Scanner(System.in);
            System.out.print("Nhập mã lớp học '3 hoặc 4 số': ");
            String m = scr.nextLine().trim();
            int d = 0;
            System.out.println("********************************************************");
            System.out.printf("Kết quả tìm kiếm lớp học có mã %s : \n", m);
            for (LopHoc x : listLH) {
                if (x.getMaLop().equals(m)) {
                    d++;
                    if (i == 0) {
                        x.printLopHoc();
                        for (int j = 0; j < listds.size(); j++) {
                            if (listds.get(j).getMaLop().equals(m)) {
                                listds.get(j).showInLop();
                                String a = listds.get(j).getMaHS();
                                System.out.println("\t\t\t\t----------------------------------------------------------------------------------------------");
                                for (int k = 0; k < listDiem.size(); k++) {
                                    if (listDiem.get(k).getMaHs().equals(a)) {
                                        listDiem.get(k).showDiemLop();
                                    }
                                }
                                System.out.println("\t\t\t\t----------------------------------------------------------------------------------------------\n");
                            }
                        }
                    } else {
                        // x.AvailablePrint();
                    }
                    break;
                }
            }
            if (d == 0) {
                System.out.println("Không tìm thấy !");
            }
        }
    }

    //tìm kiếm theo tên lớp
    public void searchTenLop(int i) throws ParseException {
        DanhSachLH ds = new DanhSachLH();
        ArrayList<LopHoc> listLH = ds.docLH();
        DanhSachHS HS = new DanhSachHS();
        ArrayList<HocSinh> lisths = HS.docHS();
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();    
        if (listLH.isEmpty()) {
            System.out.println("No data !");
        } else {
            Scanner scr = new Scanner(System.in);
            System.out.print("Nhập tên lớp học cần tìm kiếm:");
            String m = scr.nextLine().trim().toUpperCase();
            int d = 0;
            for (LopHoc x : listLH) {
                if (x.getTenLop().equals(m)) {
                    System.out.println("\n********************************************************");
                    System.out.printf("  Kết quả tìm kiếm lớp học có tên: %s%n%n", m);
                    x.printLopHoc();
                    System.out.println("\t*****************************************************************************************************************");

                    String t = x.getMaLop();
                    for (int j = 0; j < lisths.size(); j++) {
                        if (lisths.get(j).getMaLop().equals(t)) {
                            System.out.printf("\t%-20s%-20s%-20s%-20s%-20s%-20s%n", "Mã học sinh", "Tên học sinh", "Quê quán", "giới tính", "Năm sinh", "Thẻ căn cước");
                            lisths.get(j).showInLop();
                            String a = lisths.get(j).getMaHS();
                            System.out.println("\n\t\t\t\t\tBảng điểm và đánh giá xếp loại học sinh");
                            System.out.println("\t\t\t-------------------------------------------------------------------------------");
                            System.out.printf("\t\t\t%-20s%-20s%-20s%-20s%n", "Năm học", "Hạnh kiểm cả năm", "Điểm TB cả năm", "Xếp loại cả năm");
                            for (int k = 0; k < listDiem.size(); k++) {
                                if (listDiem.get(k).getMaHs().equals(a)) {
                                    listDiem.get(k).showDiemLop();
                                }
                            }
                            System.out.println("\t\t\t-------------------------------------------------------------------------------\n");
                            System.out.println("\t*****************************************************************************************************************");

                        }

                    }
                    d++;
                    break;
                }
            }
            if (d == 0) {
                System.out.println("Không tìm thấy lớp nào có tên:" + m);
            }
        }
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

    //tìm kiếm theo mã học sinh
    public void searchmaHS(int i) throws ParseException {
        DanhSachLH ds = new DanhSachLH();
        ArrayList<LopHoc> listLH = ds.docLH();
        DanhSachHS HS = new DanhSachHS();
        ArrayList<HocSinh> lisths = HS.docHS();
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        if (lisths.isEmpty()) {
            System.out.println("No data !");
        } else {
            Scanner scr = new Scanner(System.in);
            System.out.print("Nhập mã học sinh: ");
            String m = scr.nextLine().trim();
            int d = 0;
            System.out.println("********************************************************");
            System.out.printf("Kết quả tìm kiếm học sinh có mã %s : \n", m);

            for (int j = 0; j < lisths.size(); j++) {
                if (lisths.get(j).getMaHS().equals(m)) {
                    lisths.get(j).hienThi();
                    String a = lisths.get(j).getMaHS();
                    System.out.println("\t\t\t\t----------------------------------------------------------------------------------------------");
                    for (int k = 0; k < listDiem.size(); k++) {
                        if (listDiem.get(k).getMaHs().equals(a)) {
                            listDiem.get(k).showDiemLop();
                        }
                    }
                    System.out.println("\t\t\t\t----------------------------------------------------------------------------------------------\n");
                }

            }

        }
    }

    // kiểm tra mã lớp có tồn tại?
    public boolean checkMaLop(String m) {
        if (DSLopHoc.isEmpty()) {
            return false;
        } else {
            for (LopHoc x : DSLopHoc) {
                if (x.getMaLop().equals(m)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Kiểm tra mã lớp hợp lệ?
    public boolean KTma(String s) throws Exception {
        try {
            if (s.matches("[0-9]{3,4}")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

}
