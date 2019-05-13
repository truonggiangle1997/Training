package com.example.baimau;

import android.app.Activity;
import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ModelAdapter extends ArrayAdapter<Model> {
    Context context;
    int layoutResourceId;
    ArrayList<Model> data = null;

    public ModelAdapter(Context context, int layoutResourceId, ArrayList<Model> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    static class ModelHolder{
        ImageView imgIcon;
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ModelHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ModelHolder();
            holder.imgIcon = row.findViewById(R.id.imgIcon);
            holder.txtTitle = row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else{
            holder = (ModelHolder) row.getTag();
        }
        Model item = data.get(position);
        holder.txtTitle.setText(item.title);
        holder.imgIcon.setImageResource(item.icon);

        return row;
    }
}

