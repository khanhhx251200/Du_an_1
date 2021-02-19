package com.example.du_an_1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hang_giay);
        setTitle("Thêm hãng giày");
        iconBack();

        initUI();

        hangGiayDAO = new HangGiayDAO(this);
        hangGiayList = new ArrayList<>();
        hangGiayList = hangGiayDAO.getAllHangGiay();


    }

    private void initUI() {
        edTenHangGiay = findViewById(R.id.edTenHangGiay);
        edMoTa = findViewById(R.id.edMoTa);
        edMaHangGiay = findViewById(R.id.ed_mahanggiay);
        tilTenHangGiay = findViewById(R.id.tilthg);
        tilMaHangGiay = findViewById(R.id.tilmhg);
        tilMoTa = findViewById(R.id.tilmota);
        btnThemHG = findViewById(R.id.btnThemHangGiay);
    }


    public void ThemHangGiay( View view ) {
        String maHanggiay = edMaHangGiay.getText().toString().trim();
        String tenHanggiay = edTenHangGiay.getText().toString().trim();
        String moTa = edMoTa.getText().toString().trim();

        hangGiay = new HangGiay(maHanggiay, tenHanggiay, moTa);

        long isInsertTrue = hangGiayDAO.insertHangGiay(hangGiay);
        if (isInsertTrue > 0) {
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void HuyHangGiay( View view ) {
        finish();
    }


    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, DanhSachHangGiayActivity.class);
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
