package com.example.quanlytaikhoanthanhtoansinhvien.DanhSachCacKhoanDaThanhToan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import com.example.quanlytaikhoanthanhtoansinhvien.R;

public class ListKhoanThanhToanActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<KhoanDaThanhToan> arrayList = null;
    SQLHelper sqlHelper = new SQLHelper(ListKhoanThanhToanActivity.this);
    ArrayAdapter<KhoanDaThanhToan> adapter = null;
//    EditText edID, edTen, edDiem, edDate, edSdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_khoan_thanh_toan);

        lv = findViewById(R.id.lvDanhSach);
        arrayList = new ArrayList<KhoanDaThanhToan>();
        reloadList();
        // bắt sự kiện click cho list view
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                edID.setText(arrayList.get(position).getID() + "");
//                edDate.setText(arrayList.get(position).getNgayTao());
//                edDiem.setText(arrayList.get(position).getDiem());
//                edTen.setText(arrayList.get(position).getTen());
//                edSdt.setText(arrayList.get(position).getSdt());
//                spTenKhoanThu.setSelection(adapterSpinner.getPosition(arrayList.get(position).getTenKhoanThu()));
//                edTen.requestFocus();


            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                setBtnXoa(arrayList.get(position).getID());
                AlertDialog.Builder builder = new AlertDialog.Builder(ListKhoanThanhToanActivity.this);
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
                        KhoanDaThanhToan khoanDaThanhToan = (KhoanDaThanhToan) lv.getAdapter().getItem(position);
                        sqlHelper.deleteCard(khoanDaThanhToan.getID());
                        reloadList();
                    }
                });
                builder.show();
                return false;
            }
        });
    }

    public void reloadList()
    {
        try{
            arrayList = sqlHelper.getList();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            lv.setAdapter(adapter);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setBtnXoa(int id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        processItem(item);
        return true;
    }

    public void processItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.btnTroLai:
                finish(); break;
        }
    }

}
