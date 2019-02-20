/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.stereotype.Component;


/**
 *
 * @author cristian
 */


@Component("inMemoryCinemaPersistence")
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        cinemas.put("cinemaX", c);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        if(cinemas.containsKey(cinema)){
            Cinema cine = cinemas.get(cinema);
            for(CinemaFunction cf: cine.getFunctions()){
                if(cf.getMovie().getName().equals(movieName) && cf.getDate().equals(date)){
                    cf.buyTicket(row,col);
                }
            }
        }
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> functions = new ArrayList<CinemaFunction>();
        if(cinemas.containsKey(cinema)){
            Cinema cine = cinemas.get(cinema);
            for(CinemaFunction cf: cine.getFunctions()){
                if(cf.getDate().equals(date)){
                    functions.add(cf);
                    //System.out.println(date);
                }
            }
        }
        return functions;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
    	if(!cinemas.containsKey(name)) {
    		throw new CinemaPersistenceException("The cinema doesn't exists :"+name);
    	}
        return cinemas.get(name);
    }

 
    @Override
    public Set<Cinema> getAllCinemas() {
        Set<Cinema> cinemasAll = new HashSet<Cinema>();
        for (Map.Entry<String, Cinema> e : cinemas.entrySet()) {
        	cinemasAll.add(e.getValue());
        }
        return cinemasAll;
    }
    
}
