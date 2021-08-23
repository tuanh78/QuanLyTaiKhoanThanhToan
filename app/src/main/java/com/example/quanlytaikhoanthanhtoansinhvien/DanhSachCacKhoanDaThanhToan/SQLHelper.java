package com.example.quanlytaikhoanthanhtoansinhvien.DanhSachCacKhoanDaThanhToan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    final static String DB_NAME="QL_TaiKhoan";
    final static String TABLE_NAME="The_Store";
    final static String  TABLE_ID="id";
    final  static String CUSTOMER_NAME="name";
    final static  String DATE ="date";
    final static  String POINTS="point";
    final static String PHONE="phone";
    final static String RevenueName="address";
    final static int VERSION =1;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists The_Store (id integer primary key, name text not null, date text not null, point text not null, " +
                "phone text not null, address text not null)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }
    public void insertCard(KhoanDaThanhToan k)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(TABLE_ID, k.getID());
        contentValues.put(CUSTOMER_NAME, k.getTen());
        contentValues.put(DATE, k.getNgayTao());
        contentValues.put(POINTS, k.getDiem());
        contentValues.put(PHONE, k.getSdt());
        contentValues.put(RevenueName, k.getTenKhoanThu());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }
    public void deleteCard(int id)
    {
        sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, TABLE_ID+ "=?", new String[]{String.valueOf(id)});
    }
    public void updateCard(KhoanDaThanhToan k)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(CUSTOMER_NAME, k.getTen());
        contentValues.put(DATE, k.getNgayTao());
        contentValues.put(POINTS, k.getDiem());
        contentValues.put(PHONE, k.getSdt());
        contentValues.put(RevenueName, k.getTenKhoanThu());
        sqLiteDatabase.update(TABLE_NAME, contentValues, TABLE_ID + "=?", new String[]{String.valueOf(k.getID())});
    }
    public ArrayList<KhoanDaThanhToan> getList()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<KhoanDaThanhToan> list=new ArrayList<>();
        KhoanDaThanhToan k;
        try
        {
            cursor=sqLiteDatabase.query(TABLE_NAME, null, null, null, null
                    , null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(TABLE_ID));
            String name=cursor.getString(cursor.getColumnIndex(CUSTOMER_NAME));
            String date =cursor.getString(cursor.getColumnIndex(DATE));
            String point=cursor.getString(cursor.getColumnIndex(POINTS));
            String address=cursor.getString(cursor.getColumnIndex(RevenueName));
            String phone =cursor.getString(cursor.getColumnIndex(PHONE));
            //int ID, String ten, String diem, String ngayTao, String sdt, String diachi
            k=new KhoanDaThanhToan(id, name, point, date, phone, address);
            list.add(k);
        }

        return list;
    }

}

