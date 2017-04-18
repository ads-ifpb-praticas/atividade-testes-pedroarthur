/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Pedro Arthur
 */

public class ITMovieTest extends GenericDatabaseTestCase {
    
    private static EJBContainer container;
    private static Context context;
    
    private static final String RESOURCE = "java:global/classes/MovieServiceImpl!br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService";
    
    @BeforeClass
    public static void setUpClass() {
        container = EJBContainer.createEJBContainer();
        context = container.getContext();
    }
    
    @AfterClass
    public static void tearDownClass() throws NamingException {
        context.close();
        container.close();
    }
    
    @Test
    public void testInsert() throws NamingException {
        //
        MovieService service = (MovieService) context.lookup(RESOURCE);
        //
        Movie movie = new Movie("Sing Street", Gender.DRAMA, 120, false);
        movie.setId(5L);
        service.save(movie);
        
        assertEquals(5, service.listAll().size());
        
        Movie movie2 = new Movie("A volta dos que não foram #3", Gender.COMEDY, 92, false);
        movie.setId(6L);
        service.save(movie);
        
        assertEquals(6, service.listAll().size());
    }
    
    @Test
    public void testUpdate() throws NamingException {
        MovieService service = (MovieService) context.lookup(RESOURCE);
        //
        Movie movie = new Movie("The Amazing Spider Man", Gender.ACTION, 93, false);
        movie.setId(2L);
        
        service.update(movie);
        
        assertTrue(service.listAll().contains(movie));
        //
        Movie movie2 = new Movie("World of Warcraft", Gender.ACTION, 133, true);
        movie2.setId(3L);
        
        service.update(movie2);
        
        assertTrue(service.listAll().contains(movie2));
    }
    
    @Test
    public void testListAll() throws NamingException {
        MovieService service = (MovieService) context.lookup(RESOURCE);
        
        List<Movie> listAll = service.listAll();
        Movie[] arrayListAll = new Movie[listAll.size()];
        arrayListAll = listAll.toArray(arrayListAll);
        
        List<Movie> expectedListAll = expectedListAll();
        Movie[] arrayExpectedListAll = new Movie[expectedListAll.size()];
        arrayExpectedListAll = expectedListAll.toArray(arrayExpectedListAll);
          
        assertArrayEquals(arrayExpectedListAll, arrayListAll);
    }
    
    private List<Movie> expectedListAll() {
        List<Movie> movies = new ArrayList<>();
        
        Movie movie = new Movie("REC", Gender.HORROR, 120, true);
        movie.setId(1L);
        Movie movie1 = new Movie("The Amazing Spider Man", Gender.ACTION, 120, false);
        movie1.setId(2L);
        Movie movie2 = new Movie("World of Warcraft", Gender.ADVENTURE, 120, false);
        movie2.setId(3L);
        Movie movie3 = new Movie("Sing Street", Gender.DRAMA, 120, true);
        movie3.setId(4L);
        
        movies.add(movie);
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        
        return movies;
    }

    @Override
    public String getDataSetFile() {
        return "src/test/java/resources/movies.xml";
    }
}
