package com.example.quanlytaikhoanthanhtoansinhvien.DanhSachCacKhoanDaThanhToan;

import java.util.ArrayList;

public class KhachHang {
    int ID;
    String ten, diem, ngayTao, sdt, diachi;


    public KhachHang(int ID, String ten, String diem, String ngayTao, String sdt, String diachi) {
        this.ID = ID;
        this.ten = ten;
        this.diem = diem;
        this.ngayTao = ngayTao;
        this.sdt=sdt;
        this.diachi=diachi;
    }

    @Override
    public String toString() {
        return
                "ID: " + ID + '\n'+
                        "Tên KH: " + ten + '\n' +
                        "Điểm tích lũy: " + diem + '\n' +
                        "Ngày tạo: " + ngayTao + '\n' +
                        "SĐT: " + sdt + '\n' +
                        "Địa chỉ: " + diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public KhachHang() {
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
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}

