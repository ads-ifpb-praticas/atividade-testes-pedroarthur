/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.web.beans;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Rent;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.RentState;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.RentService;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro Arthur
 */

@Named
@RequestScoped
public class RentBean {
    
    @Inject
    private RentService rentService;
    
    @PostConstruct
    private void postConstruct() {
        System.out.println("[RentBean.postContructed] constructed bean.");
    }

    public String saveRent(Movie movie) {
        System.out.println("[RentBean.saveRent] OOOOIIII!");
        Rent rent = new Rent();
        
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(2);
        
        rent.setDataInicio(startDate);
        rent.setDataFim(endDate);
        rent.setState(RentState.UP_TO_DATE);
        rent.setMovie(movie);
        
        System.out.println("[RentBean.saveRent] movie: "+movie);
        rentService.rent(rent);
        
        addMessage("new-rent-message", successRentMessage(rent));
        return null;
    }
    
    public void finalizeRent(Rent rent) {
        rentService.finalize(rent);
        
        addMessage("finalize-rent-message", successFinalizeMessage(rent));
    }
    
    public boolean isRentFinalized(Rent rent) {
        return rent.getState().equals(RentState.FINALIZED);
    }
    
    public List<Rent> getRents() {
        return this.rentService.listAll();
    }
    
    private FacesMessage successRentMessage(Rent rent) {
        
        FacesMessage message = new FacesMessage("A locação do filme "+rent.getMovie().getTitle()+" foi efetuada com sucesso!");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        
        return message;
    }
    
    private FacesMessage successFinalizeMessage(Rent rent) {
        
        FacesMessage message = new FacesMessage("A locação do filme "+rent.getMovie().getTitle()+" foi finalizada com sucesso!");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        
        return message;
    }
    
    private void addMessage(String clientId, FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }
    
}
