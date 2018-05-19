package com.pruebas.be_productos.servicios;

import com.pruebas.DataBase.Servicio;
import com.pruebas.be_productos.entidades.Compra;
import com.pruebas.be_productos.entidades.Historial;
import com.pruebas.be_productos.entidades.Producto;
import com.pruebas.be_productos.entidades.Usuario;
import com.pruebas.be_productos.querys.Querys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HistorialServicio:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 18/05/2018
 */
public class HistorialServicio extends Servicio {

    public HistorialServicio() {
        super();
    }
    public List<Historial>listar(String us){
        String sql = String.format(Querys.LISTARHISTORIAL,us);
        ResultSet rs = super.executeQuery(sql);
        List<Historial> res = new ArrayList<>();
        try {
            while(rs.next()){
               Producto p = new Producto();
               p.setDescripcion(rs.getString(2));
                            
               Usuario u = new Usuario();
               u.setUsuario(rs.getString(1));
               
               Compra c = new Compra();
               c.setCantidad(rs.getInt(3));
               c.setProducto(p);
               c.setUsuario(u);
               
               Historial h = new Historial();
               h.setFecha(rs.getTimestamp(4));
               h.setCompra(c);
               res.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistorialServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
