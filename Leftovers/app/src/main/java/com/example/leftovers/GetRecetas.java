package com.example.leftovers;

public class GetRecetas {
    private String r_nom;
    private byte[] r_ima;
    private String tiempo;
    private String i1;
    private String i2;
    private String i3;
    private String i4;
    private String i5;
    private String proc;
    private String e1;
    private String e2;

    public GetRecetas(String r_nom, byte[] r_ima, String tiempo, String i1, String i2, String i3, String i4, String i5, String proc, String e1, String e2) {
        this.r_nom = r_nom;
        this.r_ima = r_ima;
        this.tiempo = tiempo;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.i5 = i5;
        this.proc = proc;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String getR_nom() {
        return r_nom;
    }

    public byte[] getR_ima() {
        return r_ima;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getI1() {
        return i1;
    }

    public String getI2() {
        return i2;
    }

    public String getI3() {
        return i3;
    }

    public String getI4() {
        return i4;
    }

    public String getI5() {
        return i5;
    }

    public String getProc() {
        return proc;
    }

    public String getE1() {
        return e1;
    }

    public String getE2() {
        return e2;
    }
}
