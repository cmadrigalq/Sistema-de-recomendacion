package com.pruebas.be_productos.servicios;

import com.pruebas.DataBase.Servicio;
import com.pruebas.be_productos.entidades.Usuario;
import com.pruebas.be_productos.querys.Querys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UsuariosServicio:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 16/05/2018
 */
public class UsuariosServicio extends Servicio{
    public UsuariosServicio(){
        super();
    }

    public Usuario login(Usuario u) {
        String query = String.format(Querys.LOGIN,u.getUsuario(),u.getContrasenna());
        ResultSet rs = super.executeQuery(query);
        try {
            if(rs.next()){
                u.setIsAdmin(rs.getBoolean(3));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Boolean agregar(Usuario u){
        String query = String.format(Querys.ADDUSER,
                                     u.getUsuario(),
                                     u.getContrasenna(),
                                     u.getIsAdmin());
        super.executeQuery(query);
        return  true;
    }
    public Boolean update(Usuario u){
        String query = String.format(Querys.UPDATEUSER,
                                     u.getContrasenna(),
                                     u.getIsAdmin(),
                                     u.getUsuario());
        super.executeQuery(query);
        return  true;
    }
    public Boolean delete(Usuario u){
        String sql = String.format(Querys.BORRARHISTORIAL, u.getUsuario());
        executeQuery(sql);
        sql = String.format(Querys.BORRARCOMPRAS, u.getUsuario());
        executeQuery(sql);
        sql = String.format(Querys.BORRARUSER, u.getUsuario());
        super.executeQuery(sql);
        return  true;
    }
    public List<Usuario>listarUsuarios(String arg/**BLANKS para todos*/){
        String sql = String.format(Querys.BUSCARUSER,arg);
        ResultSet rs = super.executeQuery(sql);
        List<Usuario>res = new ArrayList<>();
        try {
            while(rs.next()){
                Usuario u = new Usuario();
                u.setUsuario(rs.getString(1));
                u.setContrasenna(rs.getString(2));
                u.setIsAdmin(rs.getBoolean(3));
                res.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
