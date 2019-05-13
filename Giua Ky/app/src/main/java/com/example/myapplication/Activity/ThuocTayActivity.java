package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.THUOCTAY;
import com.example.myapplication.R;
import com.example.myapplication.Adapter.ThuocTayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThuocTayActivity extends AppCompatActivity {
    ListView listView;
    List<THUOCTAY> thuoctayList;
    DatabaseHandler databaseHandler;
    FloatingActionButton fabThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuoc_tay);
        setTitle("Quản lý thuốc tây");

        listView = findViewById(R.id.lvThuocTay);
        fabThem = findViewById(R.id.fabThuocTay_them);
        databaseHandler = new DatabaseHandler(this);

        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThuocTayActivity.this,ThuocTay_Them.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadThuocTayList();
    }

    private void loadThuocTayList(){
        thuoctayList = new ArrayList<>();
        thuoctayList = databaseHandler.getDSTHUOCTAY();

        ThuocTayAdapter thuocTayAdapter = new ThuocTayAdapter(this, R.layout.thuoctay_list_item, thuoctayList);
        listView.setAdapter(thuocTayAdapter);
        thuocTayAdapter.notifyDataSetChanged();
    }
}
