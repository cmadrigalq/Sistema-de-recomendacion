package com.pruebas.be_productos.servicios;

import com.pruebas.DataBase.Servicio;
import com.pruebas.be_productos.entidades.Compra;
import com.pruebas.be_productos.querys.Querys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CompraServicio:
 *
 * @version 1.0.0
 * @author Cynthia Madrigal Quesada
 * @date 18/05/2018
 */
public class CompraServicio extends Servicio {

    public CompraServicio() {
        super();
    }

    public Boolean add(Compra c) {
        String sql = String.format(Querys.ADDCOMPRA,
                c.getUsuario().getUsuario(),
                c.getProducto().getCodigo(),
                c.getCantidad());

        super.executeQuery(sql);
        int last = last();
        addHisto(last);
        return true;
    }

    Integer last() {
        ResultSet rs = executeQuery(Querys.LASTCOMP);
        try {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompraServicio.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    Boolean addHisto(int last) {
        String sql = String.format(Querys.ADDHISTO, last);
        super.executeQuery(sql);
        return true;
    }
}
