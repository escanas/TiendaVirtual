/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.util;

/**
 *
 * @author Ccepeda
 */
public class Constantes {

    public enum MENSAJES {
        ERROR, EXITO
    }

    public static String VAL_CONSULTA_PRODUCTOS = "No se encontró información de productos registrados";
    public static String VAL_CONSULTA_PRODUCTO_POR_ID = "No se encontró información del producto con el id ingresado";

    public static String VAL_REGISTRO_PRODUCTO_OK = "La información del producto se guardó exitosamente";
    public static String VAL_REGISTRO_PRODUCTO_ERROR = "Error al guardar la información del producto, consulte con el administador";

    public static String VAL_ELIMINAR_PRODUCTO_OK = "El producto se eliminó correctamente";
    public static String VAL_ELIMINAR_PRODUCTO_ERROR = "Error al eliminar la información del producto, consulte con el administador";

    public static String VAL_LIST_CATEGORIAS_ERROR = "No se encuentran categorias activas en el sistema";

    public static String ADD_NEW_CATEGORIA_OK = "La categoria se creó exitosamente";
    public static String ADD_NEW_CATEGORIA_ERROR = "Error al crear la categoria";
    public static String ADD_CONSULTA_CATEGORIA_ERROR = "Error al consultar la información de la categoria seleccionada";
    
    
    public static String DELETE_CATEGORIA_OK = "La categoria se eliminó exitosamente";
    public static String DELETE_CATEGORIA_ERROR ="Error al eliminar la categoria";
    public static String DELETE_CATEGORIA_EXISTE="La categoria no se puede eliminar porque está asignada a uno o mas productos";

}
