package com.example.du_an_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.database.DatabaseHealper;
import com.example.du_an_1.model.MauGiay;

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
}
