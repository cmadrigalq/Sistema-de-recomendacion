package com.pruebas.be_productos.probando;

import com.pruebas.DataBase.GlobalException;
import com.pruebas.DataBase.Servicio;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * main:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 15/05/2018
 */
public class Main {
    public static void main(String[]args){
        Servicio s = new Servicio();
        try {
            s.conectar();
            s.desconectar();
        } catch (GlobalException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
