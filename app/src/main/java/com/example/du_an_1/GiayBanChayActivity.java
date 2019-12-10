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
import android.widget.Toast;

import com.example.du_an_1.adapter.LvMauGiayBanChayAdapter;
import com.example.du_an_1.adapter.MauGiayAdapter;
import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class GiayBanChayActivity extends AppCompatActivity {
    EditText edGiayBanChay;
    Button btnGiayBanChay;
    ListView lvGiayBanChay;
    List<MauGiay> mauGiayList;
    HangGiayDAO hangGiayDAO;
    MauGiayDAO mauGiayDAO;
    LvMauGiayBanChayAdapter mauGiayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giay_ban_chay);
        setTitle("Top 10 giày bán chạy");


        iconBack();
        init();

        btnGiayBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edGiayBanChay.getText().toString().equals("")) {
                    Toast.makeText(GiayBanChayActivity.this, "Vui lòng nhập tháng cần tìm", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        String month = edGiayBanChay.getText().toString();
                        mauGiayList = mauGiayDAO.getTop10(month);

                    } catch (Exception ex) {
                        edGiayBanChay.setText(ex + "");
                        Toast.makeText(GiayBanChayActivity.this, "Vui lòng nhập đúng tháng" + ex, Toast.LENGTH_SHORT).show();
                    }
                    if (mauGiayList.size() > 0) {
                        ;
                        mauGiayAdapter = new LvMauGiayBanChayAdapter(getApplication(), mauGiayList);
                        lvGiayBanChay.setAdapter(mauGiayAdapter);
                        mauGiayAdapter.notifyDataSetChanged();

                    } else {
                        mauGiayList.clear();
                        mauGiayAdapter = new LvMauGiayBanChayAdapter(getApplication(), mauGiayList);
                        lvGiayBanChay.setAdapter(mauGiayAdapter);
                    }
                }
            }
        });
    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        edGiayBanChay = findViewById(R.id.edGiayBanChay);
        btnGiayBanChay = findViewById(R.id.btnGiayBanChay);
        lvGiayBanChay = findViewById(R.id.lvGiayBanChay);
        mauGiayDAO = new MauGiayDAO(this);
        hangGiayDAO = new HangGiayDAO(this);
        mauGiayList = new ArrayList<>();


    }


}
