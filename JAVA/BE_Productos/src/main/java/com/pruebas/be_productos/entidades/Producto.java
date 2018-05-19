package com.pruebas.be_productos.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Producto:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 15/05/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    String codigo;
    String nombre;
    String descripcion;
    Integer precio;
    Integer cantidad;
    Integer imagen;
    Float rating;
}
