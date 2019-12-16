package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.du_an_1.fragment.FragmentNode;
import com.google.android.material.tabs.TabLayout;

public class DoanhThuActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentNode fragmentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        setTitle("Doanh thu Shop");
        iconBack();

        viewPager = findViewById(R.id.vpDoanhThu);
        tabLayout = findViewById(R.id.tlDoanhThu);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentNode = new FragmentNode(fragmentManager);
        viewPager.setAdapter(fragmentNode);
        tabLayout.setupWithViewPager(viewPager);
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
                startActivity(new Intent(getApplication(), MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
