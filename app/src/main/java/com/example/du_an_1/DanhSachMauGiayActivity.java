package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.du_an_1.adapter.MauGiayAdapter;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMauGiayActivity extends AppCompatActivity {
    ListView lvMG;
    List<MauGiay> mauGiayList;
    MauGiayDAO mauGiayDAO;
    MauGiayAdapter mauGiayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mau_giay);
        setTitle("Danh sách mẫu giày");

        mauGiayDAO = new MauGiayDAO(this);
        mauGiayList = new ArrayList<>();
        mauGiayList = mauGiayDAO.getAllMauGiay();
        mauGiayAdapter = new MauGiayAdapter(this, mauGiayList);
        lvMG = findViewById(R.id.lvDanhSachMauGiay);
        lvMG.setAdapter(mauGiayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mau_giay, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add_mau_giay:
                startActivity(new Intent(this, ThemMauGiayActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
