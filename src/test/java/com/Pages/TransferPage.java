package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferPage extends BasePage {

    private By usuarioId = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@name='password']");
    private By botonLogin = By.xpath("//input[@value='Log In']");
    private By transferFunds = By.linkText("Transfer Funds");
    private By amountId = By.id("amount");
    private By destinationId = By.id("toAccountId");
    private By botonTransfer = By.xpath("//INPUT[@type='submit']");
    private By messageExitoso = By.xpath("//h1[normalize-space()='Transfer Complete!']");

    public TransferPage(WebDriver driver, WebDriverWait wait) {
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

    public void clickTransferFunds() throws InterruptedException {
        this.clickear(transferFunds);
    }

    public void escribirCantidad(String amount) throws InterruptedException {
        sendText(amount, amountId);
    }

    public void seleccionarDestinatario() throws InterruptedException {
        Select select = new Select(driver.findElement(destinationId));
        select.selectByVisibleText("30327");
    }

    public void clickSubmit() throws InterruptedException {
        this.clickear(botonTransfer);
    }



    // Mensajes

    public String obtenerMensajeExitoso() {
        System.out.println("MENSAJE: " + this.getText(messageExitoso));
        return this.getText(messageExitoso);
    }
}
