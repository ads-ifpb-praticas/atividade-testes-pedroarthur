/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.validation;

import br.edu.ifpb.praticas.atividade.testes.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.exceptions.MovieException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pedro Arthur
 */
public class MovieValidation {
    
    public void validate(Movie movie) {
        if(movie.getDuration() <= 0) throw new MovieException("The Movie duration must be greater than 0!");
        if(movie.getGender() == null) throw new MovieException("A Movie must have a gender!");
        validateTitle(movie.getTitle()); 
    }
    
    private void validateTitle(String title) {
        if(title.length() > 50) throw new MovieException("A movie title must contain til 50 characters!");
        Pattern p = Pattern.compile("^[a-zA-Z0-9!#?]*$");
        Matcher m = p.matcher(title);
        System.out.println(title+" : "+m.matches());
        if(!m.matches())
            throw new MovieException("A movie title must contain only #,! or ? as a special character!");
    }
}
