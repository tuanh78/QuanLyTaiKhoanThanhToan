package com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy.Subject;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    final static String DB_NAME="QL_CONGNO";
    final static String TABLE_NAME="Mon_Hoc";
    final static String  TABLE_ID="id";
    final  static String SUBJECT_NAME="name";
    final static  String MONEY="money";
    final static String TIME="time";
    final static String ADDRESS="address";
    final static String QUANTITY_STUDENT="quantity_student";
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

        String sql = "create table if not exists Mon_Hoc (id integer primary key, name text not null, money text not null, time text not null, " +
                "address text not null, quantity_student int default 0)";
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
    public void insertCard(Subject s)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(TABLE_ID, s.getId());
        contentValues.put(SUBJECT_NAME, s.getName());
        contentValues.put(TIME, s.getTime());
        contentValues.put(MONEY, s.getMoney());
        contentValues.put(QUANTITY_STUDENT, s.getQuantityStudent());
        contentValues.put(ADDRESS, s.getLocation());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteCard(String id)
    {
        sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, TABLE_ID+ "=?", new String[]{id});
    }

    public void updateCard(Subject s)
    {
        sqLiteDatabase=getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(TABLE_ID, s.getId());
        contentValues.put(SUBJECT_NAME, s.getName());
        contentValues.put(TIME, s.getTime());
        contentValues.put(MONEY, s.getMoney());
        contentValues.put(QUANTITY_STUDENT, s.getQuantityStudent());
        contentValues.put(ADDRESS, s.getLocation());
        sqLiteDatabase.update(TABLE_NAME, contentValues, TABLE_ID + "=?", new String[]{String.valueOf(s.getId())});
    }
    public ArrayList<Subject> getList()
    {
        sqLiteDatabase=getReadableDatabase();

        ArrayList<Subject> list=new ArrayList<>();
        Subject s;
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
            String id = cursor.getString(cursor.getColumnIndex(TABLE_ID));
            String name=cursor.getString(cursor.getColumnIndex(SUBJECT_NAME));
            String money =cursor.getString(cursor.getColumnIndex(MONEY));
            String time=cursor.getString(cursor.getColumnIndex(TIME));
            String address=cursor.getString(cursor.getColumnIndex(ADDRESS));
            String quantity_student =cursor.getString(cursor.getColumnIndex(QUANTITY_STUDENT));

            s = new Subject(id, name, money, time, address, quantity_student);
            list.add(s);
        }

        return list;
    }

}