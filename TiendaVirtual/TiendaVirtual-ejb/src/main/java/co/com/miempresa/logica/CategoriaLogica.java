/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.logica;

import co.com.miempresa.dao.CategoriaDAO;
import co.com.miempresa.dao.ProductoDAO;
import co.com.miempresa.dto.CategoriaDTO;
import co.com.miempresa.dto.ResposeDTO;
import co.com.miempresa.entities.Categoria;
import co.com.miempresa.util.Constantes;
import co.com.miempresa.util.TransformacionDozer;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author CarlosJavier
 */
@Stateless
public class CategoriaLogica {

    @EJB
    private CategoriaDAO categoriaDAO;
    @EJB
    private ProductoDAO productoDAO;

    public ResposeDTO listarCategorias() {
        ResposeDTO response = new ResposeDTO();
        List<Categoria> listabd = categoriaDAO.listarCategoriasActivas();
        if (listabd != null && !listabd.isEmpty()) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setObject(TransformacionDozer.transformar(listabd, CategoriaDTO.class));
        } else {
            response.setCodigo(Constantes.MENSAJES.ERROR.toString());
            response.setMensaje(Constantes.VAL_LIST_CATEGORIAS_ERROR);
        }
        return response;
    }

    public ResposeDTO crearEditarCategoria(CategoriaDTO categoriaDTO) {
        ResposeDTO response = new ResposeDTO();
        response.setCodigo(Constantes.MENSAJES.ERROR.toString());
        boolean respuesta;
        Categoria categoria = TransformacionDozer.transformar(categoriaDTO, Categoria.class);
        if (categoria.getCatId() != null) {
            respuesta = categoriaDAO.editar(categoria);
        } else {
            respuesta = categoriaDAO.crear(categoria);
        }
        if (respuesta) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setMensaje(Constantes.ADD_NEW_CATEGORIA_OK);
        } else {
            response.setMensaje(Constantes.ADD_NEW_CATEGORIA_ERROR);
        }
        return response;
    }

    public ResposeDTO buscarPorId(Integer idcat) {
        ResposeDTO response = new ResposeDTO();
        response.setCodigo(Constantes.MENSAJES.ERROR.toString());
        Categoria categoria = categoriaDAO.buscarPorId(idcat);
        if (categoria != null) {
            response.setCodigo(Constantes.MENSAJES.EXITO.toString());
            response.setObject(TransformacionDozer.transformar(categoria, CategoriaDTO.class));
        } else {
            response.setMensaje(Constantes.ADD_CONSULTA_CATEGORIA_ERROR);
        }
        return response;
    }

    public ResposeDTO eliminarCategoria(CategoriaDTO categoriaDTO) {
        ResposeDTO response = new ResposeDTO();
        response.setCodigo(Constantes.MENSAJES.ERROR.toString());
       
        Categoria categoria = TransformacionDozer.transformar(categoriaDTO, Categoria.class);

        Integer cantRegistrosCat = productoDAO.consultarPorIdCategoria(categoriaDTO.getId());
        if (cantRegistrosCat == 0) {
            boolean resultado = categoriaDAO.eliminarCategoria(categoria);
            if (resultado) {
                response.setCodigo(Constantes.MENSAJES.EXITO.toString());
                response.setMensaje(Constantes.DELETE_CATEGORIA_OK);
            } else {
                response.setMensaje(Constantes.DELETE_CATEGORIA_ERROR);
            }
        } else {
            if (cantRegistrosCat > 0) {
                response.setMensaje(Constantes.DELETE_CATEGORIA_EXISTE);
            } else {
                response.setMensaje(Constantes.DELETE_CATEGORIA_ERROR);
            }
        }
        return response;
    }

}
