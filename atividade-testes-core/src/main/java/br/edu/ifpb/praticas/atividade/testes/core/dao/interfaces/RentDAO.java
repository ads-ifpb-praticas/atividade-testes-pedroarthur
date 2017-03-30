/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.dao.interfaces;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Rent;
import java.util.List;

/**
 *
 * @author Pedro Arthur
 */
public interface RentDAO {
    void save(Rent rent);
    List<Rent> listAll();
}
