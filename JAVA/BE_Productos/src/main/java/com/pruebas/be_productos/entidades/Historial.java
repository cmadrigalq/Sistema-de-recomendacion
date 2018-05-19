package com.pruebas.be_productos.entidades;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Historial:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 15/05/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Historial {
    Integer secuencia;
    Compra compra;
    Date fecha;
    public void setFecha(Timestamp t){
        fecha = new Date(t.getYear(),
                         t.getMonth(),
                         t.getDate(),
                         t.getHours(),
                         t.getMinutes(),
                         t.getSeconds());
    }
    public String getFechaStr() {
        return String.format("%d-%d-%d-%d-%d-%d",
                fecha.getYear(),
                fecha.getMonth(),
                fecha.getDate(),
                fecha.getHours(),
                fecha.getMinutes(),
                fecha.getSeconds());
    }
}
