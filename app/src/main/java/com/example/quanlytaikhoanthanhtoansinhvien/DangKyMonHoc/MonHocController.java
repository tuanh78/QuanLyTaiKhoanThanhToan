package com.example.quanlytaikhoanthanhtoansinhvien.DangKyMonHoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlytaikhoanthanhtoansinhvien.R;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MonHocController extends AppCompatActivity {
    ListView listMonHoc;
    ArrayList<MonHoc> arrList;
    Spinner spinner;
    EditText editTien;
    EditText editNgay;
    EditText editTin;

    EditText edID ;
    Button btnThem;
    Button sua;

    ArrayList<String> arrMon = new ArrayList<>();
    ArrayAdapter<MonHoc> adapter = null;
    ArrayList<MonHoc> arrayList = null;
    SQLHelper sqlHelper;

    int dem =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangkymonhoc);
        listMonHoc = (ListView)findViewById(R.id.listMonHoc);
        editTien = (EditText)findViewById(R.id.editTien);
        editTin = (EditText)findViewById(R.id.editTin);
        editNgay = (EditText)findViewById(R.id.editNgay);
        btnThem = (Button)findViewById(R.id.btnThem);

        edID = findViewById(R.id.edMaThe);


        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        editNgay.setText(currentDate);
        spinner = (Spinner) findViewById(R.id.spinner);

        arrMon.add("c++");
        arrMon.add("sql");
        arrMon.add("c");
        arrMon.add(".net");
        arrMon.add("di động");
        Random r = new Random();
        int id = r.nextInt(1000);
        edID.setText(id + "");
        sqlHelper = new SQLHelper(this);
        sqlHelper.insertCard(new MonHoc(123, "c", "12/09/2021", 3, 300000));
        sqlHelper.insertCard(new MonHoc(126, "c", "12/09/2021", 3, 300000));
        ArrayAdapter arrdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrMon);
        arrdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        reloadList();



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dem = position;
                if(arrMon.get(position)=="c++"){


                    editTien.setText("990000");
                    editTin.setText("3");


                }
                if(arrMon.get(position)=="c"){

                    editTien.setText("630000");
                    editTin.setText("2");
                }
                if(arrMon.get(position)=="sql"){

                    editTien.setText("1200000");
                    editTin.setText("4");
                }
                if(arrMon.get(position)==".net"){

                    editTien.setText("1200000");
                    editTin.setText("4");
                }
                if(arrMon.get(position)=="di động"){

                    editTien.setText("990000");
                    editTin.setText("3");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner.setAdapter(arrdapter);

        listMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edID.setText(arrayList.get(position).getId() + "");
                editNgay.setText(arrayList.get(position).getNgayDangKy());
                editTin.setText(arrayList.get(position).getSoTin());
                editTien.setText(arrayList.get(position).getTien()+"");
                editTien.requestFocus();


            }
        });
        listMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                setBtnXoa(arrayList.get(position).getId());
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudangkymonhoc, menu);
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
                btnSua(); break;
            case R.id.mnuTieptuc:
                Random r = new Random();
                int id = r.nextInt(1000);
                edID.setText(id + "");
                editTien.setText("");
                editNgay.setText("");

                break;
            case R.id.mnuThoat:
                setBtnThoat(); break;
        }
    }


    public void reloadList()
    {
        try{
            arrayList = new ArrayList<>();
            arrayList = sqlHelper.getList();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            listMonHoc.setAdapter(adapter);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void btnSua() {

        MonHoc kh = new MonHoc(Integer.parseInt(edID.getText().toString()), spinner.getSelectedItem().toString(), editNgay.getText().toString(), Integer.parseInt( editTin.getText().toString()), Double.parseDouble( editTien.getText().toString()));
        sqlHelper.updateCard(kh);
        reloadList();


    }



    public void add(View v) {
        try {
            int ID = Integer.parseInt(edID.getText().toString());
            String ten = spinner.getSelectedItem().toString();
            Double tien =Double.parseDouble( editTien.getText().toString());
            String ngayTao = editNgay.getText().toString().trim();
            int tinchi = Integer.parseInt( editTin.getText().toString());

            if (ten.isEmpty() || ten.isEmpty() || ngayTao.isEmpty() ) {
                Toast.makeText(getBaseContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

            } else {

                MonHoc kh = new MonHoc(ID, ten, ngayTao, tinchi, tien);
                sqlHelper.insertCard(kh);
                reloadList();
            }
        } catch (Exception e) {
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
                sqlHelper.deleteCard(id);
                reloadList();
            }
        });
        builder.show();
    }

}