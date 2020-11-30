package com.example.leftovers;

public class GetIngredientes {
    private String nombre_ing;
    private String ing_categoria;
    private String agregado;

    public GetIngredientes(String nombre_ing, String ing_categoria, String agregado) {
        this.nombre_ing = nombre_ing;
        this.ing_categoria = ing_categoria;
        this.agregado = agregado;
    }

    public String getNombre_ing() {
        return nombre_ing;
    }

    public String getIng_categoria() {
        return ing_categoria;
    }

    public String getAgregado() {
        return agregado;
    }
}
