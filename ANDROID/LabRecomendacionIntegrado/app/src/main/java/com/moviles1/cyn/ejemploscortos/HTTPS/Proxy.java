package com.moviles1.cyn.ejemploscortos.HTTPS;

import com.moviles1.cyn.ejemploscortos.Entidades.Historial;
import com.moviles1.cyn.ejemploscortos.Entidades.Producto;
import com.moviles1.cyn.ejemploscortos.Entidades.Usuario;

import java.util.HashMap;
import java.util.List;

public class Proxy {
    static Proxy proxy = new Proxy();
    Json json;
    clConexion cl;
    private Proxy(){
        cl = new clConexion();
        json = new Json();
    }
    public static Proxy instancia(){
        if(proxy == null)
            Proxy.proxy = new Proxy();
        return Proxy.proxy;
    }
    public  Usuario login(String usuario,String password){
        String url = "?action=login";
        HashMap<String,Object> args = new HashMap<>();
        args.put("usuario",usuario);
        args.put("contrasenna",password);
        String txt = cl.getOutputFromUrl(url,null,"POST",args);
        if(txt.equals("null"))
            return null;
        return json.toUsuario(txt);
    }
    ///////////////////////////////////////////7
    public Boolean addUsuario(String u,String c,String isA){
        String url = "?action=addU";
        HashMap<String,Object> args = new HashMap<>();
        args.put("usuario",u);
        args.put("contrasenna",c);
        args.put("isAdmin",isA);
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return res.toUpperCase().equals("TRUE");
    }
    ///////////////////////////////////////////7
    public Boolean updateUsuario(String u,String c,String isA){
        String url = "?action=updateU";
        HashMap<String,Object> args = new HashMap<>();
        args.put("usuario",u);
        args.put("pswrd",c);
        args.put("isA",isA);
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return res.toUpperCase().equals("TRUE");
    }
    /////////////////////////////////////////////////////
    public Boolean deleteUsuario(String name){
        String url = "?action=deleteU";
        HashMap<String,Object> args = new HashMap<>();
        args.put("arg",name);
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return res.toUpperCase().equals("TRUE");
    }
    ////////////////////////////////////////////////////7
    public List<Usuario> listarUsuarios(String arg){
        String url = "?action=listaU";
        HashMap<String,Object> args = new HashMap<>();
        args.put("arg",arg);
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return json.toUsuatios(res);
    }
    /////////////////////////////////////////////////////
    public List<Producto> listarProducto(Integer Limite){
        String url = "?action=listarP";
        HashMap<String,Object> args = new HashMap<>();
        args.put("limite",Limite.toString());
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return json.toProductoss(res);
    }
    /////////////////////////////////////////////////
    public Boolean updateProducto(String codigo,String nombre,String desc,Double precio){
        String url = "?action=updateP";
        HashMap<String,Object> args = new HashMap<>();
        args.put("codigo",codigo);
        args.put("nombre",nombre);
        args.put("desc",desc);
        args.put("precio",new Integer(precio.intValue()).toString());
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return res.toUpperCase().equals("TRUE");
    }
    /////////////////////////////////////////////////
    public Boolean deleteProducto(String codigo){
        String url = "?action=deleteP";
        HashMap<String,Object> args = new HashMap<>();
        args.put("codigo",codigo);
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return res.toUpperCase().equals("TRUE");
    }
    ////////////////////////////////////////////////////77
    public Boolean addProducto(String codigo,String nombre,String desc,Integer precio){
        String url = "?action=addP";
        HashMap<String,Object> args = new HashMap<>();
        args.put("codigo",codigo);
        args.put("nombre",nombre);
        args.put("desc",desc);
        args.put("precio",precio.toString());
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return res.toUpperCase().equals("TRUE");
    }

    ////////////////////////////////////////////////////77
    public Boolean addCompra(String usuario,String producto,Integer cant){
        String url = "?action=addC";
        HashMap<String,Object> args = new HashMap<>();
        args.put("usuario",usuario);
        args.put("producto",producto);
        args.put("cant",cant.toString());
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return res.toUpperCase().equals("TRUE");
    }
    ////////////////////////////////////////////////////777
    public List<Historial> listarHistorial(String usuario){
        String url = "?action=historial";
        HashMap<String,Object> args = new HashMap<>();
        args.put("usuario",usuario);
        String res = cl.getOutputFromUrl(url,null,"POST",args);
        return json.toHistoriales(res);
    }


}
