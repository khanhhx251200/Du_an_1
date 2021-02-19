package com.example.du_an_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.database.DatabaseHealper;
import com.example.du_an_1.model.HangGiay;

import java.util.ArrayList;
import java.util.List;

public class HangGiayDAO {

    public static final String TABLE_NAME = "HangGiay";
    private static final String KEY_ID_BRAND = "mahanggiay";
    private static final String KEY_NAME_BRAND = "tenhanggiay";
    private static final String KEY_DES_BRAND = "mota";
    public static final String SQL_HANG_GIAY = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID_BRAND + " text primary key , " + KEY_NAME_BRAND + " TEXT, " + KEY_DES_BRAND + " TEXT)";

    private SQLiteDatabase db;
    private DatabaseHealper dbHealper;

    public HangGiayDAO( Context context ) {
        dbHealper = new DatabaseHealper(context);
        db = dbHealper.getWritableDatabase();
    }

    public long insertHangGiay( HangGiay hangGiay ) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID_BRAND, hangGiay.getMaHangGiay());
        values.put(KEY_NAME_BRAND, hangGiay.getTenHangGiay());
        values.put(KEY_DES_BRAND, hangGiay.getMoTa());

        return db.insert(TABLE_NAME, null, values);
    }

    public long updateHangGiay( HangGiay hangGiay ) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID_BRAND, hangGiay.getMaHangGiay());
        values.put(KEY_NAME_BRAND, hangGiay.getTenHangGiay());
        values.put(KEY_DES_BRAND, hangGiay.getMoTa());

        return db.update(TABLE_NAME, values, KEY_ID_BRAND + "=?", new String[]{hangGiay.getMaHangGiay()});
    }

    public int deleteHangGiay( String mahanggiay ) {
        return db.delete(TABLE_NAME, KEY_ID_BRAND+"=?", new String[]{mahanggiay});
    }

    public List<HangGiay> getAllHangGiay() {
        List<HangGiay> hangGiayList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HangGiay hangGiay = new HangGiay();
            hangGiay.setMaHangGiay(cursor.getString(cursor.getColumnIndex("mahanggiay")));
            hangGiay.setTenHangGiay(cursor.getString(cursor.getColumnIndex("tenhanggiay")));
            hangGiay.setMoTa(cursor.getString(cursor.getColumnIndex("mota")));
            hangGiayList.add(hangGiay);
            cursor.moveToNext();
        }
        return hangGiayList;
    }

//    public int getindexlast(){
//        List<HangGiay> hangGiayList = new ArrayList<>();
//        hangGiayList = getAllHangGiay();
//
//        String search = "SELECT TOP1 mahanggiay as LastID FROM HangGiay ORDERBY mahanggiay DESC";
//    }


}
