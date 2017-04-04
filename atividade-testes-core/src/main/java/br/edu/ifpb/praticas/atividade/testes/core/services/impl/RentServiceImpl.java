/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.services.impl;

import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.RentDAO;
import br.edu.ifpb.praticas.atividade.testes.core.exceptions.RentException;
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

    private void validateRent(Rent rent) {
        if(rent == null) throw new RentException("You're passing a empty rent.");
        if(rent.getDataInicio() == null) throw new RentException("DataInicio is empty.");
        if(rent.getDataFim() == null) throw new RentException("DataFim is empty.");
        if(rent.getMovie() == null) throw new RentException("Movie is empty.");
        if(rent.getState() == null) throw new RentException("State is empty.");
    }
    
    private void validateNewRent(Rent rent) {
        validateRent(rent);
        LocalDate now = LocalDate.now(); 
        if(rent.getDataInicio().isBefore(now))
            throw new RentException("The rent has an old date. Please try to rent"
                    + " a movie with date equal or greater than "+now);
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
        validateNewRent(rent);
        rent.getMovie().setRented(true);
        rentDAO.save(rent);
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
    
    public void setRentDAO(RentDAO rentDAO) {
        this.rentDAO = rentDAO;
    } 
    
}
