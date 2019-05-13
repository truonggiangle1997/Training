package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Activity.ThuocTay_Sua;
import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.THUOCTAY;
import com.example.myapplication.R;

import java.util.List;

public class ThuocTayAdapter extends ArrayAdapter<THUOCTAY> {
    Context context;
    int resource;
    List<THUOCTAY> thuoctayList;

    public ThuocTayAdapter(Context context, int resource, List<THUOCTAY> thuoctayList) {
        super(context, resource, thuoctayList);
        this.context = context;
        this.resource = resource;
        this.thuoctayList = thuoctayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View item = layoutInflater.inflate(resource,null,true);
        TextView tvMa = item.findViewById(R.id.tvThuocTay_ma);
        TextView tvTen = item.findViewById(R.id.tvThuocTay_ten);
        TextView tvDonVi = item.findViewById(R.id.tvThuocTay_don_vi);
        TextView tvDonGia = item.findViewById(R.id.tvThuocTay_don_gia);
        Button btnXoa = item.findViewById(R.id.btnThuocTay_xoa);

        final THUOCTAY thuoctay = thuoctayList.get(position);
        tvMa.setText(String.valueOf(thuoctay.getMATHUOC()));
        tvTen.setText(thuoctay.getTENTHUOC());
        tvDonVi.setText(thuoctay.getDONVITINH());
        tvDonGia.setText(String.valueOf(thuoctay.getDONGIA()));
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.deleteTHUOCTAY(thuoctay.getMATHUOC())){
                    thuoctayList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThuocTay_Sua.class);
                intent.putExtra("MA", String.valueOf(thuoctay.getMATHUOC()));
                intent.putExtra("TEN", thuoctay.getTENTHUOC());
                intent.putExtra("DONVI", thuoctay.getDONVITINH());
                intent.putExtra("DONGIA", String.valueOf(thuoctay.getDONGIA()));
                v.getContext().startActivity(intent);
            }
        });
        return item;
    }
}
