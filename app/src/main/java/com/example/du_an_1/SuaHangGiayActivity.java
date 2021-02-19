package com.example.du_an_1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.model.HangGiay;

import java.util.ArrayList;
import java.util.List;

public class SuaHangGiayActivity extends AppCompatActivity {
    private EditText edSuaMaHangGiay, edSuaTenHangGiay, edSuaMoTa, edSuaViTri;
    private List<HangGiay> hangGiayList;
    private HangGiayDAO hangGiayDAO;
    private Intent intent;
    private String mahanggiay, tenhanggiay, mota;
    private int vitri;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_hang_giay);
        setTitle("Sửa Hãng Giày");
        iconBack();
        initUI();

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        HangGiay hangGiay = (HangGiay) bundle.getSerializable("HangGiay");
        edSuaMaHangGiay.setText(hangGiay.getMaHangGiay());
        edSuaTenHangGiay.setText(hangGiay.getTenHangGiay());
        edSuaMoTa.setText(hangGiay.getMoTa());

    }

    private void initUI() {
        edSuaMaHangGiay = findViewById(R.id.edSuaMaHangGiay);
        edSuaTenHangGiay = findViewById(R.id.edSuaTenHangGiay);
        edSuaMoTa = findViewById(R.id.edSuaMoTa);

        hangGiayDAO = new HangGiayDAO(this);
    }

    public void huysuahanggiay( View view ) {
        finish();
    }

    public void suaHanggiay( View view ) {

        String id = edSuaMaHangGiay.getText().toString();
        String name = edSuaTenHangGiay.getText().toString();
        String des = edSuaMoTa.getText().toString();
        HangGiay hangGiay = new HangGiay(id, name, des);

        long result = hangGiayDAO.updateHangGiay(hangGiay);
        if (result > 0) {
            Toast.makeText(this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
        }
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
