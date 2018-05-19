package com.moviles1.cyn.ejemploscortos.Entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable{
    private String userName;
    private String password;
    private ArrayList<Producto> listaProductos;
    private int tipo; // 1. Administrador  2. Cliente

    public Usuario() {
    }

    public Usuario(String userName, String password, ArrayList<Producto> listaProductos, int tipo) {
        this.userName = userName;
        this.password = password;
        this.listaProductos = listaProductos;
        this.tipo = tipo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
