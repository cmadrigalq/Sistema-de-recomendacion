package com.moviles1.cyn.ejemploscortos.Entidades;
import java.sql.Timestamp;
import java.util.Date;



public class Historial {
    Integer secuencia;
    Compra compra;
    Date fecha;

    public Historial() {

    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

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
