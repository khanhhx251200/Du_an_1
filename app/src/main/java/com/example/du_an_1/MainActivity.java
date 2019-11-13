package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout lnHangGiay, lnMauGiay, lnHoaDon, lnDoanhThu, lnTop10, lnThoat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Quản lý cửa hàng");

        lnHangGiay = findViewById(R.id.lnHangGiay);
        lnMauGiay = findViewById(R.id.lnMauGiay);
        lnHoaDon = findViewById(R.id.lnHoaDon);
        lnDoanhThu = findViewById(R.id.lnDoanhThu);
        lnTop10 = findViewById(R.id.lnBestSeller);
        lnThoat = findViewById(R.id.lnThoat);
        lnHangGiay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DanhSachHangGiayActivity.class));
            }
        });
        lnMauGiay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DanhSachMauGiayActivity.class));
            }
        });
        lnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThemHoaDonActivity.class));
            }
        });
        lnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DoanhThuActivity.class));
            }
        });
        lnTop10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GiayBanChayActivity.class));
            }
        });
        lnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


    }
}
