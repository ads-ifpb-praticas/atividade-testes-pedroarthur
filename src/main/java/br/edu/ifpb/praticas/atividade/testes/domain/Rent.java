/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.domain;

import java.time.LocalDate;

/**
 *
 * @author Pedro Arthur
 */
public class Rent {
    
    private Long id;
    private Movie movie;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    
    public Rent(Movie movie, LocalDate dataInicio, LocalDate dataFim) {
        this.movie = movie;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    
    public Rent() {
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Rent{" + "id=" + id + ", movie=" + movie + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + '}';
    }
}
