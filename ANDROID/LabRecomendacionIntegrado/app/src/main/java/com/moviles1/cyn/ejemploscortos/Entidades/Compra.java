package com.moviles1.cyn.ejemploscortos.Entidades;
public class Compra {
    Integer num_compra;
    Usuario usuario;
    Producto producto;
    Integer cantidad;
    public Compra(){

    }

    public Integer getNum_compra() {
        return num_compra;
    }

    public void setNum_compra(Integer num_compra) {
        this.num_compra = num_compra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
