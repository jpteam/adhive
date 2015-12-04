package com.c1x.mobile.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import c1x.lib.adhive.AdHiveApplication;
import c1x.lib.adhive.dao.AdRequest;
import c1x.lib.adhive.dao.enums.Event;
import c1x.lib.adhive.dao.interfaces.AdEventListener;
import c1x.lib.adhive.view.PopupAdView;
import c1x.lib.adhive.view.InterstitialAdView;

public class MainActivity extends AppCompatActivity implements AdEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView adunitsList = (ListView) findViewById(R.id.adunit_list);
        MainListAdapter mainListAdapter = new MainListAdapter(this);
        adunitsList.setAdapter(mainListAdapter);

        AdHiveApplication.setGender(true);

        adunitsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, BannerActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        AdRequest.Builder builder = new AdRequest.Builder()
                                .isTest(true);
                        PopupAdView popupAdView = new PopupAdView(MainActivity.this);
                        popupAdView.displayAd(builder);
                        break;
                    case 2:
                        AdRequest.Builder interstitialBuilder = new AdRequest.Builder()
                                .isTest(true);
                        InterstitialAdView interstitialAdView = new InterstitialAdView(MainActivity.this);
                        interstitialAdView.loadAd(interstitialBuilder);
                        interstitialAdView.setAdEventListener(MainActivity.this);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, NativeListAdActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, NativeCustomAdActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onAdLoaded() {

    }

    @Override
    public void onAdOpened() {

    }

    @Override
    public void onAdClosed() {

    }

    @Override
    public void onAdLoadFailed(Event event) {
        Toast.makeText(this, "No fill from ad server.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMediationOpened() {

    }

    @Override
    public void onMeidationLoaded() {

    }

    @Override
    public void onMediationClosed() {

    }

    @Override
    public void onMediationLoadFailed(Event event) {
        Toast.makeText(this, "No fill from ad server.", Toast.LENGTH_LONG).show();
    }
}
