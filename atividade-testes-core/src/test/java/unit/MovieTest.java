package unit;


import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.MovieDAO;
import br.edu.ifpb.praticas.atividade.testes.shared.exceptions.MovieException;
import br.edu.ifpb.praticas.atividade.testes.core.services.impl.MovieServiceImpl;
import br.edu.ifpb.praticas.atividade.testes.core.validation.MovieValidation;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import org.mockito.MockitoAnnotations;

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
    
    private MovieDAO movieDAO;
    private MovieService movieService;
    
    private MovieValidation validator = new MovieValidation(); 
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.movieDAO = Mockito.mock(MovieDAO.class);
        
        this.movieService = new MovieServiceImpl(movieDAO);
        
        doReturn(mockedListAll()).when(movieDAO).listAll();
        doNothing().when(movieDAO).save(any(Movie.class));
        doNothing().when(movieDAO).update(any(Movie.class));
    }
    
    private List<Movie> mockedListAll() {
        List<Movie> movies = new ArrayList<>();
        
        Movie movie1 = new Movie("A volta dos que não foram #3", Gender.ACTION, 120, false);
        movie1.setId(1L);
        Movie movie2 = new Movie("Eu você e a mulher!", Gender.DRAMA, 120, true);
        movie2.setId(2L);
        Movie movie3 = new Movie("Não diga não! #3", Gender.COMEDY, 120, false);
        movie3.setId(3L);
        Movie movie4 = new Movie("É ela?", Gender.ANIMATION, 120, true);
        movie4.setId(4L);
        
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        
        return movies;
    }
    
    @Test(expected = MovieException.class)
    public void testFailingMovieUpdate() {
        
        Movie mockedMovie = new Movie("A volta dos que não foram #3", Gender.ACTION, 120, true);
        mockedMovie.setId(1L);
        doReturn(mockedMovie).when(movieDAO).get(any(Long.class));
        
        Movie movie = new Movie("A volta dos que não foram #3", Gender.ACTION, 120, false);
        movie.setRented(true);
        movieService.update(movie);
    }
    
    @Test
    public void testSuccessMovieUpdate() {
        
        Movie mockedMovie = new Movie("A volta dos que não foram #3", Gender.ACTION, 120, false);
        mockedMovie.setId(1L);
        doReturn(mockedMovie).when(movieDAO).get(any(Long.class));
        
        Movie movie = new Movie("A volta dos que não foram #3", Gender.ACTION, 120, true);
        movie.setId(1L);
        movieService.update(movie);
    }
    
    @Test
    public void testSuccessMovieTitle() {
        Movie movie = new Movie("REC", Gender.HORROR, 120, false);
        validator.validate(movie);
        
        movie.setTitle("Spider Man #3");
        validator.validate(movie);
        
        movie.setTitle("A volta dos que não foram! #2");
        validator.validate(movie);
        
        movie.setTitle("REC 3");
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
    
    @Test(expected = MovieException.class)
    public void testFailingMovieTitle() {
        Movie movie = new Movie("REC#*¨¬$@)({}", Gender.HORROR, 120, false);
        validator.validate(movie);
    }
   
}
