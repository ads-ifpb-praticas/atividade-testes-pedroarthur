/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;
import br.edu.ifpb.praticas.atividade.testes.core.dao.impl.RentDAOJpaImpl;
import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.RentDAO;
import br.edu.ifpb.praticas.atividade.testes.core.exceptions.RentException;
import br.edu.ifpb.praticas.atividade.testes.core.services.impl.RentServiceImpl;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Rent;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.RentState;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


/**
 *
 * @author Pedro Arthur
 */
public class RentTest {
    
    private RentDAO rentDAO;
    private RentServiceImpl rentService;
    
    @Before
    public void setUp() {
        //init mocks
        MockitoAnnotations.initMocks(this);
        rentDAO = Mockito.mock(RentDAO.class);
        
        this.rentService = new RentServiceImpl();
        rentService.setRentDAO(rentDAO);
        
        doNothing().when(rentDAO).save(any(Rent.class));
        doNothing().when(rentDAO).update(any(Rent.class));
        doReturn(mockedListAll()).when(rentDAO).listAll();
    }
    
    private List<Rent> mockedListAll() {
        
        List<Rent> rents = new ArrayList<>();
        
        Movie movie1 = new Movie("A volta dos que não foram #3", Gender.ACTION, 120, false);    
        Rent rent1 = new Rent(movie1, LocalDate.now(), LocalDate.now().plusDays(2), RentState.UP_TO_DATE);   
        Rent rent2 = new Rent(movie1, LocalDate.now().minusDays(10), LocalDate.now().minusDays(8), RentState.FINALIZED);     
        Rent rent3 = new Rent(movie1, LocalDate.now().minusDays(8), LocalDate.now().minusDays(6), RentState.FINALIZED);
        
        Movie movie2 = new Movie("A volta dos que não foram #3", Gender.DRAMA, 80, false);
        Rent rent4 = new Rent(movie2, LocalDate.now(), LocalDate.now().plusDays(2), RentState.UP_TO_DATE);
        
        rents.add(rent1);
        rents.add(rent2);
        rents.add(rent3);
        rents.add(rent4);
        
        return rents;
    }
    
    @Test(expected = RentException.class)
    public void testFailingNewRent() {
        
        Movie movie = new Movie("Titulo", Gender.ACTION, 120, true);
        Rent rent = new Rent(movie, LocalDate.now().minusDays(1), LocalDate.now(), RentState.UP_TO_DATE);
        
        rentService.rent(rent);
    }
    
    @Test(expected = RentException.class)
    public void testFailingUpdateRent() {
        Movie movie = new Movie("Titulo", Gender.ACTION, 120, true);
        Rent rent = new Rent(movie, LocalDate.now().plusDays(1), LocalDate.now(), RentState.UP_TO_DATE);
        
        rentService.finalize(rent);
    }
    
    @Test
    public void testSuccessfulNewRent() {
        Movie movie = new Movie("Titulo", Gender.ACTION, 120, true);
        Rent rent = new Rent(movie, LocalDate.now(), LocalDate.now().plusDays(2), RentState.UP_TO_DATE);
        
        rentService.rent(rent);
    }
    
    @Test
    public void testListAll() {

        List<Rent> listAll = rentService.listAll();
        Rent[] listAllArray = new Rent[listAll.size()];
        listAllArray = listAll.toArray(listAllArray);
        
        List<Rent> mockedListAll = mockedListAll();
        Rent[] mockedListAllArray = new Rent[listAll.size()];
        mockedListAllArray = listAll.toArray(mockedListAllArray);
        
        assertArrayEquals(mockedListAllArray, listAllArray);   
    }
    
    

}
