package com.example.quanlytaikhoanthanhtoansinhvien.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlytaikhoanthanhtoansinhvien.DangKyMonHoc.MonHocController;
import com.example.quanlytaikhoanthanhtoansinhvien.DanhSachCacKhoanDaThanhToan.MainActivity;
import com.example.quanlytaikhoanthanhtoansinhvien.Login.LoginController;
import com.example.quanlytaikhoanthanhtoansinhvien.NapTienTaiKhoan.NapTienController;
import com.example.quanlytaikhoanthanhtoansinhvien.R;

public class TrangChuController extends AppCompatActivity {
    Button btnDangKyMonHoc,btnCacKhoan,btnNapTien, btnMonHocDaDangKy,btnQLTK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWitget();

        dangKyMonHoc();
        cacKhoan();
        napTien();
        monHocDaDangKy();
//        dotThi();
    }
    private void getWitget(){
        btnDangKyMonHoc=(Button)findViewById(R.id.btnDangKyMonHoc);
        btnCacKhoan=(Button)findViewById(R.id.btnCacKhoan);
        btnNapTien=(Button)findViewById(R.id.btnNapTien);
        btnMonHocDaDangKy=(Button)findViewById(R.id.btnMonHocDaDangKy);
        btnQLTK=(Button)findViewById(R.id.btnQLTK);
    }
    private void dangKyMonHoc(){
        btnDangKyMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), MonHocController.class);
                startActivity(intent);
            }
        });
    }
    private void cacKhoan(){
        btnCacKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void napTien(){
        btnNapTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), NapTienController.class);
                startActivity(intent);
            }
        });
    }
    private void monHocDaDangKy(){
        btnMonHocDaDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy.MainActivity.class);
                startActivity(intent);
            }
        });
    }
//    private void qLTK(){
//        btnQLTK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getBaseContext(), DotThiController.class);
//                startActivity(intent);
//            }
//        });
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_trangchu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_trangchuadmin_thoat:
                dangxuat();
                break;
            default:
                Toast.makeText(this, "khoong co", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void dangxuat(){
        Intent intent=new Intent(TrangChuController.this, LoginController.class);
        startActivity(intent);
    }
}
