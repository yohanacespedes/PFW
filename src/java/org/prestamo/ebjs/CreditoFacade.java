/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prestamo.ebjs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.prestamo.entidades.Credito;

/**
 *
 * @author Aidee Ledezma
 */
@Stateless
public class CreditoFacade extends AbstractFacade<Credito> {

    @PersistenceContext(unitName = "PFWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditoFacade() {
        super(Credito.class);
    }
    
}
