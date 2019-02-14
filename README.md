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
