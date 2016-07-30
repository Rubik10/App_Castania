package com.rubik.apppractise3_volleysimple.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Rubik on 28/7/16.
 */
public class Product implements Serializable{

    private String titulo ;
    private String imageURL;
    private int unidades;
    private Double pvp;
    private String descripcion;
    private Timestamp fecha;


    public Product(String imageURL, String titulo, Timestamp fecha) {
        this.imageURL = imageURL;
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public Product(String titulo, String imageURL, int unidades, Double pvp, String descripcion  ) {
        this.descripcion = descripcion;
        this.imageURL = imageURL;
        this.titulo = titulo;
        this.unidades = unidades;
        this.pvp=pvp;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
