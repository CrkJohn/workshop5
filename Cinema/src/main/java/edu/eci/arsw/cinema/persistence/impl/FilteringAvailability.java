package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.persistence.TypeFiltro;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author 2129082
 */

@Component("filteringAvailability")
public class FilteringAvailability implements TypeFiltro{

    private  List<Movie> movies;

    @Override
    public List<Movie> filteredByGender(CinemaPersitence cinema, String cinemasName, String Date, String gender) {
         movies = new ArrayList<Movie> ();
                
         return movies;
    }

    @Override
    public List<Movie> filteringByAvailability(CinemaPersitence cinema, String cinemasName, int emptySeats) {
        movies = new ArrayList<Movie>();        
        Set<Cinema> cinemas =  cinema.getAllCinemas();
        try{
            for(CinemaFunction cinemaFunctions : cinema.getCinema(cinemasName).getFunctions()){
                if(cinemaFunctions.getEmptySeat() == emptySeats){
                       movies.add(cinemaFunctions.getMovie());
                }
            } 
        }catch(Exception ex){  
            
        }
        return movies;
    }

 
    
}
