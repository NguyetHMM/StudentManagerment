
package quanlyhocsinh;

import java.util.Scanner;

public class LopHoc {

    private String maLop, tenLop, nienKhoa, gvcn;
    private int siSo;

    public LopHoc() {
        maLop = tenLop = nienKhoa = gvcn = "";
        siSo = 0;
    }

    public LopHoc(String maLop, String tenLop, String nienKhoa, String gvcn, int siSo) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.nienKhoa = nienKhoa;
        this.gvcn = gvcn;
        this.siSo = siSo;
    }   

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public String getGvcn() {
        return gvcn;
    }

    public void setGvcn(String gvcn) {
        this.gvcn = gvcn;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
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
    
    
    public LopHoc(String ttlh) {
        String[] tmp = ttlh.split("#");
        maLop = tmp[0];
        tenLop = tmp[1];
        nienKhoa = tmp[2];
        gvcn = tmp[3];
        siSo = Integer.decode(tmp[4]);
    }
    
    //Nhập thông tin lớp học
    public void nhapLop() throws Exception {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập tên lớp: ");
            tenLop = sc.nextLine().trim().toUpperCase();
            if(tenLop.equals("")){
                System.out.println("Chưa nhập tên lớp! mời bạn nhập lại!");
            }
        } while (tenLop.equals(""));
        do {
            System.out.print("Nhập niên khóa 'yyyy-yyyy' : ");
            nienKhoa = sc.nextLine().trim();
            if (!KTnienKhoa(nienKhoa) || nienKhoa.equals("")) {
                System.out.println("Chưa nhập hoặc sai định dạng!Mời nhập lại!");
            }

        } while (!KTnienKhoa(nienKhoa) || nienKhoa.equals(""));
        do {
            System.out.print("Giáo viên chủ nhiệm:");
            gvcn = sc.nextLine().trim();
        } while (gvcn.equals(""));
    }

    //Hiển thị thông tin lớp học
    public void printLopHoc() {      
        System.out.println("  Mã lớp:"+ maLop);
        System.out.println("  Tênlớp:"+ tenLop);
        System.out.println("  Giáo viên chủ nhiệm:"+ gvcn);
        System.out.println("  Niên khóa:"+ nienKhoa);
        System.out.println("  Sĩ số:"+ siSo +"\n");
    }

    public String toStringLH() {
        return maLop + "#" + tenLop + "#" + nienKhoa + "#" + gvcn + "#" + siSo;
    }
}
