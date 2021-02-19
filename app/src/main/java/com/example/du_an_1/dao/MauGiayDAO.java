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
    public static final String SQL_MAU_GIAY = "CREATE TABLE MauGiay(mamaugiay text primary key, mahanggiay text, tenmaugiay text, soluong int, mausac text, giaban integer)";

    public MauGiayDAO(Context context) {
        dbHelper = new DatabaseHealper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertMauGiay(MauGiay mauGiay) {
        ContentValues values = new ContentValues();
        values.put("mamaugiay", mauGiay.getMaMauGiay());
        values.put("mahanggiay", mauGiay.getMaHangGiay());
        values.put("tenmaugiay", mauGiay.getTenMauGiay());
        values.put("soluong", mauGiay.getSoLuong());
        values.put("mausac", mauGiay.getMauSac());
        values.put("giaban", mauGiay.getGiaBan());

        return db.insert(TABLE_NAME, null, values);
    }

    public int updateMauGiay(String mamaugiay, String mahanggiay, String tenmaugiay, int soluong, String mausac, double giaban) {
        ContentValues values = new ContentValues();
        values.put("mamaugiay", mamaugiay);
        values.put("mahanggiay", mahanggiay);
        values.put("tenmaugiay", tenmaugiay);
        values.put("soluong", soluong);
        values.put("mausac", mausac);
        values.put("giaban", giaban);

        long result = db.update(TABLE_NAME, values, "mamaugiay=?", new String[]{mamaugiay});
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
            mauGiay.setGiaBan(cursor.getInt(cursor.getColumnIndex("giaban")));
            mauGiayList.add(mauGiay);
            cursor.moveToNext();
        }
        return mauGiayList;
    }

    public List<MauGiay> getMayGiayByName(String tenMauGiayCanTim) {
        List<MauGiay> mauGiayList = new ArrayList<>();
        String sqlSelect = "select mamaugiay, mahanggiay,tenmaugiay, soluong, mausac, giaban from MauGiay where tenmaugiay like ?";
        String[] paramaters = {tenMauGiayCanTim};
        Cursor cursor = db.rawQuery(sqlSelect, paramaters);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MauGiay mauGiay = new MauGiay();
            mauGiay.setMaMauGiay(cursor.getString(cursor.getColumnIndex("mamaugiay")));
            mauGiay.setMaHangGiay(cursor.getString(cursor.getColumnIndex("mahanggiay")));
            mauGiay.setTenMauGiay(cursor.getString(cursor.getColumnIndex("tenmaugiay")));
            mauGiay.setSoLuong(cursor.getInt(cursor.getColumnIndex("soluong")));
            mauGiay.setMauSac(cursor.getString(cursor.getColumnIndex("mausac")));
            mauGiay.setGiaBan(cursor.getInt(cursor.getColumnIndex("giaban")));
            mauGiayList.add(mauGiay);
            cursor.moveToNext();
        }
        return mauGiayList;
    }

    public List<MauGiay> getTop10(String month) {
        List<MauGiay> mauGiayList = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String sql = "select mamaugiay,Sum(soluongmua) from HoaDonChiTiet inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon " +
                "where strftime('%m', HoaDon.ngaymua) = ? group by mamaugiay order by soluongmua desc limit 10";
        Cursor cursor = db.rawQuery(sql, new String[]{month});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MauGiay mauGiay = new MauGiay();
            mauGiay.setMaMauGiay(cursor.getString(0));
            mauGiay.setSoLuong(cursor.getInt(1));
            mauGiay.setGiaBan(0);
            mauGiay.setMauSac("");
            mauGiay.setMaHangGiay("");
            mauGiay.setTenMauGiay("");
            mauGiayList.add(mauGiay);
            cursor.moveToNext();
        }
        cursor.close();
        return mauGiayList;
    }
}