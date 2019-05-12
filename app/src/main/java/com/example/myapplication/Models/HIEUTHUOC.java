package com.example.myapplication.Models;

public class HIEUTHUOC {
    private int MAHT;
    private String TENHT;
    private String DIACHI;

    public HIEUTHUOC() {
    }

    public HIEUTHUOC(int MAHT, String TENHT, String DIACHI) {
        this.MAHT = MAHT;
        this.TENHT = TENHT;
        this.DIACHI = DIACHI;
    }

    public int getMAHT() {
        return MAHT;
    }

    public void setMAHT(int MAHT) {
        this.MAHT = MAHT;
    }

    public String getTENHT() {
        return TENHT;
    }

    public void setTENHT(String TENHT) {
        this.TENHT = TENHT;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }
}
