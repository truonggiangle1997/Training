package com.example.myapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.THUOCTAY;
import com.example.myapplication.R;

public class ThuocTay_Them extends AppCompatActivity {
    private EditText etMa, etTen, etDonGia, etDonVi;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuoc_tay__them);

        etMa = findViewById(R.id.etThemThuoc_ma);
        etTen = findViewById(R.id.etThemThuoc_ten);
        etDonVi = findViewById(R.id.etThemThuoc_don_vi);
        etDonGia = findViewById(R.id.etThemThuoc_don_gia);
        btnSave = findViewById(R.id.btnThemThuoc_luu);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THUOCTAY thuoctay = new THUOCTAY(Integer.parseInt(etMa.getText().toString()), etTen.getText().toString(), etDonVi.getText().toString(), Integer.parseInt(etDonGia.getText().toString()));
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.addTHUOCTAY(thuoctay)){
                    Toast.makeText(ThuocTay_Them.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ThuocTay_Them.this, "Thất bại , vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
