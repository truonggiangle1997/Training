package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.Adapter.HieuThuocAdapter;
import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.HIEUTHUOC;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HieuThuocActivity extends AppCompatActivity {
    ListView listView;
    List<HIEUTHUOC> hieuthuocList;
    DatabaseHandler databaseHandler;
    FloatingActionButton fabThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hieu_thuoc);
        setTitle("Quản lý hiệu thuốc");

        listView = findViewById(R.id.lvHieuThuoc);
        fabThem = findViewById(R.id.fabHieuThuoc_them);
        databaseHandler = new DatabaseHandler(this);

        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HieuThuocActivity.this,HieuThuoc_Them.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHieuThuocList();
    }

    private void loadHieuThuocList(){
        hieuthuocList = new ArrayList<>();
//        hieuthuocList.add(new HIEUTHUOC(1,"heu thuoc 1", "cong hoa"));
        hieuthuocList = databaseHandler.getDSHIEUTHUOC();

        HieuThuocAdapter hieuThuocAdapter = new HieuThuocAdapter(this, R.layout.hieu_thuoc_item, hieuthuocList);
        listView.setAdapter(hieuThuocAdapter);
        hieuThuocAdapter.notifyDataSetChanged();
    }
}
