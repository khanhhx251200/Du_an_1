package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class ThemHoaDonChiTietActivity extends AppCompatActivity {
    EditText edMaHDCT, edNgayMua, edSoLuong;
    Spinner spnTenMG;
    ListView lvHDCT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don_chi_tiet);
        setTitle("Hóa Đơn Chi Tiết");

        edMaHDCT = findViewById(R.id.edMaHDCT);
        edNgayMua = findViewById(R.id.edNgayMua);
        edSoLuong = findViewById(R.id.edSoluongMua);
        spnTenMG = findViewById(R.id.spnTenMauGiay);
        lvHDCT = findViewById(R.id.lvHDCT);
    }
}
