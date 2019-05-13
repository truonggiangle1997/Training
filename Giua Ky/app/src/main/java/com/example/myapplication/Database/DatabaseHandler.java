package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Models.CHITIETBANHANG;
import com.example.myapplication.Models.HIEUTHUOC;
import com.example.myapplication.Models.HOADON;
import com.example.myapplication.Models.THUOCTAY;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "QLTHUOCTAY";
    public static final int DB_VERSION = 1;
    public static final String TABLE_THUOCTAY = "THUOCTAY";
    public static final String TABLE_HIEUTHUOC= "HIEUTHUOC";
    public static final String TABLE_HOADON= "HOADON";
    public static final String TABLE_CHITIETBANHANG = "CHITIETBANHANG";

    // Cac thuoc tinh cua bang THUOCTAY
    private static final String KEY_MATHUOC = "MATHUOC";
    private static final String KEY_TENTHUOC = "TENTHUOC";
    private static final String KEY_DONVITINH = "DONVITINH";
    private static final String KEY_DONGIA = "DONGIA";

    // Cac thuoc tinh cua bang HIEUTHUOC
    private static final String KEY_MAHT = "MAHT";
    private static final String KEY_TENHT = "TENHT";
    private static final String KEY_DIACHI = "DIACHI";

    // Cac thuoc tinh cua bang HOADON
    private static final String KEY_SOHD = "SOHD";
    private static final String KEY_NGAY = "NGAY";
    private static final String KEY_MAHT_ID = "MAHT";

    // Cac thuoc tinh cua bang CHITIETBANHANG
    private static final String KEY_SOHD_ID = "SOHD";
    private static final String KEY_MATHUOC_ID = "MATHUOC";
    private static final String KEY_SOLUONG = "SOLUONG";
    
    // Tao cac bang
    private static final String CREATE_TABLE_THUOCTAY = "CREATE TABLE " + TABLE_THUOCTAY
            + "(" + KEY_MATHUOC + " INTEGER PRIMARY KEY," + KEY_TENTHUOC + " TEXT,"
            + KEY_DONVITINH + " TEXT," + KEY_DONGIA + " INTEGER" + ")";

    private static final String CREATE_TABLE_HIEUTHUOC = "CREATE TABLE " + TABLE_HIEUTHUOC
            + "(" + KEY_MAHT + " INTEGER PRIMARY KEY," + KEY_TENHT + " TEXT,"
            + KEY_DIACHI + " TEXT" + ")";

    private static final String CREATE_TABLE_HOADON = "CREATE TABLE "
            + TABLE_HOADON + "(" + KEY_SOHD + " INTEGER PRIMARY KEY,"
            + KEY_NGAY + " DATETIME," + KEY_MAHT_ID + " INTEGER NOT NULL "
            +"REFERENCES TABLE_HIEUTHUOC(MAHT) ON DELETE CASCADE"
            + ")";

    private static final String CREATE_TABLE_CHITIETBANHANG = "CREATE TABLE " + TABLE_CHITIETBANHANG
            + "(" + KEY_SOHD_ID + " INTEGER REFERENCES TABLE_HOADON(SOHD),"
            + KEY_MATHUOC_ID + " INTEGER REFERENCES TABLE_THUOCTAY(MATHUOC),"
            + KEY_SOLUONG + " INTEGER,"
            + "PRIMARY KEY("+KEY_SOHD_ID+","+KEY_MATHUOC_ID+")"
            + ")";

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_THUOCTAY);
        db.execSQL(CREATE_TABLE_HIEUTHUOC);
        db.execSQL(CREATE_TABLE_HOADON);
        db.execSQL(CREATE_TABLE_CHITIETBANHANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_THUOCTAY_table = String.format("DROP TABLE IF EXISTS %s", TABLE_THUOCTAY);
        String drop_HIEUTHUOC_table = String.format("DROP TABLE IF EXISTS %s", TABLE_HIEUTHUOC);
        String drop_HOADON_table = String.format("DROP TABLE IF EXISTS %s", TABLE_HOADON);
        String drop_CHITIETBANHANG_table = String.format("DROP TABLE IF EXISTS %s", TABLE_CHITIETBANHANG);
        db.execSQL(drop_THUOCTAY_table);
        db.execSQL(drop_HIEUTHUOC_table);
        db.execSQL(drop_HOADON_table);
        db.execSQL(drop_CHITIETBANHANG_table);
        onCreate(db);
    }

    //===================================================================
    //Method cua table THUOCTAY
    public boolean addTHUOCTAY(THUOCTAY thuoctay){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MATHUOC, thuoctay.getMATHUOC());
        values.put(KEY_TENTHUOC, thuoctay.getTENTHUOC());
        values.put(KEY_DONVITINH, thuoctay.getDONVITINH());
        values.put(KEY_DONGIA,thuoctay.getDONGIA());
        try {
            db.insert(TABLE_THUOCTAY,null,values);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public THUOCTAY getTHUOCTAY(int MATHUOC){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_THUOCTAY, null, KEY_MATHUOC + " = ?", new String[] { String.valueOf(MATHUOC) },null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        THUOCTAY thuoctay = new THUOCTAY(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        return thuoctay;
    }

    public List<THUOCTAY> getDSTHUOCTAY(){
        List<THUOCTAY> thuoctayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_THUOCTAY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            THUOCTAY thuoctay = new THUOCTAY(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            thuoctayList.add(thuoctay);
            cursor.moveToNext();
        }
        return thuoctayList;
    }

    public boolean updateTHUOCTAY(THUOCTAY thuoctay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TENTHUOC, thuoctay.getTENTHUOC());
        values.put(KEY_DONVITINH, thuoctay.getDONVITINH());
        values.put(KEY_DONGIA, thuoctay.getDONGIA());
        try {
            db.update(TABLE_THUOCTAY, values, KEY_MATHUOC + " = ?", new String[]{String.valueOf(thuoctay.getMATHUOC())});
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteTHUOCTAY(int MATHUOC){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(TABLE_THUOCTAY, KEY_MATHUOC + " = ?", new String[] { String.valueOf(MATHUOC)});
            db.close();
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //===================================================================

    //===================================================================
    //Method cua table HOADON
    public boolean addHOADON(HOADON hoadon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SOHD, hoadon.getSOHD());
        values.put(KEY_NGAY, hoadon.getNGAY());
        values.put(KEY_MAHT_ID, hoadon.getMAHT());
        try {
            db.insert(TABLE_HOADON,null,values);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public HOADON getHOADON(int SOHD){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HOADON, null, KEY_SOHD + " = ?", new String[] { String.valueOf(SOHD) },null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        HOADON hoadon = new HOADON(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        return hoadon;
    }

    public List<HOADON> getDSHOADON(){
        List<HOADON> hoadonList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_HOADON;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            HOADON hoadon = new HOADON(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            hoadonList.add(hoadon);
            cursor.moveToNext();
        }
        return hoadonList;
    }

    public boolean updateHOADON(HOADON hoadon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NGAY, hoadon.getNGAY());
        values.put(KEY_MAHT_ID, hoadon.getMAHT());
        try {
            db.update(TABLE_HOADON, values, KEY_SOHD + " = ?", new String[]{String.valueOf(hoadon.getSOHD())});
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteHOADON(int SOHD){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(TABLE_HOADON, KEY_SOHD + " = ?", new String[] { String.valueOf(SOHD)});
            db.close();
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //===================================================
    //Method cua bang HIEUTHUOC
    public boolean addHIEUTHUOC(HIEUTHUOC hieuthuoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MAHT, hieuthuoc.getMAHT());
        values.put(KEY_TENHT, hieuthuoc.getTENHT());
        values.put(KEY_DIACHI, hieuthuoc.getDIACHI());
        try {
            db.insert(TABLE_HIEUTHUOC,null,values);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public HIEUTHUOC getHIEUTHUOC(int MAHT){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HIEUTHUOC, null, KEY_MAHT + " = ?", new String[] { String.valueOf(MAHT) },null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        HIEUTHUOC hieuthuoc = new HIEUTHUOC(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return hieuthuoc;
    }

    public List<HIEUTHUOC> getDSHIEUTHUOC(){
        List<HIEUTHUOC> hieuthuocList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_HIEUTHUOC;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            HIEUTHUOC hieuthuoc = new HIEUTHUOC(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            hieuthuocList.add(hieuthuoc);
            cursor.moveToNext();
        }
        return hieuthuocList;
    }

    public boolean updateHIEUTHUOC(HIEUTHUOC hieuthuoc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TENHT, hieuthuoc.getTENHT());
        values.put(KEY_DIACHI, hieuthuoc.getDIACHI());
        try {
            db.update(TABLE_HIEUTHUOC, values, KEY_MAHT + " = ?", new String[]{String.valueOf(hieuthuoc.getMAHT())});
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteHIEUTHUOC(int MAHT){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(TABLE_HIEUTHUOC, KEY_MAHT + " = ?", new String[] { String.valueOf(MAHT)});
            db.close();
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //=======================================================================
    // Method cua bang CHITIETBANHANG

    public boolean addCHITIETBANHANG(CHITIETBANHANG chitietbanhang){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SOHD_ID, chitietbanhang.getSOHD());
        values.put(KEY_MATHUOC_ID, chitietbanhang.getMATHUOC());
        values.put(KEY_SOLUONG, chitietbanhang.getSOLUONG());

        try {
            db.insert(TABLE_CHITIETBANHANG,null,values);
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public CHITIETBANHANG getCHITIETBANHANG(int SOHD){
        SQLiteDatabase db = this.getReadableDatabase();
        CHITIETBANHANG chitietbanhang;

        Cursor cursor = db.query(TABLE_CHITIETBANHANG, null, KEY_SOHD_ID + " = ?"  , new String[] { String.valueOf(SOHD)} ,null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        chitietbanhang = new CHITIETBANHANG(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
        return chitietbanhang;
    }

    public List<CHITIETBANHANG> getDSCHITIETBANHANG(){
        List<CHITIETBANHANG> chitietbanhangList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CHITIETBANHANG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            CHITIETBANHANG chitietbanhang = new CHITIETBANHANG(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            chitietbanhangList.add(chitietbanhang);
            cursor.moveToNext();
        }
        return chitietbanhangList;
    }

    public boolean updateCHITIETBANHANG(CHITIETBANHANG chitietbanhang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SOLUONG, chitietbanhang.getSOLUONG());
        try {
            db.update(TABLE_CHITIETBANHANG, values, KEY_SOHD_ID + " = ? AND "+ KEY_MATHUOC_ID + " = ?", new String[]{String.valueOf(chitietbanhang.getSOHD()), String.valueOf(chitietbanhang.getMATHUOC())});
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCHITIETHOADON(int SOHD, int MATHUOC){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(TABLE_CHITIETBANHANG, KEY_SOHD_ID + " = ? AND "+ KEY_MATHUOC_ID + " = ?", new String[]{String.valueOf(SOHD), String.valueOf(MATHUOC)});
            db.close();
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
