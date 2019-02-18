
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristian
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws CinemaPersistenceException{
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        ipct.saveCinema(c);
        
        assertNotNull("Loading a previously stored cinema returned null.",ipct.getCinema(c.getName()));
        assertEquals("Loading a previously stored cinema returned a different cinema.",ipct.getCinema(c.getName()), c);
    }


    @Test
    public void saveExistingCinemaTest() {
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        
        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }
        
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3","Action"),functionDate);
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3","Horror"),functionDate);
        functions.add(funct12);
        functions.add(funct22);
        Cinema c2=new Cinema("Movies Bogotá",functions2);
        try{
            ipct.saveCinema(c2);
            fail("An exception was expected after saving a second cinema with the same name");
        }
        catch (CinemaPersistenceException ex){
        }
  
    }
    
    @Test
    public void getFunctionsbyCinemaAndDateTest(){
    	InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
    	
    	String functionDate = "2019-02-18 16:45";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("Inception","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("Anabelle","Terror"),functionDate);
        
        String functionDate2 = "2019-02-17 14:00";
        CinemaFunction funct3 = new CinemaFunction(new Movie("JohnCena","Terror"),functionDate2);
        
        functions.add(funct1);
        functions.add(funct2);
        functions.add(funct3);
        Cinema c=new Cinema("CineColombia",functions);
        
        try{
            ipct.saveCinema(c);
        }
        catch (CinemaPersistenceException ex){
        	 fail("Cinema persistence failed inserting the first cinema.");
        }
        
        List<CinemaFunction> cinemaFunctions = ipct.getFunctionsbyCinemaAndDate("CineColombia", "2019-02-18 16:45");
        
        assertEquals(cinemaFunctions.size(),2);
        assertEquals("Inception", cinemaFunctions.get(0).getMovie().getName());
        assertEquals("Anabelle",cinemaFunctions.get(1).getMovie().getName());
        
        List<CinemaFunction> cinemaFunctions2 = ipct.getFunctionsbyCinemaAndDate("CineColombia", "2019-02-17 14:00");
        
        assertEquals(cinemaFunctions2.size(),1);
        assertEquals("JohnCena", cinemaFunctions2.get(0).getMovie().getName());
    }
    
    @Test
    public void buyTicketTest(){
    	InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        
        String functionDate = "2018-12-18 15:45";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("Inception","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("Anabelle","Terror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("CineColombia",functions);
        
        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }
    	try {
			ipct.buyTicket(0, 0, "CineColombia", "2018-12-18 15:45", "Inception");
		} catch (CinemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			ipct.buyTicket(0, 0, "CineColombia", "2018-12-18 15:45", "Inception");
			fail("An exception was expected after buying a seat");
		} catch (CinemaException e) {
		}
    	
    }
    
    @Test
    public void getCinemaByName(){
    	
    	InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        
        String functionDate = "2018-12-18 15:45";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("Inception","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("Anabelle","Terror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("CineColombia",functions);
        
        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }
        
        try {
			Cinema cinemaPrueba = ipct.getCinema("CineColombia");
		} catch (CinemaPersistenceException e) {
			fail("Cinema was not found by name");
		}
        
    }
    
}
