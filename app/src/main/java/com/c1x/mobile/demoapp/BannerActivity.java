package com.c1x.mobile.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import c1x.lib.adhive.dao.AdRequest;
import c1x.lib.adhive.dao.enums.AdSize;
import c1x.lib.adhive.view.BannerAdView;

/**
 * Created by arindamnath on 21/09/15.
 */
public class BannerActivity extends AppCompatActivity {

    private Spinner sizeSelector;
    private AdRequest adRequest;
    private BannerAdView bannerAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Banner Ads");

        sizeSelector = (Spinner) findViewById(R.id.banner_ad_size_selector);
        bannerAdView = (BannerAdView) findViewById(R.id.banner_ad);
        adRequest = new AdRequest.Builder()
                .appId(370877)
                .adUnitId(150441)
                .adSize(AdSize._320x50)
                .build();

        findViewById(R.id.banner_ad_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adRequest != null) {
                    bannerAdView.loadAd(adRequest);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sizeSelector.post(new Runnable() {
            @Override
            public void run() {
                sizeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                adRequest = new AdRequest.Builder()
                                        .appId(370877)
                                        .adUnitId(150441)
                                        .adSize(AdSize._320x50)
                                        .build();
                                break;
                            case 1:
                                adRequest = new AdRequest.Builder()
                                        .isTest(true)
                                        .adSize(AdSize._320x100)
                                        .build();
                                break;
                            case 2:
                                adRequest = new AdRequest.Builder()
                                        .isTest(true)
                                        .adSize(AdSize._300x250)
                                        .build();
                                break;
                            case 3:
                                adRequest = new AdRequest.Builder()
                                        .isTest(true)
                                        .adSize(AdSize._480x32)
                                        .build();
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        bannerAdView.unload();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}