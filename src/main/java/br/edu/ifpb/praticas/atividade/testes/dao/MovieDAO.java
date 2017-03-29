/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.dao;

import br.edu.ifpb.praticas.atividade.testes.domain.Movie;
import java.util.List;

/**
 *
 * @author Pedro Arthur
 */
public interface MovieDAO {
    
    void add(Movie movie);
    void update(Movie movie);
    List<Movie> listAll();
    Movie get(Long id);
    
}
