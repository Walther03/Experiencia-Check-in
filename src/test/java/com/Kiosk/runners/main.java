package com.Kiosk.runners;

import com.Kiosk.pageObjects.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class main {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();

        // Configurar opciones del navegador
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");

        // Inicializar el WebDriver con opciones
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Crear instancia de la página de inicio de sesión
        LoginPage loginPage = new LoginPage(driver);

        try {
            // Navegar a la página de inicio de sesión
            driver.get("https://timeline-web-qa.skyairline.com/login");

            // Realizar el proceso de inicio de sesión
            loginPage.login("tu_usuario", "tu_contraseña");

            // Puedes agregar verificaciones adicionales aquí

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el navegador
            driver.quit();
        }
    }
}