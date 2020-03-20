package quanlyhocsinh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachDiem {

    String fileHS = "D:\\QLHS\\Diem.txt";
    private ArrayList<Diem> listDiem;

    public DanhSachDiem() {
        listDiem = new ArrayList<Diem>();
    }

    public ArrayList<Diem> getListDiem() {
        return listDiem;
    }

    public void setListDiem(ArrayList<Diem> listDiem) {
        this.listDiem = listDiem;
    }

    //thêm điểm
    public void themDiem() throws Exception {

        Scanner sc = new Scanner(System.in);
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        String t;
        do {
            System.out.println("_________________________________\nNhập thông tin điểm\n");
            // tao lớp tạm thời và nhập
            Diem temp = new Diem();
            temp.NhapDiem();
            listDiem.add(temp);
            dsdiem.ghiDIEM(listDiem);
            System.out.print("\nGõ 'C' để nhập thêm điểm, gõ bất kỳ để thoát: ");
            t = sc.nextLine().trim();
            t = t.toUpperCase();
        } while (t.equals("C"));
    }

    //sửa điểm
    public void suaDiemm() throws Exception {
        DanhSachHS hshs = new DanhSachHS();
        ArrayList<HocSinh> lishs = hshs.docHS();
        DanhSachDiem dsd = new DanhSachDiem();
        ArrayList<Diem> listdiem = dsd.docDIEM();
        Scanner sc = new Scanner(System.in);
        String t = "", t1 = "";
        int k = 0;
        int l = 0;
        if (lishs.isEmpty()) {
            System.out.println("Không có học sinh nào!");
        } else {
            System.out.println("Nhập mã học sinh cần sửa điểm '8 số':");
            t = sc.nextLine();
            for (int i = 0; i < lishs.size(); i++) {
                if (lishs.get(i).getMaHS().equals(t)) {
                    System.out.println("Nhập năm học cần sửa:");
                    t1 = sc.nextLine();
                    for (int j = 0; j < listdiem.size(); j++) {
                        if (listdiem.get(j).getMaHs().equals(t)) {
                            if (listdiem.get(j).getNamHoc().equals(t1)) {
                                listdiem.get(j).suadiem();
                                System.out.println("Sửa điểm thành công!");
                                dsd.ghiDIEM(listdiem);
                                l++;
                            }
                        }
                    }
                    k++;
                }
            }
        }
        if (k == 0) {
            System.out.println("Không có học sinh nào có mã:" + t);
        } else if (l == 0) {
            System.out.println("Học sinh mã " + t + " chưa có điểm trong năm học " + t1);
        }
    }

    //xóa điểm theo năm học
    public void xoadiem() throws ParseException, IOException {
        Scanner scr = new Scanner(System.in);
        DanhSachHS DS = new DanhSachHS();
        ArrayList<HocSinh> lisths = DS.docHS();

        DanhSachDiem dsd = new DanhSachDiem();
        ArrayList<Diem> lisd = dsd.docDIEM();
        String mahss = "";
        String namhoc = "";
        int d = 0;
        int cs = 0;
        do {
            System.out.println("Nhập mã học sinh muốn xóa điểm:");
            mahss = scr.nextLine().trim();
            d = 0;
            for (HocSinh hs : lisths) {
                if (hs.getMaHS().equals(mahss)) {
                    do {
                        try {
                            cs = -1;
                            System.out.println("Nhập năm học muốn xóa điểm hoặc Enter để kết thúc xóa:: ");
                            namhoc = scr.nextLine().trim();
                            for (int j = 0; j < lisd.size(); j++) {
                                if (lisd.get(j).getNamHoc().equals(namhoc) && lisd.get(j).getMaHs().equals(mahss)) {
                                    cs = j;
                                }
                            }
                            if (cs != -1) {
                                lisd.remove(cs);
                                dsd.ghiDIEM(lisd);
                                System.out.println("Xóa thành công");
                            } else {
                                throw new Exception("Năm học không tồn tại! Mời nhập lại!");
                            }
                        } catch (Exception e) {
                            if (namhoc.equals("")) {
                                break;
                            } else {
                                System.out.println(e.getMessage());
                            }
                        }
                    } while (true);
                    System.out.println("Xóa điểm kết thúc!");
                    System.out.println("\nNhấn Enter để tiếp tục!");
                    String r = scr.nextLine();
                    d++;
                }
            }
            if (d == 0) {
                System.out.println("Nhập mã học sinh không hợp lệ! Mời nhập lại!");
            }
        } while (!checkMahs(mahss));
    }

    //xóa tất cả điểm của một học sinh
    public void xoadiemall() throws ParseException, IOException {
        Scanner scr = new Scanner(System.in);
        DanhSachHS DS = new DanhSachHS();
        ArrayList<HocSinh> lisths = DS.docHS();

        DanhSachDiem dsd = new DanhSachDiem();
        ArrayList<Diem> lisd = dsd.docDIEM();
        String mahss = "";
        String namhoc = "";
        int d = 0;
        int cs = 0;
        do {
            System.out.println("Nhập mã học sinh muốn xóa điểm:");
            mahss = scr.nextLine().trim();
            d = 0;
            for (HocSinh hs : lisths) {
                if (hs.getMaHS().equals(mahss)) {
                    do {
                        try {
                            cs = -1;
                            for (int j = 0; j < lisd.size(); j++) {
                                if (lisd.get(j).getMaHs().equals(mahss)) {
                                    cs = j;
                                }
                            }
                            if (cs != -1) {
                                lisd.remove(cs);
                                dsd.ghiDIEM(lisd);
                            } else {
                                throw new Exception("Năm học không tồn tại! Mời nhập lại!");
                            }
                        } catch (Exception e) {
                            if (namhoc.equals("")) {
                                break;
                            } else {
                                System.out.println(e.getMessage());
                            }
                        }
                    } while (true);
                    System.out.println("Xóa thành công!");
                    System.out.println("\nNhấn Enter để tiếp tục!");
                    String r = scr.nextLine();
                    d++;
                }
            }
            if (d == 0) {
                System.out.println("Nhập mã học sinh không hợp lệ! Mời nhập lại!");
            }
        } while (!checkMahs(mahss));
    }

    //hiện danh sách điểm
    public void hienDsDiem() throws ParseException {
        DanhSachDiem dsd = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsd.docDIEM();
        if (listDiem.isEmpty()) {
            System.out.print("Không có dữ liệu!!");
        } else {
            System.out.println("--------------------------------------------- Danh Sách Điểm -------------------------------------- \n");
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%n%n", "Mã học sinh", "Năm học", "Hạnh kiểm cả năm", "Điểm TB cả năm", "Xếp loại");
            for (int i = 0; i < listDiem.size(); i++) {
                listDiem.get(i).hienDiem();
            }
        }
    }

    //ghi điểm
    public void ghiDIEM(ArrayList<Diem> listDiem) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("D:\\QLHS\\Diem.txt"));
            for (int i = 0; i < listDiem.size(); i++) {
                bw.write(listDiem.get(i).toStringDiem());
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

    //đọc điểm
    public ArrayList<Diem> docDIEM() throws ParseException {
        BufferedReader br = null;
        ArrayList<Diem> listDiem = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("D:\\QLHS\\Diem.txt"));
            String ttdiem = br.readLine();
            while (ttdiem != null) {
                Diem diem = new Diem(ttdiem);
                listDiem.add(diem);
                ttdiem = br.readLine();
            }
        } catch (IOException e) {
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return listDiem;
    }

    //kiểm tra mã học sinh
    public boolean checkMahs(String m) throws ParseException {
        DanhSachHS dshs = new DanhSachHS();
        ArrayList<HocSinh> lisl = dshs.docHS();
        if (lisl.isEmpty()) {
            return false;
        } else {
            for (HocSinh x : lisl) {
                if (x.getMaHS().equals(m)) {
                    return true;
                }
            }
        }
        return false;
    }
}
