package com.example.quanlytaikhoanthanhtoansinhvien.DangKyMonHoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SQLHelper extends SQLiteOpenHelper {
    final static String DB_NAME="QL_MonHoc";
    final static String TABLE_NAME="MonHoc";
    final static String  TABLE_ID="id";
    final  static String CUSTOMER_NAME="name";
    final static  String DATE ="date";
    final static  String POINTS="point";
    final static String MONEY="money";
    final static int VERSION =1;
    Cursor cursor;
    Context context;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    public SQLHelper( Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists MonHoc(id integer primary key, name text not null, date text not null, point text not null, money text not null)";
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
    public void insertCard(MonHoc k)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(TABLE_ID, k.getId());
        contentValues.put(CUSTOMER_NAME, k.getTenMon());
        contentValues.put(DATE, k.getNgayDangKy());
        contentValues.put(POINTS, k.getSoTin());
        contentValues.put(MONEY, k.getTien());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }
    public void deleteCard(int id)
    {
        sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, TABLE_ID+ "=?", new String[]{String.valueOf(id)});
    }
    public void updateCard(MonHoc k)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(CUSTOMER_NAME, k.getTenMon());
        contentValues.put(DATE, k.getNgayDangKy());
        contentValues.put(POINTS, k.getSoTin());
        contentValues.put(MONEY, k.getTien());

        sqLiteDatabase.update(TABLE_NAME, contentValues, TABLE_ID + "=?", new String[]{String.valueOf(k.getId())});
    }
    public ArrayList<MonHoc> getList()
    {
        sqLiteDatabase=getReadableDatabase();
        ArrayList<MonHoc> list=new ArrayList<>();
        MonHoc k;
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
            int point=cursor.getInt(cursor.getColumnIndex(POINTS));
            Double money =cursor.getDouble(cursor.getColumnIndex(MONEY));

            k=new MonHoc(id, name, date,point, money);
            list.add(k);
        }

        return list;
    }

}
