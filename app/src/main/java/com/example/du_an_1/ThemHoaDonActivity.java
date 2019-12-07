package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.adapter.HoaDonAdapter;
import com.example.du_an_1.dao.HoaDonChiTietDAO;
import com.example.du_an_1.dao.HoaDonDAO;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.model.HoaDonChiTiet;
import com.example.du_an_1.model.MauGiay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThemHoaDonActivity extends AppCompatActivity {
    EditText edMaHoaDon, edNgayMua;
    Button btnThanhToan;
    TextView tvTongTien;
    ListView lvHoaDon;
    List<MauGiay> mauGiayList;
    List<HoaDonChiTiet> hoaDonChiTietList;
    ArrayList<Integer> soLuong;
    HoaDonAdapter hoaDonAdapter;
    MauGiayDAO mauGiayDAO;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HoaDonDAO hoaDonDAO;
    String maHoaDon, ngayMua;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        setTitle("Hóa Đơn");

        iconBack();
        init();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        if (bundle != null) {
            maHoaDon = bundle.getString("mahoadon");
            ngayMua = bundle.getString("ngaymua");
            edMaHoaDon.setText(maHoaDon);
            edNgayMua.setText(ngayMua);
            edMaHoaDon.setEnabled(false);
            edNgayMua.setEnabled(false);
            mauGiayList = (List<MauGiay>) intent.getSerializableExtra("list");
            soLuong = intent.getIntegerArrayListExtra("soluong");
            hoaDonAdapter = new HoaDonAdapter(this, mauGiayList, soLuong);
            lvHoaDon.setAdapter(hoaDonAdapter);

            setTongTien();
        }
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon hoaDon = null;
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH + 1);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String date = year + "-" + month + "-" + day + "  " + hour + ":" + minutes + ":" + second;
                try {
                    hoaDon = new HoaDon(maHoaDon, simpleDateFormat.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(ThemHoaDonActivity.this, "Không lưu được ngày giờ", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < mauGiayList.size(); i++) {
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(hoaDon, mauGiayList.get(i), soLuong.get(i));
                    boolean result = hoaDonChiTietDAO.insertHoaDonChitiet(hoaDonChiTiet);
                    if (result) {
                        MauGiay mauGiay = mauGiayList.get(i);
                        int resultUpdateMauGiay = mauGiayDAO.updateMauGiay(mauGiay.getMaMauGiay(), mauGiay.getMaHangGiay(), mauGiay.getTenMauGiay(), mauGiay.getSoLuong() - soLuong.get(i), mauGiay.getMauSac(), mauGiay.getGiaBan());
                        if (resultUpdateMauGiay > 0) {
                            Toast.makeText(ThemHoaDonActivity.this, "Thành công!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(), MainActivity.class));
                        } else {
                            Toast.makeText(ThemHoaDonActivity.this, "Thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ThemHoaDonActivity.this, "Thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }


    private void init() {
        edMaHoaDon = findViewById(R.id.edMaHDXHD);
        edNgayMua = findViewById(R.id.edNgayMuaXHD);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        tvTongTien = findViewById(R.id.tvTongTien);
        lvHoaDon = findViewById(R.id.lvXemHoaDon);

        mauGiayDAO = new MauGiayDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);

        mauGiayList = new ArrayList<>();

        soLuong = new ArrayList<>();


    }

    private void setTongTien() {
        double tongtien = 0;
        //hóa đơn chi tiết list chưa sang được
        //xem lại intent
        for (int i = 0; i < mauGiayList.size(); i++) {
            tongtien += mauGiayList.get(i).getGiaBan() * soLuong.get(i);
        }
        tvTongTien.setText("Tổng tiền: " + tongtien + "00 VNĐ");
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }
}
