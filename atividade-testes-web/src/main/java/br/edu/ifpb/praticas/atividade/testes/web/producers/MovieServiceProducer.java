/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.web.producers;

import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Pedro Arthur
 */

@ApplicationScoped
public class MovieServiceProducer {
    
    private final String resource = "java:global/atividade-testes-core/MovieServiceImpl!br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService";
    
    @Default
    @Dependent
    @Produces
    public MovieService getMovieService() {
        return new ServiceLocator().lookup(resource, MovieService.class);
    }
}
