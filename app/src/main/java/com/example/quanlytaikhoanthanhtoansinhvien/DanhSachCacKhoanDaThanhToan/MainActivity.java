package com.example.quanlytaikhoanthanhtoansinhvien.DanhSachCacKhoanDaThanhToan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import com.example.quanlytaikhoanthanhtoansinhvien.R;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    EditText edID, edTen, edDiem, edDate, edSdt;
    Button btnSua, btnThem, btndate, btnThoat, btnTiep;
    Spinner spTenKhoanThu;
    ArrayList<KhoanDaThanhToan> arrayList = null;
    ArrayAdapter<KhoanDaThanhToan> adapter = null;
    String[] arrSpinner = {"Lập trình Java", "Lập trình di động", "Lập trình Web", "ASP.NET", "SQL Server", "MySQL", "Kỹ năng mềm", "Kỹ năng làm việc nhóm", "Hệ quản trị cơ sở dữ liệu"};
    ArrayAdapter<String> adapterSpinner = null;
    int cYear, cMonth, cDay;
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getID();
        Random r = new Random();
        int id = r.nextInt(1000);
        edID.setText(id + "");
        sqlHelper = new SQLHelper(MainActivity.this);
        sqlHelper.insertCard(new KhoanDaThanhToan(220, "Học Java", "102312", "07/08/2021", "3", "Lập trình Java"));
        sqlHelper.insertCard(new KhoanDaThanhToan(222, "Học Frontend", "2322312", "09/08/2021", "4", "Lập trình Web"));
        adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrSpinner);
        spTenKhoanThu.setAdapter(adapterSpinner);
        btnThem.setOnClickListener(new processEvent());
        btndate.setOnClickListener(new processEvent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        processItem(item);
        return true;
    }

    public void processItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.btnSua:
                btnSua(); break;
            case R.id.btnTiep:
                Random r = new Random();
                int id = r.nextInt(1000);
                edID.setText(id + "");
                edDiem.setText("");
                edSdt.setText("");
                edTen.requestFocus();
                edDate.setText("");
                break;
            case R.id.btnThoat:
                setBtnThoat(); break;
            case R.id.btnDanhSach:
                openDanhSach(); break;
        }
    }

    private class processEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnThem:
                    btnThem();
                    break;
                case R.id.btnSua:
                    btnSua();
                    break;
                case R.id.btnThoat:
                    setBtnThoat();
                    break;
                case R.id.btnDate:
                    processBirthday();
                    break;
                case R.id.btnTiep:
                    Random r = new Random();
                    int id = r.nextInt(1000);
                    edID.setText(id + "");
                    edDate.setText("");
                    edDiem.setText("");
                    edTen.setText("");
                    edSdt.setText("");
                    edTen.requestFocus();
                    break;
            }
        }
    }

    private void getID() {
        lv = findViewById(R.id.lvDanhSach);
        edDate = findViewById(R.id.edDate);
        edDiem = findViewById(R.id.edDiemTichLuy);
        edID = findViewById(R.id.edMaThe);
        edTen = findViewById(R.id.edTenKH);
        btnSua = findViewById(R.id.btnSua);
        btnThoat = findViewById(R.id.btnThoat);
        btnThem = findViewById(R.id.btnThem);
        btndate = findViewById(R.id.btnDate);
        btnTiep = findViewById(R.id.btnTiep);
        edSdt = findViewById(R.id.edSDt);
        spTenKhoanThu = findViewById(R.id.spTenKhoanThu);
    }

    public void processBirthday() {
        Calendar c = Calendar.getInstance();
        this.cDay = c.get(Calendar.DAY_OF_MONTH);
        this.cMonth = c.get(Calendar.MONTH);
        this.cYear = c.get(Calendar.YEAR);
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                edDate.setText(arg3 + "/" + (arg2 + 1) + "/" + arg1);
            }
        };
        DatePickerDialog dateDialog = new DatePickerDialog(this, callBack, cYear, cMonth, cDay);
        dateDialog.setTitle("Date");
        dateDialog.show();
    }

    public void btnSua() {

//            for (int i=0; i< arrayList.size(); i++)
//            {
//               if(arrayList.get(i).getID()==Integer.parseInt(edID.getText().toString()))
//               {
//                   arrayList.get(i).setTen(edTen.getText().toString());
//                   arrayList.get(i).setNgayTao(edDate.getText().toString());
//                   arrayList.get(i).setDiem(edDiem.getText().toString());
//                   arrayList.get(i).setSdt(edSdt.getText().toString());
//                   arrayList.get(i).setDiachi(spDiaChi.getSelectedItem().toString());
//
//                   adapter.notifyDataSetChanged();
//               }
//            }
        KhoanDaThanhToan kh = new KhoanDaThanhToan(Integer.parseInt(edID.getText().toString()), edTen.getText().toString(), edDiem.getText().toString(), edDate.getText().toString(), edSdt.getText().toString(), spTenKhoanThu.getSelectedItem().toString());
        sqlHelper.updateCard(kh);


    }

    public boolean checkID() {
        for (KhoanDaThanhToan khachHang : arrayList) {
            if (khachHang.getID() == Integer.parseInt(edID.getText().toString()))
                return true;
        }
        return false;
    }

    public void btnThem() {
        try {
            int ID = Integer.parseInt(edID.getText().toString());
            String ten = edTen.getText().toString().trim();
            String diem = edDiem.getText().toString().trim();
            String ngayTao = edDate.getText().toString().trim();
            String diachi = spTenKhoanThu.getSelectedItem().toString();
            String sdt = edSdt.getText().toString();
            if (ten.isEmpty() || diem.isEmpty() || ngayTao.isEmpty() || sdt.isEmpty() || diachi.isEmpty()) {
                Toast.makeText(getBaseContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

            } else {
                if (checkID()) {
                    Toast.makeText(getBaseContext(), "ID đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    KhoanDaThanhToan kh = new KhoanDaThanhToan(ID, ten, diem, ngayTao, sdt, diachi);
                    sqlHelper.insertCard(kh);
                }
            }
        } catch (Exception e) {
            Log.e("có lỗi xảy ra ", e.toString());
            Toast.makeText(this, "Có lỗi nhập liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void setBtnThoat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chú ý");
        builder.setMessage("Bạn có chắc muốn thoát không?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }



    public void openDanhSach() {
        Intent myIntent = new Intent(MainActivity.this, ListKhoanThanhToanActivity.class);
        startActivity(myIntent);
    }



}