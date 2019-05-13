package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHandler;
import com.example.myapplication.Models.CHITIETBANHANG;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    private EditText etTenHD, etSoLuong, etTenThuoc;
    private TextView tvTenHd, tvSoLuong, tvTenThuoc;
    private Button btnSave;
    private String soHD, maThuoc;
    private DatabaseHandler databaseHandler;
    private CHITIETBANHANG chitietbanhang;
    private List<CHITIETBANHANG> chitietbanhangList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        setTitle("Chi tiết hóa đơn");
        etTenHD = findViewById(R.id.etCTHoaDon_tenhd);
        etSoLuong = findViewById(R.id.etCTHoaDon_soluong);
        etTenThuoc =  findViewById(R.id.etCTHoaDon_tenthuoc);
        btnSave = findViewById(R.id.btnCTHoaDon_luu);

        Intent intent = getIntent();
        soHD = intent.getStringExtra("SOHD");
        etTenHD.setText(soHD);
        databaseHandler = new DatabaseHandler(this);
        chitietbanhang = new CHITIETBANHANG();
        if(checkCTBanHang(Integer.parseInt(soHD))){
            chitietbanhang = databaseHandler.getCHITIETBANHANG(Integer.parseInt(soHD));
            SuaCT();
        }else {
            ThemCT();
        }


    }
    private void ThemCT(){

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseHandler.addCHITIETBANHANG(new CHITIETBANHANG(Integer.parseInt(etTenHD.getText().toString()),Integer.parseInt(etTenThuoc.getText().toString()),Integer.parseInt(etSoLuong.getText().toString())))){
                    Toast.makeText(ChiTietHoaDonActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ChiTietHoaDonActivity.this, "Thất bại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SuaCT(){
        etTenThuoc.setText(String.valueOf(chitietbanhang.getMATHUOC()));
        etSoLuong.setText(String.valueOf(chitietbanhang.getSOLUONG()));
        etTenThuoc.setKeyListener(null);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseHandler.updateCHITIETBANHANG(new CHITIETBANHANG(Integer.parseInt(etTenHD.getText().toString()),Integer.parseInt(etTenThuoc.getText().toString()),Integer.parseInt(etSoLuong.getText().toString())))){
                    Toast.makeText(ChiTietHoaDonActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ChiTietHoaDonActivity.this, "Thất bại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkCTBanHang(int soHD){
        chitietbanhangList = new ArrayList<>();
        chitietbanhangList = databaseHandler.getDSCHITIETBANHANG();
        for(int i = 0; i < chitietbanhangList.size(); i++){
            if(chitietbanhangList.get(i).getSOHD() == soHD){
                return true;
            }
        }
        return false;
    }
}
