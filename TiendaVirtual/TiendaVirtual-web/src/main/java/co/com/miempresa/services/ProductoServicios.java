/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.services;

import co.com.miempresa.dto.ResposeDTO;
import co.com.miempresa.logica.ProductoLogica;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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

}
