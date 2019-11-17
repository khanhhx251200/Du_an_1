package com.example.du_an_1.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class HangGiay implements Serializable {
    private String maHangGiay;
    private String tenHangGiay;
    private String moTa;
    private int viTri;

    public HangGiay(String maHangGiay, String tenHangGiay, String moTa, int viTri) {
        this.maHangGiay = maHangGiay;
        this.tenHangGiay = tenHangGiay;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public HangGiay() {

    }

    public String getMaHangGiay() {
        return maHangGiay;
    }

    public void setMaHangGiay(String maHangGiay) {
        this.maHangGiay = maHangGiay;
    }

    public String getTenHangGiay() {
        return tenHangGiay;
    }

    public void setTenHangGiay(String tenHangGiay) {
        this.tenHangGiay = tenHangGiay;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    @NonNull
    @Override
    public String toString() {
        return maHangGiay + " | " + tenHangGiay;
    }
}
