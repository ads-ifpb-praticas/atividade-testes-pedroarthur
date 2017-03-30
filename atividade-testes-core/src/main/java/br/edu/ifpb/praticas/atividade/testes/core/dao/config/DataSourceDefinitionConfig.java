/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.core.dao.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;

/**
 *
 * @author Pedro Arthur
 */

@DataSourceDefinition(
        name = "java:app/atividade-teste-datasource",
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:mem:test",
        user = "sa",
        password = ""
)
@Stateless
public class DataSourceDefinitionConfig {
    
}
