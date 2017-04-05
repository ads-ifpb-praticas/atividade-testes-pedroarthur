/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.web.beans;

import br.edu.ifpb.praticas.atividade.testes.shared.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.shared.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.shared.services.interfaces.MovieService;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
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
@ConversationScoped
public class MovieBean implements Serializable {
    
    @Inject
    private MovieService movieService;
    private Movie movie = new Movie();
    private boolean isUpdating;
    
    @Inject
    private Conversation conversation;
    
    private void initConverstation() {
        if(!this.conversation.isTransient())
            this.conversation.end();
        this.conversation.begin();
    }
    
    private void endConversation() {
        if(this.conversation.isTransient())
            this.conversation.begin();
        this.conversation.end();
    }
    
    @PostConstruct()
    private void init() {
        initConverstation();
        this.isUpdating = false;
    }
    
    public String registerMovie() {
        movieService.save(movie);
        addMessage("new-movie-message", getSuccessRegisterMovieMessage());
        this.movie = new Movie();
        endConversation();
        return null;
    }
    
    public String cancelUpdate() {
        this.movie = new Movie();
        endConversation();
        return null;
    }
    
    public String updateMovie(Movie movie) {
        initConverstation();
        this.isUpdating = true;
        this.movie = movie;
        return null;
    }
    
    public String saveChanges() {
        this.movieService.update(movie);
        this.isUpdating = false;
        this.movie = new Movie();
        addMessage("update-movie-message", getSuccessUpdateMovieMessage());
        endConversation();
        return null;
    }
    
    private FacesMessage getSuccessRegisterMovieMessage() {
        FacesMessage message = new FacesMessage("O filme "+movie.getTitle()+" foi cadastrado com sucesso!");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        return message;
    }
    
    private FacesMessage getSuccessUpdateMovieMessage() {
        FacesMessage message = new FacesMessage("O filme "+movie.getTitle()+" foi atualizado com sucesso!");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        return message;
    }
    
    private void addMessage(String clientId, FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }
    
    public List<Movie> getMovies() {
        return movieService.listAll();
    }
    
    public Gender[] getGenders() {
        return Gender.values();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public boolean isIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(boolean isUpdating) {
        this.isUpdating = isUpdating;
    }
    
    
}
