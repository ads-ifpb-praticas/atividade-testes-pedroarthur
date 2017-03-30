/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.services.impl;

import br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces.RentDAO;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Rent;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.RentService;
import java.util.List;
import javax.ejb.EJB;
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

    @Override
    public void rent(Rent rent) {
        rentDAO.save(rent);
    }

    @Override
    public List<Rent> listAll() {
        return rentDAO.listAll();
    }
    
}
