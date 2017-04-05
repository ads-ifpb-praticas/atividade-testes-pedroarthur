/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.services.impl;

import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.MovieDAO;
import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.RentDAO;
import br.edu.ifpb.praticas.atividade.testes.shared.exceptions.RentException;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Rent;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.RentState;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.RentService;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Pedro Arthur
 */

@Stateless
@Remote(RentService.class)
public class RentServiceImpl implements RentService {
    
    @EJB
    private RentDAO rentDAO;
    @EJB
    private MovieDAO movieDAO;

    public RentServiceImpl(RentDAO rentDAO, MovieDAO movieDAO) {
        this.rentDAO = rentDAO;
        this.movieDAO = movieDAO;
    }
    
    public RentServiceImpl() {
        
    }

    private void validateRent(Rent rent) {
        System.out.println("[RentServiceImpl.validateRent] Validating rent... ");
        if(rent == null) throw new RentException("You're passing a empty rent.");
        System.out.println("[RentServiceImpl.validateRent] Is not null.");
        if(rent.getDataInicio() == null) throw new RentException("DataInicio is empty.");
        System.out.println("[RentServiceImpl.validateRent] DataInicio is not null.");
        if(rent.getDataFim() == null) throw new RentException("DataFim is empty.");
        System.out.println("[RentServiceImpl.validateRent] DataFim is not null.");
        if(rent.getMovie() == null) throw new RentException("Movie is empty.");
        System.out.println("[RentServiceImpl.validateRent] Movie is not null.");
        if(rent.getState() == null) throw new RentException("State is empty.");
        System.out.println("[RentServiceImpl.validateRent] State is not null.");
    }
    
    private void validateNewRent(Rent rent) {
        validateRent(rent);
        System.out.println("[RentServiceImpl.validateNewRent] Basic validation successfull!");
        LocalDate now = LocalDate.now(); 
        if(rent.getDataInicio().isBefore(now))
            throw new RentException("The rent has an old date. Please try to rent"
                    + " a movie with date equal or greater than "+now);
        System.out.println("[RentServiceImpl.validateNewRent] DataInicio is not before now");
    }
    
    private void validateFinalizingRent(Rent rent) {
        validateRent(rent);
        LocalDate now = LocalDate.now(); 
        if(now.isBefore(rent.getDataInicio())) 
            throw new RentException("You're trying to finalize the rent before the location Date. "
                    + "This is not possible."); 
    }
        
    @Override
    public void rent(Rent rent) {
        System.out.println("[RentServiceImpl.rent] renting: "+rent);
        validateNewRent(rent);
        System.out.println("[RentServiceImpl.rent] Setting movie state to true");
        rent.getMovie().setRented(true);
        System.out.println("[RentServiceImpl.rent] Saving rent!");
        rentDAO.save(rent);
        System.out.println("[RentServiceImpl.rent] Updating movie! movieDAO: "+movieDAO);
        movieDAO.update(rent.getMovie());
        
    }
    
    @Override
    public void finalize(Rent rent) {
        validateFinalizingRent(rent);
        rent.setState(RentState.FINALIZED);
        rent.getMovie().setRented(false);
        rentDAO.update(rent);
    }

    @Override
    public List<Rent> listAll() {
        return rentDAO.listAll();
    }
    
}
