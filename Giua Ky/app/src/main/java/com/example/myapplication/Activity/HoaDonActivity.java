package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.Adapter.HoaDonAdapter;
import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.HOADON;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {
    ListView listView;
    List<HOADON> hoadonList;
    DatabaseHandler databaseHandler;
    FloatingActionButton fabThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        setTitle("Quản lý hóa đơn");

        listView = findViewById(R.id.lvHoaDon);
        fabThem = findViewById(R.id.fabHoaDon_them);
        databaseHandler = new DatabaseHandler(this);
        loadHoaDonList();

        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoaDonActivity.this, HoaDon_Them.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHoaDonList();
    }

    private void loadHoaDonList(){
        hoadonList = new ArrayList<>();
        hoadonList = databaseHandler.getDSHOADON();
        HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(getApplicationContext(),R.layout.hoa_don_item,hoadonList);
        listView.setAdapter(hoaDonAdapter);
        hoaDonAdapter.notifyDataSetChanged();
    }
}
