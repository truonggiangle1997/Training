package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.CHITIETBANHANG;
import com.example.myapplication.Models.HIEUTHUOC;
import com.example.myapplication.Models.HOADON;
import com.example.myapplication.R;
import com.example.myapplication.Models.THUOCTAY;

public class MainActivity extends AppCompatActivity {
    Button btnThuocTay, btnHoaDon, btnHieuTHuoc;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);
        databaseHandler.addTHUOCTAY(new THUOCTAY(1,"Panadol","VND",20000));
        databaseHandler.addTHUOCTAY(new THUOCTAY(2,"Panadol Cam","VND",25000));
        databaseHandler.addTHUOCTAY(new THUOCTAY(3,"Panadol Dau","VND",26000));
        databaseHandler.addTHUOCTAY(new THUOCTAY(4,"Panadol Chanh Day","VND",24000));
        databaseHandler.addTHUOCTAY(new THUOCTAY(5,"Panadol Vai","VND",28000));

        databaseHandler.addHIEUTHUOC(new HIEUTHUOC(1,"Pharmacy 1","Duong X, Quan Y"));
        databaseHandler.addHIEUTHUOC(new HIEUTHUOC(2,"Pharmacy 2","Duong X, Quan Y"));
        databaseHandler.addHIEUTHUOC(new HIEUTHUOC(3,"Pharmacy 3","Duong X, Quan Y"));
        databaseHandler.addHIEUTHUOC(new HIEUTHUOC(4,"Pharmacy 4","Duong X, Quan Y"));
        databaseHandler.addHIEUTHUOC(new HIEUTHUOC(5,"Pharmacy 5","Duong X, Quan Y"));

        databaseHandler.addHOADON(new HOADON(1,"05/05/2019",1));
        databaseHandler.addHOADON(new HOADON(2,"06/05/2019",2));
        databaseHandler.addHOADON(new HOADON(3,"07/05/2019",3));
        databaseHandler.addHOADON(new HOADON(4,"08/05/2019",4));
        databaseHandler.addHOADON(new HOADON(10,"09/05/2019",5));

//        databaseHandler.addCHITIETBANHANG(new CHITIETBANHANG(1, 2,5));
        databaseHandler.addCHITIETBANHANG(new CHITIETBANHANG(2, 2,7));
        databaseHandler.addCHITIETBANHANG(new CHITIETBANHANG(3, 3,8));
        databaseHandler.addCHITIETBANHANG(new CHITIETBANHANG(4, 1,7));
        databaseHandler.addCHITIETBANHANG(new CHITIETBANHANG(5, 4,3));

        btnThuocTay = findViewById(R.id.btnThuocTay);
        btnHoaDon = findViewById(R.id.btnHoaDon_themct);
        btnHieuTHuoc = findViewById(R.id.btnHieuThuoc);





        btnThuocTay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThuocTayActivity.class));
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HoaDonActivity.class));
            }
        });
        btnHieuTHuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HieuThuocActivity.class));
            }
        });
    }
}
