/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.services.impl;

import br.edu.ifpb.praticas.atividade.testes.core.exceptions.EntityNotFoundException;
import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.MovieDAO;
import br.edu.ifpb.praticas.atividade.testes.core.exceptions.MovieException;
import br.edu.ifpb.praticas.atividade.testes.core.validation.MovieValidation;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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

    private final MovieValidation validator;

    public MovieServiceImpl() {
        validator = new MovieValidation();
    }

    public MovieServiceImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
        validator = new MovieValidation();
    }

    private Movie getById(Long id) {
        Movie movie = movieDAO.get(id);
        if (movie == null) {
            throw new EntityNotFoundException("There isn't movie with the id " + id);
        }
        return movie;
    }

    private void verifyUpdatableMovie(Movie movie) {
        Movie persistedMovie = getById(movie.getId());
        System.out.println("filme: "+persistedMovie);
        if (persistedMovie.isRented()) {
            System.out.println("Está alugado, retornando exceção...");
            throw new MovieException("You can't update a rented movie!");
        }
    }

    @Override
    public void save(Movie movie) {
        validator.validate(movie);
        movieDAO.save(movie);
    }

    @Override
    public void update(Movie movie) {
        validator.validate(movie);
        verifyUpdatableMovie(movie);
        movieDAO.update(movie);
    }

    @Override
    public List<Movie> listAll() {
        return movieDAO.listAll();
    }

}
