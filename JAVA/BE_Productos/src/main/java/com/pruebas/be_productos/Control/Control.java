package com.pruebas.be_productos.Control;

import com.pruebas.be_productos.entidades.Compra;
import com.pruebas.be_productos.entidades.Historial;
import com.pruebas.be_productos.entidades.Producto;
import com.pruebas.be_productos.entidades.Usuario;
import com.pruebas.be_productos.servicios.CompraServicio;
import com.pruebas.be_productos.servicios.HistorialServicio;
import com.pruebas.be_productos.servicios.ProductoServicio;
import com.pruebas.be_productos.servicios.UsuariosServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

/**
 * Control:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 16/05/2018
 */
public class Control {
    UsuariosServicio  us = new UsuariosServicio();
    ProductoServicio  ps = new ProductoServicio();
    CompraServicio    cs = new CompraServicio();
    HistorialServicio hs = new HistorialServicio();
    public Control(){
        
    }
    public String login(String usu,String psw){
        Usuario in = new Usuario(usu,psw,false);
        Usuario res = us.login(in);
        if(res != null){
            return toJson(res);
        }else{
            return "null";
        }
    }
    public String addUser(String u,String p,String i){
        Usuario usu = new Usuario(u,p,Boolean.valueOf(i));
        return this.us.agregar(usu).toString();
    }
    public String deleteUser(String u){
        Usuario usu = new Usuario(u,"",false);
        return this.us.delete(usu).toString();
    }
    public String update(String u,String nuevaContrasena,String isAdmin){
        Usuario usu = new Usuario(u,nuevaContrasena,Boolean.valueOf(isAdmin));
        return this.us.update(usu).toString();
    }
    public String listarUsuarios(String arg){
        return toJson(this.us.listarUsuarios(arg));
    }
    
    String toJson(Usuario u){
        JSONObject js = new JSONObject();
        js.put("usuario",u.getUsuario())
          .put("contrasenna",u.getContrasenna())
          .put("isAdmin",u.getIsAdmin());
        return js.toString();
    }
    String toJson(List<Usuario>us){
        List<String>mapeo = new ArrayList<>();
        for(Usuario u : us){
            mapeo.add( toJson(u) );
        }
        JSONObject js = new JSONObject();
        js.put("Usuarios",mapeo.toArray(new String[]{}));
        return js.toString();
    }
    ////////////////////////////// ////////////////////////////// //////////////////////////////
    
    public String listaProductos(int cantidad){
        List<Producto> res = ps.listar(cantidad);
        return toJsonLP(res);
    }
    
    public String Update(String codigo,String nombre,String desc,Integer precio){
        Producto p = new Producto(
                codigo,
                nombre,
                desc,
                precio,
                100,
                0,
                null);
        return ps.update(p).toString();
    }
    public String addProducto(String codigo,String name,String desc,int precio){
        
        Producto p = new Producto(codigo,name , desc,precio, 100,0,4.3f);
        return ps.add(p).toString();
    }
    public String deleteProducto(String codigo){
        Producto p = new Producto();
        p.setCodigo(codigo);
        return ps.borrar(p).toString();
    }
    
    String toJson(Producto u) {
        JSONObject js = new JSONObject();
        js.put("codigo", u.getCodigo())
          .put("nombre", u.getNombre())
          .put("descripcion", u.getDescripcion())
          .put("precio", u.getPrecio())
          .put("cantidad", u.getCantidad())
          .put("rating", u.getRating())
          .put("imagen", u.getImagen());
        return js.toString();
    }

    String toJsonLP(List<Producto> us) {
        List<String> mapeo = new ArrayList<>();
        for (Producto p : us) {
            mapeo.add(toJson(p));
        }
        JSONObject js = new JSONObject();
        js.put("productos", mapeo.toArray(new String[]{}));
        return js.toString();
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public String addCompra(String u,String p,int cant){
        Usuario usu = new Usuario(u,"",false);
        Producto pr = new Producto(p,"","",0,0,0,null);
        Compra cp = new Compra(0,usu,pr,cant);  
        return cs.add(cp).toString();
    }
    
    //////////////////////////////////////////////7
    public String listarH(String usuario){
        List<Historial>res = hs.listar(usuario);
        return toJsonLH(res);
    }
    String toJson(Historial u) {
        JSONObject js = new JSONObject();
        js.put("usuario",u.getCompra().getUsuario().getUsuario())
          .put("producto",u.getCompra().getProducto().getDescripcion())
          .put("cantidad",u.getCompra().getCantidad())
          .put("fecha",u.getFechaStr());
        return js.toString();
    }

    String toJsonLH(List<Historial> us) {
        List<String> mapeo = new ArrayList<>();
        for (Historial p : us) {
            mapeo.add(toJson(p));
        }
        JSONObject js = new JSONObject();
        js.put("historial", mapeo.toArray(new String[]{}));
        return js.toString();
    }
    
}





