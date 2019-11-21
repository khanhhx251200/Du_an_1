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
    public static final String SQL_HANG_GIAY = "CREATE TABLE HangGiay(mahanggiay text primary key AUTOINCREMENT, tenhanggiay text, mota text, vitri number)";

    private SQLiteDatabase db;
    private DatabaseHealper dbHealper;

    public HangGiayDAO(Context context) {
        dbHealper = new DatabaseHealper(context);
        db = dbHealper.getWritableDatabase();
    }

    public boolean insertHangGiay(HangGiay hangGiay) {
        ContentValues values = new ContentValues();
        values.put("mahanggiay", hangGiay.getMaHangGiay());
        values.put("tenhanggiay", hangGiay.getTenHangGiay());
        values.put("mota", hangGiay.getMoTa());
        values.put("vitri", hangGiay.getViTri());

        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) return false;
        return true;
    }
    public int updateHangGiay(String mahanggiay, String tenhanggiay,String mota, int vitri){
        ContentValues values = new ContentValues();
        values.put("mahanggiay",mahanggiay);
        values.put("tenhanggiay",tenhanggiay);
        values.put("mota", mota);
        values.put("vitri", vitri);

        int result = db.update(TABLE_NAME,values,"mahanggiay=?",new String[]{mahanggiay});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public int deleteHangGiay(String mahanggiay) {
        int result = db.delete(TABLE_NAME, "mahanggiay=?", new String[]{mahanggiay});
        if (result == 0) {
            return -1;
        }
        return 1;
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
            hangGiay.setViTri(cursor.getInt(cursor.getColumnIndex("vitri")));
            hangGiayList.add(hangGiay);
            cursor.moveToNext();
        }
        return hangGiayList;
    }
}
