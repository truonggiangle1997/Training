package com.example.myapplication.Models;

public class CHITIETBANHANG {
    private int SOHD;
    private int MATHUOC;
    private int SOLUONG;

    public CHITIETBANHANG() {
    }

    public CHITIETBANHANG(int SOHD, int MATHUOC, int SOLUONG) {
        this.SOHD = SOHD;
        this.MATHUOC = MATHUOC;
        this.SOLUONG = SOLUONG;
    }

    public int getSOHD() {
        return SOHD;
    }

    public void setSOHD(int SOHD) {
        this.SOHD = SOHD;
    }

    public int getMATHUOC() {
        return MATHUOC;
    }

    public void setMATHUOC(int MATHUOC) {
        this.MATHUOC = MATHUOC;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }
}
