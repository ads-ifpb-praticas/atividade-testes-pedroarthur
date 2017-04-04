/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.dao.impl;

import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.RentDAO;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Rent;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pedro Arthur
 */


@Stateless
@Local(RentDAO.class)
public class RentDAOJpaImpl implements RentDAO {
    
    @PersistenceContext(unitName = "atividade-testes-pu")
    private EntityManager entityManager;

    @Override
    public void save(Rent rent) {
        this.entityManager.persist(rent);
    }

    @Override
    public List<Rent> listAll() {
        TypedQuery<Rent> query = this.entityManager
                .createQuery("SELECT r FROM Rent r", Rent.class);
        return query.getResultList();
    }

    @Override
    public void update(Rent rent) {
        this.entityManager.merge(rent);
    }
    
}
