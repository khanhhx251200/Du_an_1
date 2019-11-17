package com.example.du_an_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.database.DatabaseHealper;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class MauGiayDAO {
    private DatabaseHealper dbHelper;
    private SQLiteDatabase db;

    public static final String TABLE_NAME = "MauGiay";
    public static final String SQL_MAU_GIAY = "CREATE TABLE MauGiay(mamaugiay text primary key, mahanggiay text, tenmaugiay text, soluong int, mausac text, giaban double)";

    public MauGiayDAO(Context context) {
        dbHelper = new DatabaseHealper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertMauGiay(MauGiay mauGiay) {
        ContentValues values = new ContentValues();
        values.put("mamaugiay", mauGiay.getMaMauGiay());
        values.put("mahanggiay", mauGiay.getMaHangGiay());
        values.put("tenmaugiay", mauGiay.getTenMauGiay());
        values.put("soluong", mauGiay.getSoLuong());
        values.put("mausac", mauGiay.getMauSac());
        values.put("giaban", mauGiay.getGiaBan());

        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) return false;
        return true;
    }

    public int updateMauGiay(MauGiay mauGiay) {
        ContentValues values = new ContentValues();
        values.put("mamaugiay", mauGiay.getMaMauGiay());
        values.put("mahanggiay", mauGiay.getMaHangGiay());
        values.put("tenmaugiay", mauGiay.getTenMauGiay());
        values.put("soluong", mauGiay.getSoLuong());
        values.put("mausac", mauGiay.getMauSac());
        values.put("giaban", mauGiay.getGiaBan());

        long result = db.update(TABLE_NAME, values, "mamaugiay=?", new String[]{mauGiay.getMaHangGiay()});
        if (result == 0) return -1;
        return 1;
    }

    public int deleteMauGiay(String mamaugiay) {
        return db.delete(TABLE_NAME, "mamaugiay=?", new String[]{mamaugiay});
    }

    public List<MauGiay> getAllMauGiay() {
        List<MauGiay> mauGiayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MauGiay mauGiay = new MauGiay();
            mauGiay.setMaMauGiay(cursor.getString(cursor.getColumnIndex("mamaugiay")));
            mauGiay.setMaHangGiay(cursor.getString(cursor.getColumnIndex("mahanggiay")));
            mauGiay.setTenMauGiay(cursor.getString(cursor.getColumnIndex("tenmaugiay")));
            mauGiay.setSoLuong(cursor.getInt(cursor.getColumnIndex("soluong")));
            mauGiay.setMauSac(cursor.getString(cursor.getColumnIndex("mausac")));
            mauGiay.setGiaBan(cursor.getDouble(cursor.getColumnIndex("giaban")));
            mauGiayList.add(mauGiay);
            cursor.moveToNext();
        }
        return mauGiayList;
    }
}
