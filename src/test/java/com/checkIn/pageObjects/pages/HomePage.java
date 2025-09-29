package com.Kiosk.pageObjects.pages;

import com.Kiosk.pageObjects.elements.HomePageElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Método auxiliar para obtener un elemento con espera
    private WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Acciones específicas de la página
    public void enterPnr(String pnr) {
        getElement(HomePageElements.pnrField).sendKeys(pnr);
    }

    public void enterLastName(String lastName) {
        getElement(HomePageElements.lastNameField).sendKeys(lastName);
    }

    public void clickSearch() {
        getElement(HomePageElements.searchButton).click();
    }

    // Método para flujo completo
    public void busquedaReserva(String pnr, String apellido) {
        enterPnr(pnr);
        enterLastName(apellido);
        TestUtils.takeScreenshot(driver, "antes_de_buscar.png");

        clickSearch();
    }
}
