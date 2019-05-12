package com.example.myapplication.Models;

public class HOADON {
    private int SOHD;
    private String NGAY;
    private int MAHT;

    public HOADON() {
    }

    public HOADON(int SOHD, String NGAY, int MAHT) {
        this.SOHD = SOHD;
        this.NGAY = NGAY;
        this.MAHT = MAHT;
    }

    public int getSOHD() {
        return SOHD;
    }

    public void setSOHD(int SOHD) {
        this.SOHD = SOHD;
    }

    public String getNGAY() {
        return NGAY;
    }

    public void setNGAY(String NGAY) {
        this.NGAY = NGAY;
    }

    public int getMAHT() {
        return MAHT;
    }

    public void setMAHT(int MAHT) {
        this.MAHT = MAHT;
    }
}
