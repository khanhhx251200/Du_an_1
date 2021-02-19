package com.example.du_an_1.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MauGiay implements Serializable {
    private String maMauGiay;
    private String maHangGiay;
    private String tenMauGiay;
    private int soLuong;
    private String mauSac;
    private double giaBan;

    public MauGiay(String maMauGiay, String maHangGiay, String tenMauGiay, int soLuong, String mauSac, double giaBan) {
        this.maMauGiay = maMauGiay;
        this.maHangGiay = maHangGiay;
        this.tenMauGiay = tenMauGiay;
        this.soLuong = soLuong;
        this.mauSac = mauSac;
        this.giaBan = giaBan;
    }

    public MauGiay() {

    }

    public String getMaMauGiay() {
        return maMauGiay;
    }

    public void setMaMauGiay(String maMauGiay) {
        this.maMauGiay = maMauGiay;
    }

    public String getMaHangGiay() {
        return maHangGiay;
    }

    public void setMaHangGiay(String maHangGiay) {
        this.maHangGiay = maHangGiay;
    }

    public String getTenMauGiay() {
        return tenMauGiay;
    }

    public void setTenMauGiay(String tenMauGiay) {
        this.tenMauGiay = tenMauGiay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    @NonNull
    @Override
    public String toString() {
        return tenMauGiay;
    }
}
