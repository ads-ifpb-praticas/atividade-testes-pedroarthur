/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Rent;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.RentState;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.RentService;
import java.time.LocalDate;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Pedro Arthur
 */

@Ignore
public class RentTest extends GenericDatabaseTestCase {
    
    private static EJBContainer container;
    private static Context context;
    
    private static final String RESOURCE = "java:global/classes/RentServiceImpl!br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.RentService";

    @Override
    public String getDataSetFile() {
        return "src/test/java/resources/rents.xml";
    }
    
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
    public void testNewRent() throws NamingException {
        
        RentService rentService = (RentService) context.lookup(RESOURCE);
        //
        Movie movie = new Movie("The Amazing Spider Man", Gender.ACTION, 120, false);
        movie.setId(2L);
        Rent rent = new Rent(movie, LocalDate.now(), LocalDate.now().plusDays(2), RentState.UP_TO_DATE);
        rent.setId(5L);
        //
        rentService.rent(rent);
        //
        Assert.assertEquals(5, rentService.listAll().size());
    }
    
    @Test
    public void testFinalizeRent() throws NamingException {
        
        RentService rentService = (RentService) context.lookup(RESOURCE);
        // Finalizing Rent #1
        Movie movie = new Movie("REC", Gender.HORROR, 120, true);
        movie.setId(1L);
        //dataInicio = 03/04/2017, dataFim = 05/04/2017
        Rent rent = new Rent(movie, LocalDate.now().minusDays(1), 
                LocalDate.now().plusDays(1), RentState.FINALIZED);
        rent.setId(1L);

        rentService.finalize(rent);
        
        assertTrue(rentService.listAll().contains(rent));
        
        // Finalizing Rent #2
        Movie movie2 = new Movie("Sing Street", Gender.DRAMA, 120, true);
        movie2.setId(4L);
        //dataInicio = 02/04/2017, dataFim = 04/04/2017
        Rent rent2 = new Rent(movie2, LocalDate.now().minusDays(2), 
                LocalDate.now(), RentState.FINALIZED);
        rent.setId(4L);
        
        rentService.finalize(rent2);
        
        assertTrue(rentService.listAll().contains(rent2));
    }
    
}
