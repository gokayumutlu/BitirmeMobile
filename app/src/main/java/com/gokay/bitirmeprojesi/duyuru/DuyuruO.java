package com.gokay.bitirmeprojesi.duyuru;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.gokay.bitirmeprojesi.R;

public class DuyuruO extends AppCompatActivity {

    private ViewPager mViewPager;
    private DGonderPagerAdapter dgpAdapter;
    private TabLayout mtabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_o);
        toolbar=findViewById(R.id.duyuru_o_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Duyuru Yayınla");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager=findViewById(R.id.duyuruOVP);
        dgpAdapter=new DGonderPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(dgpAdapter);
        mtabLayout=findViewById(R.id.duyuruOTab);
        mtabLayout.setupWithViewPager(mViewPager);

    }
}
