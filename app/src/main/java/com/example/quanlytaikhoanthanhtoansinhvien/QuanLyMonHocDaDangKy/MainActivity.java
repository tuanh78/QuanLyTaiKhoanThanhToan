package com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.quanlytaikhoanthanhtoansinhvien.R;

import com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy.AdapterSubject;
import com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy.SQLHelper;
import com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy.Subject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    AdapterSubject adapterSubject;
    ArrayList<Subject> listSubjects;
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random r = new Random();
        int id = r.nextInt(1000);

        sqlHelper = new SQLHelper(this);
        sqlHelper.insertCard(new Subject("1", "Kỹ Thuật Lập Trình", "1.000.000", "Tiết 1,2,3", "409 - Nhà A9", "12"));
        sqlHelper.insertCard(new Subject("2", "Tiếng Anh CNTT", "2.000.000", "Tiết 4,5,6", "304 - Nhà A10", "30"));
        sqlHelper.insertCard(new Subject("3", "Lập Trình Android", "3.000.000", "Tiết 11,12,13", "Hội Trường A2", "40"));

        ListView listViewSubjects = findViewById(R.id.listViewSubjects);
        listSubjects = new ArrayList<>();
        listSubjects = sqlHelper.getList();
        adapterSubject = new AdapterSubject(listSubjects);
        listViewSubjects.setAdapter(adapterSubject);
        listViewSubjects.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận rút học phần");
                builder.setMessage("Bạn có chắc chắn rút học phần này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqlHelper.deleteCard(listSubjects.get(position).getId());
                        reloadList();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();

                return true;
            }
        });
    }

    public void reloadList()
    {
        try{
            ListView listViewSubjects = findViewById(R.id.listViewSubjects);
            listSubjects = new ArrayList<>();
            listSubjects = sqlHelper.getList();
            adapterSubject = new AdapterSubject(listSubjects);
            listViewSubjects.setAdapter(adapterSubject);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
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
            case R.id.mnuTieptuc:
                break;
            case R.id.mnuThoat:
                setBtnThoat();
                break;
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

}