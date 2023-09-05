package com.Tests;

import com.Pages.NewAccountPage;
import com.Pages.TransferPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentReports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferTest {

    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES-TRANSFER.html");
    static ExtentReports extent;

    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
    }

    @Test
    void TransferenciaExitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de transferencia Exitosa");
        test.log(Status.INFO, "Comienza el Test");

        TransferPage transferPage = new TransferPage(driver, wait);
        transferPage.setup();
        transferPage.open("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.PASS, "Se ha ingresado exitosamente a la pagina.");

        transferPage.escribirUsuario("user17");
        transferPage.escribirPassword("password");
        transferPage.clickBotonLogin();

        transferPage.clickTransferFunds();
        transferPage.escribirCantidad("1000");
        transferPage.seleccionarDestinatario();

        transferPage.clickSubmit();


        assertTrue(transferPage.obtenerMensajeExitoso().contains( "Transfer Complete!"));
        test.log(Status.PASS, "Se realizó la transferencia exitosamente.");

    }


    @AfterEach
    public void cerrar() {
        NewAccountPage searchPage = new NewAccountPage(driver, wait);
        searchPage.close();
    }

    @AfterAll
    public static void reporte() {
        extent.flush();
    }

}
