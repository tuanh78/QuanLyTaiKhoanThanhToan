package com.example.quanlytaikhoanthanhtoansinhvien.NapTienTaiKhoan;

import java.util.ArrayList;

public class NapTien {
    int ID;
    String soTien,nganHang,ngayNap;
    public NapTien(){ }
    public NapTien(int ID,String soTien,String nganHang,String ngayNap){
        this.ID=ID;
        this.soTien=soTien;
        this.nganHang=nganHang;
        this.ngayNap=ngayNap;
    }

    @Override
    public String toString(){
        return
                "Mã giao dịch: " + ID + '\n'+
                        "Số tiền nạp: " + soTien + "  VND" + '\n'+
                        "Ngân hàng: " + nganHang + '\n'+
                        "Ngày nạp: " + ngayNap + '\n'+
                        "=====================================";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean ktraID(int id){
        ArrayList<NapTien> ds = new ArrayList<>();
        for(NapTien nt:ds){
            if(nt.getID()==id){
                return true;
            }
        }
        return false;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public String getNgayNap() {
        return ngayNap;
    }

    public void setNgayNap(String ngayNap) {
        this.ngayNap = ngayNap;
    }
}
