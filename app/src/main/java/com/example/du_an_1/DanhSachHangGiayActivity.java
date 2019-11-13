package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.du_an_1.adapter.HangGiayAdapter;
import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.model.HangGiay;

import java.util.ArrayList;
import java.util.List;

public class DanhSachHangGiayActivity extends AppCompatActivity {

    ListView lvDanhSachHangGiay;
    HangGiayAdapter hangGiayAdapter;
    HangGiayDAO hangGiayDAO;
    List<HangGiay> hangGiayList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hang_giay);
        setTitle("Danh sách hãng giày");

        lvDanhSachHangGiay = findViewById(R.id.lvDanhSachHangGiay);

        hangGiayDAO = new HangGiayDAO(this);
        hangGiayList = new ArrayList<>();
        hangGiayList = hangGiayDAO.getAllHangGiay();
        hangGiayAdapter = new HangGiayAdapter(hangGiayList, DanhSachHangGiayActivity.this);
        lvDanhSachHangGiay.setAdapter(hangGiayAdapter);
        lvDanhSachHangGiay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(DanhSachHangGiayActivity.this,SuaHangGiayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("HangGiay",hangGiayList.get(position));
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hang_giay, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add_hang_giay:
                startActivity(new Intent(DanhSachHangGiayActivity.this, ThemHangGiayActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
