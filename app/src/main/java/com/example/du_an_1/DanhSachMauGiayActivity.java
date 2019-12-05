package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.du_an_1.adapter.MauGiayAdapter;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMauGiayActivity extends AppCompatActivity {
    private ListView lvMG;
    private EditText edSearch;
    private List<MauGiay> mauGiayList;
    private List<HangGiay> hangGiayList;
    private MauGiayDAO mauGiayDAO;
    private MauGiayAdapter mauGiayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mau_giay);
        setTitle("Danh sách mẫu giày");

        iconBack();
        edSearch = findViewById(R.id.edTimMauGiay);
        lvMG = findViewById(R.id.lvDanhSachMauGiay);
        mauGiayDAO = new MauGiayDAO(this);
        mauGiayList = new ArrayList<>();
        mauGiayList = mauGiayDAO.getAllMauGiay();
        mauGiayAdapter = new MauGiayAdapter(this, mauGiayList, hangGiayList);

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
            String keySearch;
            List<MauGiay> dsGiayTim = new ArrayList<>();
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mauGiayList = mauGiayDAO.getAllMauGiay();
                mauGiayAdapter = new MauGiayAdapter(getApplication(), mauGiayList, hangGiayList);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                keySearch = edSearch.getText().toString();
//                dsGiayTim = mauGiayDAO.getMayGiayByName(keySearch);
//                Toast.makeText(getApplication(), "onTextChanged" + dsGiayTim.size(), Toast.LENGTH_SHORT).show();
//                mauGiayAdapter = new MauGiayAdapter(getApplication(),dsGiayTim, hangGiayList);
//                lvMG.setAdapter(mauGiayAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {
//                keySearch = edSearch.getText().toString();
//                dsGiayTim = mauGiayDAO.getMayGiayByName(keySearch);
//                Toast.makeText(getApplication(), "afterTextChanged" + dsGiayTim.size(), Toast.LENGTH_SHORT).show();
                mauGiayList = mauGiayDAO.getAllMauGiay();
                mauGiayAdapter = new MauGiayAdapter(getApplication(), mauGiayList, hangGiayList);
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
