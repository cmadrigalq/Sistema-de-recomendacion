package com.pruebas.be_productos.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Usuario:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 15/05/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    //String codigo;
    String usuario;
    String contrasenna;
    Boolean isAdmin;
}
