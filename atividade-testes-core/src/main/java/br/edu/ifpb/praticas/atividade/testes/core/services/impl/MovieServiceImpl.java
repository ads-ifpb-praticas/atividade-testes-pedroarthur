/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.services.impl;

import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.MovieDAO;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Pedro Arthur
 */

@Stateless
@Remote(MovieService.class)
public class MovieServiceImpl implements MovieService {
    
    @EJB
    private MovieDAO movieDAO;

    @Override
    public void save(Movie movie) {
        movieDAO.save(movie);
    }

    @Override
    public void update(Movie movie) {
        movieDAO.update(movie);
    }

    @Override
    public void remove(Movie movie) {
        movieDAO.remove(movie);
    }

    @Override
    public List<Movie> listAll() {
        return movieDAO.listAll();
    }
    
}
