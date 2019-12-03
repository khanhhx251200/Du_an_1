package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.example.du_an_1.model.MauGiay;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ThemMauGiayActivity extends AppCompatActivity {
    private TextInputLayout tilMMG, tilTMG, tilSL, tilMS, tilGB;
    private EditText edMaMG, edTenMG, edSoLuongMG, edMauSac, edGiaBan;
    private Spinner spnMaHG;


    MauGiayDAO mauGiayDAO;
    HangGiayDAO hangGiayDAO;
    List<MauGiay> mauGiayList;
    List<HangGiay> hangGiayList;
    String mahanggiay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mau_giay);
        setTitle("Thêm mẫu giày");

        tilMMG = findViewById(R.id.tilmmg);
        tilTMG = findViewById(R.id.tiltmg);
        tilSL = findViewById(R.id.tilslmg);
        tilMS = findViewById(R.id.tilms);
        tilGB = findViewById(R.id.tilgb);

        edMaMG = findViewById(R.id.edMaMauGiay);
        edTenMG = findViewById(R.id.edTenMauGiay);
        edSoLuongMG = findViewById(R.id.edSoluongMG);
        edMauSac = findViewById(R.id.edMauSac);
        edGiaBan = findViewById(R.id.edGiaBan);

        spnMaHG = findViewById(R.id.spnMaHangGiay);
        getMaHangGiay();
        spnMaHG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mahanggiay = hangGiayList.get(spnMaHG.getSelectedItemPosition()).getMaHangGiay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getMaHangGiay() {
        hangGiayDAO = new HangGiayDAO(this);
        hangGiayList = hangGiayDAO.getAllHangGiay();
        ArrayAdapter<HangGiay> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hangGiayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaHG.setAdapter(arrayAdapter);

    }

    public void themMauGiay(View view) {
        mauGiayDAO = new MauGiayDAO(this);

        String maMG = edMaMG.getText().toString();
        String tenMG = edTenMG.getText().toString();
        String mausac = edMauSac.getText().toString();
        int soluong = Integer.parseInt(edSoLuongMG.getText().toString());
        double giaban = Double.parseDouble(edGiaBan.getText().toString());

        MauGiay mauGiay = new MauGiay(maMG, mahanggiay, tenMG, soluong, mausac, giaban);
        boolean result = mauGiayDAO.insertMauGiay(mauGiay);
        if (result) {
            Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DanhSachMauGiayActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
