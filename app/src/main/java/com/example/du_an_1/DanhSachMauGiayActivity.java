package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class DanhSachMauGiayActivity extends AppCompatActivity {
    ListView lvMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mau_giay);
        lvMG = findViewById(R.id.lvDanhSachMauGiay);
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
