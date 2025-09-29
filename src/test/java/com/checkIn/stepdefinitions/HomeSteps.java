package com.Kiosk.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.Kiosk.pageObjects.pages.HomePage;

public class HomeSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/walther/IdeaProjects/Operaciones-Kiosko/src/test/resources/driver/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // para evitar errores en Chrome 111+

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        driver.get("https://check-in-qa.skyairline.com/es/chile/");
        homePage = new HomePage(driver); // inicializas tu PageObject
        System.out.println("INFO: Entrando a la home page");
    }



    @Given("busco la reserva por su codigo {string} y apellido {string}")
    public void busco_la_reserva_por_su_codigo_y_apellido(String pnr, String apellido) {
        System.out.println("INFO: Buscando reserva con PNR: " + pnr + " y Apellido: " + apellido);
        homePage.busquedaReserva(pnr, apellido);
    }



    @Then("I close the browser")
    public void i_close_the_browser() {
       // driver.quit();
    }

/*
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


 */

}
