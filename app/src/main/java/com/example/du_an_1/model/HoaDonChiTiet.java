package com.example.du_an_1.model;

import androidx.annotation.NonNull;

public class HoaDonChiTiet {
    private String maHoaDonChiTiet;
    private String ngayMua;
    private String tenMauGiay;
    private int soLuongMua;

    public HoaDonChiTiet(String maHoaDonChiTiet, String ngayMua, String tenMauGiay, int soLuongMua) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.ngayMua = ngayMua;
        this.tenMauGiay = tenMauGiay;
        this.soLuongMua = soLuongMua;
    }

    public String getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(String maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getTenMauGiay() {
        return tenMauGiay;
    }

    public void setTenMauGiay(String tenMauGiay) {
        this.tenMauGiay = tenMauGiay;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }


}
