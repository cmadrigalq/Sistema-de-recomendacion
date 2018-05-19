package com.moviles1.cyn.ejemploscortos.Entidades;

import java.io.Serializable;

public class Producto implements Serializable{
    private int id;
    private String title;
    private String shortdesc;
    private double rating;
    private double price;
    private int image;
    private int cantidad = 0;
    public Producto(){

    }
    public Producto(int id, String title, String shortdesc, double rating, double price, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCantidad(){
        return this.cantidad;
    }
    public void setCantidad(int c){
        this.cantidad = c;
    }
}
