package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.HIEUTHUOC;
import com.example.myapplication.R;

public class HieuThuoc_Them extends AppCompatActivity {
    EditText etTen, etMa, etDiaChi;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hieu_thuoc__them);

        etMa = findViewById(R.id.etThemHieuThuoc_ma);
        etTen = findViewById(R.id.etThemHieuThuoc_ten);
        etDiaChi = findViewById(R.id.etThemHieuThuoc_dia_chi);
        btnSave = findViewById(R.id.btnThemHieuThuoc_luu);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HIEUTHUOC hieuthuoc = new HIEUTHUOC(Integer.parseInt(etMa.getText().toString()), etTen.getText().toString(), etDiaChi.getText().toString());
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.addHIEUTHUOC(hieuthuoc)){
                    Toast.makeText(HieuThuoc_Them.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(HieuThuoc_Them.this, "Thất bại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
