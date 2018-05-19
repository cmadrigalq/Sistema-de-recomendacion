package com.moviles1.cyn.ejemploscortos.Pantallas;

import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.Entidades.Usuario;
import com.moviles1.cyn.ejemploscortos.HTTPS.Proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 17/4/2018.
 */

public class Model {
    static List<Producto> productoList;
    static List<Producto> listaProductosCarrito;
    static List<Usuario> listaUsuarios;
    static Usuario usuarioActivo;
    public static int instance = 0;
    static Model instancia;
    static String ip;
    /*Para pasarlo luego a singleton*/

    public static void setIp(String ipp){
        if(ipp == null || ipp.trim().isEmpty()){
            ipp = "192.168.0.12:8081";
        }
        ip = ipp;
    }
    public static String getIp(){
        if(ip == null || ip.trim().isEmpty())
            ip = "192.168.0.12:8081";
        return ip;
    }
    public static Model getInstancia(){
        if(Model.instancia == null){
            Model.instancia = new Model();
        }
        return Model.instancia;
    }

    private Model() {

    }

    public static void init(){
        instance = 1;
        productoList = new ArrayList<>();
        listaProductosCarrito = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        usuarioActivo = new Usuario();
        initProductoList();
        initUsuarioList();

    }

    public static List<Producto> getProductoList() {
        return productoList;
    }

    public static void setProductoList(List<Producto> productoList) {
        productoList = productoList;
    }

    public static List<Producto> getListaProductosCarrito() {
        return listaProductosCarrito;
    }

    public static void setListaProductosCarrito(List<Producto> listaProductosCarrito) {
        listaProductosCarrito = listaProductosCarrito;
    }

    public static List<Usuario> getListaUsuarios() {
        Proxy proxy = Proxy.instancia();
        return listaUsuarios = proxy.listarUsuarios("");
    }

    public static void setListaUsuarios(List<Usuario> listaUsuarios) {
        Model.listaUsuarios = listaUsuarios;
    }

    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public static void setUsuarioActivo(Usuario usuarioActivo) {
        Model.usuarioActivo = usuarioActivo;
    }

    public static void initProductoList(){
        Proxy proxy = Proxy.instancia();
        productoList = proxy.listarProducto(0);
    }

    public static void initUsuarioList(){
        Proxy proxy = Proxy.instancia();
        listaUsuarios = proxy.listarUsuarios("");
    }

}
