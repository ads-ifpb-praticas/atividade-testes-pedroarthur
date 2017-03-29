/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.domain;

/**
 *
 * @author Pedro Arthur
 */
public class Movie {
    
    private Long id;
    private String title;
    private Gender gender;
    private Integer duration;
    private boolean rented;

    public Movie(String title, Gender gender, Integer duration, boolean rented) {
        this.title = title;
        this.gender = gender;
        this.duration = duration;
        this.rented = rented;
    }
    
    public Movie() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", gender=" + gender + ", duration=" + duration + ", rented=" + rented + '}';
    }
}
