/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import br.edu.ifpb.praticas.atividade.testes.dao.MovieDAO;
import br.edu.ifpb.praticas.atividade.testes.dao.MovieDAOH2Impl;
import br.edu.ifpb.praticas.atividade.testes.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.domain.Movie;
import java.sql.SQLException;
import static org.h2.engine.Constants.UTF8;
import org.h2.tools.RunScript;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author Pedro Arthur
 */
public class MovieTest extends GenericDatabaseTestCase {
    
    private MovieDAO movieDAO;
    
    private static final String schema = "./src/main/resources/create_schema.sql";

    @Override
    public String getDataSetFile() {
        return "src/test/java/resources/movies.xml";
    }
    
    @BeforeClass
    public static void createSchema() throws SQLException {
        System.out.println("Creating schema...");
        RunScript.execute("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1","sa","",schema,UTF8,false);
    }
    
    @Before
    public void setUp() {
        this.movieDAO = new MovieDAOH2Impl();
    }
    
    @Test
    public void persistTest() {
        
        Movie movie = new Movie("A", Gender.ACTION, 100, false);
        movieDAO.add(movie);
        
        Movie got = movieDAO.get(5L);
        
        //assertNotNull(got);
    }
}
