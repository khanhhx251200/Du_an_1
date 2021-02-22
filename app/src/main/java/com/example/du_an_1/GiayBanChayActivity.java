package com.example.du_an_1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.adapter.LvMauGiayBanChayAdapter;
import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class GiayBanChayActivity extends AppCompatActivity {
    EditText edGiayBanChay;
    Button btnGiayBanChay;
    Spinner spnMonth;
    ListView lvGiayBanChay;
    List<MauGiay> mauGiayList;
    HangGiayDAO hangGiayDAO;
    MauGiayDAO mauGiayDAO;
    LvMauGiayBanChayAdapter mauGiayAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giay_ban_chay);
        setTitle("Top 10 giày bán chạy");


        iconBack();
        init();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Tháng 1");
        arrayList.add("Tháng 2");
        arrayList.add("Tháng 3");
        arrayList.add("Tháng 4");
        arrayList.add("Tháng 5");
        arrayList.add("Tháng 6");
        arrayList.add("Tháng 7");
        arrayList.add("Tháng 8");
        arrayList.add("Tháng 9");
        arrayList.add("Tháng 10");
        arrayList.add("Tháng 11");
        arrayList.add("Tháng 12");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMonth.setAdapter(arrayAdapter);
        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long l ) {
                try {
                    String month = String.valueOf(position + 1);
                    mauGiayList = mauGiayDAO.getTop10(month);

                } catch (Exception ex) {
                    Toast.makeText(GiayBanChayActivity.this, "Vui lòng nhập đúng tháng" + ex, Toast.LENGTH_SHORT).show();
                }
                if (mauGiayList.size() > 0) {

                    mauGiayAdapter = new LvMauGiayBanChayAdapter(getApplication(), mauGiayList);
                    lvGiayBanChay.setAdapter(mauGiayAdapter);
                    mauGiayAdapter.notifyDataSetChanged();

                } else {
                    mauGiayList.clear();
                    mauGiayAdapter = new LvMauGiayBanChayAdapter(getApplication(), mauGiayList);
                    lvGiayBanChay.setAdapter(mauGiayAdapter);
                }
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView ) {

            }
        });

//        btnGiayBanChay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (edGiayBanChay.getText().toString().equals("")) {
//                    Toast.makeText(GiayBanChayActivity.this, "Vui lòng nhập tháng cần tìm", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    try {
//                        String month = edGiayBanChay.getText().toString();
//                        mauGiayList = mauGiayDAO.getTop10(month);
//
//                    } catch (Exception ex) {
//                        edGiayBanChay.setText(ex + "");
//                        Toast.makeText(GiayBanChayActivity.this, "Vui lòng nhập đúng tháng" + ex, Toast.LENGTH_SHORT).show();
//                    }
//                    if (mauGiayList.size() > 0) {
//                        ;
//                        mauGiayAdapter = new LvMauGiayBanChayAdapter(getApplication(), mauGiayList);
//                        lvGiayBanChay.setAdapter(mauGiayAdapter);
//                        mauGiayAdapter.notifyDataSetChanged();
//
//                    } else {
//                        mauGiayList.clear();
//                        mauGiayAdapter = new LvMauGiayBanChayAdapter(getApplication(), mauGiayList);
//                        lvGiayBanChay.setAdapter(mauGiayAdapter);
//                    }
//                }
//            }
//        });
    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        spnMonth = findViewById(R.id.spn_month);
        lvGiayBanChay = findViewById(R.id.lvGiayBanChay);
        mauGiayDAO = new MauGiayDAO(this);
        hangGiayDAO = new HangGiayDAO(this);
        mauGiayList = new ArrayList<>();


    }


}
