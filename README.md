# WorkShop5


![GitHub last commit](https://img.shields.io/github/last-commit/CrkJohn/workshop5.svg?style=for-the-badge)

**Lenguaje manejado.**     ![90+%]( https://img.shields.io/github/languages/top/crkJohn/workshop5.svg?style=for-the-badge&colorB=red)



###### Integrantes
- [John David Ibañez](https://github.com/CrkJohn)
- [Juan Camilo Velandia](https://github.com/jcamilovelandiab)
------------


## Introduction to Spring and Configuration using annotations [statement](https://eci.gitbook.io/workshops/introduction-to-spring-and-configuration-using-annotations)

### Part I
To illustrate the use of the Spring framework, and the development environment for its use through Maven (and NetBeans), the configuration of a text analysis application will be made, which makes use of a grammar verifier that requires a spelling checker. The grammar checker will be injected, at the time of execution, with the spelling checker required (for now, there are two available: English and Spanish).

### 2
Review the Spring configuration file already included in the project (src / main / resources). It indicates that Spring will automatically search for the 'Beans' available in the indicated package.    
```xml  
<bean id="spellChecker" class = "edu.eci.arsw.springdemo.SpanishSpellChecker"></bean>   
<bean id="spl" class="edu.eci.arsw.springdemo.GrammarChecker">       
  <property name="sc" ref="spellChecker" />
  <property name="x" value="Spanish"/>
</bean>
```

```java
  public SpellChecker getSc() {
    return sc;
  }

  public void setSc(SpellChecker sc) {
    this.sc = sc;
  }

  public String getX() {
    return x;
  }

  public void setX(String x) {
    this.x = x;
  }
```

### 3
Making use of the Spring configuration based on annotations mark with the annotations ```@Autowired``` and ```@Service``` the dependencies that must be injected, and the 'beans' candidates to be injected -respectively-:
- ```GrammarChecker``` will be a bean, which depends on something like ```SpellChecker```.
- ```EnglishSpellChecker``` and ```SpanishSpellChecker``` are the two possible candidates to be injected. One must be selected, or another, but NOT both (there would be dependency resolution conflict). For now, have EnglishSpellChecker used. 

```java
@Service
public class GrammarChecker{    

  @Autowired
  @Qualifier("englishSpellChecker")
  SpellChecker sc; 
  String x;             
 
}
 ```
 
```java 
@Component("spanishSpellChecker")
public class SpanishSpellChecker implements SpellChecker {
}
```
```java 
@Component("englishSpellChecker")
public class EnglishSpellChecker implements SpellChecker { 
      
}
```


### Part II

Modify the configuration with annotations so that the Bean 'GrammarChecker' now makes use of the SpanishSpellChecker class (so that GrammarChecker is injected with EnglishSpellChecker instead of SpanishSpellChecker.) Verify the new result.

```java
@Service
public class GrammarChecker{    

  @Autowired
  @Qualifier("spanishSpellChecker")
  SpellChecker sc; 
  String x;             
 
}
 ```
## Cinema Book System

### Part I

1. Configure the application to work under a dependency injection scheme, as shown in the previous diagram. The above requires:
       1. Add the dependencies of Spring. 
       2. Add the Spring configuration.
       3. Configure the application -by means of annotations- so that the persistence scheme is injected at the moment of              creation of the 'CinemaServices' bean. 

2. Complete the getCinemaByName (), buyTicket (), and getFunctionsbyCinemaAndDate () operations. Implement everything required from the lower layers (for now, the available persistence scheme 'InMemoryCinemasPersistence') by adding the corresponding tests in 'InMemoryPersistenceTest'.

[Cinema services](https://github.com/CrkJohn/workshop5/blob/master/Cinema/src/main/java/edu/eci/arsw/cinema/services/CinemaServices.java)

[Implementation of the methods](https://github.com/CrkJohn/workshop5/blob/master/Cinema/src/main/java/edu/eci/arsw/cinema/persistence/impl/InMemoryCinemaPersistence.java)



3. For later queries, we want to implement two functionalities:
       1. A method 'getFunctionsbyCinemaAndDate' that allows to obtain all the functions of a certain cinema for a certain date. 
       2. Allow the purchase or reservation of tickets for a certain position of chairs in the room through the 'buyTicket' method. 

4. Make a program in which you create (through Spring) an instance of CinemaServices, and rectify the functionality of it: register cinemas, consult cinemas, obtain the functions of certain cinema, buy / book tickets, etc.

[Solucion](https://github.com/CrkJohn/workshop5/blob/master/Cinema/src/main/java/edu/eci/arsw/cinema/services/App.java)

5. It is wanted that the consultations realize a filtering process of the films to exhibit, said filters look for to give him the facility to the user to see the most suitable films according to his necessity. Adjust the application (adding the abstractions and implementations that you consider) so that the CinemaServices class is injected with one of two possible 'filters' (or possible future filters). The use of more than one at a time is not contemplated:
       1.(A) Filtered by gender: Allows you to obtain only the list of the films of a certain genre (of a certain cinema and a certain date) (The genre enters by parameter). 
       2. (B) Filtering by availability: Allows you to obtain only the list of films that have more than x empty seats (of a certain cinema and a certain date) (The number of seats is entered per parameter).
![](https://github.com/CrkJohn/workshop5/blob/master/Screenshots/model.png)


[Interface ](https://github.com/CrkJohn/workshop5/blob/master/Cinema/src/main/java/edu/eci/arsw/cinema/persistence/TypeFiltro.java)


[FilteredByGender](https://github.com/CrkJohn/workshop5/blob/master/Cinema/src/main/java/edu/eci/arsw/cinema/persistence/impl/FilteredByGender.java)

[FilteringByAvailability](https://github.com/CrkJohn/workshop5/blob/master/Cinema/src/main/java/edu/eci/arsw/cinema/persistence/impl/FilteringByAvailability.java)



3. Add the corresponding tests to each of these filters, and test their operation in the test program, verifying that only by changing the position of the annotations -without changing anything else-, the program returns the list of films filtered in the manner (A ) or in the way (B).
##### A.
![](https://github.com/CrkJohn/workshop5/blob/master/Screenshots/disponibilidad.png)
##### B.
![](https://github.com/CrkJohn/workshop5/blob/master/Screenshots/gender.png)

