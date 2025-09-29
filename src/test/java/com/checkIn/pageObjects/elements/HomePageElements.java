package com.checkIn.pageObjects.elements;

import org.openqa.selenium.By;

public class HomePageElements {
    public static By pnrField = By.xpath("(//input[@type='text'])[1]");
    public static By lastNameField = By.xpath("(//input[@type='text'])[2]");
    public static By searchButton = By.xpath("(//button[normalize-space()='Buscar vuelo'])[1]");
}
