package com.example.leftovers;

public class GetElementos {
    private String nombre_ele;
    private String agregado_ele;
    private byte[] imagen_ele;

    public GetElementos(String nombre_ele, String agregado_ele, byte[] imagen_ele) {
        this.nombre_ele = nombre_ele;
        this.agregado_ele = agregado_ele;
        this.imagen_ele = imagen_ele;
    }

    public String getNombre_ele() {
        return nombre_ele;
    }

    public String getAgregado_ele() {
        return agregado_ele;
    }

    public byte[] getImagen_ele() {
        return imagen_ele;
    }
}
