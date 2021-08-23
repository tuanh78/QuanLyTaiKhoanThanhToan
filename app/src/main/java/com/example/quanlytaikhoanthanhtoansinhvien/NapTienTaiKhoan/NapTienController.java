package com.example.quanlytaikhoanthanhtoansinhvien.NapTienTaiKhoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlytaikhoanthanhtoansinhvien.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class NapTienController extends AppCompatActivity {
    Spinner spNganhang;
    EditText editId, editTien, editNgaynap;
    Button btnNgaynap,btnNap,btnVnpay,btnViettelpay;
    ListView lvTien;
    ArrayList<NapTien> arrayList = null;
    ArrayAdapter<NapTien> adapter = null;
    String[] arrNganHang = {"ViettinBank","VietcomBank","BIDV","VPBank","TechcomBank","ACB","AgriBank"};
    ArrayAdapter<String> adapterNganHang = null;
    int cYear, cMonth, cDay;
    MyDatabaseHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangkymonhoc);

        getWidget();

        Random r = new Random();
        int id = r.nextInt(1000);
        editId.setText(id + "");
        myDBHelper = new MyDatabaseHelper(this);
        myDBHelper.insertCard(new NapTien(123, "123456", "ViettinBank","12/03/2021"));
        myDBHelper.insertCard(new NapTien(321, "123456", "VietcomBank","11/01/2021"));
        myDBHelper.insertCard(new NapTien(345, "123456", "BIDV","09/06/2021"));
        reloadList();

        adapterNganHang = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrNganHang);
        spNganhang.setAdapter(adapterNganHang);

        //arrayList = new ArrayList<>();
        //adapter = new NapTienAdapter(MainActivity.this,R.layout.naptien_list_item,arrayList);
        //lvTien.setAdapter(adapter);

        btnNap.setOnClickListener( new processEvent());
        btnNgaynap.setOnClickListener(new processEvent());

        lvTien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editId.setText(arrayList.get(position).getID() + "");
                editTien.setText(arrayList.get(position).getSoTien() + "");
                spNganhang.setSelection(adapterNganHang.getPosition(arrayList.get(position).getNganHang()));
                editNgaynap.setText(arrayList.get(position).getNgayNap());
                editTien.requestFocus();
            }
        });

        lvTien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                setBtnXoa(arrayList.get(position).getID());
                return false;
            }
        });

    }

    private class processEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnNap:
                    btnNap();
                    break;
                case R.id.btnNgaynap:
                    processNgaynap();
                    break;
            }
        }
    }

    private void getWidget(){
        editId=findViewById(R.id.editId);
        editTien=findViewById(R.id.editTien);
        editNgaynap=findViewById(R.id.editNgaynap);
        spNganhang=findViewById(R.id.spNganhang);
        btnNgaynap=findViewById(R.id.btnNgaynap);
        btnNap=findViewById(R.id.btnNap);
        btnVnpay=findViewById(R.id.btnVnpay);
        btnViettelpay=findViewById(R.id.btnViettelpay);
        lvTien=findViewById(R.id.lvTien);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menunaptien, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        processItem(item);
        return true;
    }

    public void processItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnuSua:
                btnSua();
                break;
            case R.id.mnuTieptuc:
                Random r = new Random();
                int id = r.nextInt(1000);
                editId.setText(id + "");
                editTien.setText("");
                editNgaynap.setText("");
                editTien.requestFocus();
                break;
            case R.id.mnuThoat:
                setBtnThoat();
                break;
        }
    }

    public void processNgaynap(){
        Calendar c = Calendar.getInstance();
        this.cDay = c.get(Calendar.DAY_OF_MONTH);
        this.cMonth = c.get(Calendar.MONTH);
        this.cYear = c.get(Calendar.YEAR);
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                editNgaynap.setText(arg3 + "/" + (arg2 + 1) + "/" + arg1);
            }
        };
        DatePickerDialog dateDialog = new DatePickerDialog(this, callBack, cYear, cMonth, cDay);
        dateDialog.setTitle("Ngày nạp");
        dateDialog.show();
    }

    public boolean checkID() {
        for (NapTien napTien : arrayList) {
            if (napTien.getID() == Integer.parseInt(editId.getText().toString()))
                return true;
        }
        return false;
    }

    public void btnNap() {
        try {
            int ID = Integer.parseInt(editId.getText().toString());
            String soTien = editTien.getText().toString().trim();
            String nganHang = spNganhang.getSelectedItem().toString();
            String ngayNap = editNgaynap.getText().toString().trim();

            if (soTien.isEmpty() || nganHang.isEmpty() ) {
                Toast.makeText(getBaseContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

            } else {
                if (checkID()) {
                    Toast.makeText(getBaseContext(), "ID đã tồn tại", Toast.LENGTH_SHORT).show();
                } else if (soTien == "0") {
                    Toast.makeText(getBaseContext(), "Số tiền không được nhỏ hơn 0", Toast.LENGTH_SHORT).show();
                } else {
                    NapTien nt = new NapTien(ID, soTien, nganHang,ngayNap);
                    myDBHelper.insertCard(nt);
                    reloadList();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Có lỗi nhập liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void reloadList()
    {
        try{
            arrayList = new ArrayList<>();
            arrayList = myDBHelper.getList();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            lvTien.setAdapter(adapter);
        }catch (Exception e)
        {
            e.printStackTrace();
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


    public void setBtnXoa(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chú ý");
        builder.setMessage("Bạn có chắc muốn xóa không?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayList.remove(which + 1);
                myDBHelper.deleteCard(id);
                reloadList();
            }
        });
        builder.show();
    }

    public void btnSua() {
        NapTien kh = new NapTien(Integer.parseInt(editId.getText().toString()), editTien.getText().toString(), spNganhang.getSelectedItem().toString(), editNgaynap.getText().toString());
        myDBHelper.updateCard(kh);
        reloadList();
    }
}