package com.example.du_an_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.database.DatabaseHealper;
import com.example.du_an_1.model.HoaDonChiTiet;
import com.example.du_an_1.model.ThongKe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "CREATE TABLE HoaDonChiTiet(mahoadonchitiet integer primary key AUTOINCREMENT, mahoadon text, mamaugiay text, soluongmua integer)";
    private DatabaseHealper dbHelper;
    private SQLiteDatabase db;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");


    public HoaDonChiTietDAO(Context context) {
        dbHelper = new DatabaseHealper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertHoaDonChitiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues values = new ContentValues();

        values.put("mahoadon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        values.put("mamaugiay", hoaDonChiTiet.getMauGiay().getMaMauGiay());
        values.put("soluongmua", hoaDonChiTiet.getSoLuongMua());

        long result = db.insert(TABLE_NAME, null, values);
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<ThongKe> getHoaDonTheoNgay(String ngay) {
        List<ThongKe> thongKeList = new ArrayList<>();
        String sSQL = "select HoaDonChiTiet.mahoadon, HoaDon.ngaymua, sum((MauGiay.giaban*HoaDonChiTiet.soluongmua)) as tongtien from HoaDonChiTiet inner join MauGiay on HoaDonChiTiet.mamaugiay=MauGiay.mamaugiay inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%Y %m %d', HoaDon.ngaymua) = strftime('%Y %m %d', ?) group by HoaDonChiTiet.mahoadon,HoaDon.ngaymua order by HoaDon.ngaymua asc";
        Cursor cursor = db.rawQuery(sSQL, new String[]{ngay});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahoadon = cursor.getString(cursor.getColumnIndex("mahoadon"));
            String ngaymua = cursor.getString(cursor.getColumnIndex("ngaymua"));
            String tongtien = cursor.getString(cursor.getColumnIndex("tongtien"));
            ThongKe thongKe = new ThongKe(mahoadon, ngaymua, tongtien);
            thongKeList.add(thongKe);
            cursor.moveToNext();
        }
        cursor.close();

        return thongKeList;
    }

    public List<ThongKe> getTongDoanhThuTheoMoiThang() {
        List<ThongKe> thongKeList = new ArrayList<>();
        Cursor cursor = null;
        String sSQL = "select Sum(tongtien) from (select (MauGiay.giaban*HoaDonChiTiet*soluongmua) as tongtien from HoaDonChiTiet inner join MauGiay on HoaDonChiTiet.mamaugiay=MauGiay.mamaugiay inner join HoaDon on HoaDonChiTiet.mahoadon=HoaDon.mahoadon where strftime('%m',HoaDon.ngaymua)=?)";
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                String month = "0" + i;
                cursor = db.rawQuery(sSQL, new String[]{month});
            } else {
                i = +1;
                cursor = db.rawQuery(sSQL, new String[]{String.valueOf(i)});
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                double tongtien = 0;
                try {
                    cursor.getDouble(0);

                } catch (Exception e) {
                    tongtien = 0;
                }
                ThongKe thongKe = new ThongKe(tongtien + "");
                thongKeList.add(thongKe);
                cursor.moveToNext();
            }
        }

        return thongKeList;
    }

    public List<ThongKe> getDoanhThuTheoKhoangThoiGian(String from, String to) {
        List<ThongKe> thongKeList = new ArrayList<>();
        String sSQL = "select HoaDon.mahoadon, sum(MauGiay.giaban*HoaDonChiTiet.soluongmua) as tongtien, HoaDon.ngaymua from HoaDon inner join HoaDonChiTiet on HoaDon.mahoadon=HoaDonChiTiet.mahoadon inner join MauGiay on HoaDonChiTiet.mamaugiay=MauGiay.mamaugiay where strftime('%Y %m %d', HoaDon.ngaymua) between strftime('%Y %m %d', ?) and strftime('%Y %m %d', ?) group by HoaDon.mahoadon,HoaDon.ngaymua order by HoaDon.ngaymua asc";
        Cursor cursor = db.rawQuery(sSQL, new String[]{from, to});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahoadon = cursor.getString(cursor.getColumnIndex("mahoadon"));
            String ngaymua = cursor.getString(cursor.getColumnIndex("ngaymua"));
            String tongtien = cursor.getString(cursor.getColumnIndex("tongtien"));
            ThongKe thongKe = new ThongKe(mahoadon, ngaymua, tongtien);
            thongKeList.add(thongKe);
            cursor.moveToNext();
        }
        cursor.close();
        return thongKeList;
    }

    public List<ThongKe> getHoaDonChiTietTheoMaHoaDon(String mahoadon) {
        List<ThongKe> thongKeList = new ArrayList<>();
        String sSQL = "select HoaDonChiTiet.mahoadonchitiet, HoaDonChiTiet.mamaugiay, HoaDonChiTiet.soluongmua, MauGiay.giaban from HoaDonChiTiet inner join MauGiay on MauGiay.mamaugiay=HoaDonChiTiet.mamaugiay where HoaDonChiTiet.mahoadon=?";
        Cursor cursor = db.rawQuery(sSQL, new String[]{mahoadon});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahoadonchitiet = cursor.getString(0);
            String mamaugiay = cursor.getString(1);
            String soluong = cursor.getString(2);
            String giaban = cursor.getString(3);
            ThongKe thongKe = new ThongKe(mahoadonchitiet, mamaugiay, soluong, giaban);
            thongKeList.add(thongKe);
            cursor.moveToNext();
        }
        cursor.close();
        return thongKeList;
    }
}
