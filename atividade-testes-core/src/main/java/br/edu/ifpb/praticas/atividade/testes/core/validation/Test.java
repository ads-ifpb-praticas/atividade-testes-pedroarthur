/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.validation;

import br.edu.ifpb.praticas.atividade.testes.core.exceptions.MovieException;

/**
 *
 * @author Pedro Arthur
 */
public class Test {
    
    public static void main(String[] args) {
        
        MovieValidation validator = new MovieValidation();
        try {
            validator.validateTitle("A volta dos que não!% #2?");
        } catch(MovieException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
