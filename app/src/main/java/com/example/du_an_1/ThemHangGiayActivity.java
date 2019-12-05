package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ThemHangGiayActivity extends AppCompatActivity {
    TextInputLayout tilMaHangGiay, tilTenHangGiay, tilMoTa, tilViTri;
    EditText edMaHangGiay, edTenHangGiay, edMoTa, edViTri;
    Button btnThemHG;
    HangGiay hangGiay;
    HangGiayDAO hangGiayDAO;
    List<HangGiay> hangGiayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hang_giay);
        setTitle("Thêm hãng giày");
        iconBack();
        edMaHangGiay = findViewById(R.id.edMaHangGiay);
        edTenHangGiay = findViewById(R.id.edTenHangGiay);
        edMoTa = findViewById(R.id.edMoTa);
        edViTri = findViewById(R.id.edViTri);

        tilMaHangGiay = findViewById(R.id.tilmhg);
        tilTenHangGiay = findViewById(R.id.tilthg);
        tilMoTa = findViewById(R.id.tilmota);
        tilViTri = findViewById(R.id.tilvitri);
        btnThemHG = findViewById(R.id.btnThemHangGiay);

        hangGiayDAO = new HangGiayDAO(this);
        hangGiayList = new ArrayList<>();
        hangGiayList = hangGiayDAO.getAllHangGiay();

        edMaHangGiay.setText("HG0");

    }


    public void ThemHangGiay(View view) {
        hangGiayDAO = new HangGiayDAO(this);
        validate();
        String maHanggiay = edMaHangGiay.getText().toString();
        String tenHanggiay = edTenHangGiay.getText().toString();
        String moTa = edMoTa.getText().toString();
        int viTri = Integer.parseInt(edViTri.getText().toString());

        hangGiay = new HangGiay(maHanggiay, tenHanggiay, moTa, viTri);

        boolean isInsertTrue = hangGiayDAO.insertHangGiay(hangGiay);
        if (isInsertTrue) {
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ThemHangGiayActivity.this, DanhSachHangGiayActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }


    }

    public void HuyHangGiay(View view) {
        startActivity(new Intent(ThemHangGiayActivity.this, DanhSachHangGiayActivity.class));
    }

    public void validate() {

        boolean isValid = true;

        if (edMaHangGiay.getText().toString().isEmpty()) {

            tilMaHangGiay.setError("Vui lòng nhập Mã hãng giày");
            isValid = false;
        } else {
            tilMaHangGiay.setErrorEnabled(false);

        }
        if (edTenHangGiay.getText().toString().isEmpty()) {
            tilTenHangGiay.setError("Vui lòng nhập Tên hãng giày");
            isValid = false;
        } else {
            tilTenHangGiay.setErrorEnabled(false);

        }
        if (edMoTa.getText().toString().isEmpty()) {
            tilMoTa.setError("Vui lòng nhập Mô tả");
            isValid = false;
        } else {
            tilMoTa.setErrorEnabled(false);
        }
        if (edViTri.getText().toString().isEmpty()) {
            tilViTri.setError("Vui lòng nhập Vị trí");
            isValid = false;
        } else {


            tilViTri.setErrorEnabled(false);
        }
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
