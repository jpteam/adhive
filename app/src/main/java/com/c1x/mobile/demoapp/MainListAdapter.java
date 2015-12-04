package com.c1x.mobile.demoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by arindamnath on 03/11/15.
 */
public class MainListAdapter extends BaseAdapter {

    private String[] data;
    private int[] image;
    private LayoutInflater mInflater;

    public MainListAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        data = context.getResources().getStringArray(R.array.type_list);
        image = new int[] {R.mipmap.ic_banner,
                R.mipmap.ic_popup,
                R.mipmap.ic_interstitial,
                R.mipmap.ic_list,
                R.mipmap.ic_inline};
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
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
            convertView = mInflater.inflate(R.layout.adapter_main, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.adapter_main_image);
            holder.mHeaderText = (TextView) convertView.findViewById(R.id.adapter_main_text);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.imageView.setImageResource(image[position]);
        holder.mHeaderText.setText(data[position]);
        return convertView;
    }

    static class ViewHolder {
        TextView mHeaderText;
        ImageView imageView;
    }
}
