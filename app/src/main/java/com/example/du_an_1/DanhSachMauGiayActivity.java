package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.du_an_1.adapter.MauGiayAdapter;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMauGiayActivity extends AppCompatActivity {
    ListView lvMG;
    EditText edSearch;
    List<MauGiay> mauGiayList;
    List<HangGiay> hangGiayList;
    MauGiayDAO mauGiayDAO;
    MauGiayAdapter mauGiayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mau_giay);
        setTitle("Danh sách mẫu giày");
        edSearch = findViewById(R.id.edTimMauGiay);

        mauGiayDAO = new MauGiayDAO(this);
        mauGiayList = new ArrayList<>();
        mauGiayList = mauGiayDAO.getAllMauGiay();
        mauGiayAdapter = new MauGiayAdapter(this, mauGiayList, hangGiayList);
        lvMG = findViewById(R.id.lvDanhSachMauGiay);
        lvMG.setAdapter(mauGiayAdapter);
        lvMG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhSachMauGiayActivity.this, SuaMauGiayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MauGiay", mauGiayList.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
