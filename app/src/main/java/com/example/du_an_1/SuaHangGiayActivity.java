package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_hang_giay);
        setTitle("Sửa Hãng Giày");
        iconBack();
        edSuaMaHangGiay = findViewById(R.id.edSuaMaHangGiay);
        edSuaTenHangGiay = findViewById(R.id.edSuaTenHangGiay);
        edSuaMoTa = findViewById(R.id.edSuaMoTa);
        edSuaViTri = findViewById(R.id.edSuaViTri);

        hangGiayDAO = new HangGiayDAO(this);
        hangGiayList = new ArrayList<>();
        hangGiayList = hangGiayDAO.getAllHangGiay();

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        HangGiay hangGiay = (HangGiay) bundle.getSerializable("HangGiay");

        mahanggiay = hangGiay.getMaHangGiay();
        tenhanggiay = hangGiay.getTenHangGiay();
        mota = hangGiay.getMoTa();
        vitri = hangGiay.getViTri();

        edSuaMaHangGiay.setText(mahanggiay);
        edSuaTenHangGiay.setText(tenhanggiay);
        edSuaMoTa.setText(mota);
        edSuaViTri.setText(vitri + "");
        edSuaMaHangGiay.setEnabled(false);

    }

    public void huysuahanggiay(View view) {
        intent = new Intent(SuaHangGiayActivity.this, DanhSachHangGiayActivity.class);
        startActivity(intent);
    }

    public void suaHanggiay(View view) {
        hangGiayDAO = new HangGiayDAO(this);
        long result = hangGiayDAO.updateHangGiay(edSuaMaHangGiay.getText().toString(), edSuaTenHangGiay.getText().toString(), edSuaMoTa.getText().toString(), Integer.parseInt(edSuaViTri.getText().toString()));
        if (result > 0) {
            Toast.makeText(this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
            intent = new Intent(SuaHangGiayActivity.this, DanhSachHangGiayActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
