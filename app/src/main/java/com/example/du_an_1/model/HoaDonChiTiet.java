package com.example.du_an_1.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class HoaDonChiTiet implements Serializable {
    private int maHoaDonChiTiet;
    private HoaDon hoaDon;
    private MauGiay mauGiay;
    private int soLuongMua;

    public HoaDonChiTiet(int maHoaDonChiTiet, HoaDon hoaDon, MauGiay mauGiay, int soLuongMua) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.hoaDon = hoaDon;
        this.mauGiay = mauGiay;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(HoaDon hoaDon, MauGiay mauGiay, int soLuongMua) {
        this.hoaDon = hoaDon;
        this.mauGiay = mauGiay;
        this.soLuongMua = soLuongMua;
    }

    public int getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(int maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public MauGiay getMauGiay() {
        return mauGiay;
    }

    public void setMauGiay(MauGiay mauGiay) {
        this.mauGiay = mauGiay;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
}
