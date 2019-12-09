package com.example.du_an_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.database.DatabaseHealper;
import com.example.du_an_1.model.HoaDonChiTiet;

import java.text.SimpleDateFormat;

public class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "CREATE TABLE HoaDonChiTiet(mahoadonchitiet integer primary key autoincrement, mahoadon text, mamaugiay text, soluongmua integer)";
    private DatabaseHealper dbHelper;
    private SQLiteDatabase db;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");


    public HoaDonChiTietDAO(Context context) {
        dbHelper = new DatabaseHealper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertHoaDonChitiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues values = new ContentValues();
        values.put("mahoadonchitiet", hoaDonChiTiet.getMaHoaDonChiTiet());
        values.put("mahoadon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        values.put("mamaugiay", hoaDonChiTiet.getMauGiay().getMaMauGiay());
        values.put("soluongmua", hoaDonChiTiet.getSoLuongMua());

        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) return false;
        return true;
    }
}
