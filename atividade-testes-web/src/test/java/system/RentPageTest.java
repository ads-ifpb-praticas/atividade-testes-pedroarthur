/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Pedro Arthur
 */

@Ignore
public class RentPageTest {
    
    private static WebDriver driver;
    
    @BeforeClass
    public static void setUpTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }
    
    @Before
    public void setUp() {
        driver.get("http://localhost:8080/atividade-testes-web/faces/rents.xhtml");
    }
    
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
    
    @Test
    public void testaTituloDaPagina() {
        String title = "Locações";
        assertEquals(title, driver.getTitle());
    }
}
