/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.dao;

import br.edu.ifpb.praticas.atividade.testes.domain.Movie;
import br.edu.ifpb.praticas.atividade.testes.domain.Rent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.h2.engine.Constants.UTF8;
import org.h2.tools.RunScript;

/**
 *
 * @author Pedro Arthur
 */
public class RentDAOH2Impl implements RentDAO {
    
    private Connection connection;
    private final String schema = "./src/main/resources/create_schema.sql";
    
    private void connect() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:;", "sa", "");  
        if(!isTableExists())
            RunScript.execute("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1","sa","",schema,UTF8,false);
    }
    
    private boolean isTableExists() throws SQLException {
        ResultSet rset = connection.getMetaData().getTables(null, null, "WORD_TYPES", null);
        return rset.next();
    }
    
    private void disconnect() throws SQLException {
        this.connection.close();
    }

    @Override
    public void add(Rent rent) {
        
        String sql = "INSERT INTO rent(rent_date, delivery_date, movie_id) VALUES(?,?,?)";
        
        try {
            //connects
            connect();
            //
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            int i = 1;
            pstm.setDate(i++, java.sql.Date.valueOf(rent.getDataInicio()));
            pstm.setDate(i++, java.sql.Date.valueOf(rent.getDataFim()));
            pstm.setLong(i++, rent.getMovie().getId());
            pstm.executeUpdate();
            //
            //disconnects
            disconnect();                        
        } catch (SQLException ex) {
            Logger.getLogger(RentDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Rent get(Long id) {
        String sql = "SELECT * FROM rent WHERE id = ?";
        
        try {
            //
            connect();
            //
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            int i = 1;
            pstm.setLong(i++, id);
            
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                return createRent(rs);
            }
            //
            disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(RentDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private Rent createRent(ResultSet rs) throws SQLException {
        
        Rent rent = new Rent();
        rent.setId(rs.getLong("id"));
        rent.setDataInicio(rs.getDate("rent_date").toLocalDate());
        rent.setDataFim(rs.getDate("delivery_date").toLocalDate());
        rent.setMovie(getMovieById(rs.getLong("movie_id")));
        
        return rent;
    }
    
    private Movie getMovieById(Long movie_id) {
        
        MovieDAO movieDAO = new MovieDAOH2Impl();
        
        return movieDAO.get(movie_id);
    }
    
}
