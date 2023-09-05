package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    protected By botonRegister = By.linkText("Register");
    private By firstNameId = By.id("customer.firstName");
    private By lastNameId = By.id("customer.lastName");
    private By addressId = By.id("customer.address.street");
    private By cityId = By.id("customer.address.city");
    private By stateId = By.id("customer.address.state");
    private By zipCodeId = By.id("customer.address.zipCode");
    private By telephoneId = By.id("customer.phoneNumber");
    private By ssnId = By.id("customer.ssn");
    private By usernameId = By.id("customer.username");
    private By passwordId = By.id("customer.password");
    private By passwordConfirmId = By.id("repeatedPassword");
    private By botonSubmit = By.xpath("(//INPUT[@type='submit'])[2]");
    private By registrado = By.xpath("//P[text()='Your account was created successfully. You are now logged in.']");
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickRegister() throws InterruptedException {
        this.clickear(botonRegister);
    }

    public void escribirFirstName(String name) throws InterruptedException {
        sendText(name, firstNameId);
    }

    public void escribirLastName(String lastName) throws InterruptedException {
        sendText(lastName, lastNameId);
    }

    public void escribirAddress(String address) throws InterruptedException {
        sendText(address, addressId);
    }

    public void escribirCity(String city) throws InterruptedException {
        sendText(city, cityId);
    }

    public void escribirState(String state) throws InterruptedException {
        sendText(state, stateId);
    }

    public void escribirZipCode(String zipCode) throws InterruptedException {
        sendText(zipCode, zipCodeId);
    }

    public void escribirTelephone(String telephone) throws InterruptedException {
        sendText(telephone, telephoneId);
    }

    public void escribirSsn(String ssn) throws InterruptedException {
        sendText(ssn, ssnId);
    }

    public void escribirUsername(String username) throws InterruptedException {
        sendText(username, usernameId);
    }

    public void escribirPassword(String password) throws InterruptedException {
        sendText(password, passwordId);
    }

    public void escribirConfirmacionPassword(String password) throws InterruptedException {
        sendText(password, passwordConfirmId);
    }

    public void clickSubmit() throws InterruptedException {
        this.clickear(botonSubmit);
    }

    public String obtenerMensajeRegistrado() {
        System.out.println("MENSAJE: " + this.getText(registrado));
        return this.getText(registrado);
    }
}