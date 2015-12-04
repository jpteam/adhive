package com.c1x.mobile.demoapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import c1x.lib.adhive.dao.AdRequest;
import c1x.lib.adhive.dao.enums.ADFormat;
import c1x.lib.adhive.dao.enums.AdSize;
import c1x.lib.adhive.view.NativeContentAdView;
import c1x.lib.adhive.view.NativeInstallAdView;

/**
 * Created by arindamnath on 21/06/15.
 */
public class NativeCustomAdActivity extends AppCompatActivity {

    private Button mRefresh;
    private RadioButton mRequestAppInstallAds, mRequestContentAds;
    private NativeContentAdView nativeContentAdView;
    private NativeInstallAdView nativeInstallAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Native Ads");

        mRefresh = (Button) findViewById(R.id.btn_refresh);
        mRequestAppInstallAds = (RadioButton) findViewById(R.id.cb_appinstall);
        mRequestContentAds = (RadioButton) findViewById(R.id.cb_content);
        nativeInstallAdView = (NativeInstallAdView) findViewById(R.id.adhive_native_install);
        nativeContentAdView = (NativeContentAdView) findViewById(R.id.adhive_native_content);

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshAd(mRequestAppInstallAds.isChecked(),
                        mRequestContentAds.isChecked(), false);
            }
        });


    }

    private void refreshAd(boolean requestAppInstallAds, boolean requestContentAds,
                           boolean requestCustomTemplateAds) {
        if (!requestAppInstallAds && !requestContentAds && !requestCustomTemplateAds) {
            Toast.makeText(this, "At least one ad format must be checked to request an ad.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(requestAppInstallAds) {
            nativeInstallAdView.setVisibility(View.VISIBLE);
            nativeContentAdView.setVisibility(View.GONE);
            AdRequest adRequest = new AdRequest.Builder(true, AdSize._NATIVE)
                    .adFormat(ADFormat.INSTALL)
                    .build();
            nativeInstallAdView.loadAd(adRequest);
        }

        if (requestContentAds) {
            nativeInstallAdView.setVisibility(View.GONE);
            nativeContentAdView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder(true, AdSize._NATIVE)
                    .adFormat(ADFormat.CONTENT)
                    .build();
            nativeContentAdView.loadAd(adRequest);
        }
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