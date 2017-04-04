/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import java.util.List;

/**
 *
 * @author Pedro Arthur
 */
public interface MovieService {
    
    void save(Movie movie);
    void update(Movie movie);
    List<Movie> listAll();
}
