/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jpa;

import com.ensode.jpa.AbstractFacade;
import com.ensode.jpa.MppUsers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sammy
 */
@Stateless
public class MppUsersFacade extends AbstractFacade<MppUsers> {

    @PersistenceContext(unitName = "jsfjpaCRUD2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MppUsersFacade() {
        super(MppUsers.class);
    }
    
}
