/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.logica;

import co.com.miempresa.dao.ProductoDAO;
import co.com.miempresa.dao.UsuarioDAO;
import co.com.miempresa.dto.ProductoDTO;
import co.com.miempresa.dto.ResposeDTO;
import co.com.miempresa.entities.Producto;
import co.com.miempresa.entities.User;
import co.com.miempresa.util.Constantes;
import co.com.miempresa.util.TransformacionDozer;
import org.apache.commons.codec.binary.Base64;
import java.util.ArrayList;
import java.util.Date;
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
    @EJB
    private UsuarioDAO usuarioDAO;

    public ResposeDTO listarAll() {
        ResposeDTO response = new ResposeDTO();
        List<Producto> listaBD = productoDAO.listarAll();
        List<ProductoDTO> listaRetorno = null;

        //listaRetorno = TransformacionDozer.transformar(listaBD, ProductoDTO.class);
        // Es la implementación de esto!!!
        if (listaBD != null && !listaBD.isEmpty()) {
            listaRetorno = new ArrayList<>();
            for (Producto producto : listaBD) {
                ProductoDTO productoDTO = TransformacionDozer.transformar(producto, ProductoDTO.class);
                if (producto.getProductImagen() != null) {
                    productoDTO.setImagen(Base64.encodeBase64String(producto.getProductImagen()));
                }

                listaRetorno.add(productoDTO);
            }
        }
        if (listaRetorno != null && !listaRetorno.isEmpty()) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setObject(listaRetorno);
        } else {
            response.setCodigo(Constantes.MENSAJES.ERROR.toString());
            response.setMensaje(Constantes.VAL_CONSULTA_PRODUCTOS);
        }
        return response;
    }

    public ResposeDTO consultarPorId(final Long idproducto) {
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

    //public ResposeDTO guardarInformacion(final ProductoDTO productoDTO, UsuarioDTO usuarioDTO) {
    public ResposeDTO guardarInformacion(final ProductoDTO productoDTO) {
        ResposeDTO response = new ResposeDTO();
        boolean respuesta;

       
        //este se cabia por el usuario que esta en sesion y llega por parametros
        User usuario = usuarioDAO.consultarPorId(new Long(1));

        Producto productoBD = TransformacionDozer.transformar(productoDTO, Producto.class);
        
        if(productoDTO.getImagen()!=null){
            productoBD.setProductImagen(Base64.decodeBase64(productoDTO.getImagen()));
        }
        if (productoBD.getProductId() != null) {
            productoBD.setProductFact(new Date());
            productoBD.setUserUpdate(usuario);
            respuesta = productoDAO.actualizarProducto(productoBD);
        } else {
            productoBD.setUser(usuario);
            productoBD.setProductFreg(new Date());
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
