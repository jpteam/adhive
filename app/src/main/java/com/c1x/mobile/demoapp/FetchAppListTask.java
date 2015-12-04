package com.c1x.mobile.demoapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 13/07/15.
 */
public class FetchAppListTask extends AsyncTask<Void, Void, Integer> {

    public interface OnAppListLoaded {
        void onSuccess(List<AppPackageDAO> packages);
    }

    private List<AppPackageDAO> list = new ArrayList<>();
    private OnAppListLoaded onAppListLoaded;
    private PackageManager pm;
    private ProgressDialog progressDialog;

    public FetchAppListTask(Context context, OnAppListLoaded onAppListLoaded) {
        pm = context.getPackageManager();
        this.onAppListLoaded = onAppListLoaded;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                list.add(new AppPackageDAO(
                        packageInfo.applicationInfo.loadLabel(pm).toString(),
                        packageInfo.applicationInfo.loadIcon(pm),
                        packageInfo.packageName));
            }
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        progressDialog.dismiss();
        onAppListLoaded.onSuccess(list);
    }
}
