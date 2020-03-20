package quanlyhocsinh;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Diem {

    public int numHocSinhGioi = 0;
    public int numHocSinhKha = 0;
    public int numHocSinhTB = 0;
    public int numHocSinhYeu = 0;

    private String maHs, namHoc, HKCN;
    private double diemTBCN;

    public Diem() {
        maHs = namHoc = HKCN = "";
        diemTBCN = 0;
    }

    public Diem(String maHs, String namHoc, String HKCN, double diemTBCN) {
        this.maHs = maHs;
        this.namHoc = namHoc;
        this.HKCN = HKCN;
        this.diemTBCN = diemTBCN;
    }

    public String getMaHs() {
        return maHs;
    }

    public void setMaHs(String maHs) {
        this.maHs = maHs;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public String getHKCN() {
        return HKCN;
    }

    public void setHKCN(String HKCN) {
        this.HKCN = HKCN;
    }

    public double getDiemTBCN() {
        return diemTBCN;
    }

    public void setDiemTBCN(double diemTBCN) {
        this.diemTBCN = diemTBCN;
    }

    public Diem(String ttdiem) throws ParseException {
        String[] tmp = ttdiem.split("#");
        maHs = tmp[0];
        namHoc = tmp[1];
        HKCN = tmp[2];
        diemTBCN = Double.parseDouble(tmp[3]);
    }

    //kiểm tra niên khóa
    public boolean KTnienKhoa(String s) throws Exception {
        try {
            if (s.matches("[0-9]{4}[-]{1}[0-9]{4}")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    //Nhập điểm
    public void NhapDiem() throws ParseException, Exception {
        DanhSachHS dshs = new DanhSachHS();
        ArrayList<HocSinh> listHS = dshs.docHS();
        Scanner sc = new Scanner(System.in);
        int d = 0;
        int chon = 0;

        do {
            System.out.println("Nhập mã học sinh '8 số':");
            maHs = sc.nextLine().trim();
            d = 0;
            for (HocSinh hi : listHS) {
                if (!hi.getMaHS().equals(maHs)) {
                    d++;
                }
            }
            if (d == listHS.size()) {
                System.out.println("Chưa nhập mã hoặc mã không hợp lệ! Mời nhập lại!");
            }
        } while (d == listHS.size());

        do {
            System.out.println("Nhập năm học 'yyyy-yyyy':");
            namHoc = sc.nextLine().trim();
            if (namHoc.equals("") || !KTnienKhoa(namHoc)) {
                System.out.println("Chưa nhập năm học hoặc sai định dạng! Mời nhập lại!");
            }
        } while (namHoc.equals("") || !KTnienKhoa(namHoc));

        do {
            try {
                System.out.println("Giới tính học sinh ' 1-tốt , 2-khá , 3-trung bình , 4-yếu ' ");
                chon = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            if (chon < 1 || chon > 4) {
                System.out.println("Bạn chọn sai! Mời chọn lại !");
            } else if (chon == 1) {
                HKCN = "tốt";
            } else if (chon == 2) {
                HKCN = "khá";
            } else if (chon == 3) {
                HKCN = "trung bình";
            } else if (chon == 4) {
                HKCN = "yếu";
            }
        } while (chon < 1 || chon > 4);
        do {
            try {
                System.out.println("Nhập điểm trung bình cả năm: ");
                diemTBCN = sc.nextDouble();
            } catch (InputMismatchException e) {
                sc.next();
            }

        } while (diemTBCN > 10 || diemTBCN < 0);
    }

    //Sửa điểm
    public void suadiem() throws ParseException {
        Scanner sc = new Scanner(System.in);
        int chon = 0;

        do {
            try {
                System.out.println("Nhập giới tính học sinh:' 1-tốt , 2-khá , 3-trung bình , 4-yếu '");
                chon = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
            if (chon < 1 || chon > 4) {
                System.out.println("Bạn chọn sai! Mời chọn lại !");
            } else if (chon == 1) {
                HKCN = "tốt";
            } else if (chon == 2) {
                HKCN = "khá";
            } else if (chon == 3) {
                HKCN = "trung bình";
            } else if (chon == 4) {
                HKCN = "yếu";
            }
        } while (chon < 1 || chon > 4);

        do {
            System.out.println("Nhập điểm trung bình cả năm: ");
            diemTBCN = sc.nextFloat();
        } while (diemTBCN > 10 || diemTBCN < 0);
    }

    //hiển thị điểm
    public void hienDiem() {
        String type = "";
        if (diemTBCN >= 8.0f && diemTBCN <= 10f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh giỏi";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else if (diemTBCN >= 6.5f && diemTBCN <= 7.9f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else if (diemTBCN >= 5.0f && diemTBCN <= 6.4f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh trung bình";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh trung bình";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else {
            type = "Học sinh yếu";
        }
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", maHs, namHoc, HKCN, diemTBCN, type);
    }

    //hiển thị xếp loại cho học sinh
    public void showDiemLop() {
        String type = "";
        if (diemTBCN >= 8.0f && diemTBCN <= 10f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh giỏi";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else if (diemTBCN >= 6.5f && diemTBCN <= 7.9f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else if (diemTBCN >= 5.0f && diemTBCN <= 6.4f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh trung bình";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh trung bình";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else {
            type = "Học sinh yếu";
        }
        System.out.printf("\t\t\t%-20s%-20s%-20s%-20s%n", namHoc, HKCN, diemTBCN, type);
    }

    //thống kê theo năm học
    public void thongkeNam() {
        String type = "";
        if (diemTBCN >= 8.0f && diemTBCN <= 10f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh giỏi";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else if (diemTBCN >= 6.5f && diemTBCN <= 7.9f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh tiên tiến";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else if (diemTBCN >= 5.0f && diemTBCN <= 6.4f) {
            if (HKCN.equals("tốt")) {
                type = "Học sinh trung bình";
            } else if (HKCN.equals("khá")) {
                type = "Học sinh trung bình";
            } else if (HKCN.equals("trung bình")) {
                type = "Học sinh trung bình";
            } else {
                type = "Học sinh yếu";
            }
        } else {
            type = "Học sinh yếu";
        }
        System.out.printf("%-20s%-20s%-20s%-20s%n", namHoc, HKCN, diemTBCN, type);
    }

    //thống kê tất số hs giỏi,tb...
    public void thongke() throws ParseException {
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> dsdiemm = dsdiem.docDIEM();
        numHocSinhGioi = 0;
        numHocSinhKha = 0;
        numHocSinhTB = 0;
        numHocSinhYeu = 0;

        if (dsdiemm.isEmpty()) {
            System.out.println("Chưa có điểm!");
        } else {

            for (Diem x : dsdiemm) {
                if (x.diemTBCN >= 8.0f && x.diemTBCN <= 10f) {
                    if (x.HKCN.equals("tốt")) {
                        numHocSinhGioi = numHocSinhGioi + 1;
                    } else if (x.HKCN.equals("khá")) {
                        numHocSinhKha = numHocSinhKha + 1;
                    } else if (x.HKCN.equals("trung bình")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else {
                        numHocSinhYeu = numHocSinhYeu + 1;
                    }
                } else if (x.diemTBCN >= 6.5f && x.diemTBCN <= 7.9f) {
                    if (x.HKCN.equals("tốt")) {
                        numHocSinhKha = numHocSinhKha + 1;
                    } else if (x.HKCN.equals("khá")) {
                        numHocSinhKha = numHocSinhKha + 1;
                    } else if (x.HKCN.equals("trung bình")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else {
                        numHocSinhYeu = numHocSinhYeu + 1;
                    }
                } else if (x.diemTBCN >= 5.0f && x.diemTBCN <= 6.4f) {
                    if (x.HKCN.equals("tốt")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else if (x.HKCN.equals("khá")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else if (x.HKCN.equals("trung bình")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else {
                        numHocSinhYeu = numHocSinhYeu + 1;
                    }
                } else {
                    numHocSinhYeu = numHocSinhYeu + 1;
                }
            }
        }
        System.out.println("\t\t\t\t\tSố học sinh giỏi:" + numHocSinhGioi);
        System.out.println("\t\t\t\t\tSố học sinh tiên tiến:" + numHocSinhKha);
        System.out.println("\t\t\t\t\tSố học sinh trung bình:" + numHocSinhTB);
        System.out.println("\t\t\t\t\tSố học sinh yếu:" + numHocSinhYeu);
        System.out.println("\n");
    }

    //thống kê theo năm hoọc
    public void thongkee(String t) throws ParseException {
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> dsdiemm = dsdiem.docDIEM();
        numHocSinhGioi = 0;
        numHocSinhKha = 0;
        numHocSinhTB = 0;
        numHocSinhYeu = 0;
        for (int j = 0; j < dsdiemm.size(); j++) {
            if (dsdiemm.get(j).getNamHoc().equals(t)) {
                if (dsdiemm.get(j).diemTBCN >= 8.0f && dsdiemm.get(j).diemTBCN <= 10f) {
                    if (dsdiemm.get(j).HKCN.equals("tốt")) {
                        numHocSinhGioi = numHocSinhGioi + 1;
                    } else if (dsdiemm.get(j).HKCN.equals("khá")) {
                        numHocSinhKha = numHocSinhKha + 1;
                    } else if (dsdiemm.get(j).HKCN.equals("trung bình")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else {
                        numHocSinhYeu = numHocSinhYeu + 1;
                    }
                } else if (dsdiemm.get(j).diemTBCN >= 6.5f && dsdiemm.get(j).diemTBCN <= 7.9f) {
                    if (dsdiemm.get(j).HKCN.equals("tốt")) {
                        numHocSinhKha = numHocSinhKha + 1;
                    } else if (dsdiemm.get(j).HKCN.equals("khá")) {
                        numHocSinhKha = numHocSinhKha + 1;
                    } else if (dsdiemm.get(j).HKCN.equals("trung bình")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else {
                        numHocSinhYeu = numHocSinhYeu + 1;
                    }
                } else if (dsdiemm.get(j).diemTBCN >= 5.0f && dsdiemm.get(j).diemTBCN <= 6.4f) {
                    if (dsdiemm.get(j).HKCN.equals("tốt")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else if (dsdiemm.get(j).HKCN.equals("khá")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else if (dsdiemm.get(j).HKCN.equals("trung bình")) {
                        numHocSinhTB = numHocSinhTB + 1;
                    } else {
                        numHocSinhYeu = numHocSinhYeu + 1;
                    }
                } else {
                    numHocSinhYeu = numHocSinhYeu + 1;
                }
            }
        }
        System.out.println("\t\t\t\t\t\t ___________ **** Bảng thống kê **** ___________");
        System.out.println("\t\t\t\t\t\t|                                               |");
        System.out.println("\t\t\t\t\t\t|           Số học sinh giỏi:" + numHocSinhGioi +"\t\t\t|");
        System.out.println("\t\t\t\t\t\t|           Số học sinh tiên tiến:" + numHocSinhKha+"\t\t|");
        System.out.println("\t\t\t\t\t\t|           Số học sinh trung bình:" + numHocSinhTB+"\t\t|");
        System.out.println("\t\t\t\t\t\t|           Số học sinh yếu:" + numHocSinhYeu+"\t\t\t|");
        System.out.println("\t\t\t\t\t\t|_______________________________________________|");
        System.out.println("\n");
    }

    public String toStringDiem() {
        return maHs + "#" + namHoc + "#" + HKCN + "#" + diemTBCN;
    }

}
