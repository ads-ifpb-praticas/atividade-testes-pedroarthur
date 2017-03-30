/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import java.util.List;

/**
 *
 * @author Pedro Arthur
 */
public interface MovieDAO {

    void save(Movie movie);
    void update(Movie movie);
    void remove(Movie movie);
    List<Movie> listAll();
}
