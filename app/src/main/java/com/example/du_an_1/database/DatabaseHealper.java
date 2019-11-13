package com.example.du_an_1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.du_an_1.dao.HangGiayDAO;

public class DatabaseHealper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbSneakerShopManager";
    public static final int VERSION = 2;

    public DatabaseHealper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HangGiayDAO.SQL_HANG_GIAY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ HangGiayDAO.TABLE_NAME);
        onCreate(db);
    }
}