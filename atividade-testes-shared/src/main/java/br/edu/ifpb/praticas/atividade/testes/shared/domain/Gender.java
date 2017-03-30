/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.shared.domain;

/**
 *
 * @author Pedro Arthur
 */
public enum Gender {
    
    ACTION("Ação"),
    ADVENTURE("Aventura"),
    ADULT("Adulto"),
    ANIMATION("Animação"),
    COMEDY("Comédia"),
    DRAMA("Drama"),
    THRILLER("Thriller"),
    HORROR("Terror");
    
    private final String description;
    
    Gender(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
}
