package com.Kiosk.pageObjects.elements;

import org.openqa.selenium.By;

public class LoginPageElements {
    public static By usernameField = By.xpath("//*[@id='app']/div/div[1]/div/div[1]/div[2]/div[2]/div/form/div[1]/div/input");
    public static By passwordField = By.xpath("//input[@name='password']");
    public static By loginButton = By.id("login__button");
}
