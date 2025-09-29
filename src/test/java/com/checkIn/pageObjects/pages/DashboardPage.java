package com.checkIn.pageObjects.pages;

import com.checkIn.pageObjects.elements.DashboardPageElements;
import com.checkIn.pageObjects.elements.HomePageElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // Método auxiliar para obtener un elemento con espera
    private WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public String getTitulo() {
        WebElement titleElement = getElement(DashboardPageElements.dasboardTitle);
        TestUtils.takeScreenshot(driver, "resultado_busqueda.png");
        return titleElement.getText();
    }


}
