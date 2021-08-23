package com.example.quanlytaikhoanthanhtoansinhvien.DanhSachCacKhoanDaThanhToan;

import java.util.ArrayList;

public class KhoanDaThanhToan {
    int ID;
    String ten, soTien, ngayTao, sdt, tenKhoanThu;


    public KhoanDaThanhToan(int ID, String ten, String soTien, String ngayTao, String sdt, String tenKhoanThu) {
        this.ID = ID;
        this.ten = ten;
        this.soTien = soTien;
        this.ngayTao = ngayTao;
        this.sdt=sdt;
        this.tenKhoanThu=tenKhoanThu;
    }

    @Override
    public String toString() {
        return
                "ID: " + ID + '\n'+
                        "Tên khoản thu: " + tenKhoanThu + '\n' +
                        "Số tiền: " + soTien + '\n' +
                        "Ngày thu tiền: " + ngayTao + '\n' +
                        "Số tín chỉ: " + sdt + '\n' +
                        "Diễn giải: " + ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public KhoanDaThanhToan() {
    }
    public boolean ktraID(int id)
    {
        ArrayList<KhachHang> ds=new ArrayList<>();
        for (KhachHang kh:ds) {
            if(kh.getID()==id)
                return true;
        }

        return false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiem() {
        return soTien;
    }

    public void setDiem(String diem) {
        this.soTien = diem;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}

