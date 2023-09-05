package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetallePage extends BasePage {

    private By usuarioId = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@name='password']");
    private By botonLogin = By.xpath("//input[@value='Log In']");
    private By accountsOverview = By.linkText("Accounts Overview");
    private By mensajeBalance = By.xpath("//TD[@colspan='3'][text()='*Balance includes deposits that may be subject to holds\n" +
            "        ']");
    //*Balance includes deposits that may be subject to holds
    private By accountNumber = By.linkText("15564");
    private By tituloDetalleCuenta = By.xpath("//H1[@class='title'][text()='Account Details']");
    private By actividadPeriodoId = By.id("month");
    private By tipoId = By.id("transactionType");
    private By botonSubmit = By.xpath("//INPUT[@type='submit']");


    public DetallePage(WebDriver driver, WebDriverWait wait) {
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

    public void clickAccountNumber() throws InterruptedException {
        this.clickear(accountNumber);
    }

    public void seleccionarPeriodoActividad() throws InterruptedException {
        Select select = new Select(driver.findElement(actividadPeriodoId));
        select.selectByVisibleText("All");
    }
    public void seleccionarTipoActividad() throws InterruptedException {
        Select select = new Select(driver.findElement(tipoId));
        select.selectByVisibleText("All");
    }

    public void clickSubmit() throws InterruptedException {
        this.clickear(botonSubmit);
    }


    // Mensajes

    public String obtenerMensajeBalance() {
        System.out.println("MENSAJE: " + this.getText(mensajeBalance));
        return this.getText(mensajeBalance);
    }

    public String obtenerTituloDetalle() {
        return this.getText(tituloDetalleCuenta);
    }
}
