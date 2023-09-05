package com.Tests;

import com.Pages.DetallePage;
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

public class DetalleCuentaTest {

    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES-DETALLECUENTA.html");
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
    void DetalleDeCuentaExitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de detalle de cuenta Exitosa");
        test.log(Status.INFO, "Comienza el Test");

        DetallePage detallePage = new DetallePage(driver, wait);
        detallePage.setup();
        detallePage.open("https://parabank.parasoft.com/parabank/index.htm");
        test.log(Status.PASS, "Se ha ingresado exitosamente a la pagina.");

        detallePage.escribirUsuario("user17");
        detallePage.escribirPassword("password");
        detallePage.clickBotonLogin();

        detallePage.clickAccountsOverview();

        assertTrue(detallePage.obtenerMensajeBalance().contains( "*Balance includes deposits that may be subject to holds"));
        test.log(Status.PASS, "Se visualizó el detalle de balance de cuentas exitosamente.");

        detallePage.clickAccountNumber();

        assertTrue(detallePage.obtenerMensajeBalance().contains( "Account Details"));
        test.log(Status.PASS, "Se visualizó el titulo 'Account Details' exitosamente.");

        detallePage.seleccionarPeriodoActividad();
        detallePage.seleccionarTipoActividad();

        detallePage.clickSubmit();
        test.log(Status.PASS, "Finalizó el test de detalle de cuenta exitosamente.");

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
