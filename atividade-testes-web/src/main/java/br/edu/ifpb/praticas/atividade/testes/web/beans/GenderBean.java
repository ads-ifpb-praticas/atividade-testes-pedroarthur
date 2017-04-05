/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.web.beans;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Gender;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Pedro Arthur
 */

@Named
@ApplicationScoped
public class GenderBean {
    
    public Gender[] getGenders() {
        System.out.println("[GenderBean.getGenders] genders: "+Gender.values());
        return Gender.values();
    }
}
