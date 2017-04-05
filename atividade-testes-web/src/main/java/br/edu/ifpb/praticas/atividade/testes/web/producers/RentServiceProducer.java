/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.web.producers;

import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.RentService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Pedro Arthur
 */

@ApplicationScoped
public class RentServiceProducer {
    
    private final String resource = "java:global/atividade-testes-core/RentServiceImpl!br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.RentService";
    
    @Default
    @Dependent
    @Produces
    public RentService getRentService() {
        return new ServiceLocator().lookup(resource, RentService.class);
    }
}
