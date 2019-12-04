package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SuaMauGiayActivity extends AppCompatActivity {
    private TextInputLayout tilMMG, tilTMG, tilSL, tilMS, tilGB;
    private EditText edMaMG, edTenMG, edSoLuongMG, edMauSac, edGiaBan;
    private Spinner spnMaHG;

    private HangGiayDAO hangGiayDAO;
    private MauGiayDAO mauGiayDAO;
    private List<HangGiay> hangGiayList;
    private List<MauGiay> mauGiayList;
    private Intent intent;
    private String mamaugiay, mahanggiay, tenmaugiay, mausac;
    private Double giaban;
    private int soluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mau_giay);

        tilMMG = findViewById(R.id.tilSuammg);
        tilTMG = findViewById(R.id.tilSuatmg);
        tilSL = findViewById(R.id.tilSuaslmg);
        tilMS = findViewById(R.id.tilSuams);
        tilGB = findViewById(R.id.tilSuagb);

        mauGiayDAO = new MauGiayDAO(this);
        hangGiayDAO = new HangGiayDAO(this);


        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        MauGiay mauGiay = (MauGiay) bundle.getSerializable("MauGiay");

        edMaMG = findViewById(R.id.edSuaMauGiay);
        edTenMG = findViewById(R.id.edSuaTenMauGiay);
        edSoLuongMG = findViewById(R.id.edSuaSoluongMG);
        edMauSac = findViewById(R.id.edSuaMauSac);
        edGiaBan = findViewById(R.id.edSuaGiaBan);
        spnMaHG = findViewById(R.id.spnSuaMaHangGiay);
        getMaHangGiay();

        mamaugiay = mauGiay.getMaMauGiay();
        tenmaugiay = mauGiay.getTenMauGiay();
        soluong = mauGiay.getSoLuong();
        mausac = mauGiay.getMauSac();
        giaban = mauGiay.getGiaBan();

        edMaMG.setText(mamaugiay);
        edMaMG.setEnabled(false);
        edTenMG.setText(tenmaugiay);
        edSoLuongMG.setText(soluong + "");
        edMauSac.setText(mausac);
        edGiaBan.setText(giaban + "00");

        hangGiayList = new ArrayList<>();
        hangGiayList = hangGiayDAO.getAllHangGiay();


        int index = -1;
        for (int i = 0; i < hangGiayList.size(); i++) {
            if (mauGiay.getMaHangGiay().equalsIgnoreCase(hangGiayList.get(i).getMaHangGiay())) {
                index = i;
                break;
            }
        }
        Log.e("hihi", index + "");
        spnMaHG.setSelection(index);

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


    public void suaMauGiay(View view) {
        mauGiayDAO = new MauGiayDAO(this);
        long result = mauGiayDAO.updateMauGiay(edMaMG.getText().toString(), mahanggiay, edTenMG.getText().toString(), Integer.parseInt(edSoLuongMG.getText().toString()), edMauSac.getText().toString(), Double.parseDouble(edGiaBan.getText().toString()));
        if (result > 0) {
            Toast.makeText(this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DanhSachMauGiayActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getMaHangGiay() {
        hangGiayDAO = new HangGiayDAO(this);
        hangGiayList = hangGiayDAO.getAllHangGiay();
        ArrayAdapter<HangGiay> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hangGiayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaHG.setAdapter(arrayAdapter);

    }
}
