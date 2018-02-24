/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.dao;

import co.com.miempresa.entities.Categoria;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author CarlosJavier
 */
@Stateless
public class CategoriaDAO {
    
    @PersistenceContext(unitName = "TiendaVirtialPU")
    private EntityManager entityManager;
    
    private static final Logger LOG = Logger.getLogger(CategoriaDAO.class.getSimpleName());
    
    public List<Categoria> listarCategoriasActivas() {
        try {
            Query query = entityManager.createNamedQuery("Categoria.findAll");
            return (List<Categoria>) query.getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al consultar la lista de categorias {0}", e.getMessage());
        }
        return null;
    }
    
}
