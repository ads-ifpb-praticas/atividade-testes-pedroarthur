/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.praticas.atividade.testes.dao;

import br.edu.ifpb.praticas.atividade.testes.domain.Gender;
import br.edu.ifpb.praticas.atividade.testes.domain.Movie;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.h2.engine.Constants.UTF8;
import org.h2.tools.RunScript;

/**
 *
 * @author Pedro Arthur
 */
public class MovieDAOH2Impl implements MovieDAO {
    
    private Connection connection;
    private final String schema = "./src/main/resources/create_schema.sql";
    
    private void connect() throws SQLException, FileNotFoundException {
        if(this.connection == null) {
            System.out.println("connected!");
            this.connection = DriverManager.getConnection("jdbc:h2:mem:;", "sa", "");
            RunScript.execute(connection, new FileReader(new File(schema)));
        }
    }

    @Override
    public void add(Movie movie) {
        
        String sql = "INSERT INTO movie(title,gender,duration,rented)"
                + " VALUES(?,?,?,?)";
        try {
            connect();
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, movie.getTitle());
            pstm.setInt(i++, movie.getGender().ordinal());
            pstm.setInt(i++, movie.getDuration());
            pstm.setBoolean(i++, movie.isRented());
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void update(Movie movie) {
        String sql = "UPDATE movie WHERE id = ? SET title = ?, gender = ?, duration = ?, rented = ?";
        try {
            connect();
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            int i = 1;
            pstm.setLong(i++, movie.getId());
            pstm.setString(i++, movie.getTitle());
            pstm.setInt(i++, movie.getGender().ordinal());
            pstm.setInt(i++, movie.getDuration());
            pstm.setBoolean(i++, movie.isRented());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Movie> listAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie";
        try {
            connect();
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while(result.next()) {
                movies.add(createMovie(result));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return movies;
    }
    
    private Movie createMovie(ResultSet rs) throws SQLException {
        //
        Movie movie = new Movie();
        movie.setId(rs.getLong("id"));
        movie.setTitle(rs.getString("title"));
        //gender
        int genderIndex = rs.getInt("gender");
        Gender gender = Gender.values()[genderIndex];
        movie.setGender(gender);
        //
        movie.setRented(rs.getBoolean("rented"));
        
        return movie;
    }

    @Override
    public Movie get(Long id) {
        
        String sql = "SELECT * FROM movie WHERE id = ?";
        
        try {
            connect();
            
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            int i = 1;
            pstm.setLong(i++, id);
            
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                return createMovie(rs);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieDAOH2Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
