package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Activity.ChiTietHoaDonActivity;
import com.example.myapplication.Activity.HoaDon_Sua;
import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.HOADON;
import com.example.myapplication.R;

import java.util.List;

public class HoaDonAdapter extends ArrayAdapter<HOADON> {
    Context context;
    int resource;
    List<HOADON> hoadonList;

    public HoaDonAdapter(Context context, int resource, List<HOADON> hoadonList) {
        super(context, resource, hoadonList);
        this.context = context;
        this.resource = resource;
        this.hoadonList = hoadonList;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View item = layoutInflater.inflate(resource,null,true);
        TextView tvSoHD = item.findViewById(R.id.tvHoaDon_sohd);
        TextView tvNgay = item.findViewById(R.id.tvHoaDon_ngay);
        TextView tvMaHT = item.findViewById(R.id.tvHoaDon_maht);
        Button btnThemCT = item.findViewById(R.id.btnHoaDon_themct);
        Button btnXoa = item.findViewById(R.id.btnHoaDon_xoa);

        final HOADON hoadon = hoadonList.get(position);
        tvSoHD.setText(String.valueOf(hoadon.getSOHD()));
        tvNgay.setText(hoadon.getNGAY());
        tvMaHT.setText(String.valueOf(hoadon.getMAHT()));
        btnThemCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChiTietHoaDonActivity.class);
                intent.putExtra("SOHD", String.valueOf(hoadon.getSOHD()));
                v.getContext().startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.deleteTHUOCTAY(hoadon.getSOHD())){
                    hoadonList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HoaDon_Sua.class);
                intent.putExtra("SOHD", String.valueOf(hoadon.getSOHD()));
                intent.putExtra("NGAY", hoadon.getNGAY());
                intent.putExtra("MAHT", String.valueOf(hoadon.getMAHT()));
                v.getContext().startActivity(intent);
            }
        });
        return item;
    }
}
