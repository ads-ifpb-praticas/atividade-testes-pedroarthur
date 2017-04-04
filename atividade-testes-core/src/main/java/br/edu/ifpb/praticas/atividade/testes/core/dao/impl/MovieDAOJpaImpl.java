/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.dao.impl;

import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.MovieDAO;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
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
@Local(MovieDAO.class)
public class MovieDAOJpaImpl implements MovieDAO {
    
    @PersistenceContext(unitName = "atividade-testes-pu")
    private EntityManager entityManager;

    @Override
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public void update(Movie movie) {
        entityManager.merge(movie);
    }

    @Override
    public List<Movie> listAll() {
        TypedQuery<Movie> query = this.entityManager
                .createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }

    @Override
    public Movie get(Long id) {
        Movie found = this.entityManager.find(Movie.class, id);
        return found;
    }
    
}
