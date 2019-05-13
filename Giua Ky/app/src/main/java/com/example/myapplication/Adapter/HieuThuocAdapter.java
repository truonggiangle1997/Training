package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Activity.HieuThuoc_Sua;
import com.example.myapplication.Activity.ThuocTay_Sua;
import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.HIEUTHUOC;
import com.example.myapplication.Models.THUOCTAY;
import com.example.myapplication.R;

import java.util.List;

public class HieuThuocAdapter extends ArrayAdapter<HIEUTHUOC> {
    Context context;
    int resource;
    List<HIEUTHUOC> hieuthuocList;

    public HieuThuocAdapter(Context context, int resource, List<HIEUTHUOC> hieuthuocList) {
        super(context, resource, hieuthuocList);
        this.context = context;
        this.resource = resource;
        this.hieuthuocList = hieuthuocList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View item = layoutInflater.inflate(resource,null,true);
        TextView tvMa = item.findViewById(R.id.tvHieuThuoc_ma);
        TextView tvTen = item.findViewById(R.id.tvHieuThuoc_ten);
        TextView tvDiaChi = item.findViewById(R.id.tvHieuThuoc_dia_chi);
        Button btnXoa = item.findViewById(R.id.btnHieuThuoc_xoa);

        final HIEUTHUOC hieuthuoc = hieuthuocList.get(position);
        tvMa.setText(String.valueOf(hieuthuoc.getMAHT()));
        tvTen.setText(hieuthuoc.getTENHT());
        tvDiaChi.setText(hieuthuoc.getDIACHI());
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.deleteHIEUTHUOC(hieuthuoc.getMAHT())){
                    hieuthuocList.remove(position);
                    notifyDataSetChanged();
                }

            }
        });

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HieuThuoc_Sua.class);
                intent.putExtra("MA", String.valueOf(hieuthuoc.getMAHT()));
                intent.putExtra("TEN", hieuthuoc.getTENHT());
                intent.putExtra("DIACHI", hieuthuoc.getDIACHI());
                v.getContext().startActivity(intent);
            }
        });
        return item;
    }
}
