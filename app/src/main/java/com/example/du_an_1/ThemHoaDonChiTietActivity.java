package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_1.adapter.HoaDonChiTietAdapter;
import com.example.du_an_1.dao.HoaDonDAO;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.model.HoaDonChiTiet;
import com.example.du_an_1.model.MauGiay;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThemHoaDonChiTietActivity extends AppCompatActivity {
    EditText edMaHDCT, edNgayMua, edSoLuong;
    Spinner spnTenMG;
    ListView lvHDCT;
    MauGiayDAO mauGiayDAO;
    HoaDonDAO hoaDonDAO;
    List<MauGiay> mauGiayList;
    List<HoaDon> hoaDonList;
    List<HoaDonChiTiet> hoaDonChiTietList;
    Button btnThemVaoGioHang, btnXemHoaDon;
    HoaDonChiTietAdapter hoaDonChiTietAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don_chi_tiet);
        setTitle("Hóa Đơn Chi Tiết");

        iconBack();
        init();
        getTenMauGiay();

        edMaHDCT.setText("HD00");
        hoaDonChiTietAdapter = new HoaDonChiTietAdapter(hoaDonChiTietList, this);
        lvHDCT.setAdapter(hoaDonChiTietAdapter);


        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edMaHDCT.getText().toString().equals("") || edSoLuong.getText().toString().equals("")) {
                    Toast.makeText(ThemHoaDonChiTietActivity.this, "Vui lòng nhập đầy đủ các trường! ", Toast.LENGTH_SHORT).show();
                    return;
                } else if (getindexofMaHD(edMaHDCT.getText().toString()) != -1) {
                    Toast.makeText(ThemHoaDonChiTietActivity.this, "Mã Hóa Đơn Đã Tồn Tại!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    final MauGiay mauGiay = (MauGiay) spnTenMG.getSelectedItem();
                    if (hoaDonChiTietList.size() > 0) {
                        int index = -1;
                        for (int i = 0; i < hoaDonChiTietList.size(); i++) {
                            if (mauGiay.getMaMauGiay().equals(hoaDonChiTietList.get(i).getMauGiay().getMaMauGiay())) {
                                index = i;
                                break;
                            }
                        }
                        if (index >= 0) {
                            int tongSoLuongSach = hoaDonChiTietList.get(index).getMauGiay().getSoLuong();
                            if ((hoaDonChiTietList.get(index).getSoLuongMua() + Integer.parseInt(edSoLuong.getText().toString())) > tongSoLuongSach) {
                                Toast.makeText(ThemHoaDonChiTietActivity.this, "Số lượng mẫu giày tối đa bạn có thể mua là: " + (tongSoLuongSach - hoaDonChiTietList.get(index).getSoLuongMua() + ""), Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietList.get(index);
                                int soLuong = hoaDonChiTiet.getSoLuongMua() + Integer.parseInt(edSoLuong.getText().toString());
                                HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet(hoaDonChiTiet.getHoaDon(), mauGiay, soLuong);
                                hoaDonChiTietList.set(index, hoaDonChiTiet1);
                                hoaDonChiTietAdapter.notifyDataSetChanged();

                            }
                        } else {
                            themgiohang();
                        }
                    } else {
                        themgiohang();
                    }
                }
            }
        });
        btnXemHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hoaDonChiTietList.size() > 0) {
                    Intent intent = new Intent(ThemHoaDonChiTietActivity.this, ThemHoaDonActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mahoadon", edMaHDCT.getText().toString());
                    bundle.putString("ngaymua", edNgayMua.getText().toString());
                    List<MauGiay> mauGiayHoaDon = new ArrayList<>();
                    ArrayList<Integer> soLuong = new ArrayList<>();
                    for (int i = 0; i < hoaDonChiTietList.size(); i++) {
                        MauGiay mauGiay = hoaDonChiTietList.get(i).getMauGiay();
                        mauGiayHoaDon.add(mauGiay);
                        soLuong.add(hoaDonChiTietList.get(i).getSoLuongMua());
                    }
                    intent.putIntegerArrayListExtra("soluong", soLuong);
                    intent.putExtra("list", (Serializable) mauGiayHoaDon);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(ThemHoaDonChiTietActivity.this, "Hóa đơn chưa có sản phẩm!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void themgiohang() {
        final MauGiay mauGiay = (MauGiay) spnTenMG.getSelectedItem();
        String maHoaDon = edMaHDCT.getText().toString();
        edMaHDCT.setEnabled(false);
        edNgayMua.setEnabled(false);
        String soLuong = edSoLuong.getText().toString();

        if (Integer.parseInt(soLuong) > mauGiay.getSoLuong()) {
            Toast.makeText(this, "Số lượng mẫu giày còn lại trong kho là :" + mauGiay.getSoLuong(), Toast.LENGTH_SHORT).show();
            return;
        }

        HoaDon hoaDon = new HoaDon(maHoaDon, Calendar.getInstance().getTime());
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(hoaDon, mauGiay, Integer.parseInt(soLuong));

        hoaDonChiTietList.add(hoaDonChiTiet);
        hoaDonChiTietAdapter = new HoaDonChiTietAdapter(hoaDonChiTietList, this);
        lvHDCT.setAdapter(hoaDonChiTietAdapter);
        hoaDonChiTietAdapter.notifyDataSetChanged();
    }

    public void getTenMauGiay() {
        mauGiayDAO = new MauGiayDAO(this);
        mauGiayList = mauGiayDAO.getAllMauGiay();
        ArrayAdapter<MauGiay> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mauGiayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTenMG.setAdapter(arrayAdapter);
    }

    private int getindexofMaHD(String toString) {
        int index = -1;
        for (int i = 0; i < hoaDonList.size(); i++) {
            if (hoaDonList.get(i).getMaHoaDon().equals(toString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void init() {
        edMaHDCT = findViewById(R.id.edMaHDCT);
        edNgayMua = findViewById(R.id.edNgayMua);
        edSoLuong = findViewById(R.id.edSoluongMua);
        spnTenMG = findViewById(R.id.spnTenMauGiay);
        btnThemVaoGioHang = findViewById(R.id.btnThemGioHang);
        btnXemHoaDon = findViewById(R.id.btnXemHoaDon);
        lvHDCT = findViewById(R.id.lvHDCT);

        mauGiayDAO = new MauGiayDAO(this);
        hoaDonDAO = new HoaDonDAO(this);

        hoaDonChiTietList = new ArrayList<>();
        hoaDonList = new ArrayList<>();

        try {
            hoaDonList = hoaDonDAO.getAllHoaDon();
        } catch (ParseException e) {
            Toast.makeText(this, "Khong the lay list hoa don", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Date date = Calendar.getInstance().getTime();
        edNgayMua.setText(simpleDateFormat.format(date));
        edNgayMua.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getApplication(), MainActivity.class));
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
