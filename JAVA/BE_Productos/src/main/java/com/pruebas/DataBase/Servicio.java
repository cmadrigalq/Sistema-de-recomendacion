package com.pruebas.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel Céspedes
 */
public class Servicio {

    protected Connection conexion = null;

    public Servicio() {
    }

    public void conectar() throws GlobalException {
        try {
            //String URL = "jdbc:postgresql://localhost:5432/Prodcutos?user=";
            String url = "jdbc:postgresql://localhost:5432/Productos";
            Properties prop = new Properties();
            prop.put("charSet", "UTF-8");
            prop.put("user", "postgres");
            prop.put("password", "root");
           // prop.put("ssl", "true");
            Class.forName("org.postgresql.Driver").newInstance();
            conexion = DriverManager.getConnection(url,prop);
            //JOptionPane.showMessageDialog(null,"Conectado correctamente \n");
        } catch (SQLException e) {
            throw new GlobalException("No se ha podido conectar "+e);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
    public ResultSet executeQuery(String statement){
        try {
            this.conectar();
            System.err.print("******* "+statement+" ************");
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(statement);
            this.desconectar();
            return rs;
        } catch (SQLException | GlobalException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
