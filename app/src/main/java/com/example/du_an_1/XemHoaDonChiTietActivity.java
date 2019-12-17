package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.du_an_1.adapter.LvThongKeHoaDonAdapter;
import com.example.du_an_1.adapter.LvXemHoaDonChiTiet;
import com.example.du_an_1.dao.HoaDonChiTietDAO;
import com.example.du_an_1.model.ThongKe;

import java.util.ArrayList;
import java.util.List;

public class XemHoaDonChiTietActivity extends AppCompatActivity {
    ListView listView;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    List<ThongKe> thongKeList;
    LvXemHoaDonChiTiet lvXemHoaDonChiTiet;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hoa_don_chi_tiet);
        setTitle("Xem Hóa Đơn Chi Tiết");

        listView = findViewById(R.id.lvXemHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        intent = getIntent();
        String mahoadon = intent.getStringExtra("mahoadon");
        thongKeList = new ArrayList<>();
        thongKeList = hoaDonChiTietDAO.getHoaDonChiTietTheoMaHoaDon(mahoadon);
        lvXemHoaDonChiTiet = new LvXemHoaDonChiTiet(this, thongKeList);
        listView.setAdapter(lvXemHoaDonChiTiet);

    }
}
