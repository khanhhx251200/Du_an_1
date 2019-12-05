package com.example.du_an_1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.dao.HoaDonChiTietDAO;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.dao.HoaDonDAO;

public class DatabaseHealper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbSneakerShopManager";
    public static final int VERSION = 3;

    public DatabaseHealper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HangGiayDAO.SQL_HANG_GIAY);
        db.execSQL(MauGiayDAO.SQL_MAU_GIAY);
        db.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ HangGiayDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + MauGiayDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + HoaDonChiTietDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + HoaDonDAO.TABLE_NAME);
        onCreate(db);
    }
}
