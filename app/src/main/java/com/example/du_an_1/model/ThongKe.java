package com.example.du_an_1.model;

public class ThongKe {
    private String mahoadon;
    private String mahoadonchitiet;
    private String ngaymua;
    private String tongtien;
    private String magiay;
    private String soluong;
    private String giaban;
    private String tongdoanhthuthang;

    public ThongKe(String tongdoanhthuthang) {
        this.tongdoanhthuthang = tongdoanhthuthang;
    }

    public ThongKe(String mahoadonchitiet, String magiay, String soluong, String giaban) {
        this.mahoadonchitiet = mahoadonchitiet;
        this.magiay = magiay;
        this.soluong = soluong;
        this.giaban = giaban;
    }

    public ThongKe(String mahoadon, String ngaymua, String tongtien) {
        this.mahoadon = mahoadon;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getMahoadonchitiet() {
        return mahoadonchitiet;
    }

    public void setMahoadonchitiet(String mahoadonchitiet) {
        this.mahoadonchitiet = mahoadonchitiet;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getMagiay() {
        return magiay;
    }

    public void setMagiay(String magiay) {
        this.magiay = magiay;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGiaban() {
        return giaban;
    }

    public void setGiaban(String giaban) {
        this.giaban = giaban;
    }

    public String getTongdoanhthuthang() {
        return tongdoanhthuthang;
    }

    public void setTongdoanhthuthang(String tongdoanhthuthang) {
        this.tongdoanhthuthang = tongdoanhthuthang;
    }
}
