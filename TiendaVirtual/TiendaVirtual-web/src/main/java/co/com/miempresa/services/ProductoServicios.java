/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.services;

import co.com.miempresa.dto.ProductoDTO;
import co.com.miempresa.dto.ResposeDTO;
import co.com.miempresa.logica.ProductoLogica;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ccepeda
 */
@Path("productoServicios")
@Stateless
public class ProductoServicios {

    @EJB
    private ProductoLogica productoLogica;

    @GET
    @Path("/listarProductos")
    @Produces(MediaType.APPLICATION_JSON)
    public ResposeDTO consultarProductos() {
        return productoLogica.listarAll();
    }

    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResposeDTO guardarInformacion(final ProductoDTO producto) {
        return productoLogica.guardarInformacion(producto);
    }

    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResposeDTO buscarProductoPorId(@PathParam("id") final Long idproducto) {
        return productoLogica.consultarPorId(idproducto);
    }
    
    @POST
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResposeDTO eliminarInformacion(final ProductoDTO producto) {
        return productoLogica.eliminarInformacion(producto);
    }
}
