package quanlyhocsinh;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    //menu chính
    public void menuChinh() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            int chon = -1;
            do {

                System.out.println("\n******************************************\n");
                System.out.println(" _____________Trình quản lý______________");
                System.out.println("|                                        |");
                System.out.println("|        1.Quản lý lớp học               |");
                System.out.println("|        2.Quản lý học sinh              |");
                System.out.println("|        3.Quản lý điểm                  |");
                System.out.println("|        4.Thống kê                      |");
                System.out.println("|        0.Thoát !                       |");
                System.out.println("|                                        |");
                System.out.println("|   Mời bạn chọn chức năng: 0 đến 64^_^  |");
                System.out.println("|________________________________________|");
                System.out.print(" Bạn chọn: ");
                try {
                    chon = Integer.parseInt(sc.nextLine().trim());
                } catch (Exception e) {
                }
                if (chon > 4 || chon < 0) {
                    System.out.println("Vui lòng chọn 0 - 4 !");
                }
            } while (chon > 4 || chon < 0);
            switch (chon) {
                case 1:
                    qlyLop();
                    break;
                case 2:
                    qlHocSinh();
                    break;
                case 3:
                    qlDiem();
                    break;
                case 4:
                    thongke();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (check);
    }

    //menu quản lý lớp
    public void qlyLop() throws Exception {
        DanhSachLH dslh = new DanhSachLH();
        ArrayList<LopHoc> listLop = dslh.docLH();
        DanhSachHS dshs = new DanhSachHS();
        ArrayList<HocSinh> listHS = dshs.docHS();
        Scanner sc = new Scanner(System.in);
        boolean checkSearch = true;
        do {

            int chon = -1;
            do {
                System.out.println("\n******************************************\n");
                System.out.println(" ______________Quản lý lớp________________");
                System.out.println("|                                         |");
                System.out.println("|          1.Thêm lớp học                 |");
                System.out.println("|          2.Hiển thị lớp                 |");
                System.out.println("|          3.Sửa lớp                      |");
                System.out.println("|          4.Xóa lớp                      |");
                System.out.println("|          5.Tìm kiếm lớp theo tên        |");
                System.out.println("|          6.Xóa học sinh                 |");
                System.out.println("|          7.Quay lại                     |");
                System.out.println("|          0.Thoát !                      |");
                System.out.println("|                                         |");
                System.out.println("|   Mời bạn chọn chức năng: 0 đến 7 ^_^   |");
                System.out.println("|_________________________________________|");
                System.out.print(" Bạn chọn ");
                try {
                    chon = Integer.parseInt(sc.nextLine().trim());
                } catch (Exception e) {
                }
                if (chon > 7 || chon < 0) {
                    System.out.println("Vui lòng chọn 0 - 7 !");
                }
            } while (chon > 7 || chon < 0);
            switch (chon) {
                case 1:
                    dslh.themLop(listLop);
                    dslh.ghiLH(listLop);
                    break;
                case 2:
                    dslh.hienDSLop(listLop);
                    break;
                case 3:
                    dslh.suaLop(listLop);
                    dslh.ghiLH(listLop);
                    break;
                case 4:
                    dslh.xoaLop(listLop);
                    dslh.ghiLH(listLop);
                    break;
                case 5:
                    dslh.searchTenLop(0);
                    break;
                case 6:
                    dshs.deletehs(listLop);
                    dslh.ghiLH(listLop);
                    break;
                case 7:
                    checkSearch = false;
                    break;
                case 0:
                    // closeFile();
                    System.exit(0);
                    break;
            }
        } while (checkSearch);
    }

    //menu quản lý Hoc Sinh
    public void qlHocSinh() throws Exception {
        DanhSachLH dslh = new DanhSachLH();
        ArrayList<LopHoc> lislop = dslh.docLH();
        DanhSachHS dshs = new DanhSachHS();
        ArrayList<HocSinh> listHS = dshs.docHS();

        Scanner sc = new Scanner(System.in);
        boolean checkHS = true;
        do {
            int chon = -1;
            do {
                System.out.println("\n******************************************\n");
                System.out.println(" _____________ Quản lý học sinh ___________");
                System.out.println("|                                          |");
                System.out.println("|          1.Thêm 1 học sinh               |");
                System.out.println("|          2.Hiển thị thông tin học sinh   |");
                System.out.println("|          3.Xóa học sinh                  |");
                System.out.println("|          4.Sửa thông tin học sinh        |");
                System.out.println("|          5.Tìm kiếm học sinh theo tên    |");
                System.out.println("|          6.Quay lại !                    |");
                System.out.println("|          0.Thoát!                        |");
                System.out.println("|                                          |");
                System.out.println("|    Mời bạn chọn chức năng: 0 đến 6 ^_^   |");
                System.out.println("|__________________________________________|");
                System.out.print("|Bạn chọn:");
                try {
                    chon = Integer.parseInt(sc.nextLine().trim());
                } catch (Exception e) {
                }
                if (chon > 6 || chon < 0) {
                    System.out.println("Vui lòng chọn 0 - 6 !");
                }
            } while (chon > 6 || chon < 0);
            switch (chon) {
                case 1:
                    dshs.themDSHS();
                    break;
                case 2:
                    dshs.hienDSHS();
                    break;
                case 3:
                    dshs.xoahss();
                    break;
                case 4:
                    dshs.suaHocsinh();
                    break;
                case 5:
                    dshs.searchTenhHS(0);
                    break;
                case 6:
                    checkHS = false;
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (checkHS);
    }

    //menu quản lý  Điểm
    public void qlDiem() throws Exception {
        DanhSachDiem dsdiem = new DanhSachDiem();
        ArrayList<Diem> listDiem = dsdiem.docDIEM();
        Scanner sc = new Scanner(System.in);
        boolean checkHS = true;
        do {
            int chon = -1;
            do {
                System.out.println("\n******************************************\n");
                System.out.println(" _____________ Quản lý điểm _______________");
                System.out.println("|                                          |");
                System.out.println("|         1.Thêm điểm                      |");
                System.out.println("|         2.Hiển thị điểm                  |");
                System.out.println("|         3.Sửa điểm                       |");
                System.out.println("|         4.Xóa điểm theo năm              |");
                System.out.println("|         5.Xóa điểm của một học sinh      |");
                System.out.println("|         6.Quay lại !                     |");
                System.out.println("|         0.Thoát!                         |");
                System.out.println("|                                          |");
                System.out.println("|    Mời bạn chọn chức năng: 0 đến 6 ^_^   |");
                System.out.println("|__________________________________________|");
                System.out.print("|Bạn chọn:");
                try {
                    chon = Integer.parseInt(sc.nextLine().trim());
                } catch (Exception e) {
                }
                if (chon > 6 || chon < 0) {
                    System.out.println("Vui lòng chọn 0 - 6 !");
                }
            } while (chon > 6 || chon < 0);
            switch (chon) {
                case 1:
                    dsdiem.themDiem();
                    break;
                case 2:
                    dsdiem.hienDsDiem();
                    break;
                case 3:
                    dsdiem.suaDiemm();
                    break;
                case 4:
                    dsdiem.xoadiem();
                    break;
                case 5:
                    dsdiem.xoadiemall();
                    break;
                case 6:
                    checkHS = false;
                    break;
                case 0:
                    //closeFile();
                    System.exit(0);
                    break;
            }
        } while (checkHS);
    }

//    //menu tìm kiếm
//    public void timkiem() throws Exception {
//        DanhSachLH dslh = new DanhSachLH();
//        DanhSachHS dshs = new DanhSachHS();
//        Scanner sc = new Scanner(System.in);
//        boolean checkHS = true;
//        do {
//            int chon = -1;
//            do {
//                System.out.println("\n******************************************\n");
//                System.out.println(" ________________ Trình tìm kiếm _______________");
//                System.out.println("|                                               |");
//                System.out.println("|     1.Tìm kiếm lớp học bằng tên lớp học       |");
//                System.out.println("|     2.Tìm kiếm học sinh theo tên lớp          |");
//                System.out.println("|     3.Tìm kiếm học sinh bằng tên học sinh     |");
//                System.out.println("|     4.Quay lại !                              |");
//                System.out.println("|     0.Thoát!                                  |");
//                System.out.println("|                                               |");
//                System.out.println("|    Mời bạn chọn chức năng: 0 đến 4 ^_^        |");
//                System.out.println("|_______________________________________________|");
//                System.out.print("|Bạn chọn:");
//                try {
//                    chon = Integer.parseInt(sc.nextLine().trim());
//                } catch (Exception e) {
//                }
//                if (chon > 4 || chon < 0) {
//                    System.out.println("Vui lòng chọn 0 - 4 !");
//                }
//            } while (chon > 4 || chon < 0);
//            switch (chon) {
//                case 1:
//                    dslh.searchTenLop(0);
//                    break;
//                case 2:
//                    dslh.searchTenLop(0);
//                    break;
//                case 3:
//                    dslh.searchTenhHS(0);
//                    break;
//                case 4:
//                    checkHS = false;
//                    break;
//                case 0:
//                    System.exit(0);
//                    break;
//            }
//        } while (checkHS);
//    }

    //thống kê
    public void thongke() throws Exception {
        DanhSachLH dslh = new DanhSachLH();
        ArrayList<LopHoc> listLop = dslh.docLH();
        Scanner sc = new Scanner(System.in);
        boolean checkHS = true;
        do {
            int chon = -1;
            do {
                System.out.println("\n******************************************\n");
                System.out.println(" _____________________ Trình thống kê ____________________");
                System.out.println("|                                                         |");
                System.out.println("|         1.Thống kê tất cả học sinh giỏi, khá, Tb, yếu   |");
                System.out.println("|         2.Thống kê theo năm học                         |");
                System.out.println("|         3.Quay lại !                                    |");
                System.out.println("|         0.Thoát!                                        |");
                System.out.println("|                                                         |");
                System.out.println("|         Mời bạn chọn chức năng: 0 đến 3 ^_^             |");
                System.out.println("|_________________________________________________________|");
                System.out.print("|Bạn chọn:");
                try {
                    chon = Integer.parseInt(sc.nextLine().trim());
                } catch (Exception e) {
                }
                if (chon > 3 || chon < 0) {
                    System.out.println("Vui lòng chọn 0 - 3 !");
                }
            } while (chon > 3 || chon < 0);
            switch (chon) {
                case 1:
                    dslh.thogke(listLop);
                    break;
                case 2:
                    dslh.thogkeNamHoc(listLop);
                    break;
                case 3:
                    checkHS = false;
                    break;
                case 0:
                    //closeFile();
                    System.exit(0);
                    break;
            }
        } while (checkHS);
    }

    //tạo file
    public void createFile() {
        File dir = new File("D:\\QLHS");
        dir.mkdirs();
        File f1 = new File("D:\\QLHS\\LopHoc.txt");
        File f2 = new File("D:\\QLHS\\HocSinh.txt");
        File f3 = new File("D:\\QLHS\\Diem.txt");
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
        if (!f2.exists()) {
            try {
                f2.createNewFile();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
        if (!f3.exists()) {
            try {
                f3.createNewFile();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
