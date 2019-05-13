package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.THUOCTAY;
import com.example.myapplication.R;

public class ThuocTay_Sua extends AppCompatActivity {
    private EditText etMa, etTen, etDonGia, etDonVi;
    private Button btnSave;
    private String Ma, Ten, DonVi, DonGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuoc_tay__sua);
        setTitle("Sửa thuốc tây");
        etMa = findViewById(R.id.etSuaThuoc_ma);
        etTen = findViewById(R.id.etSuaThuoc_ten);
        etDonVi = findViewById(R.id.etSuaThuoc_don_vi);
        etDonGia = findViewById(R.id.etSuaThuoc_don_gia);
        btnSave = findViewById(R.id.btnSuaThuoc_luu);

        Intent intent = getIntent();
        Ma = intent.getStringExtra("MA");
        Ten = intent.getStringExtra("TEN");
        DonVi = intent.getStringExtra("DONVI");
        DonGia = intent.getStringExtra("DONGIA");

        etMa.setText(Ma);
        etTen.setText(Ten);
        etDonGia.setText(DonGia);
        etDonVi.setText(DonVi);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THUOCTAY thuoctay = new THUOCTAY(Integer.parseInt(Ma), etTen.getText().toString(), etDonVi.getText().toString(), Integer.parseInt(etDonGia.getText().toString()));
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.updateTHUOCTAY(thuoctay)) {
                    Toast.makeText(ThuocTay_Sua.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ThuocTay_Sua.this, "Lưu thất bại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
}
}
