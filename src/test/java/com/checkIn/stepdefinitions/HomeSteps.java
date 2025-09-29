package com.checkIn.stepdefinitions;

import com.checkIn.pageObjects.pages.DashboardPage;
import com.checkIn.runners.RunPostmanCollection;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.checkIn.pageObjects.pages.HomePage;
import org.testng.Assert;

public class HomeSteps {
    private WebDriver driver;
    private HomePage homePage;
    private DashboardPage dashboardPage;
    private RunPostmanCollection ejecutarColeccion;
    private String reloc;
    private String lastName;
    private int result;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // para evitar errores en Chrome 111+
        options.addArguments("--headless=new");          // Headless moderno (Chrome >= 109)
        options.addArguments("--no-sandbox");            // Necesario en CI (evita problemas de permisos)
        options.addArguments("--disable-dev-shm-usage"); // Evita problemas de memoria compartida en Docker
        options.addArguments("--window-size=1920,1080"); // Define tamaño de ventana virtual



        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    
    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Paso: " + scenario.getName());
            } catch (org.openqa.selenium.NoSuchSessionException e) {
                System.out.println("INFO: El driver ya fue cerrado, no se puede capturar screenshot en este paso.");
            }
        }
    }


    // Cierra navegador y guarda evidencia si falla
    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Error en: " + scenario.getName());
            }
            driver.quit();
        }
    }
    @And("ejecuto la colección de Postman y obtengo la reserva")
    public void ejecutoPostmanYCapturoVariables() throws Exception {

        String collectionPath = "src/test/resources/Postman/Reservas-Dom.json";
        String environmentPath = "src/test/resources/Postman/QA_environment.json";

        JSONObject capturedVariables = RunPostmanCollection.runCollectionAndCaptureVariables(collectionPath, environmentPath);
        System.out.println("Variables capturadas: " + capturedVariables.toString(4));

        if (result == 0) {
            reloc = capturedVariables.getString("reloc");
            lastName = capturedVariables.getString("lastName");

            System.out.println("Reloc: " + reloc);
            System.out.println("LastName: " + lastName);
        } else {
            System.out.println("Fallo la creación de reserva: " + result);
            throw new Exception();
        }
        // Guardarlas en variables de clase para usarlas en otros steps

    }


    @Given("Ingreso a la pagina de Check-in")
    public void Ingreso_a_la_pagina_de_Check_in() {
        driver.get("https://check-in-qa.skyairline.com/es/chile/");
        homePage = new HomePage(driver); // inicializas tu PageObject
        System.out.println("INFO: Entrando a la home page");
    }



    @And("busco la reserva por su codigo  y apellido creado")
    public void busco_la_reserva_por_su_codigo_y_apellido() {
        System.out.println("INFO: Buscando reserva con PNR: " + reloc + " y Apellido: " + lastName);
        homePage.busquedaReserva(reloc, lastName);
    }

    @Then("debo ver el titulo del dashboard")
    public void debo_ver_el_titulo_del_dashboard() {
        dashboardPage = new DashboardPage(driver);
        String actualTitle = dashboardPage.getTitulo();
        System.out.println("INFO: El título del dashboard es: " + actualTitle);
        Assert.assertEquals("Check-in", actualTitle);  // ajusta el texto esperado
    }


    @And("se cierra el navegador")
    public void i_close_the_browser() {
        driver.quit();

    }






}
