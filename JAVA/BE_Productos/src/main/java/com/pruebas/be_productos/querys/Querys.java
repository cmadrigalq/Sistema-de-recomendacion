package com.pruebas.be_productos.querys;
/**
 * Querys:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 16/05/2018
 */
public class Querys {
    public static final String LOGIN =
            "SELECT * FROM Usuario "
            +"WHERE usuario = '%s' "
            +"AND contrasenna = '%s' ;";
    
    public static final String ADDUSER = 
            "Insert into usuario "
            + "values('%s','%s',%b);";
    
    public static final String UPDATEUSER =
            "Update usuario "
            + "SET   contrasenna = '%s', "
            + "      isAdmin = %b "
            + "where usuario = '%s';";
    
    public static final String BUSCARUSER ="Select * from usuario "
            + " Where UPPER(usuario) like '%%%s%%' ";
    
    public static final String  BORRARHISTORIAL =
            "Delete from historial "
            + "where num_compra IN (SELECT num_compra from compra c"
            + "  where c.usuario = '%s'"
            + ");";
    public static final String  BORRARHISTORIALBYP =
            "Delete from historial "
            + "where num_compra IN (SELECT num_compra from compra c"
            + "  where c.producto = '%s'"
            + ");";
    public static final String BORRARCOMPRAS = 
            "Delete from compra "
            + "where usuario = '%s';";
    public static final String BORRARCOMPRASBYP = 
            "Delete from compra "
            + "where producto = '%s';";
    public static final String BORRARUSER = 
            "Delete from usuario where usuario = '%s';";
    ////////////////////////////////////////////////////////////////////
    public static final String LISTARPRODUCTOS =
            "SELECT * FROM Producto where cantidad > %d;";
    
    public static final String UPDATEPRODUCTO = 
            "UPDATE  PRODUCTO "
            + "set nombre = '%s',"
            + "descripcion = '%s',"
            + "precio = %d "
            + "where codigo = '%s';";
    public static final String DELETEPRODUCTO = 
            "Delete from Producto "
            + "where codigo = '%s';";
    public static final String ADDPRODUCTO = 
            "Insert Into Producto values "
            + "('%s','%s','%s',%d,%d,%d,%f);";
    ///////////////////////////////////////////////////////////////////
    public static final String ADDCOMPRA =
            "Insert Into Compra "
            + "(usuario,producto,cantidad) "
            + "values ('%s','%s',%d);";
    public static final String LASTCOMP = 
            "select num_compra from compra " +
            "order by num_compra desc " +
            "limit 1;";
    
    public static final String ADDHISTO =
            "Insert into historial "
            + "(num_compra,fecha) values (%d,now())";
    ////////////////////////////////////////////////////////////////////
    public static final String LISTARHISTORIAL =
            "SELECT U.usuario,P.codigo||'-'||P.nombre,C.cantidad,H.fecha "
            + "from HISTORIAL H "
            + "inner join Compra C "
            + "on H.num_compra = C.num_compra "
            + "inner join Usuario U "
            + "on U.usuario = C.usuario "
            + "inner join Producto P "
            + "on P.codigo = C.producto "
            + "WHERE U.usuario = '%s';";
}
