/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.dao;

import co.com.miempresa.entities.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Ccepeda
 */
@Stateless
public class ProductoDAO {

    @PersistenceContext(unitName = "TiendaVirtialPU")
    private EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getSimpleName());

    public List<Producto> listarAll() {
        try {
            Query query = entityManager.createNamedQuery("Producto.findAll"); //entityManager.createNativeQuery("SELECT * FROM tbl_pruductos");
            List<Producto> listabd = (List<Producto>) query.getResultList(); // List<Object>
            return listabd;
        } catch (ClassCastException | NoResultException e) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de productos de la base de datos {0}", e.getCause());
        }
        return new ArrayList<>();
    }

    public Producto consultarPorId(final Long idproducto) {
        try {
            Query query = entityManager.createNamedQuery("Producto.findByProductId").setParameter("productId", idproducto); //entityManager.createNativeQuery("SELECT * FROM tbl_pruductos WHERE producto_id = 1");
            Producto producto = (Producto) query.getSingleResult(); // Object
            return producto;
        } catch (ClassCastException | NoResultException | NonUniqueResultException e) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de productos de la base de datos {0}", e.getCause());
        }
        return null;
    }

    public Producto consultarPorIdB(final Double idproducto) {
        try {
            Query query = entityManager.createNamedQuery("Producto.findByProductIdRepetidos").setParameter("productId", idproducto);
            List<Producto> listabd = (List<Producto>) query.getResultList(); // List<Object>
            return listabd.get(0);
        } catch (ClassCastException | NoResultException e) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de productos de la base de datos {0}", e.getCause());
        }
        return null;
    }

    public boolean guardarProducto(final Producto producto) {
        try {
            //INSER INTO ('') VALUES('','','','');
            entityManager.persist(producto);
            return true;
        } catch (PersistenceException e) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar los datos del producto {0}", e.getCause());
        }
        return false;
    }

    public boolean actualizarProducto(final Producto producto) {
        try {
            entityManager.merge(producto);
            return true;
        } catch (PersistenceException e) {
            LOG.log(Level.SEVERE, "Ocurrio un error al actualizar los datos del producto {0}", e.getCause());
        }
        return false;
    }

    public boolean eliminarProducto(final Producto producto) {
        try {
            entityManager.remove(entityManager.contains(producto) ? producto : entityManager.merge(producto));
            return true;
        } catch (PersistenceException e) {
            LOG.log(Level.SEVERE, "Ocurrio un error al borrar los datos del producto {0}", e.getCause());
        }
        return false;
    }
}
