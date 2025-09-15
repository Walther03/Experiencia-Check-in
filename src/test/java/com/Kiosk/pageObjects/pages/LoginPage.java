package com.Kiosk.pageObjects.pages;

import com.Kiosk.pageObjects.elements.LoginPageElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor que recibe el WebDriver y configura la espera
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Método para obtener un elemento con espera
    private WebElement getElement(WebElement locator) {
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }

    // Método para ingresar el nombre de usuario
    public void enterUsername(String username) {
        WebElement usernameField = getElement(driver.findElement(LoginPageElements.usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    // Método para ingresar la contraseña
    public void enterPassword(String password) {
        WebElement passwordField = getElement(driver.findElement(LoginPageElements.passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Método para hacer clic en el botón de inicio de sesión
    public void clickLoginButton() {
        WebElement loginButton = getElement(driver.findElement(LoginPageElements.loginButton));
        loginButton.click();
    }

    // Método para realizar el proceso completo de inicio de sesión
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
