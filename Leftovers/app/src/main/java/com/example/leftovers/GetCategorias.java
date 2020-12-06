package com.example.leftovers;

public class GetCategorias {
    private String nombre_categoria;
    private byte[] imagen_categoria;
    private int numero_ing;
    private int numero_ing_seleccionados;

    public GetCategorias(String nombre_categoria, byte[] imagen_categoria, int numero_ing, int numero_ing_seleccionados) {
        this.nombre_categoria = nombre_categoria;
        this.imagen_categoria = imagen_categoria;
        this.numero_ing = numero_ing;
        this.numero_ing_seleccionados = numero_ing_seleccionados;
    }


    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public byte[] getImagen_categoria() {
        return imagen_categoria;
    }

    public int getNumero_ing() {
        return numero_ing;
    }

    public int getNumero_ing_seleccionados() {
        return numero_ing_seleccionados;
    }
}
