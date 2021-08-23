package com.example.quanlytaikhoanthanhtoansinhvien.NapTienTaiKhoan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    final static String TAG = "MyDatabaseHelper";
    final static String DATABASE_NAME = "ql_tkthanhtoansv";
    final static int DATABASE_VERSION = 1;
    final static String TABLE_NAME = "Nap_Tien";
    final static String TABLE_ID = "id";
    final static String SO_TIEN_NAP = "sotien";
    final static String NGAN_HANG = "nganhang";
    final static String NGAY_NAP = "ngaynap";
    Cursor cursor;
    Context context;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    public MyDatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists Nap_Tien (id integer primary key, sotien text not null, nganhang text not null, ngaynap text not null )";
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

    public void insertCard(NapTien nt){
        sqLiteDatabase=getWritableDatabase();
        contentValues= new ContentValues();
        contentValues.put(TABLE_ID, nt.getID());
        contentValues.put(SO_TIEN_NAP, nt.getSoTien());
        contentValues.put(NGAN_HANG, nt.getNganHang());
        contentValues.put(NGAY_NAP, nt.getNgayNap());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteCard(int id){
        sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, TABLE_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateCard(NapTien nt){
        sqLiteDatabase=getWritableDatabase();
        contentValues= new ContentValues();
        contentValues.put(TABLE_ID, nt.getID());
        contentValues.put(SO_TIEN_NAP, nt.getSoTien());
        contentValues.put(NGAN_HANG, nt.getNganHang());
        contentValues.put(NGAY_NAP, nt.getNgayNap());
        sqLiteDatabase.update(TABLE_NAME, contentValues, TABLE_ID + "=?", new String[]{String.valueOf(nt.getID())});
    }

    public ArrayList<NapTien> getList(){
        sqLiteDatabase=getReadableDatabase();
        ArrayList<NapTien> list = new ArrayList<>();
        NapTien nt;
        try {
            cursor=sqLiteDatabase.query(TABLE_NAME, null, null ,null, null, null, null, null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(TABLE_ID));
            String sotien = cursor.getString(cursor.getColumnIndex(SO_TIEN_NAP));
            String nganhang = cursor.getString(cursor.getColumnIndex(NGAN_HANG));
            String ngaynap = cursor.getString(cursor.getColumnIndex(NGAY_NAP));

            //int id, Double soTien, String nganHang, String ngayNap
            nt = new NapTien(id, sotien, nganhang, ngaynap);
            list.add(nt);
        }
        return list;
    }
}
