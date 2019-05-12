package com.example.myapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.HOADON;
import com.example.myapplication.R;

public class HoaDon_Them extends AppCompatActivity {
    EditText etSoHD, etNgay, etMaHT;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don__them);

        etSoHD = findViewById(R.id.etThemHoaDon_sohd);
        etNgay = findViewById(R.id.etThemHoaDon_ngay);
        etMaHT = findViewById(R.id.etThemHoaDon_maht);
        btnSave = findViewById(R.id.btnThemHoaDon_luu);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HOADON hoadon = new HOADON(Integer.parseInt(etSoHD.getText().toString()), etNgay.getText().toString(),Integer.parseInt(etMaHT.getText().toString()));
                DatabaseHandler databaseHandler = new DatabaseHandler(v.getContext());
                if(databaseHandler.addHOADON(hoadon)){
                    Toast.makeText(HoaDon_Them.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(HoaDon_Them.this, "Thất bại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
