package com.pruebas.be_productos.servicios;

import com.pruebas.DataBase.Servicio;
import com.pruebas.be_productos.entidades.Producto;
import com.pruebas.be_productos.querys.Querys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ServicioProducto:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 18/05/2018
 */
public class ProductoServicio extends Servicio{
    public ProductoServicio(){
       super(); 
    }
    Producto map(ResultSet rs) throws SQLException{
        Producto p = new Producto();
        p.setCodigo(rs.getString(1));
        p.setNombre(rs.getString(2));
        p.setDescripcion(rs.getString(3));
        p.setPrecio(rs.getInt(4));
        p.setCantidad(rs.getInt(5));
        p.setImagen(rs.getInt(6));
        p.setRating(rs.getFloat(7));
        return p;
    }
    public List<Producto>listar(int cant){
        String sql = String.format(Querys.LISTARPRODUCTOS, cant);
        ResultSet rs = super.executeQuery(sql);
        List<Producto>res = new ArrayList<>();
        try {
            while(rs.next()){
                res.add(map(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public Boolean update(Producto p){
        String sql = String.format(Querys.UPDATEPRODUCTO, p.getCantidad(),p.getCodigo());
        super.executeQuery(sql);
        return  true;
    }
    public Boolean add(Producto p){
        String sql = String.format(Querys.ADDPRODUCTO, 
                                   p.getCodigo(),
                                   p.getNombre(),
                                   p.getDescripcion(),
                                   p.getPrecio(),
                                   p.getCantidad());
        super.executeQuery(sql);
        return  true;
    }
    
}   
