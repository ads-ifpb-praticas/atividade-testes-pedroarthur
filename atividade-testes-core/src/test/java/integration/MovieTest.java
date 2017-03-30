/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Arthur
 */
public class MovieTest {
    
    private static EJBContainer container;
    private static Context context;
    
    private static final String RESOURCE = "java:global/classes/MovieServiceImpl!br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService";
    
    @BeforeClass
    public static void setUpClass() {
        container = EJBContainer.createEJBContainer();
        context = container.getContext();
    }
    
    @Test
    public void testInsert() throws NamingException {
        MovieService service = (MovieService) context.lookup(RESOURCE);
        
        Movie movie = new Movie("Sing Street", Gender.DRAMA, 120, false);
        service.save(movie);
        
        assertEquals(1, service.listAll().size());
    }
}
