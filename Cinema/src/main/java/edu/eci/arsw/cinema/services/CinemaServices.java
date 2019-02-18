/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.cinema.persistence.TypeFiltro;

/**
 *
 * @author cristian
 */

@Service
public class CinemaServices {
    @Autowired
    @Qualifier("inMemoryCinemaPersistence")
    CinemaPersitence cps;
    
    @Autowired 
    @Qualifier("filteredGenered")
    TypeFiltro filtro;
    
    public static void main(String[] args){
        
        
    }
    
    public void addNewCinema(Cinema c){
        
    }
    
    public Set<Cinema> getAllCinemas(){
        return cps.getAllCinemas();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException{
        return cps.getCinema(name);
    }
    
    
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException{
        cps.buyTicket(row, col, cinema, date, movieName);
    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        return cps.getFunctionsbyCinemaAndDate(cinema, date);
    }
    
    
    public List<Movie> filteredByGender(CinemaPersitence cinema, String cinemasName , String Date ,String gender){
        return filtro.filteredByGender(cinema, cinemasName, Date, gender);
    }       
    
    
    public List<Movie> filteringByAvailability(CinemaPersitence cinema, String cinemasName, int emptySeats ){
        return filteringByAvailability(cinema, cinemasName, emptySeats);
    }
    
    
    
 


}
