package com.c1x.mobile.demoapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import c1x.lib.adhive.adapters.AdBaseAdapter;
import c1x.lib.adhive.dao.AdRequest;
import c1x.lib.adhive.dao.enums.ADFormat;
import c1x.lib.adhive.dao.enums.AdSize;

/**
 * Created by arindamnath on 16/05/15.
 */
public class NativeListAdActivity extends AppCompatActivity {

    private ListView listView;
    private ListBaseAdapter listBaseAdapter;
    private AdBaseAdapter adBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Native Adapter Ads");

        listView = (ListView) findViewById(R.id.ad_list);
        listBaseAdapter = new ListBaseAdapter(this);
        new FetchAppListTask(this, new FetchAppListTask.OnAppListLoaded() {
            @Override
            public void onSuccess(List<AppPackageDAO> packages) {
                AdRequest adRequest = new AdRequest.Builder(true, AdSize._NATIVE)
                        .adFormat(ADFormat.INSTALL)
                        .build();
                listBaseAdapter.setData(packages);
                adBaseAdapter = new AdBaseAdapter(getApplicationContext(), listBaseAdapter, ADFormat.INSTALL, adRequest);
                listView.setAdapter(adBaseAdapter);
            }
        }).execute();
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
