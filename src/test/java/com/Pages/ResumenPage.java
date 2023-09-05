package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResumenPage extends BasePage {

    private By usuarioId = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@name='password']");
    private By botonLogin = By.xpath("//input[@value='Log In']");
    private By accountsOverview = By.linkText("Accounts Overview");
    private By mensajeBalance = By.xpath("//TD[@colspan='3'][text()='*Balance includes deposits that may be subject to holds\n" +
            "        ']");
    //*Balance includes deposits that may be subject to holds


    public ResumenPage(WebDriver driver, WebDriverWait wait) {
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

    public void clickAccountsOverview() throws InterruptedException {
        this.clickear(accountsOverview);
    }

    // Mensajes

    public String obtenerMensajeBalance() {
        System.out.println("MENSAJE: " + this.getText(mensajeBalance));
        return this.getText(mensajeBalance);
    }
}
