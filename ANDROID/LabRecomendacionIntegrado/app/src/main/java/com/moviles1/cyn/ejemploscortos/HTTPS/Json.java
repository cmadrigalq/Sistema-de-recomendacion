package com.moviles1.cyn.ejemploscortos.HTTPS;

import com.moviles1.cyn.ejemploscortos.Entidades.Compra;
import com.moviles1.cyn.ejemploscortos.Entidades.Historial;
import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.Entidades.Usuario;
import com.moviles1.cyn.ejemploscortos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Json {
    Integer[]imgs = new Integer[]{
            0, R.drawable.macbook,  R.drawable.dellinspiron, R.drawable.surface,
            R.drawable.hp, R.drawable.toshiba,R.drawable.samsung,R.drawable.gateway
    };
    public Json(){

    }

    public Producto toProducto(String json){
        try {
            JSONObject jo = new JSONObject(json);
            String codigo = jo.getString("codigo");
            String nombre = jo.getString("nombre");
            String descripcion = jo.getString("descripcion");
            Integer precio = jo.getInt("precio");
            Integer cantidad = jo.getInt("cantidad");
            Double rating = jo.getDouble("rating");
            Integer imagen = jo.getInt("imagen");
            Producto p = new Producto();
            p.setId(Integer.valueOf(codigo));
            p.setTitle(nombre);
            p.setShortdesc(descripcion);
            p.setPrice(precio+0.0);
            p.setCantidad(cantidad);
            p.setRating(rating);
            if(imagen > imgs.length || imagen == 0){
                p.setImage(imgs[1]);
            }else p.setImage(imgs[imagen]);
            return p;
        }catch(Exception e){
            return null;
        }
    }


    public Usuario toUsuario(String json){
        try {
            JSONObject jo = new JSONObject(json);
            String usuario = jo.getString("usuario");
            String pswrd   = jo.getString("contrasenna");
            Boolean isA    = jo.getBoolean("isAdmin");
            Usuario u = new Usuario();
            u.setUserName(usuario);
            u.setPassword(pswrd);
            u.setTipo( isA ? 1 : 2);
            u.setListaProductos(new ArrayList<Producto>());
            return u;
        }catch(Exception e){
            return null;
        }
    }
    public List<Usuario> toUsuatios(String json){
        List<Usuario> res = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray arr = jo.getJSONArray("Usuarios");
            for(int i = 0; i<arr.length();i++){
                String jsn = (String)arr.get(i);
                Usuario nuev = toUsuario(jsn);
                if(nuev != null)
                    res.add(nuev);
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
        return res;
    }
    public List<Producto> toProductoss(String json){
        List<Producto> res = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray arr = jo.getJSONArray("productos");
            for(int i = 0; i<arr.length();i++){
                String jsn = (String)arr.get(i);
                Producto nuev = toProducto(jsn);
                if(nuev != null)
                    res.add(nuev);
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
        return res;
    }
    public List<Historial> toHistoriales(String json){
        List<Historial> res = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray arr = jo.getJSONArray("historial");
            for(int i = 0; i<arr.length();i++){
                String jsn = (String)arr.get(i);
                Historial nuev = toHistorial(jsn);
                if(nuev != null)
                    res.add(nuev);
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
        return res;
    }

    Historial toHistorial(String json){
        try {
            JSONObject jo = new JSONObject(json);
            String usuario = jo.getString("usuario");
            Usuario u = new Usuario();
            u.setUserName(usuario);

            String prodcuto = jo.getString("producto");
            Producto p = new Producto();
            p.setShortdesc(prodcuto);

            Integer cantidad = jo.getInt("cantidad");
            Compra c = new Compra();
            c.setProducto(p);
            c.setUsuario(u);
            c.setCantidad(cantidad);

            String fechaStr = jo.getString("fecha");
            String []vasl = fechaStr.split("-");
            Date fecha  = new Date(
                    Integer.parseInt(vasl[0]),
                    Integer.parseInt(vasl[1]),
                    Integer.parseInt(vasl[2]),
                    Integer.parseInt(vasl[3]),
                    Integer.parseInt(vasl[4]),
                    Integer.parseInt(vasl[5])

            );
            Historial h = new Historial();
            h.setCompra(c);
            h.setFecha(fecha);
            return h;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
