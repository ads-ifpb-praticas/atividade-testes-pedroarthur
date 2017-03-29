package units;


import br.edu.ifpb.praticas.atividade.testes.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.exceptions.MovieException;
import br.edu.ifpb.praticas.atividade.testes.validation.MovieValidation;
import static org.junit.Assert.*;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro Arthur
 */
public class MovieTest {
    
    private MovieValidation validator = new MovieValidation();
    
    @Test(expected = MovieException.class)
    public void testFailingMovieTitle() {
        Movie movie = new Movie("REC*", Gender.HORROR, 120, false);
        validator.validate(movie);
        
        movie.setTitle("@movie");
        validator.validate(movie);
        
        movie.setTitle("&¨(*&");
        validator.validate(movie);
        
        movie.setTitle("aa%%aa");
        validator.validate(movie);
    }
    
    @Test
    public void testSuccessMovieTitle() {
        Movie movie = new Movie("REC", Gender.HORROR, 120, false);
        validator.validate(movie);
        
        movie.setTitle("Movie?");
        validator.validate(movie);
        
        movie.setTitle("?Movie");
        validator.validate(movie);
        
        movie.setTitle("!!!!");
        validator.validate(movie);
        
        movie.setTitle("!!!!#");
        validator.validate(movie);
        
        movie.setTitle("!!!!#??");
        validator.validate(movie);
        
        movie.setTitle("!!!!#?Movie999");
        validator.validate(movie);
    }
   
}
