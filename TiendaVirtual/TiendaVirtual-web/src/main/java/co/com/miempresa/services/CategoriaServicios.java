/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.services;

import co.com.miempresa.dto.CategoriaDTO;
import co.com.miempresa.dto.ResposeDTO;
import co.com.miempresa.logica.CategoriaLogica;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author CarlosJavier
 */
@Path("categoriaServicios")
@Stateless
public class CategoriaServicios {

    @EJB
    private CategoriaLogica categoriaLogica;

    @GET
    @Path("listCategorias")
    @Produces(MediaType.APPLICATION_JSON)
    public ResposeDTO listarCategorias() {
        return categoriaLogica.listarCategorias();
    }

    @GET
    @Path("cat/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResposeDTO consultarPorId(@PathParam("id") Integer idcat) {
        return categoriaLogica.buscarPorId(idcat);
    }

    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResposeDTO listarCategorias(CategoriaDTO categoriaDTO) {
        return categoriaLogica.crearEditarCategoria(categoriaDTO);
    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResposeDTO eliminarCategorias(CategoriaDTO categoriaDTO) {
        return categoriaLogica.eliminarCategoria(categoriaDTO);
    }

}
