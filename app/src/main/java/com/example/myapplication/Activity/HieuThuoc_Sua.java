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

public class HieuThuoc_Sua extends AppCompatActivity {
    EditText etTen, etMa, etDiaChi;
    Button btnSave;
    String Ma, Ten, DiaChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hieu_thuoc__sua);

        etMa = findViewById(R.id.etSuaHieuThuoc_ma);
        etTen = findViewById(R.id.etSuaHieuThuoc_ten);
        etDiaChi = findViewById(R.id.etSuaHieuThuoc_dia_chi);
        btnSave = findViewById(R.id.btnSuaHieuThuoc_luu);

        Intent intent = getIntent();
        Ma = intent.getStringExtra("MA");
        Ten = intent.getStringExtra("TEN");
        DiaChi = intent.getStringExtra("DIACHI");

        etMa.setText(Ma);
        etDiaChi.setText(DiaChi);
        etTen.setText(Ten);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HIEUTHUOC hieuthuoc = new HIEUTHUOC(Integer.parseInt(Ma), etTen.getText().toString(), etDiaChi.getText().toString());
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.updateHIEUTHUOC(hieuthuoc)){
                    Toast.makeText(HieuThuoc_Sua.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(HieuThuoc_Sua.this, "Thất bại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
