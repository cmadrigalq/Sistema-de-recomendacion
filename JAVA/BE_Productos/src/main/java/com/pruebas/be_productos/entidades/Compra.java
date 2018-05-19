package com.pruebas.be_productos.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Compra:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 15/05/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    Integer num_compra;
    Usuario usuario;
    Producto producto;
    Integer cantidad;
}
