package com.example.du_an_1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.example.du_an_1.model.MauGiay;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ThemMauGiayActivity extends AppCompatActivity {
    MauGiayDAO mauGiayDAO;
    HangGiayDAO hangGiayDAO;
    List<MauGiay> mauGiayList;
    List<HangGiay> hangGiayList;
    String mahanggiay;
    private TextInputLayout tilMMG, tilTMG, tilSL, tilMS, tilGB;
    private EditText edMaMG, edTenMG, edSoLuongMG, edMauSac, edGiaBan;
    private Spinner spnMaHG;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mau_giay);
        setTitle("Thêm mẫu giày");
        iconBack();
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

        edMaMG.setText("MG00");
        spnMaHG = findViewById(R.id.spnMaHangGiay);
        getMaHangGiay();
        spnMaHG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
                mahanggiay = hangGiayList.get(spnMaHG.getSelectedItemPosition()).getMaHangGiay();
            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ) {

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

    public void themMauGiay( View view ) {
        mauGiayDAO = new MauGiayDAO(this);
        validate();
        String maMG = edMaMG.getText().toString();
        String tenMG = edTenMG.getText().toString();
        String mausac = edMauSac.getText().toString();
        int soluong = Integer.parseInt(edSoLuongMG.getText().toString());
        int giaban = Integer.parseInt(edGiaBan.getText().toString());

        MauGiay mauGiay = new MauGiay(maMG, mahanggiay, tenMG, soluong, mausac, giaban);
        long result = mauGiayDAO.insertMauGiay(mauGiay);
        if (result > 0) {
            Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void validate() {

        boolean isValid = true;

        if (edMaMG.getText().toString().isEmpty()) {

            tilMMG.setError("Vui lòng nhập Mã mẫu giày");
            isValid = false;
        } else {
            tilMMG.setErrorEnabled(false);

        }
        if (edTenMG.getText().toString().isEmpty()) {
            tilTMG.setError("Vui lòng nhập Tên hãng giày");
            isValid = false;
        } else {
            tilTMG.setErrorEnabled(false);

        }
        if (edSoLuongMG.getText().toString().isEmpty()) {
            tilSL.setError("Vui lòng nhập Mô tả");
            isValid = false;
        } else {
            tilSL.setErrorEnabled(false);
        }
        if (edMauSac.getText().toString().isEmpty()) {
            tilMS.setError("Vui lòng nhập Vị trí");
            isValid = false;
        } else {
            tilMS.setErrorEnabled(false);
        }
        if (edGiaBan.getText().toString().isEmpty()) {
            tilGB.setError("Vui lòng nhập Vị trí");
            isValid = false;
        } else {
            tilGB.setErrorEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, DanhSachMauGiayActivity.class);
                startActivity(intent);
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
