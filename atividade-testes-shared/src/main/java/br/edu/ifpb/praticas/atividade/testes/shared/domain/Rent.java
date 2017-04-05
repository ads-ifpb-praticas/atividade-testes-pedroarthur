/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.shared.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Pedro Arthur
 */

@Entity
@SequenceGenerator(initialValue = 1, allocationSize = 1,
        name = "rent_sequence_generator",
        sequenceName = "rent_id_sequence")
public class Rent implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_sequence_generator")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @Enumerated(EnumType.STRING)
    private RentState state;
    
    public Rent(Movie movie, LocalDate dataInicio, LocalDate dataFim, RentState state) {
        this.movie = movie;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.state = state;
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

    public RentState getState() {
        return state;
    }

    public void setState(RentState state) {
        this.state = state;
    }
    
    public boolean isFinalized() {
        return this.state.equals(RentState.FINALIZED);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.movie);
        hash = 53 * hash + Objects.hashCode(this.dataInicio);
        hash = 53 * hash + Objects.hashCode(this.dataFim);
        hash = 53 * hash + Objects.hashCode(this.state);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rent other = (Rent) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.movie, other.movie)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataFim, other.dataFim)) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rent{" + "id=" + id + ", movie=" + movie + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + '}';
    }
}
