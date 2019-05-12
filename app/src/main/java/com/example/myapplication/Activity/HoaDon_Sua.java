package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.HOADON;
import com.example.myapplication.R;

public class HoaDon_Sua extends AppCompatActivity {
    EditText etSoHD, etNgay, etMaHT;
    Button btnSave;
    String SoHD, Ngay, MaHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don__sua);

        etSoHD = findViewById(R.id.etSuaHoaDon_sohd);
        etNgay = findViewById(R.id.etSuaHoaDon_ngay);
        etMaHT = findViewById(R.id.etSuaHoaDon_maht);
        btnSave = findViewById(R.id.btnSuaHoaDon_luu);

        Intent intent = getIntent();
        SoHD = intent.getStringExtra("SOHD");
        Ngay = intent.getStringExtra("NGAY");
        MaHT = intent.getStringExtra("MAHT");

        etSoHD.setText(SoHD);
        etNgay.setText(Ngay);
        etMaHT.setText(MaHT);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HOADON hoadon = new HOADON(Integer.parseInt(SoHD), etNgay.getText().toString(), Integer.parseInt(MaHT));
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.updateHOADON(hoadon)){
                    Toast.makeText(HoaDon_Sua.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(HoaDon_Sua.this, "Thất bại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
