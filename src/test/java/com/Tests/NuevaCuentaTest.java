package com.Tests;

import com.Pages.NewAccountPage;
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

public class NuevaCuentaTest {

    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES-NUEVACUENTA.html");
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
    void AperturadecuentaExitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de apertura de cuenta Exitosa");
        test.log(Status.INFO, "Comienza el Test");

        NewAccountPage accountPage = new NewAccountPage(driver, wait);
        accountPage.setup();
        accountPage.open("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.PASS, "Se ha ingresado exitosamente a la pagina.");

        accountPage.escribirUsuario("user17");
        accountPage.escribirPassword("password");
        accountPage.clickBotonLogin();

        accountPage.clickOpenNewAccount();
        accountPage.seleccionarTipoCuenta();

        accountPage.clickSubmit();

        assertTrue(accountPage.obtenerMensajeExitoso().contains( "Congratulations, your account is now open."));
        test.log(Status.PASS, "Se realizó la apertura de la cuenta exitosamente.");

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
