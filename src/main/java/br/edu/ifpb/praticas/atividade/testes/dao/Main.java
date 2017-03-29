/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.dao;

import br.edu.ifpb.praticas.atividade.testes.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.domain.Movie;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Pedro Arthur
 */
public class Main {
    
    private static MovieDAO movieDAO;
    
    public static void main(String[] args) {
        
        movieDAO = new MovieDAOH2Impl();
        
        Movie movie = new Movie("A", Gender.ACTION, 100, false);
        movieDAO.add(movie);
        
        for(Movie current : movieDAO.listAll()) {
            System.out.println(current);
        }
    }
}
