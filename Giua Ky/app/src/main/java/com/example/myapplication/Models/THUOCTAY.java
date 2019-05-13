package com.example.myapplication.Models;

public class THUOCTAY {
    private int MATHUOC;
    private String TENTHUOC;
    private  String DONVITINH;
    private int DONGIA;

    public THUOCTAY() {

    }

    public THUOCTAY(int MATHUOC, String TENTHUOC, String DONVITINH, int DONGIA) {
        this.MATHUOC = MATHUOC;
        this.TENTHUOC = TENTHUOC;
        this.DONVITINH = DONVITINH;
        this.DONGIA = DONGIA;
    }

    public int getMATHUOC() {
        return MATHUOC;
    }

    public void setMATHUOC(int MATHUOC) {
        this.MATHUOC = MATHUOC;
    }

    public String getTENTHUOC() {
        return TENTHUOC;
    }

    public void setTENTHUOC(String TENTHUOC) {
        this.TENTHUOC = TENTHUOC;
    }

    public String getDONVITINH() {
        return DONVITINH;
    }

    public void setDONVITINH(String DONVITINH) {
        this.DONVITINH = DONVITINH;
    }

    public int getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(int DONGIA) {
        this.DONGIA = DONGIA;
    }
}

