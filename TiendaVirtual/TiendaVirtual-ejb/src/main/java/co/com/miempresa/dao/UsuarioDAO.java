/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.dao;

import co.com.miempresa.entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ccepeda
 */
@Stateless
public class UsuarioDAO {
    
    @PersistenceContext(unitName = "TiendaVirtialPU")
    private EntityManager entityManager;
    
    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getSimpleName());
    
    public User consultarPorId(final Long iduser) {
        try {
            Query query = entityManager.createNamedQuery("User.findByUserId").setParameter("userId", iduser);
            return (User) query.getSingleResult();
        } catch (ClassCastException | NonUniqueResultException e) {
            LOG.log(Level.SEVERE, "Error al consultar el usuario por id: {0}" + e.getLocalizedMessage());
        }
        return null;
    }
    
}
