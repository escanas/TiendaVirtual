/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.logica;

import co.com.miempresa.dao.ProductoDAO;
import co.com.miempresa.dto.ProductoDTO;
import co.com.miempresa.dto.ResposeDTO;
import co.com.miempresa.entities.Producto;
import co.com.miempresa.util.Constantes;
import co.com.miempresa.util.TransformacionDozer;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ccepeda
 */
@Stateless
public class ProductoLogica {

    @EJB
    private ProductoDAO productoDAO;

    public ResposeDTO listarAll() {
        ResposeDTO response = new ResposeDTO();
        List<Producto> listaBD = productoDAO.listarAll();
        List<ProductoDTO> listaRetorno;
        listaRetorno = TransformacionDozer.transformar(listaBD, ProductoDTO.class);
        // Es la implementación de esto!!!
        /*if (listaBD != null && !listaBD.isEmpty()) {
            listaRetorno = new ArrayList<>();
            for (Producto producto : listaBD) {
                ProductoDTO productoDTO = new ProductoDTO();
                productoDTO.setId(producto.getProductId());
                productoDTO.setCode(producto.getProductCode());
                //....
                listaRetorno.add(productoDTO);
            }
        }*/
        if (!listaRetorno.isEmpty()) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setObject(listaRetorno);
        } else {
            response.setCodigo(Constantes.MENSAJES.ERROR.toString());
            response.setMensaje(Constantes.VAL_CONSULTA_PRODUCTOS);
        }
        return response;
    }

    public ResposeDTO consultarPorId(final Double idproducto) {
        ResposeDTO response = new ResposeDTO();
        Producto producto = productoDAO.consultarPorId(idproducto);
        if (producto != null) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setObject(TransformacionDozer.transformar(producto, ProductoDTO.class));

        } else {
            response.setCodigo(Constantes.MENSAJES.ERROR.toString());
            response.setMensaje(Constantes.VAL_CONSULTA_PRODUCTO_POR_ID);
        }
        return response;
    }

    public ResposeDTO guardarInformacion(final ProductoDTO productoDTO) {
        ResposeDTO response = new ResposeDTO();
        boolean respuesta;
        Producto productoBD = TransformacionDozer.transformar(productoDTO, Producto.class);
        if (productoBD.getProductId() != null) {
            respuesta = productoDAO.actualizarProducto(productoBD);
        } else {
            respuesta = productoDAO.guardarProducto(productoBD);
        }
        if (respuesta) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setMensaje(Constantes.VAL_REGISTRO_PRODUCTO_OK);
        } else {
            response.setCodigo(Constantes.MENSAJES.ERROR.toString());
            response.setMensaje(Constantes.VAL_REGISTRO_PRODUCTO_ERROR);
        }
        return response;
    }
    
    public ResposeDTO eliminarInformacion(final ProductoDTO productoDTO) {
        ResposeDTO response = new ResposeDTO();
        boolean respuesta = false;
        Producto productoBD = TransformacionDozer.transformar(productoDTO, Producto.class);
        if (productoBD.getProductId() != null) {
            respuesta = productoDAO.eliminarProducto(productoBD);
        }
        if (respuesta) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setMensaje(Constantes.VAL_ELIMINAR_PRODUCTO_OK);
        } else {
            response.setCodigo(Constantes.MENSAJES.ERROR.toString());
            response.setMensaje(Constantes.VAL_ELIMINAR_PRODUCTO_ERROR);
        }
        return response;
    }
}
