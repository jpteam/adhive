package com.c1x.mobile.demoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 18/05/15.
 */
public class ListBaseAdapter extends BaseAdapter {

    private List<AppPackageDAO> data = new ArrayList<AppPackageDAO>();
    private LayoutInflater mInflater;

    public ListBaseAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<AppPackageDAO> listData) {
        data = listData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_list, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_image);
            holder.mHeaderText = (TextView) convertView.findViewById(R.id.list_header);
            holder.mSubHeaderText = (TextView) convertView.findViewById(R.id.list_sub_header);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.imageView.setImageDrawable(data.get(position).getIcon());
        holder.mHeaderText.setText(data.get(position).getAppName());
        holder.mSubHeaderText.setText(data.get(position).getPackageName());
        return convertView;
    }

    static class ViewHolder {
        TextView mHeaderText, mSubHeaderText;
        ImageView imageView;
    }
}
