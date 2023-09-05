package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage {


    private By usuarioId = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@name='password']");
    private By botonLogin = By.xpath("//input[@value='Log In']");
    private By openNewAccount = By.linkText("Open New Account");
    private By tipoCuentaId = By.id("type");
    private By botonSubmit = By.xpath("//INPUT[@type='submit']");


    private By cuentaExitosa = By.xpath("//P[text()='Congratulations, your account is now open.']");
    //Congratulations, your account is now open.


    public NewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void escribirUsuario(String usuario) throws InterruptedException {
        sendText(usuario, usuarioId);
    }

    public void escribirPassword(String pass) throws InterruptedException {
        sendText(pass, password);
    }

    public void clickBotonLogin() throws InterruptedException {
        this.clickear(botonLogin);
    }

    public void clickOpenNewAccount() throws InterruptedException {
        this.clickear(openNewAccount);
    }

    public void seleccionarTipoCuenta() throws InterruptedException {
        Select select = new Select(driver.findElement(tipoCuentaId));
        select.selectByVisibleText("SAVINGS");
    }

    public void clickSubmit() throws InterruptedException {
        this.clickear(botonSubmit);
    }

    // Mensajes

    public String obtenerMensajeExitoso() {
        System.out.println("MENSAJE: " + this.getText(cuentaExitosa));
        return this.getText(cuentaExitosa);
    }

}
