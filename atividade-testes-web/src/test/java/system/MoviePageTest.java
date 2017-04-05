package system;


import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro Arthur
 */

@Ignore
public class MoviePageTest {
    
    private static WebDriver driver;
    
    @BeforeClass
    public static void setUpTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }
    
    @Before
    public void setUp() {
        driver.get("http://localhost:8080/atividade-testes-web/faces/index.xhtml");
    }
    
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
    
    @Test
    public void testaTituloDaPagina() {
        String title = "Filmes";
        assertEquals(title, driver.getTitle());
    }
    
    @Test
    public void testaMovieRegister() {
        
        WebElement movieName = driver.findElement(By.id("movie-form:name"));
        WebElement movieGender = driver.findElement(By.id("movie-form:gender"));
        WebElement duration = driver.findElement(By.id("movie-form:duration"));
        
        movieName.sendKeys("Homem Aranha");
        movieGender.sendKeys("ACTION");
        duration.sendKeys("120");
        
        WebElement element = driver.findElement(By.id("movie-form:register"));
        element.click(); 
        
        WebElement alertInfo = driver.findElement(By.className("alert-info"));
        String successMsg = alertInfo.getText();
        
        assertEquals("O filme Homem Aranha foi cadastrado com sucesso!", successMsg);
        
        driver.manage().deleteAllCookies();
    }
    
    
}
